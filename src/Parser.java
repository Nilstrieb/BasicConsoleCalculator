import nodes.BracketNode;

public class Parser {

    private String contentString;

    public Parser(String line) throws NumberFormatException {

        this.contentString = line;

        BracketNode baseNode = new BracketNode();
        String leftNumber = "";
        String rightNumber = "";
        char currentOperator = '0';

        int bracketLevel = 0;


        for (int i = 0; i < contentString.length(); i++) {

            char c = contentString.charAt(i);

            if (bracketLevel == 0 || c == ')' || c == '(') {
                switch (c) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        currentOperator = c;
                    case ' ':
                        break;
                    case '(':
                        bracketLevel++;
                        System.err.println("NEW LEVEL: " + bracketLevel);
                        break;
                    case ')':
                        bracketLevel--;
                        System.err.println("DOWN LEVEL: " + bracketLevel);
                        break;

                    default:
                        if (currentOperator == '0') {
                            leftNumber += c;
                        } else {
                            rightNumber += c;
                        }
                }
            }
        }
    }
}