/*  Student information for assignment:
 *
 *  On MY honor, JP Reeves, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  Grader name: Eliza
 *  Section number: 50529
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        studentTests();
    }

    // post: run student test
    private static void studentTests() {
        // getBinary
        String expected = "-111010110110010100111";
        String actual = Recursive.getBinary(-1928359);
        valueTest(actual, expected, "getBinary test 1");
        expected = "0";
        actual = Recursive.getBinary(0);
        valueTest(actual, expected, "getBinary test 2");

        // revString
        expected = "redder malayalam tacocat racecar";
        actual = Recursive.revString("racecar tacocat malayalam redder");
        valueTest(actual, expected, "revString test 1");
        expected = "";
        actual = Recursive.revString("");
        valueTest(actual, expected, "revString test 2");
        expected = "abc";
        actual = Recursive.revString("cba");
        valueTest(actual, expected, "revString test 3");

        // nextIsDouble
        int[] doubleData = new int[] { 1, 2, 4, 8, 16, 400, 32, 64, 128, 256, 1, 5, 10 };
        int doubActual = Recursive.nextIsDouble(doubleData);
        int doubExpected = 8;
        boolTest(doubActual == doubExpected, "nextIsDouble test 1");
        doubActual = Recursive.nextIsDouble(new int[] { 1 });
        doubExpected = 0;
        boolTest(doubActual == doubExpected, "nextIsDouble test 2");

        // listMnemonics
        ArrayList<String> MneExpected = new ArrayList<>();
        ArrayList<String> mnemonics = Recursive.listMnemonics("67");
        Collections.sort(mnemonics);
        String[] six = { "M", "N", "O" };
        String[] seven = { "P", "Q", "R", "S" };
        for (String a : six) {
            for (String b : seven) {
                MneExpected.add(a + b);
            }
        }
        valueTest(mnemonics, MneExpected, "listMnemonics test 1");

        MneExpected.clear();
        mnemonics.clear();
        mnemonics = Recursive.listMnemonics("777");
        for (String a : seven) {
            for (String b : seven) {
                for (String c : seven) {
                    MneExpected.add(a + b + c);
                }
            }
        }
        Collections.sort(MneExpected);
        Collections.sort(mnemonics);
        valueTest(mnemonics, MneExpected, "listMnemonics test 2");

        // canFlowOffMap
        int[][] map = { { 2, 2, 4, 8, 6, 3, 2, 1, 300 },
                { 4, 8, 12, 6, 2, -2, 1, 4, 30 },
                { 5, 9, 13, 7, 3, -1, 2, 5, 31 } };
        boolean flow = Recursive.canFlowOffMap(map, 1, 5);
        boolean flowExpected = false;
        boolTest(flow == flowExpected, "canFlowOffMap test 1");
        flow = Recursive.canFlowOffMap(map, 1, 2);
        boolTest(flow, "canFlowOffMap test 2");

        // minDifference
        int[] abilities = { 40, 0, -20, 5, 6 };
        int numTeams = 2;
        int difActual = Recursive.minDifference(numTeams, abilities);
        int difExpected = 9;
        valueTest(difExpected, difActual, "minDifference test 1");
        abilities = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        numTeams = 10;
        difActual = Recursive.minDifference(numTeams, abilities);
        valueTest(difExpected, difActual, "minDifference test 2");

        // canEscapeMaze
        char[][] maze = { { 'S', 'G', '$', 'Y', 'Y', 'E' } };
        int mazeActual = Recursive.canEscapeMaze(maze);
        int mazeExpected = 2;
        boolTest(mazeExpected == mazeActual, "canEscapeMaze test 1");
        maze = new char[][] { { '$', 'Y', 'S', 'Y', 'Y', 'E' } };
        mazeActual = Recursive.canEscapeMaze(maze);
        mazeExpected = 1;
        boolTest(mazeExpected == mazeActual, "canEscapeMaze test 2");

    }

    private static void boolTest(boolean boo, String method) {
        String result = boo ? "PASSED" : "FAILED";
        System.out.println(method + " " + result);
    }

    private static void valueTest(Object act, Object exp, String method) {
        String result = (act.equals(exp)) ? "PASSED" : "FAILED";
        System.out.println(method + " " + result);
    }
}