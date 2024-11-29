/*  Student information for assignment:
 *
 *  On MY honor, JP Reeves, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  Grader name: Eliza
 *
 */

import java.util.HashMap;

public class HuffTree {

    private TreeNode root;

    public HuffTree(TreeNode root) {
        this.root = root;
    }

    public void huffCodes(HashMap<Integer, String> huffCodes) {
        TreeNode tracer = root;
        huffCodeRecur(tracer, "", huffCodes);
    }

    private void huffCodeRecur(TreeNode here, String code, HashMap<Integer, String> huffCodes) {
        if (here != null) {
            if (here.isLeaf()) {
                huffCodes.put(here.getValue(), code);
            }

            huffCodeRecur(here.getLeft(), code + "0", huffCodes);
            huffCodeRecur(here.getRight(), code + "1", huffCodes);
        }
    }

    public void treeData(int[] result, StringBuilder preOrder) {
        TreeNode tracer = root;
        treeRecur(tracer, result, preOrder);
    }

    /*
     * generates recursively the size and preOrder traversal of the huffman tree
     */
    private void treeRecur(TreeNode here, int[] result, StringBuilder preOrder) {
        if (here != null) {
            // appends to preOrder traversal and updates treeSize
            if (here.isLeaf()) {
                preOrder.append("1");
                preOrder.append(toBinary(here.getValue()));
                result[0] += IHuffConstants.BITS_PER_WORD + 1;
            } else {
                preOrder.append("0");
            }
            result[0] += 1;
            // left
            treeRecur(here.getLeft(), result, preOrder);
            // right
            treeRecur(here.getRight(), result, preOrder);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    /*
     * converts an integer to a BITS_PER_WORD + 1 bit binary value
     */
    private String toBinary(int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < IHuffConstants.BITS_PER_WORD + 1; i++) {
            result.insert(0, num % 2);
            num /= 2;
        }
        return result.toString();
    }
}
