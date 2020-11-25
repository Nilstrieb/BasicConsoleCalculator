package nodes;

public class OperatorNode {

    private ValueNode leftChild;
    private ValueNode rightChild;

    private final Operator type;

    public OperatorNode(ValueNode leftChild, ValueNode rightChild, Operator type) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.type = type;
    }

    public void calculate(){

        double result = switch (type){
            case ADD -> leftChild.getValue() + rightChild.getValue();
            case SUBTRACT -> leftChild.getValue() - rightChild.getValue();
            case MULTIPLY -> leftChild.getValue() * rightChild.getValue();
            case DIVIDE -> leftChild.getValue() / rightChild.getValue();
            };

        ValueNode resultNode = new ValueNode(result, getLeftNode(), getRightNode());

        getLeftNode().setRightChild(resultNode);
        getRightNode().setLeftChild(resultNode);
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
}
