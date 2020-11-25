package nodes;

/**
 * Stores a simple value, like 5
 */
public class ValueNode {

    /**
     * The value
     */
    private double value;

    /**
     * The value as a String because it's easier to append digits to it that way
     */
    private String valueString = "";

    private OperatorNode leftParent;
    private OperatorNode rightParent;

    public ValueNode(double value, OperatorNode leftParent, OperatorNode rightParent) {
        this.value = value;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
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
