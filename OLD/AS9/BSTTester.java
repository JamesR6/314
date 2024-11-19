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
import java.util.TreeSet;

/*
Experiments:
Add 1000 random integers to BST: 
     time: 0.00213
     height: 18
      size: 1000
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
      | 1k      | 2k    | 4k     | 8k     | 16k   | 32k   | 64k   | 128k   | 256k   | 512k   | 1.024M  |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
BST   |         |       |        |        |       |       |       |        |        |        |         |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
time  | 6.02E-4 | 0.002 | 0.0032 | 0.0058 | 0.011 | 0.021 | 0.035 | 0.067  | 0.18   | 0.59   | 1.63    |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
height| 21.1    | 23.8  | 27.4   | 28.6   | 32.0  | 34.4  | 37.7  | 39.9   | 44.8   | 45.6   | 48.6    |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
size  | 1000    | 2000  | 4000   | 8000   | 15999 | 31999 | 63999 | 127998 | 255993 | 511970 | 1023876 |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
min   | 9       | 10    | 11     | 12     | 13    | 14    | 15    | 16     | 17     | 18     | 19      |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
TSet  |         |       |        |        |       |       |       |        |        |        |         |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
time  | 0.002   | 0.002 | 0.002  | 0.003  | 0.006 | 0.012 | 0.024 | 0.064  | 0.24   | 0.47   | 1.27    |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
size  | 1000    | 2000  | 4000   | 8000   | 15999 | 31999 | 63999 | 127998 | 255991 | 511970 | 1023870 |
----------+---------+-------+--------+--------+-------+-------+-------+--------+--------+--------+------
 * Both the Binary search tree and TreeSet have the same general increase
 * in time (they both double when N is doubled), but Tree set is always faster
 * signifying a lower T(N) but the same O(N)
 * 
 * Ordered List times for binary search tree
 * 1k: 0.003
 * 2k: 0.01
 * 4k: 0.037
 * 8k: 0.133
 * 16k: 0.54
 * 32k: computer crashed
 * 64k: computer crashed
 * 
 * Height for ALL tests were N - 1
 * 1k: 999
 * 2k: 1999
 * 4k: 3999
 * 8k: 7999
 * 16k: 15999
 * 32k: computer crashed
 * 64k: computer crashed
 * 
 * predictions:
 * 32k: 1.3 seconds
 * 64k: 4.0 seconds
 * 128k: 10 seconds
 * 256k: 28 seconds
 * 512k: 80 seconds
 * 
 * these predictions were made because the pattern seems to be that
 * each doubling of the amount of nodes triples the time it takes
 * to add all of said nodes. Otherwise this would still be a simple
 * O(N)
 * 
 * Ordered List times for TreeSet
 * 1k: 3.47E-4
 * 2k: 5.8E-4
 * 4k: 8.1E-4
 * 8k: 7.6E-4
 * 16k: 0.001
 * 32k: 0.002
 * 64k: 0.004
 * 
 * I predict these time will scale as an almost perfect O(N) at
 * 128k: 0.008
 * 256k: 0.016
 * 512k: 0.032
 * 
 * These times are much, much lower than the Binary search tree.
 * Both follow a similar O(N) relationship relative to the amount
 * of nodes being added in this manner but the TreeSet seems to be vastly
 * more efficient while the BST seems to be closer to 3.0x the previous
 * time
 * 
 * I think this could be caused by the underlying self balancing tree
 * used in TreeSets. This differs from our naive approach to add and remove
 * in this BST and is what causes the large hit to time when adding elements
 * in the fashion
 * 
 * 
 */

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

        public static void experiments() {
                int repeat = 10;
                int[] tests = new int[] { 1000, 2000, 4000, 8000, 16000, 32000,
                                64000, 128000, 256000, 512000, 1024000 };

                // for (int run : tests) {
                // double avgTime = 0;
                // double avgHeight = 0;
                // double avgSize = 0;
                // int n = run;
                // for (int a = 0; a < repeat; a++) {
                // Stopwatch sw = new Stopwatch();
                // sw.start();
                // Random r = new Random();
                // BinarySearchTree<Integer> b = new BinarySearchTree<>();
                // for (int i = 0; i < n; i++) {
                // b.add(r.nextInt());
                // }
                // sw.stop();
                // avgTime += sw.time();
                // avgHeight += b.height();
                // avgSize += b.size();

                // }
                // System.out.println(n + " integers");
                // System.out.println("time: " + avgTime / repeat);
                // System.out.println("height: " + avgHeight / repeat);
                // System.out.println("size: " + avgSize / repeat);
                // System.out.println();
                // }

                // for (int run : tests) {
                // double avgTime = 0;
                // double avgSize = 0;
                // int n = run;
                // for (int a = 0; a < repeat; a++) {
                // Stopwatch sw = new Stopwatch();
                // TreeSet<Integer> b = new TreeSet<>();
                // Random r = new Random();
                // sw.start();
                // for (int i = 0; i < n; i++) {
                // b.add(r.nextInt());
                // }
                // sw.stop();
                // avgTime += sw.time();
                // avgSize += b.size();

                // }
                // System.out.println(n + " integers");
                // System.out.println("time: " + avgTime / repeat);
                // System.out.println("size: " + avgSize / repeat);
                // System.out.println();
                // }

                tests = new int[] { 1000, 2000, 4000, 8000, 16000, 32000, 64000 };

                // for (int t : tests) {
                //         int listSize = t;
                //         int[] ordered = new int[listSize];
                //         for (int b = 1; b <= listSize; b++) {
                //                 ordered[b - 1] = b;
                //         }

                //         double avgTime = 0;
                //         for (int r = 0; r < repeat; r++) {
                //                 BinarySearchTree<Integer> thTree = new BinarySearchTree<>();
                //                 Stopwatch sw = new Stopwatch();
                //                 sw.start();
                //                 for (int i : ordered) {
                //                         thTree.add(i);
                //                 }
                //                 sw.stop();
                //                 avgTime += sw.time();
                //         }
                //         System.out.println(avgTime / repeat);
                // }
                
                for (int t : tests) {
                        int listSize = t;
                        int[] ordered = new int[listSize];
                        for (int b = 1; b <= listSize; b++) {
                                ordered[b - 1] = b;
                        }

                        double avgTime = 0;
                        for (int r = 0; r < repeat; r++) {
                                TreeSet<Integer> orderSet = new TreeSet<>();
                                Stopwatch sw = new Stopwatch();
                                sw.start();
                                for (int i : ordered) {
                                        orderSet.add(i);
                                }
                                sw.stop();
                                avgTime += sw.time();
                        }
                        System.out.println(avgTime / repeat);
                }
        }

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

                //experiments();
        }

        private static void showTestResults(boolean passed, int testNum) {
                if (passed) {
                        System.out.println("Test " + testNum + " passed.");
                } else {
                        System.out.println("TEST " + testNum + " FAILED.");
                }
        }
}
