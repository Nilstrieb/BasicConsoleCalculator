import nodes.NodeTree;

import java.util.Scanner;

public class Calculator {

    public Calculator() {
        start();
    }

    private void start(){
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        while(!input.equals("exit")){
            Parser parser = new Parser(input);
            /*NodeTree tree = parser.getNodeTree();
            double result = tree.getValue();
            System.out.println(input);
            System.out.println(result);*/
            input = scn.nextLine();
        }
    }
}