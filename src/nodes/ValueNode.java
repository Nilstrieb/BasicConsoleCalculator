package nodes;

public class ValueNode extends Node {

   private final double value;

   public ValueNode(int id, double value) {
      super(id);
      this.value = value;
   }

   @Override
   public double getValue () {
      return value;
   }
}