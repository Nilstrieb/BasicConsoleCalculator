package nodes;

import java.util.ArrayList;

/**
 * A Node that stores and parses the contents of brackets, also works as a base node
 * It extends ValueNode because a bracket term is a value and is treated as such by operators
 */
public class BracketNode extends ValueNode {

    /**
     * All operators in the expression
     */
    private ArrayList<OperatorNode> operators;

    /**
     * Create a new BracketNode from the content of the brackets (without the brackets)
     * @param line The content
     * @throws NumberFormatException If there is something wrong
     */
    public BracketNode(String line) throws NumberFormatException {

        operators = new ArrayList<>();

        if (!line.matches("[\\d\\w]*")) {

            ValueNode leftNumber = new ValueNode();
            ValueNode rightNumber = new ValueNode();
            //the current operator, if it's set to 0, the left value is being parsed, if it has a value, the right value is being parsed
            char currentOperator = '0';
            int bracketLevel = 0;
            String bracketContent = "";

            for (int i = 0; i < line.length(); i++) {

                char c = line.charAt(i);

                if (bracketLevel == 0 || c == ')' || c == '(') {
                    switch (c) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                        case '^':
                            if (currentOperator != '0') {
                                OperatorNode newOperator = new OperatorNode(leftNumber, rightNumber, currentOperator);
                                operators.add(newOperator);
                                leftNumber.addParent(newOperator);
                                rightNumber.addParent(newOperator);
                                leftNumber = rightNumber;
                                rightNumber = new ValueNode();
                            }
                            currentOperator = c;
                            break;
                        case ' ':
                            break;
                        case '(':
                            bracketLevel++;
                            break;
                        case ')':
                            bracketLevel--;
                            if (bracketLevel == 0) {
                                if (currentOperator == '0') {
                                    leftNumber = new BracketNode(bracketContent);
                                } else {
                                    rightNumber = new BracketNode(bracketContent);
                                }
                                bracketContent = "";
                            }
                            break;

                        default:
                            if (currentOperator == '0') {
                                leftNumber.append(c);
                            } else {
                                rightNumber.append(c);
                            }
                    }
                } else {
                    bracketContent += c;
                }
            }

            OperatorNode newOperator = new OperatorNode(leftNumber, rightNumber, currentOperator);
            operators.add(newOperator);
            leftNumber.addParent(newOperator);
            rightNumber.addParent(newOperator);
        }
    }


    /**
     * Calculates and returns the value of the Node
     * @return The value
     */
    @Override
    public double getValue() {

        //First, only do the looping stuff if there are any operators
        if (operators.size() > 0) {
            //copy the ArrayList because you might want to do this again later and the calculation process is destructive
            ArrayList<OperatorNode> operatorsCopy = new ArrayList<>(operators);

            //go through each priority level individually
            for (int currentPriority = OperatorNode.MAX_PRIORITY; currentPriority >= 0; currentPriority--) {

                boolean hasPriorityNodes = true;
                //while there were any priority nodes left in the last iteration, continue trying to find new ones
                while (hasPriorityNodes && operatorsCopy.size() > 1) {
                    hasPriorityNodes = false;
                    //go through each operator
                    for (int i = 0; i < operatorsCopy.size() && operatorsCopy.size() > 1; i++) {
                        OperatorNode op = operatorsCopy.get(i);
                        //if the operator is on the current priority layer, calculate it and remove it
                        if (op.getPriority() == currentPriority) {
                            op.calculate();
                            operatorsCopy.remove(op);
                            i--;
                            hasPriorityNodes = true;
                        }
                    }

                }
            }

            //and now do the last calculation, and return it's result
            return operatorsCopy.get(0).getValue();
        } else {
            return 0;
        }
    }
}
