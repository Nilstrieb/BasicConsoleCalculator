public class OperatorNode extends Node{

   private Node value1;
   private Node value2;

   public OperatorNode(int id) {
      super(id);
   }

   @Override
   public double getValue () {
      return 0;
   }
}