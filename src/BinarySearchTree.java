/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, JP Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  TA name: Eliza
 *  Number of slip days I am using: 0
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    private int N;
 
    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    public boolean add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("add");
        }

        //if this is a new tree, make root node
        if (root == null) {
            root = new BSTNode<>(value);
            N++;
            return true;
        }

        return addRecur(value, root, root);
    }

    /*
     * recursive helper for add
     */
    private boolean addRecur(E value, BSTNode<E> here, BSTNode<E> prev) {
        //base cases
        if (here == null) {
            //insert new node left or right of previous
            if (prev.data.compareTo(value) > 0) {
                prev.left = new BSTNode<>(value);
            } else {
                prev.right = new BSTNode<>(value);
            }
            N++;
            return true;
        }
        if (here.data.equals(value)) {
            return false;
        }

        //recursive step
        if (here.data.compareTo(value) > 0) {
            return addRecur(value, here.left, here);
        }
        return addRecur(value, here.right, here);
    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException("remove");
        }

        //remove method from lecutre 11/01/2024
        int oldSize = N;
        if (isPresent(value)) {
            N--;
        }
        root = removeHelp(value, root);
        return oldSize != N;
    }

    /*
     * remove method helper from lecture 11/01/2024
     */
    private BSTNode<E> removeHelp(E val, BSTNode<E> node) {
        //Base case, value does not exist
        if (node == null) {
            return null;
        }
        int dir = val.compareTo(node.data);
        if (dir < 0) {
            //value is in the left subtree
            node.left = removeHelp(val, node.left);
        } else if (dir > 0) {
            //value is in the right subtree
            node.right = removeHelp(val, node.right);
        } else {
            //node is found
            if (node.isLeaf()) {
                //leaf node
                return null;
            } else if (node.left == null) {
                //only right child
                return node.right;
            } else if (node.right == null) {
                //only left child
                return node.left;
            } else {
                //two children
                node.data = maxHelp(node.left);

                //remove duplicate
                node.left = removeHelp(node.data, node.left);
            }
        }
        return node;
    }

    private E maxHelp(BSTNode<E> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }


    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        if (value == null) {
            throw new IllegalArgumentException("ispresent");
        }
        return isPresentRecur(value, root);
    }

     /*
     * recursive helper for isPresent
     */
    private boolean isPresentRecur(E value, BSTNode<E> here) {
        //base cases
        if (here == null) {
            return false;
        }
        if (here.data.equals(value)) {
            return true;
        }
        
        //recursive step
        if (here.data.compareTo(value) > 0) {
            return isPresentRecur(value, here.left);
        } else {
            return isPresentRecur(value, here.right);
        }
    }


    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return N;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        //if tree is empty
        if (N == 0) {
            return -1;
        }
        
        return heightRecur(root);
    }

    private int heightRecur(BSTNode<E> here) {
        //base cases
        if (here == null || here.isLeaf() ) {
            return 0;
        }

        //recursive step
        return 1 + Math.max(heightRecur(here.right), heightRecur(here.left));
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order.
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        ArrayList<E> result = new ArrayList<>();
        return getAllRecur(result, root);
    }

    /*
     * recursive helper for getAll
     */
    private List<E> getAllRecur(ArrayList<E> result, BSTNode<E> here) {
        //inOrder modification
        if (here != null) {
            getAllRecur(result, here.left);
            result.add(here.data);
            getAllRecur(result, here.right);
        }
        return result;
    }


    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max() {
        if (N == 0) {
            throw new IllegalStateException("no size");
        }

        BSTNode<E> finder = root;
        while (finder.right != null) {
            finder = finder.right;
        }
        return finder.data;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
        if (N == 0) {
            throw new IllegalStateException("no size");
        }

        BSTNode<E> finder = root;
        while(finder.left != null) {
            finder = finder.left;
        }
        return finder.data;
    }

    /**
     * An add method that implements the add algorithm iteratively 
     * instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, 
     * otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, 
     * false otherwise.
     */
    public boolean iterativeAdd(E data) {
        if (data == null) {
            throw new IllegalArgumentException("add");
        }

        //if this is a new tree, make root node
        if (root == null) {
            root = new BSTNode<>(data);
            N++;
            return true;
        }

        BSTNode<E> finder = root;
        while (true) {
            //faux base case
            if (finder.data.compareTo(data) == 0) {
                return false;
            }
            //if place to insert is found, insert new node, otherwise travel in that direction
            if (finder.data.compareTo(data) > 0) {
                if (finder.left == null) {
                    connect(finder, new BSTNode<>(data), false);
                    return true;
                }
                finder = finder.left;
            } else {
                if (finder.right == null) {
                    connect(finder, new BSTNode<>(data), true);
                    return true;
                }
                finder = finder.right;
            }
        }
    }

    /*
     * helper method for iterative add
     * connects first node to second node with given direction
     */
    private void connect(BSTNode<E> first, BSTNode<E> second, boolean right) {
        if (right) {
            first.right = second;
        } else {
            first.left = second;
        }
        N++;
    }


    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        if (kth < 0 || kth >= N) {
            throw new IndexOutOfBoundsException("get");
        }
        return getRecur(kth, root, new int[]{0});
    }

    /*
     * recursive helper for get
     */
    private E getRecur(int kth, BSTNode<E> here, int[] counter) {
        //heavily modified inOrder traversal
        if (here != null) {
            //left
            E left = getRecur(kth, here.left, counter);
            if (left != null) {
                return left;
            }

            //check
            if (counter[0] == kth) {
                return here.data;
            }
            counter[0]++;

            //right
            return getRecur(kth, here.right, counter);
        }
        return null;
    }

    /**
     * Return a List with all values in this Binary Search Tree 
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than 
     * the parameter value. If there are no values in this tree less 
     * than value return an empty list. The elements of the list are 
     * in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        ArrayList<E> result = new ArrayList<>();
        return lessThanRecur(value, result, root);
    }

    /*
     * recursive helper for getAllLessThan
     */
    private List<E> lessThanRecur(E value, ArrayList<E> result, BSTNode<E> here) {
        //heavily modified inOrder traversal
        if (here != null) {
            //left
            lessThanRecur(value, result, here.left);

            //check
            //once a larger value is hit, immediately return
            if (here.data.compareTo(value) >= 0) {
                return result;
            }
            result.add(here.data);

            //right
            return lessThanRecur(value, result, here.right);
        }
        return result;
    }


    /**
     * Return a List with all values in this Binary Search Tree 
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     *  than the parameter value. If there are no values in this tree
     * greater than value return an empty list. 
     * The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        ArrayList<E> result = new ArrayList<>();
        return greaterThanRecur(value, result, root);
    }

    /*
     * recursive helper for getAllGreaterThan
     */
    private List<E> greaterThanRecur(E value, ArrayList<E> result, BSTNode<E> here) {
        //heavily modified inOrder traversal
        if (here != null) {
            //left
            greaterThanRecur(value, result, here.left);
            
            //check
            if (here.data.compareTo(value) > 0) {
                result.add(here.data);
            }

            //right
            return greaterThanRecur(value, result, here.right);
        }
        return result;
    }



    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        if (d > height()) {
            return 0;
        }

        return nodesDepthRecur(d, 0, root);
    }
    
    /*
     * recursive helper for numNodesAtDepth
     */
    private int nodesDepthRecur(int target, int depth, BSTNode<E> here) {
        //base cases
        if (here == null) {
            return 0;
        }
        if (depth == target) {
            return 1;
        }

        //recursive step
        return nodesDepthRecur(target, depth + 1, here.left) + 
               nodesDepthRecur(target, depth + 1, here.right);

    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.right, spaces + "  ");
            System.out.println(spaces + n.data);
            printTree(n.left, spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        private BSTNode() {
            this(null);
        }

        private BSTNode(E initValue) {
            this(null, initValue, null);
        }

        private BSTNode(BSTNode<E> initLeft,
                E initValue,
                BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
