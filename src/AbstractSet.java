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

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    @Override
    public void clear() {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
    
    /**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. 
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    @Override
    public boolean contains(E obj) {
        if (obj == null) {
            throw new IllegalArgumentException("contains");
        }

        for (E val : this) {
            if (val.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    @Override
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("containsAll");
        }

        for (E val : otherSet) {
            if (!this.contains(val)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise
     */
    @Override
    public boolean remove(E val) {
        if (val == null) {
            throw new IllegalArgumentException("remove");
        }

        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(val)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    @Override
    public int size() {
        int result = 0;
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        return result;
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ISet)) {
            return false;
        }

        ISet<E> o = (ISet<E>) other;

        if (size() != o.size()) {
            return false;
        }
        Iterator<E> iter = this.iterator();
        while(iter.hasNext()) {
            if (!o.contains(iter.next())) {
                return false;
            }
        }
        return true;
    }

    //TODO ask what all the stuff means
    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    @Override
    public ISet<E> union(ISet<E> otherSet) {
        return this.union(otherSet);
    }

    /**
     * Return a String version of this set. 
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}