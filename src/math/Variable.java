package math;

public class Variable {

    private final double amount;
    private final String variable;
    private final boolean isNegative;

    public Variable(String term, boolean isNegative) {
        this.isNegative = isNegative;
        if (term.matches("[\\d\\.]+[a-zA-Z]*")) {
            String[] split = term.replaceAll("(\\d+)([a-zA-Z]*)", "$1;$2").split(";");
            amount = isNegative ? -Double.parseDouble(split[0]) : Double.parseDouble(split[0]);
            if (split.length > 1) {
                variable = split[1];
            } else {
                variable = "";
            }
        } else {
            variable = "";
            amount = 0;
        }
    }

    public Variable(double amount, String variable) {
        isNegative = amount < 0;
        this.amount = amount;
        this.variable = variable;
    }


    public Term add(Variable v) {
        if (variable.equals(v.getVariable())) {
            return new Term(new Variable(amount + v.getAmount(), variable));
        } else {
            return new Term(this, v);
        }
    }

    public Term subtract(Variable v) {
        if (variable.equals(v.getVariable())) {
            return new Term(new Variable(amount - v.getAmount(), variable));
        } else {
            return new Term(this, v.invert());
        }
    }

    public Term multiply(Variable v) {
        return new Term(new Variable(amount * v.getAmount(), variable + v.getVariable()));
    }

    public Term divide(Variable v) {
        if (variable.equals((v.getVariable()))) {
            return new Term(new Variable(amount / v.getAmount(), ""));
        } else {
            Variable v1 = new Variable(amount / v.getAmount(), variable);
            Variable v2 = new Variable(1, v.getVariable());
            return new Term(v1, v2);
        }
    }

    private Variable invert() {
        return new Variable(-amount, variable);
    }


    @Override
    public String toString() {
        if (amount == 0) {
            return String.valueOf(0);
        } else if (isNegative) {
            return -amount + variable;
        } else {
            return amount + variable;
        }
    }

    public boolean isNegative() {
        return isNegative;
    }

    public double getAmount() {
        return amount;
    }

    public String getVariable() {
        return variable;
    }
}
