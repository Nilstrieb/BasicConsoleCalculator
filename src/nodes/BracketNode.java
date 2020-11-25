package nodes;

import java.util.ArrayList;

public class BracketNode extends ValueNode {

    private ArrayList<OperatorNode> operators;

    public BracketNode(String line) {
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
                            addOperator(newOperator);
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
        addOperator(newOperator);
        leftNumber.addParent(newOperator);
        rightNumber.addParent(newOperator);
    }

    public void addOperator(OperatorNode operator) {
        operators.add(operator);
    }

    @Override
    public double getValue() {

        if (operators.size() > 0) {
            ArrayList<OperatorNode> operatorsCopy = new ArrayList<>(operators);

            boolean hasPriorityNodes = true;

            while (hasPriorityNodes && operatorsCopy.size() > 1) {
                hasPriorityNodes = false;
                for (int i = 0; i < operatorsCopy.size(); i++) {
                    OperatorNode op = operatorsCopy.get(i);
                    if (op.hasPriority()) {
                        op.calculate();
                        operatorsCopy.remove(op);
                        i--;
                        hasPriorityNodes = true;
                    }
                }

            }

            while (operatorsCopy.size() > 1) {
                for (int i = 0; i < operatorsCopy.size() - 1; i++) {
                    OperatorNode op = operatorsCopy.get(i);
                    op.calculate();
                    operatorsCopy.remove(op);
                    i--;
                }
            }

            return operatorsCopy.get(0).getValue();
        } else {
            return 0;
        }
    }
}
