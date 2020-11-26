import math.Term;
import math.Variable;

public class Main {

    public static void main(String[] args) {
        System.out.println(Term.parseTerm("4c + 5x -6vh"));

        Variable v = new Variable("5v", false);
        Variable v2 = new Variable("6v", false);
        Variable v3 = new Variable("7d", false);

        System.out.println("---");
        System.out.println(v.add(v));
        System.out.println(v.add(v3));
        System.out.println(v.subtract(v));
        System.out.println(v.subtract(v3));
        System.out.println(v.multiply(v));
        System.out.println(v.multiply(v3));

        Calculator c = new Calculator();
    }
}
