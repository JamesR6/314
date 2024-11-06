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

    /* DELETE THIS COMMENT FROM YOUR SUBMISSION.
     * 
     * RECALL:
     * 
     * NO INSTANCE VARIABLES ALLOWED.
     * 
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * 
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     * 
     * NO METHODS ADDED other than those in ISet and Object.
     */
     
      
    @Override
    public void clear() {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
    
    @Override
    public boolean contains(E obj) {
        for (E val : this) {
            if (val.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(ISet<E> otherSet) {
        for (E val : otherSet) {
            if (!this.contains(val)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(E val) {
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(val)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

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