import math.Term;
import math.Variable;

public class Main {

    public static void main(String[] args) {
        System.out.println(Term.parseTerm("4c + 5x -6vh"));

        Variable v = new Variable("10v", false);
        Variable v2 = new Variable("6v", false);
        Variable v3 = new Variable("5d", false);
        Variable v4 = new Variable("5", false);
        Variable v5 = new Variable("g", false);
        Variable vf = new Variable("g67", false);

        System.out.println("---");
        System.out.println(v.add(v));
        System.out.println(v.add(v3));
        System.out.println(v.subtract(v));
        System.out.println(v.subtract(v3));
        System.out.println(v.multiply(v));
        System.out.println(v.multiply(v3));
        System.out.println(v.divide(v));

        System.out.println("---");
        System.out.println("5g -> " + v4.toString() + " " + v5.toString());
        System.out.println(v4.multiply(v5));

        System.out.println("---");
        System.out.println(vf);

        // (5a + 6a) * 7b = 77ab

        //   5v / 7d
        System.out.println("---");
        System.out.println(v.divide(v3));

        System.out.println("---");
        System.out.println(Term.parseTerm("5x + 6y - 5").add(Term.parseTerm("2x + 2").add(Term.parseTerm("-7x + 3 - 5y"))));

        System.exit(0);

        Calculator c = new Calculator();
    }
}
