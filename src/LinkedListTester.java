/*  Student information for assignment:
 *
 *  On my honor, JP Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: JP Reeves
 *  email address: jpascualsr06@gmail.com
 *  UTEID: jsr3699
 *  Number of slip days used on this assignment: 1
 */

/* Experiment results. CS314 students, place your experiment
 *  results here:
 * 
 *   Methods faster with LL314:
 * Adding at end
 * Adding at front
 * Removing from front
 * 
 *   Methods faster with java ArrayList:
 * Getting random
 * Getting all using iterator
 * Getting all using get method
 * 
 *                        | LL314  | ArrayLists
 * Adding at end          |  O(N)  |   O(N)
 * Adding at front        |  O(N)  |   O(N^2)
 * Removing from front    |  O(N)  |   O(N^2)
 * Getting random         | O(N^2) |   O(N)
 * Getting all using it   |  O(N)  |   O(N)
 * Getting all using get  | O(N^2) |   O(N)
 * 
 * I decided these were the most likely Big Os of each
 * method because all of them followed a pattern of
 * doubling time when N is doubled (indicating O(N)) or
 * quadrupling time when N is doubled (indicating O(N^2))
 */

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;

public class LinkedListTester {

    public static void main(String[] args) {

        // CS314 students. Add your tests here:

        /*
         * LL314 compared to equivalent methods in ArrayLists because
         * we are given that ArrayList works correctly
         */

        ArrayList<Integer> expected = new ArrayList<>();
        LL314<Integer> actual = new LL314<>();
        int testLen = 10;

        // add method
        for (int a = 0; a < testLen; a++) {
            expected.add(a);
            actual.add(a);
        }
        test(actual, expected, "add test 1");
        expected.add(-1);
        actual.add(-1);
        test(actual, expected, "add test 2");

        // insert method
        actual.insert(11, 99);
        expected.add(99);
        test(actual, expected, "insert test 1");
        expected.add(0, 5);
        actual.insert(0, 5);
        test(actual, expected, "insert test 2");

        // set method
        actual.set(4, 2020);
        expected.set(4, 2020);
        test(actual, expected, "set test 1");
        actual.set(0, 0);
        expected.set(0, 0);
        test(actual, expected, "set test 1");

        // get method
        expected = new ArrayList<>();
        actual = new LL314<>();
        for (int a = 0; a < testLen; a++) {
            expected.add(a);
            actual.add(a);
        }
        valueTest(expected.get(3), expected.get(3), "get test 1");
        valueTest(expected.get(0), expected.get(0), "get test 2");

        // remove method
        expected.remove(4);
        actual.remove(4);
        test(actual, expected, "remove test 1");
        expected.remove(0);
        actual.remove(0);
        test(actual, expected, "remove test 2");

        // remove object method
        expected.remove((Integer) 100);
        actual.remove((Integer) 100);
        test(actual, expected, "remove object test 1");
        expected.remove(2);
        actual.remove(2);
        test(actual, expected, "remove object test 2");

        // getSubList
        IList<Integer> babyLL = actual.getSubList(0, 4);
        List<Integer> babyAL = expected.subList(0, 4);
        valueTest(babyLL.toString(), babyAL.toString(), "subList test 1");
        babyLL = actual.getSubList(5, 5);
        babyAL = expected.subList(5, 5);
        valueTest(babyLL.toString(), babyAL.toString(), "subList test 2");

        // size
        valueTest(actual.size(), expected.size(), "size test 1");
        for (int a = 0; a < testLen; a++) {
            expected.add(a);
            actual.add(a);
        }
        valueTest(actual.size(), expected.size(), "size test 2");

        // indexOf
        valueTest(actual.indexOf(2000), expected.indexOf(2000), "indexOf test 1");
        valueTest(actual.indexOf(4), expected.indexOf(4), "indexOf test 2");

        // indexOf with start pos
        expected = new ArrayList<>();
        actual = new LL314<>();
        for (int a = 0; a < testLen; a++) {
            expected.add(a);
            actual.add(a);
        }
        for (int a = testLen; a >= 0; a--) {
            expected.add(a);
            actual.add(a);
        }
        valueTest(actual.indexOf(2000, 3), expected.indexOf(2000), "indexOf w/index test 1");
        for (int a = 0; a < 5; a++) {
            expected.set(a, 0);
        }
        valueTest(actual.indexOf(4, 5), expected.indexOf(4), "indexOf w/index test 2");

        // makeEmpty
        LL314<Integer> empty = new LL314<>();
        actual.makeEmpty();
        valueTest(actual.toString(), empty.toString(), "makeEmpty test 1");
        // done again to test running the method on an already empty linked list
        actual.makeEmpty();
        valueTest(actual.toString(), empty.toString(), "makeEmpty test 2");

        // toString
        // tested through other methods

        // removeRange
        expected = new ArrayList<>();
        actual = new LL314<>();
        for (int a = testLen; a >= 0; a--) {
            expected.add(a);
            actual.add(a);
        }
        actual.removeRange(4, 6);
        for (int r = 0; r < 2; r++) {
            expected.remove(4);
        }
        test(actual, expected, "removeRange test 1");
        actual.removeRange(2, 2);
        test(actual, expected, "removeRange test 2");

        // equals
        LL314<Integer> eq1 = new LL314<>();
        LL314<Integer> eq2 = new LL314<>();
        LL314<String> eq3 = new LL314<>();
        boolTest(eq1.equals(eq3), "equals test 1");
        for (int a = testLen; a >= 0; a--) {
            eq1.add(a);
            eq2.add(a);
        }
        boolTest(eq1.equals(eq2), "equals test 2");

        // iterator
        actual = new LL314<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);

