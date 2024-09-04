import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// CodeCamp.java - CS314 Assignment 1 - Tester class

/*
 * Student information for assignment: 
 * Name: James Reeves
 * email address: jpascualsr06@gmail.com
 * UTEID: jsr3699
 * Section 5 digit ID: 50259
 * Grader name: Eliza Bidwell
 * Number of slip days used on this assignment: 0
 */

/*
The 1,000,000 test experiment resulted in an average of 45.12 shared birthdays per experiment.
23 PEOPLE required for a 50% chance of a shared birthday

I predict it will take 23 people to reach a 50% chance of a shared birthday (I took stat in highschool :( )
Sadly this did not surprise me, but when I first learned it, it was unbelievable.
<TABLE>
Num people: 2, number of experiments with one or more shared birthday: 139, percentage: 0.00278
Num people: 3, number of experiments with one or more shared birthday: 381, percentage: 0.00762
Num people: 4, number of experiments with one or more shared birthday: 872, percentage: 0.01744
Num people: 5, number of experiments with one or more shared birthday: 1387, percentage: 0.02774
Num people: 6, number of experiments with one or more shared birthday: 2044, percentage: 0.04088
Num people: 7, number of experiments with one or more shared birthday: 2877, percentage: 0.05754
Num people: 8, number of experiments with one or more shared birthday: 3748, percentage: 0.07496
Num people: 9, number of experiments with one or more shared birthday: 4736, percentage: 0.09472
Num people: 10, number of experiments with one or more shared birthday: 5849, percentage: 0.11698
Num people: 11, number of experiments with one or more shared birthday: 7083, percentage: 0.14166
Num people: 12, number of experiments with one or more shared birthday: 8396, percentage: 0.16792
Num people: 13, number of experiments with one or more shared birthday: 9642, percentage: 0.19284
Num people: 14, number of experiments with one or more shared birthday: 11042, percentage: 0.22084
Num people: 15, number of experiments with one or more shared birthday: 12766, percentage: 0.25532
Num people: 16, number of experiments with one or more shared birthday: 14048, percentage: 0.28096
Num people: 17, number of experiments with one or more shared birthday: 15680, percentage: 0.3136
Num people: 18, number of experiments with one or more shared birthday: 17400, percentage: 0.348
Num people: 19, number of experiments with one or more shared birthday: 18783, percentage: 0.37566
Num people: 20, number of experiments with one or more shared birthday: 20612, percentage: 0.41224
Num people: 21, number of experiments with one or more shared birthday: 21977, percentage: 0.43954
Num people: 22, number of experiments with one or more shared birthday: 23807, percentage: 0.47614
Num people: 23, number of experiments with one or more shared birthday: 25616, percentage: 0.51232
Num people: 24, number of experiments with one or more shared birthday: 26878, percentage: 0.53756
Num people: 25, number of experiments with one or more shared birthday: 28310, percentage: 0.5662
Num people: 26, number of experiments with one or more shared birthday: 30048, percentage: 0.60096
Num people: 27, number of experiments with one or more shared birthday: 31213, percentage: 0.62426
Num people: 28, number of experiments with one or more shared birthday: 32759, percentage: 0.65518
Num people: 29, number of experiments with one or more shared birthday: 33947, percentage: 0.67894
Num people: 30, number of experiments with one or more shared birthday: 35072, percentage: 0.70144
Num people: 31, number of experiments with one or more shared birthday: 36772, percentage: 0.73544
Num people: 32, number of experiments with one or more shared birthday: 37626, percentage: 0.75252
Num people: 33, number of experiments with one or more shared birthday: 38664, percentage: 0.77328
Num people: 34, number of experiments with one or more shared birthday: 39833, percentage: 0.79666
Num people: 35, number of experiments with one or more shared birthday: 40789, percentage: 0.81578
Num people: 36, number of experiments with one or more shared birthday: 41537, percentage: 0.83074
Num people: 37, number of experiments with one or more shared birthday: 42375, percentage: 0.8475
Num people: 38, number of experiments with one or more shared birthday: 43235, percentage: 0.8647
Num people: 39, number of experiments with one or more shared birthday: 43905, percentage: 0.8781
Num people: 40, number of experiments with one or more shared birthday: 44594, percentage: 0.89188
Num people: 41, number of experiments with one or more shared birthday: 45228, percentage: 0.90456
Num people: 42, number of experiments with one or more shared birthday: 45728, percentage: 0.91456
Num people: 43, number of experiments with one or more shared birthday: 46165, percentage: 0.9233
Num people: 44, number of experiments with one or more shared birthday: 46634, percentage: 0.93268
Num people: 45, number of experiments with one or more shared birthday: 47027, percentage: 0.94054
Num people: 46, number of experiments with one or more shared birthday: 47423, percentage: 0.94846
Num people: 47, number of experiments with one or more shared birthday: 47809, percentage: 0.95618
Num people: 48, number of experiments with one or more shared birthday: 48034, percentage: 0.96068
Num people: 49, number of experiments with one or more shared birthday: 48289, percentage: 0.96578
Num people: 50, number of experiments with one or more shared birthday: 48574, percentage: 0.97148
Num people: 51, number of experiments with one or more shared birthday: 48682, percentage: 0.97364
Num people: 52, number of experiments with one or more shared birthday: 48910, percentage: 0.9782
Num people: 53, number of experiments with one or more shared birthday: 49053, percentage: 0.98106
Num people: 54, number of experiments with one or more shared birthday: 49190, percentage: 0.9838
Num people: 55, number of experiments with one or more shared birthday: 49345, percentage: 0.9869
Num people: 56, number of experiments with one or more shared birthday: 49383, percentage: 0.98766
Num people: 57, number of experiments with one or more shared birthday: 49507, percentage: 0.99014
Num people: 58, number of experiments with one or more shared birthday: 49568, percentage: 0.99136
Num people: 59, number of experiments with one or more shared birthday: 49651, percentage: 0.99302
Num people: 60, number of experiments with one or more shared birthday: 49741, percentage: 0.99482
Num people: 61, number of experiments with one or more shared birthday: 49736, percentage: 0.99472
Num people: 62, number of experiments with one or more shared birthday: 49801, percentage: 0.99602
Num people: 63, number of experiments with one or more shared birthday: 49842, percentage: 0.99684
Num people: 64, number of experiments with one or more shared birthday: 49856, percentage: 0.99712
Num people: 65, number of experiments with one or more shared birthday: 49898, percentage: 0.99796
Num people: 66, number of experiments with one or more shared birthday: 49904, percentage: 0.99808
Num people: 67, number of experiments with one or more shared birthday: 49919, percentage: 0.99838
Num people: 68, number of experiments with one or more shared birthday: 49933, percentage: 0.99866
Num people: 69, number of experiments with one or more shared birthday: 49941, percentage: 0.99882
Num people: 70, number of experiments with one or more shared birthday: 49952, percentage: 0.99904
Num people: 71, number of experiments with one or more shared birthday: 49965, percentage: 0.9993
Num people: 72, number of experiments with one or more shared birthday: 49971, percentage: 0.99942
Num people: 73, number of experiments with one or more shared birthday: 49979, percentage: 0.99958
Num people: 74, number of experiments with one or more shared birthday: 49985, percentage: 0.9997
Num people: 75, number of experiments with one or more shared birthday: 49985, percentage: 0.9997
Num people: 76, number of experiments with one or more shared birthday: 49987, percentage: 0.99974
Num people: 77, number of experiments with one or more shared birthday: 49991, percentage: 0.99982
Num people: 78, number of experiments with one or more shared birthday: 49991, percentage: 0.99982
Num people: 79, number of experiments with one or more shared birthday: 49997, percentage: 0.99994
Num people: 80, number of experiments with one or more shared birthday: 49990, percentage: 0.9998
Num people: 81, number of experiments with one or more shared birthday: 49996, percentage: 0.99992
Num people: 82, number of experiments with one or more shared birthday: 49999, percentage: 0.99998
Num people: 83, number of experiments with one or more shared birthday: 49998, percentage: 0.99996
Num people: 84, number of experiments with one or more shared birthday: 49998, percentage: 0.99996
Num people: 85, number of experiments with one or more shared birthday: 49999, percentage: 0.99998
Num people: 86, number of experiments with one or more shared birthday: 49998, percentage: 0.99996
Num people: 87, number of experiments with one or more shared birthday: 49997, percentage: 0.99994
Num people: 88, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 89, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 90, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 91, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 92, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 93, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 94, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 95, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 96, number of experiments with one or more shared birthday: 49999, percentage: 0.99998
Num people: 97, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 98, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 99, number of experiments with one or more shared birthday: 50000, percentage: 1.0
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 1.0
 */

