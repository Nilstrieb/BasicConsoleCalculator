package nodes;

public class ValueNode {

    private double value;
    private String valueString = "";
    private OperatorNode leftParent;
    private OperatorNode rightParent;

    public ValueNode(double value, OperatorNode leftParent, OperatorNode rightParent) {
        this.value = value;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
    }

    public ValueNode(double value) {
        this.value = value;
    }

    public ValueNode() {
    }

    public void addParent(OperatorNode parent){
        if (leftParent == null){
            leftParent = parent;
        } else {
            rightParent = parent;
        }
    }

    public double getValue() {
        return value;
    }

    public OperatorNode getLeftParent() {
        return leftParent;
    }

    public OperatorNode getRightParent() {
        return rightParent;
    }

    public void append(char c) throws NumberFormatException{
        valueString += c;
        value = Double.parseDouble(valueString);
    }
}
