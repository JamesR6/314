/*
 * Student information for assignment: 
 * On my honor, JP Reeves, this programming assignment is my own work 
 * and I have not provided this code to any other student. 
 * UTEID: jsr3699
 * email address: jpascualsr06@gmail.com
 * Number of slip days I am using: 0
 */

/* 
 * TODO: test cases
 * TODO: Style
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LL314<E> implements IList<E> {
    // CS314 students. Add you instance variables here.
    // You decide what instance variables to use.
    // Must adhere to assignment requirements.
    // No ArrayLists or Java LinkedLists.
    private DoubleListNode<E> HEADER;
    private int size;

    // CS314 students, add constructors here:

    /*
     * return a new empty linked list
     * O(1)
     */
    public LL314() {
        HEADER = new DoubleListNode<E>();
        HEADER.next = HEADER;
        HEADER.prev = HEADER;
    }

    // CS314 students, add methods here:

    /*
     * adds item to the end of this linked list
     * pre: item != null
     * post: item added and size increased
     * O(1)
     */
    @Override
    public void add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("add: data can not be null");
        }

        DoubleListNode<E> newNode = new DoubleListNode<>(HEADER.prev, item, HEADER);
        HEADER.prev.next = newNode;
        HEADER.prev = newNode;
        size++;
    }

    /*
     * inserts item so that it takes the index pos
     * pre: item != null, pos in bounds 0 to size - 1
     * post: item added at pos and size increased
     * O(N)
     */
    @Override
    public void insert(int pos, E item) {
        if (pos > size || pos < 0) {
            throw new IndexOutOfBoundsException("insert: position out of bounds");
        }

        //add to end for efficiency
        if (pos == size) {
            add(item);
        } else {
            //find place to insert and manipulate pointers
            DoubleListNode<E> tracer = moveToIndex(pos);
            DoubleListNode<E> newNode = new DoubleListNode<>(tracer.prev, item, tracer);
            newNode.prev.next = newNode;
            newNode.next.prev = newNode;
            size++;
        }
    }

    /*
     * replaces data at index pos with data item
     * pre: item != null, pos in bounds 0 to size - 1
     * post: return the data that was replaced
     * O(N)
     */
    @Override
    public E set(int pos, E item) {
        if (pos >= size || pos < 0 || item == null) {
            throw new IndexOutOfBoundsException("set: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);

        E result = tracer.data;
        tracer.data = item;
        return result;
    }

    /*
     * return data from index pos
     * pre: pos in bounds 0 to size - 1
     * post: return data from index pos
     * O(N)
     */
    @Override
    public E get(int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("get: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);
        return tracer.data;
    }

    /*
     * removes index pos from this linked list
     * pre: pos in bounds 0 to size - 1
     * post: size decremented and index pos removed, return data of removed item
     * O(N)
     */
    @Override
    public E remove(int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("remove: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);

        tracer.prev.next = tracer.next;
        tracer.next.prev = tracer.prev;

        size--;
        return tracer.data;
    }

    /*
     * removes the first instance of obj
     * pre: none
     * post: first instance of obj removed, return true if successful,
     * false if no item to remove was found
     * O(N)
     */
    @Override
    public boolean remove(E obj) {
        DoubleListNode<E> tracer = HEADER.next;
        for (int i = 0; i < size; i++) {
            if (tracer.data.equals(obj)) {
                //manipulate pointers around element when obj is found
                tracer.prev.next = tracer.next;
                tracer.next.prev = tracer.prev;
                size--;
                return true;
            }
            tracer = tracer.next;
        }
        return false;
    }

    /*
     * return a new LL314 object of index start to stop - 1
     * pre: both start and stop in bounds 0 to size - 1
     * post: return a new LL314 object of index start to stop - 1
     * O(N)
     */
    @Override
    public IList<E> getSubList(int start, int stop) {
        if (start >= size || start < 0 || stop >= size || stop < 0 || stop < start) {
            throw new IllegalArgumentException("getSubList: arguements must be in bounds " +
                    "and stop > start");
        }

        LL314<E> result = new LL314<>();
        DoubleListNode<E> tracer = moveToIndex(start);

        for (int i = start; i < stop; i++) {
            result.add(tracer.data);
            tracer = tracer.next;
        }
        return result;
    }

    /*
     * return size of linked list
     * O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * return the first index of item
     * pre: none
     * post: return the first index of item
     * O(N)
     */
    @Override
    public int indexOf(E item) {
        DoubleListNode<E> tracer = HEADER.next;
        for (int i = 0; i < size; i++) {
            if (tracer.data.equals(item)) {
                return i;
            }
            tracer = tracer.next;
        }
        return -1;
    }

    /*
     * return the first index of item starting at position pos
     * pre: pos in bounds 0 to size - 1
     * post: return the first index of item starting at pos
     * O(N)
     */
    @Override
    public int indexOf(E item, int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("indexOf: position out of bounds");
        }

        //tracer moves to start at index pos
        DoubleListNode<E> tracer = moveToIndex(pos);

        for (int i = pos; i < size; i++) {
            if (tracer.data.equals(item)) {
                return i;
            }
            tracer = tracer.next;
        }
        return -1;
    }

    /*
     * removes all elements from linked list
     * pre: none
     * post: no elements after HEADER
     * O(1)
     */
    @Override
    public void makeEmpty() {
        HEADER.next = HEADER;
        HEADER.prev = HEADER;
        size = 0;
    }

    /*
     * return a string representation of linked list
     * format: all data between square brackets with a comma between
     * each item and a space after each comma
     * O(N)
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        Iterator<E> lit = iterator();
        StringBuilder result = new StringBuilder("[" + lit.next());

        //add every element with formatting
        while (lit.hasNext()) {
            result.append(", " + lit.next());
        }
        result.append("]");

        return result.toString();
    }

    /*
     * removes elements from start to stop - 1 from linked list
     * pre: start and stop in bounds 0 to size - 1
     * post: linked list no longer includes the elements from start to stop - 1
     * O(N)
     */
    @Override
    public void removeRange(int start, int stop) {
        if (start < 0 || start >= size || stop < 0 || stop >= size || stop < start) {
            throw new IndexOutOfBoundsException("removeRange: bounds out of bounds");
        }

        DoubleListNode<E> first = moveToIndex(start);
        DoubleListNode<E> last = moveToIndex(stop);

        //manipulate pointers around the removed range
        first.prev.next = last;
        last.prev = first.prev;

        size -= (stop - start);
    }

    /*
     * compares this linked list with another object
     * if other is not an IList, they are not equal
     * if both ILists are length 0, they are equal regardless of data type
     * both ILists must have the same elements in the same order to match
     * pre: none
     * O(N)
     */
    @Override
    public boolean equals(Object other) {
        //incomparable lists check
        if (other == null || !(other instanceof IList)) {
            return false;
        }

        //TODO iterator efficiency

        //cast to IList after checking instanceof
        IList<E> o = (IList<E>) other;

        if (size() != o.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!o.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }

    /*
     * return an iterator object for this linked list
     * O(1)
     */
    @Override
    public Iterator<E> iterator() {
        return new LLIterator();
    }

    /*
     * iterator class for LL314
     */
    private class LLIterator implements Iterator<E> {

        private DoubleListNode<E> nodeWithNext;
        private boolean removeOk;

        /*
         * return a new LLIterator
         * O(1)
         */
        public LLIterator() {
            nodeWithNext = HEADER.next;
            removeOk = false;
        }

        /*
         * return true if there is a next element to read, false otherwise
         * O(1)
         */
        @Override
        public boolean hasNext() {
            return nodeWithNext.data != null;
        }

        /*
         * 'moves' the iterator down one fencepost and returns the data of
         * the node
         * pre: hasNext() = true
         * post: returns the next node's data and iterates one element
         * O(1)
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next: no more elements");
            }

            E result = nodeWithNext.data;
            nodeWithNext = nodeWithNext.next;
            removeOk = true;
            return result;
        }

        /*
         * removes the node that most recently was returned by next
         * pre: removeOk = true, removeIndex >= 0
         * post: remove the node most recently returned by next
         * O(1)
         */
        @Override
        public void remove() {
            if (!removeOk) {
                throw new IllegalStateException("remove method");
            }

            //We know this node exists because removeOk is true
            DoubleListNode<E> rmNode = nodeWithNext.prev;

            //manipulate pointers and adjust variables
            rmNode.prev.next = rmNode.next;
            rmNode.next.prev = rmNode.prev;
            size--;
            removeOk = false;
        }
    }

    /**
     * add item to the front of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(0) = item
     * 
     * @param item the data to add to the front of this list
     * O(1)
     */
    public void addFirst(E item) {
        DoubleListNode<E> newNode = new DoubleListNode<>(HEADER, item, HEADER.next);
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }

    /**
     * add item to the end of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(size() -1) = item
     * 
     * @param item the data to add to the end of this list
     * O(1)
     */
    public void addLast(E item) {
        add(item);
    }

    /**
     * remove and return the first element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * 
     * @return the old first element of this list
     * O(1)
     */
    public E removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("removeFirst: no element to remove");
        }

        E result = HEADER.next.data;
        remove(0);
        return result;
    }

    /**
     * remove and return the last element of this list. <br>
     * pre: size() > 0 <br>
     * post: size() = old size() - 1
     * 
     * @return the old last element of this list
     * O(1)
     */
    public E removeLast() {
        if (size == 0) {
            throw new IllegalStateException("removeLast: no element to remove");
        }

        E result = HEADER.prev.data;
        remove(size - 1);
        return result;
    }

    /*
     * helper method
     * return the DoubleListNode at position pos
     * O(N) (note: O(N/2))
     */
    private DoubleListNode<E> moveToIndex(int pos) {
        if (pos < size / 2) {
            DoubleListNode<E> tracer = HEADER.next;
            for (int i = 0; i < pos; i++) {
                tracer = tracer.next;
            }
            return tracer;
        } else {
            DoubleListNode<E> tracer = HEADER.prev;
            for (int i = size - 1; i > pos; i--) {
                tracer = tracer.prev;
            }
            return tracer;
        }

    }

    /**
     * A class that represents a node to be used in a linked list.
     * These nodes are doubly linked. All methods are O(1).
     *
     * @author Mike Scott
     * @version 9/25/2023
     */
    private static class DoubleListNode<E> {

        // the data to store in this node
        private E data;

        // the link to the next node (presumably in a list)
        private DoubleListNode<E> next;

        // the reference to the previous node (presumably in a list)
        private DoubleListNode<E> prev;

        /**
         * default constructor.
         * <br>
         * pre: none
         * <br>
         * post: data = null, next = null, prev = null
         * <br>
         * O(1)
         */
        public DoubleListNode() {
            this(null, null, null);
        }

        /**
         * create a DoubleListNode that holds the specified data
         * and refers to the specified next and previous elements.
         * <br>
         * pre: none
         * <br>
         * post: this.data = data, this.next = next, this.prev = prev
         * <br>
         * O(1)
         * 
         * @param prev the previous node
         * @param data the data this DoubleListNode should hold
         * @param next the next node
         */
        public DoubleListNode(DoubleListNode<E> prev, E data, DoubleListNode<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

}