package math;

import java.util.ArrayList;

public class Term {

    //For example 4a or 6a + 4

    private ArrayList<Variable> variables = new ArrayList<>();

    public void addVariable(Variable v){
        variables.add(v);
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        return null;
    }

    public static Term parseTerm(String s){

        Term returnTerm = new Term();

        String currentVariable = "";

        boolean lastOperatorNegative = false;

        for (char c : s.toCharArray()){

            switch (c){
                case '-':
                    if(!currentVariable.equals("")) {
                        returnTerm.addVariable(new Variable(currentVariable, lastOperatorNegative));
                    }
                    lastOperatorNegative = true;
                    break;
                case '+':
                    if(!currentVariable.equals("")) {
                        returnTerm.addVariable(new Variable(currentVariable, lastOperatorNegative));
                    }
                    lastOperatorNegative = false;
                    break;

                case ' ':
                    break;

                default:
                    currentVariable += c;
            }

        }

        returnTerm.addVariable(new Variable(currentVariable, lastOperatorNegative));

        return returnTerm;
    }
}
