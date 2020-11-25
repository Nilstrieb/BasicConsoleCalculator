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
            input = scn.nextLine();
        }
    }
}