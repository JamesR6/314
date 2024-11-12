/*  Student information for assignment:
 *
 *  On MY honor, JP Reeves, 
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
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
     * O(N)
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
     * O(N)
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
      * A union operation. Add all items of otherSet that 
      * are not already present in this set to this set.
      * @param otherSet != null
      * @return true if this set changed as a result of this operation, 
      * false otherwise.
      * O(N^2)
      */
      public boolean addAll(ISet<E> otherSet) {
        boolean changed = false;
        for (E item : otherSet) {
            if (this.add(item) && !changed) {
                changed = true;
            }
        }
        return changed;
      }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     * O(N^2)
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
     * O(N)
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
     * O(N)
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
        if (size() == 0) {
            return true;
        }
        Iterator<E> iterThis = this.iterator();
        Iterator<E> iterOther = o.iterator();
        E thisData = iterThis.next();
        E otherData = iterOther.next();
        if (!thisData.getClass().equals(otherData.getClass())) {
            return false;
        }
        
        return this.containsAll(o);
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

    /**
     * Create a new set that is the difference of this set and otherSet. 
     * Return an ISet of elements that are in this Set but not in otherSet. 
     * Also called the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] 
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     * O(depends on class)
     */
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("difference");
        }
        ISet<E> union = this.union(otherSet);
        ISet<E> inter = this.intersection(otherSet);
        for (E data : union) {
            if (inter.contains(data)) {
                union.remove(data);
            }
        }
        return union;
    }
}