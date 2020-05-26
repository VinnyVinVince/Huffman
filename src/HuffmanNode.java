/*
 *  Vincent Do
 *  Data Structures
 *  Project 7
 *  5/6/2020
 *  A HuffmanNode that makes up one element of a HuffmanTree. Since the tree is binary, a HuffmanNode has two
 *  connections (left & right nodes). It stores integer values necessary for HuffmanTree operations.
 *  Author has chosen a public design for this class for simplicity, since only the HuffmanTree class makes
 *  calls to it.
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    // Occurrences of a character. Set to 0 when unused.
    public int frequency;

    // Integer value of a character. For branch nodes, this is -1.
    public int character;

    // Left reference.
    public HuffmanNode left;

    // Right reference.
    public HuffmanNode right;

    // Base constructor, used for leaf nodes.
    public HuffmanNode(int frequency, int character) {

        this.frequency = frequency;
        this.character = character;

    }

    // Branch constructor used when frequency is necessary. Express node connections.
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {

        this(frequency, -1);
        this.left = left;
        this.right = right;

    }

    // Leaf constructor when frequency does not matter.
    public HuffmanNode(int character) {

        this(0, character);

    }

    // Branch constructor.
    public HuffmanNode() {

        this(-1);

    }

    // Following Comparable interface and usual compareTo values.
    // Returns value <0, 0, or >0 for less than, equal to, or greater than respectively.
    public int compareTo(HuffmanNode other) {

        return frequency - other.frequency;

    }

    // Returns true if leaf node, false otherwise.
    public boolean isLeaf() {

        return left == null && right == null;

    }

}