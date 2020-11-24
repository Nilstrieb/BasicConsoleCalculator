package layers;

import nodes.Operator;

import javax.management.MBeanRegistration;
import java.util.ArrayList;
import java.util.Arrays;

public class Layer {

    private ArrayList<Layer> content;
    private String contentString;

    public Layer(String contentString) {

        //TODO LAYER SHOULD SKIP EXPRESSIONS IN BRACKETS SINCE THEY ARE ALREADY HANDLED BY THE LAYER INSIDE

        this.contentString = contentString;

        content = new ArrayList<>();
        boolean finished = false;

        for (int i = 0; i < contentString.length() && !finished; i++) {
            char c = contentString.charAt(i);
            switch (c){
                case '+' -> content.add(new LayerOperator(contentString.substring(0, i), contentString.substring(i + 1), Operator.ADD));
                case '-' -> content.add(new LayerOperator(contentString.substring(0, i), contentString.substring(i + 1), Operator.SUBTRACT));
                case '*' -> content.add(new LayerOperator(contentString.substring(0, i), contentString.substring(i + 1), Operator.MULTIPLY));
                case '/' -> content.add(new LayerOperator(contentString.substring(0, i), contentString.substring(i + 1), Operator.DIVIDE));

                case '(' -> content.add(new Layer(contentString.substring(i + 1)));
                case ')' -> finished = true;
            }
        }

        System.out.println("\nNEW LAYER");
        System.out.println(this);
        System.out.println(Arrays.toString(content.toArray()));
    }

    public Layer(){

    }

    @Override
    public String toString() {
        return contentString;
    }
}
