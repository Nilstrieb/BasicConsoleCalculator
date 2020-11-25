package nodes;

import java.util.ArrayList;

public class BracketNode extends ValueNode{

    private ArrayList<OperatorNode> operators;

    public BracketNode() {
        operators = new ArrayList<>();
    }

    public void addOperator(OperatorNode operator){
        operators.add(operator);
    }

    @Override
    public double getValue() {
        return 0;
    }
}