public class CodeCampTester {

    public static void main(String[] args) {

        // System.out.println(CodeCamp.millionTests());
        // for(int people = 2; people <= 100; people++){
        // System.out.println(CodeCamp.birthdayExperiment(people));
        // }

        final String newline = System.getProperty("line.separator");

        // test 1, hamming distance
        int[] h1 = { 1, 2, 3, 2345, 66, 49, 22, 4, 3, 2, 1 };
        int[] h2 = { 1, 2, 3, 0, 2345, 66, 22, 4, 3, 2, 1 };
        int expected = 3;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println("Test 1 hamming distance: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 1, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 1, hamming distance");
        }

        // test 2, hamming distance
        h1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        h2 = new int[] { 0, 1, 2, 4, 5, 0, 0, 0, 9, 10 };
        expected = 6;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test 2 hamming distance: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 2, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 2, hamming distance");
        }

        // test 3, isPermutation
        int[] a = { 1, 2, 3, 460360, 9, 8, 7 };
        int[] b = { 2, 1, 3, 460360, 8, 7, 9 };
        boolean expectedBool = true;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "test 3 isPermutation: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 3, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 3, isPermutation");
        }

        // test 4, is Permutation
        a = new int[] { 0 };
        b = new int[] { 2, 1, 3, 3 };
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "test 4 isPermutation: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 4, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 4, isPermutation");
        }

        // test 5, mostVowels
        String[] arrayOfStrings = { "aaaaaa", "aieou", "aaaaaa", "aAaaaa", "oompaloompa" };
        int expectedResult = 0;
        int actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "test 5 mostVowels: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 5, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 5, mostVowels");
        }

        // test 6, mostVowels
        arrayOfStrings = new String[] { "apple bottom jeans", "vsCodeRocks", "IHopeImDoingThisRight",
                "aeiouAeIoU", "!!!!>>+_)(*&^%$#@!>)))???\\///\n\n/n" };
        expectedResult = 3;
        actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "test 6 mostVowels: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 6, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 6, mostVowels");
        }

        // test 7, sharedBirthdays
        int pairs = CodeCamp.sharedBirthdays(1, 1000);
        System.out.println(newline + "test 7 shared birthdays: expected: 0, actual: " + pairs);
        int expectedShared = 0;
        if (pairs == expectedShared) {
            System.out.println("passed test 7, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 7, shared birthdays");
        }

        // test 8, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(1000, 20);
        System.out.println(newline + "test 8 shared birthdays: expected: "
                + "a value of 1 or more, actual: " + pairs);
        if (pairs > 0) {
            System.out.println("passed test 8, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 8, shared birthdays");
        }

        // test 9, queensAreASafe
        char[][] board = { { 'q', 'q', 'q' },
                { 'q', '.', 'q' },
                { 'q', 'q', 'q' } };

        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "test 9 queensAreSafe: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 9, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 9, queensAreSafe");
        }

        // test 10, queensAreASafe
        board = new char[][] { { '.', '.', '.', 'q' },
                                { '.', '.', '.', '.' },
                                { 'q', '.', '.', '.' },
                                { '.', '.', 'q', '.' } };
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "test 10 queensAreSafe: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 10, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 10, queensAreSafe");
        }

        // test 11, getValueOfMostValuablePlot
        int[][] city = { { 1, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 1 } };

        expected = 4;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "test 11 getValueOfMostValuablePlot: expected value: "
                + expected + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 11, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 11, getValueOfMostValuablePlot");
        }

        // test 12, getValueOfMostValuablePlot
        int[][] city2 = { { 0, -100, -100, -100, -100, 200 },
                { -100, -100, -100, -100, -100, -100 },
                { -100, -100, -100, -100, -100, -100 },
                { -100, -100, -100, -100, -100, -100 },
                { -100, -100, -100, -100, -100, -100 },
                { 100, -100, -100, -100, -100, 50 } };
        expected = 200;
        actual = CodeCamp.getValueOfMostValuablePlot(city2);
        System.out.println(newline + "test 12 getValueOfMostValuablePlot: expected value: "
                + expected + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 12, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 12, getValueOfMostValuablePlot");
        }

    } // end of main method

    // pre: list != null
    private static int[] intListToArray(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list parameter may not be null.");
        }
        int[] result = new int[list.size()];
        int arrayIndex = 0;
        for (int x : list) {
            result[arrayIndex++] = x;
        }
        return result;
    }
}