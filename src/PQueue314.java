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

import java.util.ArrayList;

public class PQueue314 {

    private ArrayList<TreeNode> cont;
    private int size;

    public PQueue314 () {
        cont = new ArrayList<>();
        size = 0;
    }

    /*
     * add a node to this priority queue based on relative frequency 
     * and upholds first in first out in the case of duplicate frequencies
     */
    public boolean enqueue(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < size; i ++) {
            if (node.compareTo(cont.get(i)) < 0) {
                cont.add(i, node);
                size++;
                return true;
            }
        }
        cont.add(node);
        size++;
        return true;
    }

    /*
     * return the first in element of priority queue if it exists
     * throw exception if there are no elements to return
     */
    public TreeNode dequeue() {
        if (size == 0) {
            throw new IllegalStateException();
        }

        TreeNode result = cont.get(0);
        cont.remove(0);
        size--;
        return result;
    }

    /*
     * return the size of the Priority Queue
     */
    public int size() {
        return size;
    }

    public void printQueue() {
        for (TreeNode node : cont) {
            System.out.println(node.toString());
        }
    }
    
}
