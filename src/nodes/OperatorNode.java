package nodes;

/**
 * A Node that stores 2 values and calculates the result
 */
public class OperatorNode {

    private ValueNode leftChild;
    private ValueNode rightChild;

    private Operator type;

    public OperatorNode(ValueNode leftChild, ValueNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public OperatorNode(ValueNode leftChild, ValueNode rightChild, Operator type) {
        this(leftChild, rightChild);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.type = type;
    }

    public OperatorNode(ValueNode leftChild, ValueNode rightChild, char operator) {
        this(leftChild, rightChild);
        this.type = switch (operator){
            case '+' -> Operator.ADD;
            case '-' -> Operator.SUBTRACT;
            case '*' -> Operator.MULTIPLY;
            case '/' -> Operator.DIVIDE;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }

    /**
     * Calculate the result of the operation and store it in the expression
     */
    public void calculate(){

        double result = getValue();

        ValueNode resultNode = new ValueNode(result, getLeftNode(), getRightNode());

        if(getLeftNode() != null) {
            getLeftNode().setRightChild(resultNode);
        }
        if(getRightNode() != null) {
            getRightNode().setLeftChild(resultNode);
        }
    }

    /**
     * Calculate the result of the operation and return it
     * @return The result
     */
    public double getValue(){

        return switch (type){
            case ADD -> leftChild.getValue() + rightChild.getValue();
            case SUBTRACT -> leftChild.getValue() - rightChild.getValue();
            case MULTIPLY ->    leftChild.getValue() * rightChild.getValue();
            case DIVIDE -> leftChild.getValue() / rightChild.getValue();
        };
    }

    public OperatorNode getLeftNode(){
        return leftChild.getLeftParent();
    }

    public OperatorNode getRightNode(){
        return rightChild.getRightParent();
    }

    public void setLeftChild(ValueNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(ValueNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean hasPriority(){
        return (type == Operator.MULTIPLY || type == Operator.DIVIDE);
    }
}
