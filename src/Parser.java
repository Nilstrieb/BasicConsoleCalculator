import layers.Layer;
import nodes.NodeTree;

public class Parser {

   private NodeTree nodeTree;

   public  Parser (String line) {
      Layer baseLayer = new Layer(line);
   }

   public NodeTree getNodeTree () {
      return nodeTree;
   }
}