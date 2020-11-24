package nodes;

public class NodeTree extends Node{

   private Node upperNode;

   public NodeTree(int id) {
      super(id);
   }

   public void setUpperNode(Node upperNode) {
      this.upperNode = upperNode;
   }

   @Override
   public double getValue () {
      return upperNode.getValue();
   }
}