        StringBuilder result = new StringBuilder();
        Iterator it = actual.iterator();
        while (it.hasNext()) {
            result.append(it.next());
        }
        valueTest(result.toString(), "123", "iterator test 1");
        actual = new LL314<>();
        it = actual.iterator();
        boolTest(!it.hasNext(), "iterator test 2");

        // addFirst
        expected = new ArrayList<>();
        actual = new LL314<>();
        for (int a = 0; a < testLen; a++) {
            actual.addFirst(a);
        }
        for (int b = testLen - 1; b >= 0; b--) {
            expected.add(b);
        }
        test(actual, expected, "addFirst test 1");
        expected = new ArrayList<>();
        actual = new LL314<>();
        expected.add(4);
        actual.addFirst(4);
        test(actual, expected, "addFirst test 2");

        // addLast, same funciton as add
        expected.add(101202303);
        actual.addLast(101202303);
        test(actual, expected, "addLast test 1");
        expected.add(0);
        actual.addLast(0);
        test(actual, expected, "addLast test 2");

        // removeFirst
        actual.removeFirst();
        List<Integer> exp = expected.subList(1, expected.size());
        valueTest(actual.toString(), exp.toString(), "removeFirst test 1");
        while (actual.size() > 0) {
            actual.removeFirst();
        }
        valueTest(actual.toString(), "[]", "removeFirst test 2");

        // removeLast
        expected = new ArrayList<>();
        actual = new LL314<>();
        for (int a = 0; a < testLen; a++) {
            expected.add(a);
            actual.add(a);
        }
        expected.remove(expected.size() - 1);
        actual.removeLast();
        test(actual, expected, "removeLast test 1");
        while (actual.size() > 0) {
            actual.removeLast();
            expected.remove(expected.size() - 1);
        }
        test(actual, expected, "removeLast test 2");

    }

    private static void boolTest(boolean boo, String method) {
        String result = boo ? "PASSED" : "FAILED";
        System.out.println(method + " " + result);
    }

    private static void test(LL314<Integer> actual, ArrayList<Integer> expected, String method) {
        String result = (expected.toString().equals(actual.toString())) ? "PASSED" : "FAILED";
        System.out.println(method + " " + result);
    }

    private static void valueTest(Object act, Object exp, String method) {
        String result = (act.equals(exp)) ? "PASSED" : "FAILED";
        System.out.println(method + " " + result);
    }
}