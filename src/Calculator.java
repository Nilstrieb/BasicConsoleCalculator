import nodes.BracketNode;

import java.util.Scanner;

public class Calculator {

    public Calculator() {
        start();
    }

    private void start() {
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        while (!input.equals("exit")) {
            try {
                System.out.println(new BracketNode(input).getValue());
            } catch (NumberFormatException e) {
                System.err.println("Not a valid expression");
            }
            input = scn.nextLine();
        }
    }
}