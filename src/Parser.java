import nodes.BracketNode;
import nodes.OperatorNode;
import nodes.ValueNode;

public class Parser {

    private String contentString;

    public Parser(String line) throws NumberFormatException {

        this.contentString = line;

        BracketNode baseNode = new BracketNode(line);

    }
}