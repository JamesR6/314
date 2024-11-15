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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

        /**
         * The main method runs the tests.
         * 
         * @param args Not used
         */
        public static void main(String[] args) {
                BinarySearchTree<Integer> tree = new BinarySearchTree<>();
                int value = 0;

                // add
                tree.add(5);
                tree.add(3);
                tree.add(7);
                tree.add(6);
                tree.add(2);
                tree.add(4);
                int intExpected = 6;
                showTestResults(tree.size() == intExpected, 1);

                tree.add(5);
                showTestResults(tree.size() == intExpected, 2);

                // remove
                tree.remove(5);
                intExpected = 5;
                showTestResults(tree.size() == intExpected, 3);

                tree.remove(400);
                showTestResults(tree.size() == intExpected, 4);

                // isPresent
                showTestResults(!tree.isPresent(intExpected), 5);

                intExpected = 6;
                showTestResults(tree.isPresent(intExpected), 6);

                // size tested through other tests (add, remove, etc)

                // height
                intExpected = 2;
                showTestResults(tree.height() == intExpected, 7);
                while (tree.size() != 0) {
                        tree.remove(tree.get(0));
                }
                intExpected = -1;
                showTestResults(tree.height() == intExpected, 8);

                // getAll
                ArrayList<Integer> listExpected = new ArrayList<>();
                for (int a = 0; a < 11; a++) {
                        tree.iterativeAdd(a);
                        listExpected.add(a);
                }
                showTestResults(tree.getAll().equals(listExpected), 9);

                tree.remove(6);
                listExpected.remove(6);
                showTestResults(tree.getAll().equals(listExpected), 10);

                // max
                intExpected = 10;
                showTestResults(tree.max() == intExpected, 11);
                tree.remove(10);
                intExpected = 9;
                showTestResults(tree.max() == intExpected, 12);

                
                // min
                tree.iterativeAdd(-60);
                intExpected = -60;
                showTestResults(tree.min() == intExpected, 13);
                
                tree.remove(-60);
                intExpected = 0;
                showTestResults(tree.min() == intExpected, 14);
                
                // iterativeAdd tested through other tests (getAll, min, etc)
                
                // get
                intExpected = 3;
                showTestResults(tree.get(intExpected) == intExpected, 15);
                
                intExpected = 7;
                showTestResults(tree.get(intExpected) == intExpected, 16);

                // getAllLessThan
                listExpected = new ArrayList<>();
                listExpected.add(0);
                listExpected.add(1);
                intExpected = 2;
                showTestResults(tree.getAllLessThan(intExpected).equals(listExpected), 17);

                listExpected.add(2);
                intExpected = 3;
                showTestResults(tree.getAllLessThan(intExpected).equals(listExpected), 18);

                // getAllGreaterThan
                listExpected = new ArrayList<>();
                listExpected.add(9);
                value = 8;
                showTestResults(tree.getAllGreaterThan(value).equals(listExpected), 19);

                value = 7;
                listExpected.remove(0);
                listExpected.add(8);
                listExpected.add(9);
                showTestResults(tree.getAllGreaterThan(value).equals(listExpected), 20);

                // numNodesAtDepth
                intExpected = 1;
                value = 5;
                showTestResults(tree.numNodesAtDepth(value) == intExpected, 21);
                
                value = 8;
                showTestResults(tree.numNodesAtDepth(value) == intExpected, 22);
        }

        private static void showTestResults(boolean passed, int testNum) {
                if (passed) {
                        System.out.println("Test " + testNum + " passed.");
                } else {
                        System.out.println("TEST " + testNum + " FAILED.");
                }
        }
}
