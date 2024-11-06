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
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("add");
        }

        if (myCon.contains(item)) {
            return false;
        }
        myCon.add(item);
        return true;
    }

    @Override
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("addAll");
        }
        
        boolean changed = false;
        for (E item : otherSet) {
            if (add(item) && !changed) {
                changed = true;
            }
        }
        return changed;
    }
    
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("addAll");
        }

        ISet<E> result = new UnsortedSet<>();
        for (E item : this) {
            if (!otherSet.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("addAll");
        }

        ISet<E> result = new UnsortedSet<>();
        for (E item : this) {
            if (otherSet.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    @Override
    public int size() {
        return myCon.size();
    }

    @Override
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("addAll");
        }

        //TODO why is this goal n squared
        ISet<E> result = new UnsortedSet<>();
        for (E item : this) {
            result.add(item);
        }
        for (E item : otherSet) {
            result.add(item);
        }
        return result;
    }

    

    //clear is efficient
    //contains is efficient
    //contains all is efficient
    //equals should be efficient

    

}