package math;

import java.util.ArrayList;
import java.util.Arrays;

public class Term {

    //For example 4a or 6a + 4

    private ArrayList<Variable> variables = new ArrayList<>();

    public Term(Variable ... variable) {
        variables.addAll(Arrays.asList(variable));
    }

    public void addVariable(Variable v) {
        variables.add(v);
    }

    public Term add(Term t){
        return null;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < variables.size(); i++) {
            Variable v = variables.get(i);
            if(!(i == 0 && !v.isNegative())) {
                if (v.isNegative()) {
                    returnString.append(" - ");
                } else {
                    returnString.append(" + ");
                }
            }
            returnString.append(v.toString());
        }
        return returnString.toString();
    }

    public static Term parseTerm(String s) {

        Term returnTerm = new Term();
        System.err.println("Started parsing " + s);

        String currentVariable = "";
        boolean lastOperatorNegative = false;

        for (char c : s.toCharArray()) {

            switch (c) {
                case '-':
                    if (!currentVariable.equals("")) {
                        returnTerm.addVariable(new Variable(currentVariable, lastOperatorNegative));
                        System.err.println("New Variable: " + currentVariable);
                    }
                    lastOperatorNegative = true;
                    currentVariable = "";
                    break;
                case '+':
                    if (!currentVariable.equals("")) {
                        returnTerm.addVariable(new Variable(currentVariable, lastOperatorNegative));
                    }
                    lastOperatorNegative = false;
                    currentVariable = "";
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
