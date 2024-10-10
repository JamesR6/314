/*
 * Student information for assignment: 
 * On my honor, JP Reeves, this programming assignment is my own work 
 * and I have not provided this code to any other student. 
 * UTEID: jsr3699
 * email address: jpascualsr06@gmail.com
 * Number of slip days I am using: 0
 */

/* 
 * TODO: write the code
 * TODO: check everything (adjust size, syntax, logic, test cases)
 * TODO: Test the code
 * TODO: BIG O of each method
 * TODO: Method Headers
 * TODO: Style
 * TODO: use iterator for indexOf stuff??
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
    public LL314() {
        HEADER = new DoubleListNode<E>();
        HEADER.next = HEADER;
        HEADER.prev = HEADER;
    }

    private DoubleListNode<E> moveToIndex(int pos) {
        DoubleListNode<E> tracer = HEADER.next;
        for (int i = 0; i < pos; i++) {
            tracer = tracer.next;
        }
        return tracer;
    }

    // CS314 students, add methods here:

    @Override
    public void add(E item) {
        DoubleListNode<E> newNode = new DoubleListNode<>(HEADER.prev, item, HEADER);
        HEADER.prev.next = newNode;
        HEADER.prev = newNode;
        size++;
    }

    //TODO Should you be able to insert at the end
    @Override
    public void insert(int pos, E item) {
        if (pos > size || pos < 0) {
            throw new IndexOutOfBoundsException("insert: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);
        DoubleListNode<E> newNode = new DoubleListNode<>(tracer.prev, item, tracer);
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    @Override
    public E set(int pos, E item) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("set: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);

        E result = tracer.data;
        tracer.data = item;
        return result;
    }

    @Override
    public E get(int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("get: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);
        return tracer.data;
    }

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
        // TODO garbage collector
    }

    @Override
    public boolean remove(E obj) {
        // TODO efficiency
        DoubleListNode<E> tracer = HEADER.next;
        for (int i = 0; i < size; i++) {
            if (tracer.data.equals(obj)) {
                remove(i);
                return true;
            }
            tracer = tracer.next;
        }
        return false;
    }

    @Override
    public IList<E> getSubList(int start, int stop) {
        if (start >= size || start < 0 || stop >= size || stop < 0 || stop < start) {
            throw new IllegalArgumentException("getSubList: arguements must be in bounds " +
                    "and stop > start");
        }
        LL314<E> result = new LL314<>();
        DoubleListNode<E> tracer = moveToIndex(start);

        for (int i = start; i <= stop; i++) {
            result.add(tracer.data);
            tracer = tracer.next;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

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

    @Override
    public int indexOf(E item, int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("indexOf: position out of bounds");
        }

        DoubleListNode<E> tracer = moveToIndex(pos);

        for (int i = pos; i < size; i++) {
            if (tracer.data.equals(item)) {
                return i;
            }
            tracer = tracer.next;
        }
        return -1;
    }

    @Override
    public void makeEmpty() {
        // TODO WHAT
        HEADER.next = HEADER;
        HEADER.prev = HEADER;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        Iterator<E> lit = iterator();
        StringBuilder result = new StringBuilder("[" + lit.next());
        while (lit.hasNext()) {
            result.append(", " + lit.next());
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public void removeRange(int start, int stop) {
        if (start < 0 || start >= size || stop < 0 || stop >= size || stop < start) {
            throw new IndexOutOfBoundsException("removeRange: bounds out of bounds");
        }

        // TODO garbage collector
        DoubleListNode<E> first = moveToIndex(start);
        DoubleListNode<E> last = moveToIndex(stop);
        
        first.prev.next = last.next;
        last.next.prev = first.prev;

        size -= (stop - start + 1);
    }

    // -------------------------------------------------------------
    @Override
    public Iterator<E> iterator() {
        return new LLIterator();
    }

    private class LLIterator implements Iterator<E> {

        private DoubleListNode<E> nodeWithNext;
        private int removeIndex;
        private boolean removeOk;

        public LLIterator() {
            nodeWithNext = HEADER;
            removeIndex = -1;
            removeOk = false;
        }

        @Override
        public boolean hasNext() {
            return nodeWithNext.next.data != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next: no more elements");
            }

            nodeWithNext = nodeWithNext.next;
            removeIndex++;
            removeOk = true;
            return nodeWithNext.data;
        }

        @Override
        public void remove() {
            if (!removeOk || removeIndex == -1) {
                throw new IllegalStateException("remove method");
            }

            LL314.this.remove(removeIndex);
            removeIndex--;
            removeOk = false;
        }
    }

    // ---------------------------------------------------------------------------
    /**
     * add item to the front of the list. <br>
     * pre: item != null <br>
     * post: size() = old size() + 1, get(0) = item
     * 
     * @param item the data to add to the front of this list
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
     */
    public E removeLast() {
        if (size == 0) {
            throw new IllegalStateException("removeLast: no element to remove");
        }
        
        E result = HEADER.prev.data;
        remove(size - 1);
        return result;
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