package nodes;

public class ValueNode {

    private double value;
    private OperatorNode leftParent;
    private OperatorNode rightParent;

    public ValueNode(double value, OperatorNode leftParent, OperatorNode rightParent) {
        this.value = value;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
    }

    public ValueNode() {
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
}
