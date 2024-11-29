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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private int[] freqCounts;
    private HuffTree huffmanTree;
    private HashMap<Integer, String> huffCodes;
    private int headerType;
    private int bitsSaved;

    public SimpleHuffProcessor() {
        freqCounts = new int[ALPH_SIZE];
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
        headerType = headerFormat;
        int ogFileSize = 0;

        // creates the frequency list
        BitInputStream bits = new BitInputStream(in);
        int inbits = bits.read();
        while (inbits != -1) {
            ogFileSize += BITS_PER_WORD;
            freqCounts[inbits]++;
            inbits = bits.read();
        }

        // creates the queue and combines until 1 TreeNode is left
        PQueue314 queue = new PQueue314();
        queueLogic(queue);

        // get the huffCodes from the tree and place into HashMap
        huffmanTree.huffCodes(huffCodes);

        for (int a : huffCodes.keySet()) {
            showString("" + a + " -- " + huffCodes.get(a));
        }

        bitsSaved = ogFileSize - compressedDataSize();
        return bitsSaved;
    }

    private int compressedDataSize() {
        int result = 0;

        // Magic number and STORE value
        result += BITS_PER_INT * 2;

        if (headerType == STORE_COUNTS) {
            result += ALPH_SIZE * BITS_PER_INT;
        } else {
            int[] treeSize = new int[1];
            StringBuilder preOrder = new StringBuilder();
            huffmanTree.treeData(treeSize, preOrder);

            result += BITS_PER_INT;
            result += treeSize[0];
        }

        int value = 0;
        for (int a : freqCounts) {
            String huff = huffCodes.get(value);
            if (huff != null) {
                result += a * huff.length();
            }
            value++;
        }

        result += huffCodes.get(PSEUDO_EOF).length();

        return result;
    }

    /*
     * creates and parses through priority queue
     */
    private void queueLogic(PQueue314 queue) {
        int value = 0;
        for (int count : freqCounts) {
            if (count != 0) {
                queue.enqueue(new TreeNode(value, count));
            }
            value++;
        }
        // Pseudo-EOF
        queue.enqueue(new TreeNode(PSEUDO_EOF, 1));

        // combine the lowest frequency values until only one is left, this is the root
        while (queue.size() > 1) {
            TreeNode leftNode = queue.dequeue();
            TreeNode rightNode = queue.dequeue();

            TreeNode newNode = new TreeNode(leftNode, 0, rightNode);
            queue.enqueue(newNode);
        }
        huffmanTree = new HuffTree(queue.dequeue());
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
        if (bitsSaved < 0 && !force) {
            myViewer.showError("Compressed file has " + -bitsSaved + " more bits than " +
                    "uncompressed file.\n" +
                    "Select 'force compression' option to compress");
            return -1;
        }
        BitOutputStream bos = new BitOutputStream(out);

        // write MAGIC NUMBER
        bos.writeBits(BITS_PER_INT, MAGIC_NUMBER);

        // write STORE
        bos.writeBits(BITS_PER_INT, headerType);

        // write Header
        if (headerType == STORE_COUNTS) {
            writeCountsHeader(bos);
        } else if (headerType == STORE_TREE) {
            writeTreeHeader(bos);
        }

        // write the Data
        writeData(in, bos);

        // write EOF
        String eof = huffCodes.get(PSEUDO_EOF);
        for (int i = 0; i < eof.length(); i++) {
            if (eof.charAt(i) == '0') {
                bos.writeBits(1, 0);
            } else {
                bos.writeBits(1, 1);
            }
        }
        bos.close();

        return compressedDataSize();
    }

    /*
     * writes the header for type counts
     */
    private void writeCountsHeader(BitOutputStream bos) {
        for (int k = 0; k < ALPH_SIZE; k++) {
            bos.writeBits(BITS_PER_INT, freqCounts[k]);
        }
    }

    /*
     * writes the header for type tree
     */
    private void writeTreeHeader(BitOutputStream bos) {
        int[] treeSize = new int[1];
        StringBuilder preOrder = new StringBuilder();
        huffmanTree.treeData(treeSize, preOrder);

        // writes the size and preOrder traversal of the huffman tree
        bos.writeBits(BITS_PER_INT, treeSize[0]);
        for (char a : preOrder.toString().toCharArray()) {
            if (a == '0') {
                bos.writeBits(1, 0);
            } else {
                bos.writeBits(1, 1);
            }
        }
    }

    /*
     * writes the data from the original file
     */
    private void writeData(InputStream in, BitOutputStream bos) throws IOException {
        BitInputStream bis = new BitInputStream(in);

        int inbits = bis.readBits(BITS_PER_WORD);
        while (inbits != -1) {
            // finds and writes associated huffCode
            String huffCode = huffCodes.get(inbits);
            for (int i = 0; i < huffCode.length(); i++) {
                if (huffCode.charAt(i) == '0') {
                    bos.writeBits(1, 0);
                } else {
                    bos.writeBits(1, 1);
                }
            }
            inbits = bis.read();
        }
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
        bitsSaved = 0;
        BitInputStream bis = new BitInputStream(in);
        BitOutputStream bos = new BitOutputStream(out);

        // read magic number
        int magic = bis.readBits(BITS_PER_INT);
        if (magic != MAGIC_NUMBER) {
            myViewer.showError("Error reading compressed file. \n" +
                    "File did not start with the huff magic number.");
            return -1;
        }

        // read header type and create tree
        headerType = bis.readBits(BITS_PER_INT);
        if (headerType == STORE_COUNTS) {
            readCounts(bis);
        } else if (headerType == STORE_TREE) {
            int treeSize = bis.readBits(BITS_PER_INT);
            TreeNode storeTree = treeFromCompressed(bis, new int[] { treeSize });
            huffmanTree = new HuffTree(storeTree);
        } else {
            myViewer.showError("Error reading compressed file. \n" +
                    "unable to uncompress custom headers");
            return -1;
        }

        decompressData(bis, bos);

        bis.close();
        bos.close();

        return bitsSaved;
    }

    private int decompressData(BitInputStream bis, BitOutputStream bos) throws IOException {
        boolean done = false;
        TreeNode tracer = huffmanTree.getRoot();
        while (!done) {
            int bit = bis.readBits(1);
            if (bit == -1) {
                myViewer.showError("Error reading compressed file. \n" +
                        "unexpected end of input. No PSEUDO_EOF value.");
                bis.close();
                bos.close();
                return -1;
            } else {
                if (bit == 0) {
                    tracer = tracer.getLeft();
                } else {
                    tracer = tracer.getRight();
                }
                if (tracer.isLeaf()) {
                    if (tracer.getValue() == PSEUDO_EOF) {
                        bitsSaved += BITS_PER_WORD;
                        done = true;
                    } else {
                        bitsSaved += BITS_PER_WORD;
                        bos.write(tracer.getValue());
                        tracer = huffmanTree.getRoot();
                    }
                }
            }
        }
        return 0;
    }

    private void readCounts(BitInputStream bis) throws IOException {
        int location = 0;
        for (int i = 0; i < ALPH_SIZE; i++) {
            int count = bis.readBits(BITS_PER_INT);
            freqCounts[location] = count;
            location++;
        }

        PQueue314 queue = new PQueue314();
        queueLogic(queue);
    }

    private TreeNode treeFromCompressed(BitInputStream bis, int[] treeSize) throws IOException {
        if (treeSize[0] == 0) {
            return null;
        }

        int current = bis.readBits(1);
        treeSize[0] -= 1;
        if (current == 1) {
            int value = bis.readBits(BITS_PER_WORD + 1);
            treeSize[0] -= BITS_PER_WORD + 1;
            return new TreeNode(value, 1);
        } else {
            TreeNode left = treeFromCompressed(bis, treeSize);
            TreeNode right = treeFromCompressed(bis, treeSize);
            TreeNode node = new TreeNode(left, 0, right);
            return node;
        }
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
