package math;

public class Variable {

    private double amount;
    private final String variable;
    private final boolean isNegative;
    private final boolean isDivided;

    public Variable(String term, boolean isNegative) {
        this.isNegative = isNegative;
        this.isDivided = false;
        if (term.matches("([\\d\\.]+[a-zA-Z]*)|([\\d\\.]*[a-zA-Z]+)")) {
            String[] split = term.replaceAll("(\\d*)([a-zA-Z]*)", "$1;$2").split(";");
            if (!split[0].matches("\\d+")) {
                amount = 1;
            } else {
                amount = isNegative ? -Double.parseDouble(split[0]) : Double.parseDouble(split[0]);
            }
            if (split.length > 1) {
                variable = split[1];
            } else if (split[0].matches("[a-zA-Z]+")) {
                variable = split[0];
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
        isDivided = false;
        this.amount = amount;
        this.variable = variable;
    }

    public Variable(double amount, String variable, boolean isDivided) {
        isNegative = amount < 0;
        this.amount = amount;
        this.variable = variable;
        this.isDivided = isDivided;
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
            Variable v2 = new Variable(1, v.getVariable(), true);
            return new Term(v1, v2);
        }
    }

    //MODIFY THE VARIABLE DIRECTLY
    public void addToThis(Variable v) {
        if (variable.equals(v.getVariable())) {
            this.amount += v.getAmount();
        }
    }

    private Variable invert() {
        return new Variable(-amount, variable);
    }


    @Override
    public String toString() {

        String amountString;

        if ((amount % 1) == 0) {
            amountString = String.valueOf(isNegative ? (int) -amount : (int) amount);
        } else {
            amountString = String.valueOf(isNegative ? -amount : amount);
        }

        if (amount == 0) {
            return true ? "0" : String.valueOf(Double.parseDouble(String.valueOf(Integer.parseInt("0")))); //ignore warning, it works     //(actually the whole if isn't needed but it's funny so I'll keep it in)
        } else if (amount == 1 || amount == -1 && !variable.equals("")) {
            return isNegative ? "-" + variable : variable;
        } else {
            return amountString + variable;
        }
    }

    public boolean isNegative() {
        return isNegative;
    }

    public boolean isDivided() {
        return isDivided;
    }

    public double getAmount() {
        return amount;
    }

    public String getVariable() {
        return variable;
    }

    public boolean isZero() {
        return (amount == 0);
    }
}
