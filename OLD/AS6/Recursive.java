/*  Student information for assignment:
 *
 *  On MY honor, James Reeves,
 *  this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  Grader name: Eliza
 *  Section number: 50259
 */

//imports

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     * <br>
     * pre: n != Integer.MIN_VALUE<br>
     * <br>
     * post: Returns a String that represents N in binary.
     * All chars in returned String are '1's or '0's.
     * Most significant digit is at position 0
     * 
     * @param n the base 10 int to convert to base 2
     * @return a String that is a binary representation of the parameter n
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "getBinary. n cannot equal "
                    + "Integer.MIN_VALUE. n: " + n);
        }

        // base case
        if (n == 0 || n == 1 || n == -1) {
            return "" + n;
        }
        // recursive case
        if (Math.abs(n % 2) == 1) {
            return getBinary(n / 2) + "1";
        } else {
            return getBinary(n / 2) + "0";
        }

    }

    /**
     * Problem 2: reverse a String recursively.<br>
     * pre: stringToRev != null<br>
     * post: returns a String that is the reverse of stringToRev
     * 
     * @param stringToRev the String to reverse.
     * @return a String with the characters in stringToRev
     *         in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        // base case
        if (stringToRev.length() == 0) {
            return "";
        }
        // recursive case
        int len = stringToRev.length();
        return stringToRev.substring(len - 1) + revString(stringToRev.substring(0, len - 1));
    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data
     * that are followed immediately by double the value
     * 
     * @param data The array to search.
     * @return The number of elements in data that
     *         are followed immediately by a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        return doubleHelper(data, 0);
    }

    // CS314 students, add your nextIsDouble helper method here
    private static int doubleHelper(int[] data, int index) {
        int len = data.length;
        // base case
        if (index == len - 1) {
            return 0;
        }
        
        // recursive step
        if (data[index + 1] == data[index] * 2) {
            return 1 + doubleHelper(data, index + 1);
        } else {
            return doubleHelper(data, index + 1);
        }
    }

    /**
     * Problem 4: Find all combinations of mnemonics
     * for the given number.<br>
     * pre: number != null, number.length() > 0,
     * all characters in number are digits<br>
     * post: see tips section of assignment handout
     * 
     * @param number The number to find mnemonics for
     * @return An ArrayList<String> with all possible mnemonics
     *         for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null || number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }

        ArrayList<String> results = new ArrayList<>(); // to store the mnemonics
        recursiveMnemonics(results, "", number);
        return results;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used
     * from the original number.
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics,
            String mnemonicSoFar, String digitsLeft) {

        // base case
        if (digitsLeft.length() == 0) {
            mnemonics.add(mnemonicSoFar);
        } else {
            // recursive step
            String options = digitLetters(digitsLeft.charAt(0));
            for (int i = 0; i < options.length(); i++) {
                String addLetter = options.substring(i, i + 1);
                recursiveMnemonics(mnemonics, mnemonicSoFar + addLetter, digitsLeft.substring(1));
            }
        }

    }

    /*
     * Static code blocks are run once when this class is loaded.
     * Here we create an unmoddifiable list to use with the phone
     * mnemonics method.
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = { "0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);

    }
    // used by method digitLetters

    /*
     * helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with
     * this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }

    /*
     * helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is
     * a digit ('0' through '9')
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * 
     * @param size  the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /*
     * Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * 
     * @param g The Graphics object to use to fill rectangles
     * 
     * @param size the size of the current square
     * 
     * @param limit the smallest allowable size of squares
     * 
     * @param x the x coordinate of the upper left corner of the current square
     * 
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit,
            double x, double y) {
        if (size <= limit) {
            return;
        }
        // draw white square
        int third = size / 3;
        g.fillRect((int) x + third, (int) y + third, (int) third, (int) third);
        // call drawSquares on each 8 sections
        final int separations = 3;
        for (int i = 0; i < separations; i++) {
            for (int j = 0; j < separations; j++) {
                if (!(x == 1 && y == 1)) {
                    double newX = x + (i * third);
                    double newY = y + (j * third);
                    drawSquares(g, third, limit, newX, newY);
                }
            }
        }

    }

    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>
     * pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length,
     * 0 <= col < map[0].length
     * <br>
     * post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise.
     * 
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     *         specified by row, column can reach the edge of the map, false
     *         otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "canFlowOffMap");
        }

        boolean[][] visited = new boolean[map.length][map[0].length];
        return flowSolver(map, visited, row, col);

    }

    /*
     * Recursive method for canFlowOffMap
     * return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise
     */
    private static boolean flowSolver(int[][] map, boolean[][] visited, int row, int col) {
        // base cases
        if (row == 0 || row == map.length - 1 || col == 0 || col == map[0].length - 1) {
            return true;
        }
        if (visited[row][col] || isSurrounded(map, row, col)) {
            return false;
        }

        // recursive step
        // choose
        visited[row][col] = true;
        // explore each direction only if it is downhill
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        if (map[row][col] > map[row - 1][col]) {
            up = flowSolver(map, visited, row - 1, col);
        }
        if (map[row][col] > map[row + 1][col]) {
            down = flowSolver(map, visited, row + 1, col);
        }
        if (map[row][col] > map[row][col - 1]) {
            left = flowSolver(map, visited, row, col - 1);
        }
        if (map[row][col] > map[row][col + 1]) {
            right = flowSolver(map, visited, row, col + 1);
        }
        // unchoose
        visited[row][col] = false;
        return (up || down || left || right);
    }

    /*
     * helper method for flowSolver
     * return true if the given position is surrounded by non-downhill land, false otherwise
     */
    private static boolean isSurrounded(int[][] map, int row, int col) {
        int here = map[row][col];
        int up = map[row - 1][col];
        int down = map[row + 1][col];
        int left = map[row][col - 1];
        int right = map[row][col + 1];
        return (up >= here && down >= here && left >= here && right >= here);
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br>
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br>
     * post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * 
     * @param numTeams  the number of teams to form.
     *                  Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     *         with the maximum total ability and the team with the minimum total
     *         ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        final int minTeams = 2;
        if (numTeams < minTeams || abilities == null || abilities.length < numTeams) {
            throw new IllegalArgumentException("minDifference: invalid parameters");
        }

        int teamDataColumns = 2;
        int[][] teams = new int[numTeams][teamDataColumns];
        return minDiffSolver(abilities, teams, 0);
    }

    /*
     * Recursive method for minDifference
     * return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability
     */
    private static int minDiffSolver(int[] abilities, int[][] teams, int index) {
        // base case
        if (index == abilities.length) {
            if (fullTeams(teams)) {
                return minMax(teams);
            }
            return Integer.MAX_VALUE;
        }

        // recursive step
        int best = Integer.MAX_VALUE;
        for (int t = 0; t < teams.length && t < index + 1; t++) {
            // choose
            adjustTeams(teams, t, abilities[index], false);

            // explore
            int compare = minDiffSolver(abilities, teams, index + 1);
            if (compare < best) {
                best = compare;
            }

            // unchoose
            adjustTeams(teams, t, abilities[index], true);
        }
        return best;
    }

    /*
     * helper method for minDiffSolver
     * return the difference of the strongest and weakest team
     */
    private static int minMax(int[][] teams) {
        final int teamScore = 0;

        int min = teams[0][teamScore];
        int max = teams[0][teamScore];

        for (int i = 0; i < teams.length; i++) {
            int currScore = teams[i][teamScore];
            if (currScore < min) {
                min = currScore;
            }
            if (currScore > max) {
                max = currScore;
            }
        }

        return (max - min);
    }

    /*
     * helper method for minDiffSolver
     * return true if every team has at least one member, false otherwise
     */
    private static boolean fullTeams(int[][] teams) {
        final int members = 1;
        for (int i = 0; i < teams.length; i++) {
            if (teams[i][members] == 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * helper method for minDiffSolver
     * adjust teams by adding or removing 1 player (index 1) and their ability (index 0)
     * from team at index
     */
    private static void adjustTeams(int[][] teams, int index, int ability, boolean removing) {
        final int teamScore = 0;
        final int numMembers = 1;

        //if removing, negate both before adding
        int member = 1;
        if (removing) {
            member = -1;
            ability = -ability;
        }

        teams[index][teamScore] += ability;
        teams[index][numMembers] += member;
    }

    /**
     * Problem 8: Maze solver.
     * <br>
     * pre: board != null
     * <br>
     * pre: board is a rectangular matrix
     * <br>
     * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>
     * pre: there is a single 'S' character present
     * <br>
     * post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * 
     * @param rawMaze represents the maze we want to escape.
     *                rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    public static int canEscapeMaze(char[][] rawMaze) {
        if (rawMaze == null || !rectangularMaze(rawMaze) || !validChars(rawMaze)) {
            throw new IllegalArgumentException("canEscapeMaze: invalid maze");
        }
        int coins = 0;
        int startRow = 0;
        int startCol = 0;
        for (int r = 0; r < rawMaze.length; r++) {
            for (int c = 0; c < rawMaze[0].length; c++) {
                if (rawMaze[r][c] == '$') {
                    coins++;
                }
                if (rawMaze[r][c] == 'S') {
                    startRow = r;
                    startCol = c;
                }
            }
        }

        char[][] copy = new char[rawMaze.length][];
        for (int i = 0; i < rawMaze.length; i++) {
            copy[i] = rawMaze[i].clone();
        }

        return mazeSolver(copy, startRow, startCol, coins);
    }

    /*
     * precondition helper for canEscapeMaze
     * return true if every character in the maze is valid and 
     * there is exactly one starting character S
     */
    private static boolean validChars(char[][] maze) {
        String valid = "SE$GY*";
        int StartCount = 0;
        for (char[] r : maze) {
            for (char c : r) {
                if (!valid.contains(c + "")) {
                    return false;
                }
                if (c == 'S') {
                    StartCount++;
                }
            }
        }
        return StartCount == 1;
    }

    /*
     * precondition helper for canEscapeMaze
     * return true if char[][] is rectangular, false otherwise
     */
    private static boolean rectangularMaze(char[][] maze) {
        if (maze.length == 0) {
            throw new IllegalArgumentException("maze must have size");
        }
        final int len = maze[0].length;
        for (int i = 0; i < maze.length; i++) {
            if (maze[i] == null || maze[i].length != len) {
                return false;
            }
        }
        return true;
    }

    /*
     * Recursive method for canEscapeMaze
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze.
     */
    private static int mazeSolver(char[][] maze, int x, int y, int coinsLeft) {
        // base cases
        if (outOfBounds(maze, x, y)) {
            return 0;
        }
        if (maze[x][y] == 'E') {
            if (coinsLeft > 0) {
                return 1;
            }
            return 2;
        }
        if (stuck(maze, x, y) || maze[x][y] == '*') {
            return 0;
        }

        //recursive step
        //choose
        char before = maze[x][y];
        int newCoins = (before == '$') ? coinsLeft - 1 : coinsLeft;
        //explore
        maze[x][y] = moveOut(maze, x, y);
        int up = mazeSolver(maze, x - 1, y, newCoins);
        int down = mazeSolver(maze, x + 1, y, newCoins);
        int left = mazeSolver(maze, x, y - 1, newCoins);
        int right = mazeSolver(maze, x, y + 1, newCoins);
        //unchoose
        maze[x][y] = before;
        return findBest(up, down, left, right);
    }
    
    /*
     * helper method for mazeSolver
     * determines what the replaced character should be, given the current
     * char at the position
     */
    private static char moveOut(char[][] maze, int x, int y) {
        char current = maze[x][y];
        if (current == 'G' || current == '$') {
            return 'Y';
        } else if (current == 'Y' || current == '*') {
            return '*';
        } else if (current == 'S') {
            return 'G';
        } else {
            return current;
        }
    }

    /*
     * helper method for mazeSolver
     * return true if the given position is surrounded by impassable cells, false otherwise
     */
    private static boolean stuck(char[][] maze, int x, int y) {
        boolean upStuck = (x == 0 || maze[x - 1][y] == '*');
        boolean downStuck = (x == maze.length - 1 || maze[x + 1][y] == '*');
        boolean leftStuck = (y == 0 || maze[x][y - 1] == '*');
        boolean rightStuck = (y == maze[0].length - 1 || maze[x][y + 1] == '*');

        return (upStuck && downStuck && leftStuck && rightStuck);
    }

    private static boolean outOfBounds(char[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            return true;
        }
        return false;
    }

    /*
     * helper method for mazeSolver
     * return the highest of the 4 given integers
     */
    private static int findBest(int a, int b, int c, int d) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        if (d > max) {
            max = d;
        }
        return max;
    }

}