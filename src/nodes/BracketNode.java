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
    private final ArrayList<OperatorNode> operators;

    /**
     * Create a new BracketNode from the content of the brackets (without the brackets)
     * @param line The content
     * @throws NumberFormatException If there is something wrong
     */
    public BracketNode(String line) throws NumberFormatException{
        operators = new ArrayList<>();

        ValueNode leftNumber = new ValueNode();
        ValueNode rightNumber = new ValueNode();
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

            //While there are any operators with priority (* and /), calculate them, from left to right
            boolean hasPriorityNodes = true;
            while (hasPriorityNodes && operatorsCopy.size() > 1) {
                hasPriorityNodes = false;
                for (int i = 0; i < operatorsCopy.size() - 1; i++) {
                    OperatorNode op = operatorsCopy.get(i);
                    if (op.hasPriority()) {
                        op.calculate();
                        operatorsCopy.remove(op);
                        i--;
                        hasPriorityNodes = true;
                    }
                }

            }

            //Calculate all the normal operators (+ and -) from left to right
            while (operatorsCopy.size() > 1) {
                for (int i = 0; i < operatorsCopy.size() - 1; i++) {
                    OperatorNode op = operatorsCopy.get(i);
                    op.calculate();
                    operatorsCopy.remove(op);
                    i--;
                }
            }

            //and now do the last calculation, and return it's result
            return operatorsCopy.get(0).getValue();
        } else {
            return 0;
        }
    }
}
