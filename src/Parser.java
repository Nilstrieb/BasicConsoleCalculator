import nodes.BracketNode;
import nodes.OperatorNode;
import nodes.ValueNode;

public class Parser {

    private String contentString;
    private BracketNode baseNode;

    public Parser(String line) throws NumberFormatException {

        this.contentString = line;

        baseNode = new BracketNode(line);

    }

    public double getValue() {
        return baseNode.getValue();
    }
}