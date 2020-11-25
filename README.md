# BasicConsoleCalculator

Takes a mathematical expression like "1 + 2 * (4 + 2 * (1 + 2))", parses it, and calculates it's value.

## How it works

### Parsing
It parses the content of the expression into a Node tree.  
The node tree consists of 3 different types of nodes:

* **ValueNode**: Stores a simple value, for example 5 or 3 or 1.123.
  
* **BracketNode**: Extends ValueNode, stores and parses the content in brackets. It also funcions as the base node. Why does it extend ValueNode? A bracket expression is fundamentally treated like a value, so it can be used by operators. Extending ValueNode makes this possible.

* **OperatorNode**: Has two ValueNodes. Will add/subtract/multiply/divide these two nodes when it's calculate() method is called. More on that method later.

Each ValueNode is connected to 1-2 parents. 
In '*1 + 2 - 3*', 
* *1* only has *+* as a parent.
* *2* has *+* and *-* as parents.
* *3* only has *-* as a parent.

Note that the 2 ValueNode is referenced by both *+* and *-*.
Now the original expression is stored in a node tree.

### Calculation
The calculation starts at the base BracketNode.  
The base node loops through each of it's operators.  
In the first pass, it checks whether the operator is a priority operator (* and /).  
If it isn't, the node is skipped.  
In the second pass, the remaining operators are calculated.  
Since brackets are treated as values, they're automatically given priority over everything else.

When the calculate() method on an operator is called, it quickly calculates the result of the two child notes and stores it in a double.  
It now creates a new ValueNode from that double.  
The original left node might have a left parent. If it has, the right child of the left parent gets replaced with the newly calulated result.  
The same thing is done to the right node the other way around.  
The two nodes that were conneted by the operator where the calculate() method was called have now been replaced by the result.  
Lastly, the operator node and its original children are removed from the node tree.  
This is repeated for all operators until all are calculated.  
The process looks like this:

1 + 2 * 3 - (4 * 5)      Firstly, the 2 * 3 is calculated because * is the leftmost priority operator (the brackets are ignored for now because remember, they are treated like values and only calculated when needed

1 + 6 - (4 * 5)          Next, the 1 + 6 is calculated because the + is the leftmost non-prioriy operator (there are no priority operators left)

7 - (4 * 5)              Now, 7 - (4 * 5) wants to be calculated. This operation contains a bracket expression, so the operator calls the getValue() method on the bracket expression, which internally calculates its value.

7 - 20                   The bracket has returned its value and the 7- 20 operation can now be calculated

-13                      The value of *1 + 2 * 3 - (4 * 5)* is *-13*
