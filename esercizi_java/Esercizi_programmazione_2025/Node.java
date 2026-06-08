/**
 * Classe: Node
 * 
 * char c;
 * int w;   // weights = occorenze
 * Node n,o,l,r;
 * 
 * new Node(c,w) : Node
 * new Node(l,r) : Node
 * 
 * n.isLeaf() : boolean
 * n.symbol() : char
 * n.weight() : int
 * n.left()   : Node
 * n.right()  : Node
 * 
 * (radici, rami, foglie con pesi)
 * 
 * 
 * 
 * Comandi input: 
 * 
 * Huffman.charFreqs("IOTest.java")
 * Huffman.huffmanTree(freq)
 * 
 */

public class Node implements Comparable<Node> {
    
    private final char sym;
    private final int wgt;
    private final Node lft;
    private final Node rgt;
    
    public Node(char c, int w) {
        sym = c;
        wgt = w;
        lft = null;
        rgt = null;
    }
    
    public Node(Node l, Node r) {
        sym = (char) 0;
        wgt = l.weight() + r.weight();
        lft = l;
        rgt = r;
    }
    
    public boolean isLeaf() {
        return (lft == null);
    }
    
    public char symbol() {
        return sym;
    }
    
    public int weight() {
        return wgt;
    }
    
    public Node left() {
        return lft;
    }
    
    public Node right() {
        return rgt;
    }
    
    public int compareTo(Node o) {
        if(this.weight() < o.weight()) {
            return -1;
        } else if (this.weight() > o.weight()) {
            return +1;
        } else {
            return 0;
        }
    }
}
