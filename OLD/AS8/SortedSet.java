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

    /*
     * return the index of target at O(logN) or the negative
     * index - 1 if the element was not found
     */
    private int bsearch(E target, int low, int high) {
        // code for binary search from class slides
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

    // code for quick sort from class slides
    private void swapReferences(ArrayList<E> a, int index1, int index2) {
       E tmp = a.get(index1);
       a.set(index1, a.get(index2));
       a.set(index2, tmp);
   }

    public void quicksort(ArrayList<E> data, int start, int stop) {
        if(start < stop) {
	    int pivotIndex = (start + stop) / 2;

	    // Place pivot at start position
             swapReferences(data, pivotIndex, start);
             E pivot = data.get(start);

             // Begin partitioning
             int j = start;

	    // from first to j are elements less than or equal to pivot
   	    // from j to i are elements greater than pivot
	    // elements beyond i have not been checked yet
	    for(int i = start + 1; i <= stop; i++ ) {
	        //is current element less than or equal to pivot
	        if (data.get(i).compareTo(pivot) <= 0) {
	            // if so move it to the less than or equal portion
	            j++;
	            swapReferences(data, i, j);
	        }
	    }
	
	    //restore pivot to correct spot
	    swapReferences(data, start, j);
	    quicksort( data, start, j - 1 );    // Sort small elements
	    quicksort( data, j + 1, stop );   // Sort large elements
        } // else start >= stop, 0 or 1 element, base case, do nothing
    }

    private void merge( ArrayList<E> data, ArrayList<E> temp, 
			int leftPos, int rightPos, int rightEnd) {
        // code for merge from class slides
	    int leftEnd = rightPos - 1;
	    int tempPos = leftPos;
	    //main loop
	    while(leftPos <= leftEnd && rightPos <= rightEnd){
		    if( data.get(leftPos).compareTo(data.get(rightPos)) < 0) {
			    temp.add(data.get(leftPos));
			    leftPos++;
		    } else if (data.get(leftPos).compareTo(data.get(rightPos)) > 0){
			    temp.add(data.get(rightPos));
			    rightPos++;
		    } else {
                temp.add(data.get(leftPos));
                rightPos++;
                leftPos++;
            }
		    tempPos++;
	    }
	    //copy rest of left half
	    while (leftPos <= leftEnd) {
            temp.add(data.get(leftPos));
		    tempPos++;
		    leftPos++;			                      
	    }
	    //copy rest of right half
	    while (rightPos <= rightEnd) {
            temp.add(data.get(rightPos));
		    tempPos++;
		    rightPos++;			                      
	    }
    }

    private void combine(ArrayList<E> combined, ISet<E> otherSet) {
        combined.addAll(myCon);
        Iterator<E> iter = otherSet.iterator();
        while (iter.hasNext()) {
            combined.add(iter.next());
        }
    }



    /**
     * create an empty SortedSet
     * O(1)
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * @param other != null
     * O(NlogN)
     */
    public SortedSet(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("sortedSet");
        }

        myCon = new ArrayList<>();
        ArrayList<E> newCon = new ArrayList<>();
        combine(newCon, other);
        quicksort(newCon, 0, newCon.size() - 1);
        myCon = newCon;
    }

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise.
     * O(N)
     */
    @Override
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("add");
        }

        int index = bsearch(item, 0, myCon.size() - 1);
        if (index < 0) {
            myCon.add(-(index + 1), item);
            return true;
        }
        return false;
    }

    /**
      * A union operation. Add all items of otherSet that 
      * are not already present in this set to this set.
      * @param otherSet != null
      * @return true if this set changed as a result of this operation, 
      * false otherwise.
      * O(N)
      */
    @Override
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("addAll");
        }

        if (otherSet instanceof SortedSet) {
            ArrayList<E> data = new ArrayList<>();
            ArrayList<E> temp = new ArrayList<>();
            combine(data, otherSet);

            //combine all data and merge sort
            merge(data, temp, 0, myCon.size(), data.size() - 1);
            boolean changed = (myCon.size() < temp.size());
            myCon = temp;
            return changed;
        } else {
            Iterator<E> iter = otherSet.iterator();
            boolean changed = false;
            while (iter.hasNext()) {
                if (this.add(iter.next()) && !changed) {
                    changed = true;
                }
            }
            return changed;
        }
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     * O(1)
     */
    @Override
    public void clear() {
        myCon = new ArrayList<>();
    }

    /**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. 
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     * O(logN)
     */
    @Override
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("contains");
        }

        return (bsearch(item, 0, myCon.size() - 1) >= 0);
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     * O(N)
     */
    @Override
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("containsAll");
        }
        
        if (otherSet instanceof SortedSet) {
            return containsAllSorted(otherSet);
        }
        return containsAllUnsorted(otherSet);
    }

    /*
     * private helper for containsAll
     */
    private boolean containsAllUnsorted(ISet<E> otherSet) {
        for (E item : otherSet) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /*
     * private helper for containsALl
     */
    private boolean containsAllSorted(ISet<E> otherSet) {
        if (otherSet.size() == 0) {
            return true;  
        } else if (this.size() == 0) {
            return false; 
        }
        //modified merge in order to find containsALl
        Iterator<E> iterThis = this.iterator();
        Iterator<E> iterOther = otherSet.iterator();
        E thisData = iterThis.next();
        E otherData = iterOther.next();
        while (true) {
            if (thisData.compareTo(otherData) < 0) {
                if (iterThis.hasNext()) {
                    thisData = iterThis.next();
                } else {
                    return false;
                }
            } else if (thisData.compareTo(otherData) == 0) {
                if (iterOther.hasNext()) {
                    otherData = iterOther.next();
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * Create a new set that is the difference of this set and otherSet. 
     * Return an ISet of elements that are in this Set but not in otherSet. 
     * Also called the relative complement. 
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     * O(N^2)
     */
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("difference");
        }

        SortedSet<E> result = new SortedSet<>();
        for (E item : this) {
            if (!otherSet.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set 
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     * O(N^2)
     */
    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("intersection");
        }
        
        ISet<E> result = new UnsortedSet<>();
        for (E item : this) {
            if (otherSet.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }


    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     * O(1)
     */
    @Override 
    public Iterator<E> iterator() {
        return myCon.iterator();
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
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("remove");
        }

        int index = bsearch(item, 0, myCon.size() - 1);
        if (index >= 0) {
            myCon.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     * O(1)
     */
    @Override
    public int size() {
        return myCon.size();
    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     * O(N^2)
     */
    @Override
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("union");
        }
        
        if (otherSet instanceof SortedSet) {
            return unionSorted(otherSet);
        }
        ISet<E> result = new UnsortedSet<>();
        for (E item : this) {
            result.add(item);
        }
        for (E item : otherSet) {
            result.add(item);
        }
        return result;
    }

    /*
     * private helper for union method
     */
    private ISet<E> unionSorted(ISet<E> otherSet) {
        ArrayList<E> data = new ArrayList<>();
        ArrayList<E> temp = new ArrayList<>();
        data.addAll(myCon);
        combine(data, otherSet);
        merge(data, temp, 0, myCon.size(), data.size() - 1);
        //merge data as an ISet
        SortedSet<E> result = new SortedSet<>();
        for (E arrData : temp) {
            result.add(arrData);
        }
        return result;
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     * O(1)
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
     * O(1)
     */
    public E max() {
        if (myCon.size() == 0) {
            throw new IllegalStateException("no size");
        }

        return myCon.get(myCon.size() - 1);
    }



}