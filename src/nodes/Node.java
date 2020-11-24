package nodes;

public abstract class Node {

    private int id;
    private Node parent;

    public int getId() {
        return 0;
    }

    public Node(int id) {

    }

    public abstract double getValue();
}