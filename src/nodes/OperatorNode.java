package nodes;

public class OperatorNode extends Node{

   private Node value1;
   private Node value2;

   private final Operator operator;

   public OperatorNode(int id, Operator operator) {
      super(id);
      this.operator = operator;
   }

   public void setValue1(Node value1) {
      this.value1 = value1;
   }

   public void setValue2(Node value2) {
      this.value2 = value2;
   }

   @Override
   public double getValue () {
      return switch (operator){
         case ADD -> value1.getValue() + value2.getValue();
         case SUBTRACT -> value1.getValue() - value2.getValue();
         case MULTIPLY -> value1.getValue() * value2.getValue();
         case DIVIDE -> value1.getValue() / value2.getValue();
      };
   }
}