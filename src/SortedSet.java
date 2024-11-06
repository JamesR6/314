/*  Student information for assignment:
 *
 *  On MY honor, JP Reeves, 
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  TA name: Eliza  
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    private int bsearch(E target, int low, int high) {
        if(low <= high){
            int mid = low + ((high - low) / 2);
	        if(myCon.get(mid).equals(target))
                return mid;
            else if(myCon.get(mid).compareTo(target) > 0)
                return bsearch(target, low, mid - 1);
            else
                return bsearch(target, mid + 1, high);
        }
        return -(low + 1);
    }

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        Iterator<E> iter = other.iterator();
        while (iter.hasNext()) {
            E input = iter.next();
            int index = bsearch(input, 0, myCon.size() - 1);
            if (index < 0) {
                myCon.add(-(index + 1), input);
            }
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        if (myCon.size() == 0) {
            throw new IllegalStateException("no size");
        }

        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
        if (myCon.size() == 0) {
            throw new IllegalStateException("no size");
        }

        return myCon.get(myCon.size() - 1);
    }



}