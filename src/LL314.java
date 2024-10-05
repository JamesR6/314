/*
 * Student information for assignment: 
 * On my honor, <NAME>, this programming assignment is my own work 
 * and I have not provided this code to any other student. 
 * UTEID: 
 * email address: 
 * Number of slip days I am using:
 */

 import java.util.Iterator;

 public class LL314<E> implements IList<E> {
     // CS314 students. Add you instance variables here.
     // You decide what instance variables to use.
     // Must adhere to assignment requirements. 
     // No ArrayLists or Java LinkedLists.
 
     // CS314 students, add constructors here:
 
     // CS314 students, add methods here:
 
     /**
      * add item to the front of the list. <br>
      * pre: item != null <br>
      * post: size() = old size() + 1, get(0) = item
      * 
      * @param item the data to add to the front of this list
      */
     public void addFirst(E item) {
     }
 
     /**
      * add item to the end of the list. <br>
      * pre: item != null <br>
      * post: size() = old size() + 1, get(size() -1) = item
      * 
      * @param item the data to add to the end of this list
      */
     public void addLast(E item) {
     }
 
     /**
      * remove and return the first element of this list. <br>
      * pre: size() > 0 <br>
      * post: size() = old size() - 1
      * 
      * @return the old first element of this list
      */
     public E removeFirst() {
         return null;
     }
 
     /**
      * remove and return the last element of this list. <br>
      * pre: size() > 0 <br>
      * post: size() = old size() - 1
      * 
      * @return the old last element of this list
      */
     public E removeLast() {
         return null;
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
          * <br>pre: none
          * <br>post: data = null, next = null, prev = null
          * <br>O(1)
          */
         public DoubleListNode() {
             this(null, null, null);
         }
 
         /**
          * create a DoubleListNode that holds the specified data
          * and refers to the specified next and previous elements.
          * <br>pre: none
          * <br>post: this.data = data, this.next = next, this.prev = prev
          * <br>O(1)
          * @param prev the previous node
          * @param data the  data this DoubleListNode should hold
          * @param next the next node
          */
         public DoubleListNode(DoubleListNode<E> prev, E data, DoubleListNode<E> next) {
             this.prev = prev;
             this.data = data;
             this.next = next;    
         }
     }
 }