/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *
 *  Student 2
 *  UTEID:
 *  email address:
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private int[] freqCounts;
    private TreeNode root;
    private HashMap<Integer, String> huffCodes;

    public SimpleHuffProcessor() {
        freqCounts = new int[256];
        root = new TreeNode(0, 0);
        huffCodes = new HashMap<>();
    }

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * 
     * @param in           is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind
     *                     of
     *                     header to use, standard count format, standard tree
     *                     format, or
     *                     possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     *         Note, to determine the number of
     *         bits saved, the number of bits written includes
     *         ALL bits that will be written including the
     *         magic number, the header format number, the header to
     *         reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        // creates the frequency list
        BitInputStream bits = new BitInputStream(in);
        int inbits = bits.readBits(IHuffConstants.BITS_PER_WORD);

        while (inbits != -1) {
            freqCounts[inbits]++;
            inbits = bits.readBits(IHuffConstants.BITS_PER_WORD);
        }

        // creates the original priority queue
        PQueue314 queue = new PQueue314();
        int value = 0;
        for (int count : freqCounts) {
            if (count != 0) {
                queue.enqueue(new TreeNode(value, count));
            }
            value++;
        }

        System.out.println("chicken" + freqCounts[0]);

        // combine the lowest frequency values until only one is left, this is the root
        // of the tree
        while (queue.size() > 1) {
            TreeNode leftNode = queue.dequeue();
            TreeNode rightNode = queue.dequeue();

            TreeNode newNode = new TreeNode(leftNode, 0, rightNode);
            queue.enqueue(newNode);
        }
        root = queue.dequeue();

        // get the huffCodes from the tree and place into HashMap
        huffCodeRecur(root, "");
        return 0;
    }

    private void huffCodeRecur(TreeNode here, String code) {
        if (here != null) {
            if (here.isLeaf()) {
                huffCodes.put(here.getValue(), code);
            }

            huffCodeRecur(here.getLeft(), code + "0");
            huffCodeRecur(here.getRight(), code + "1");
        }
    }

    /**
     * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br>
     * pre: <code>preprocessCompress</code> must be called before this method
     * 
     * @param in    is the stream being compressed (NOT a BitInputStream)
     * @param out   is bound to a file/stream to which bits are written
     *              for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than
     *              the input file.
     *              If this is false do not create the output file if it is larger
     *              than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     *                     writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        throw new IOException("compress is not implemented");
        // return 0;
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * 
     * @param in  is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     *                     writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
        throw new IOException("uncompress not implemented");
        // return 0;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s) {
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
