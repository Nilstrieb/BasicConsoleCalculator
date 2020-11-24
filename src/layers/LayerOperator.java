package layers;

import nodes.Operator;

public class LayerOperator extends Layer{

    private final String left;
    private final String right;

    private final Operator type;

    public LayerOperator(String left, String right, Operator type) {
        super();
        this.left = left;
        this.right = right;
        this.type = type;

        System.out.println("\nOPERATOR OF TYPE " + type);
        System.out.println(left + " ||| " + right);
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "|| " + left + " " + type + " " + right + " ||";
    }
}
