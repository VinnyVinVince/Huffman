import java.util.*;
import java.io.*;

/*
 *  Vincent Do
 *  Data Structures
 *  Project 7
 *  5/6/2020
 *  A HuffmanTree provides the framework for constructing and traversing binary trees storing Huffman code
 *  for encoding and decoding operations. It is compatible with legally formatted Scanner reading files, as well as
 *  integer collections.
 */
public class HuffmanTree {

    // Overall root node.
    private HuffmanNode root;

    /*
     *  Pre:  Input count has some non-zero and non-negative elements. For an alphabetical result,
     *        it should have 255 elements corresponding to each ASCII value.
     *
     *  Post: Binary tree constructed with leaf nodes corresponding to character values with a non-zero frequency.
     */
    public HuffmanTree(int[] count) {

        Queue<HuffmanNode> pq = new PriorityQueue<>();

        for (int i = 0; i < count.length; i++)
            if (count[i] > 0)
                pq.add(new HuffmanNode(count[i], i));

        pq.add(new HuffmanNode(1, count.length));

        while (pq.size() > 1) {

            HuffmanNode left = pq.remove();
            HuffmanNode right = pq.remove();
            pq.add(new HuffmanNode(left.frequency + right.frequency, left, right));

        }

        root = pq.remove();

    }

    /*
     *  Pre:  Input is reading a file in valid format.
     *
     *  Post: Binary tree constructed with leaf nodes corresponding to character values present in
     *        read file.
     */
    public HuffmanTree(Scanner input) {

        root = new HuffmanNode();

        while(input.hasNextLine()) {

            int character = Integer.parseInt(input.nextLine());
            String path = input.nextLine();

            explore(root, path, character);

        }

    }

    // Private pair method for second constructor. Recursively builds tree using x=change(x) algorithm.
    private HuffmanNode explore(HuffmanNode root, String path, int character) {

        if (!path.equals("")) {

            String newPath = "";

            if (path.length() > 1)
                newPath = path.substring(1, path.length());

            if (path.charAt(0) == '0') {

                if (root.left == null)
                    root.left = new HuffmanNode();

                root.left = explore(root.left, newPath, character);

            } else {

                if (root.right == null)
                    root.right = new HuffmanNode();

                root.right = explore(root.right, newPath, character);

            }

            return root;

        } else {

            return new HuffmanNode(character);

        }

    }

    // Prints pairs of Huffman code paths and character values on a desired file.
    public void write(PrintStream output) {

        write(root, "", output);

    }

    // Private pair for write. Preorder algorithm, recursively traverses until leaf is reached, records Huffman
    // code path as it goes.
    private void write(HuffmanNode root, String path, PrintStream output) {

        if (root.isLeaf()) {

            output.println(root.character);
            output.println(path);

        } else {

            write(root.left, path + "0", output);
            write(root.right, path + "1", output);

        }

    }

    // Receives bit data from input stream and follows Huffman code path until a leaf is reached, then prints
    // corresponding character to file. Terminates when end of file node is reached.
    public void decode(BitInputStream input, PrintStream output, int eof) {

        HuffmanNode current = root;

        while (current.character < eof) {

            if (current.isLeaf()) {

                output.write(current.character);
                current = root;

            } else {

                int bit = input.readBit();

                if (bit == 0) {

                    current = current.left;

                } else {

                    current = current.right;

                }

            }

        }

    }

}