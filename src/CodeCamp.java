//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 *
 *  replace <NAME> with your name.
 *
 *  On my honor, James Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: James Reeves
 *  email address: jpascualsr06@gmail.com
 *  UTEID: jsr3699
 *  Section 5 digit ID: 50259
 *  Grader name: Eliza Bidwell
 *  Number of slip days used on this assignment: 0
 */

 import java.util.Random;

 public class CodeCamp {
 
    public static int hammingDistance(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null || aData.length != bData.length) {
            throw new IllegalArgumentException("Violation of precondition: " +
                     "hammingDistance. neither parameter may equal null, arrays" +
                     " must be equal length.");
        }
         
        int count = 0;
        for (int index = 0; index < aData.length; index++) {
            if  (aData[index] != bData[index]) {
                count++;
            }
        }
        return count;
        
     }

     public static boolean isPermutation(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isPermutation. neither parameter may equal null.");
        }

        if (aData.length != bData.length) {
            return false;
        }
        int index = 0;
        while (index < aData.length) {
            int aCount = 0;
            int bCount = 0;
            for (int i = 0; i < aData.length; i++) {
                if (aData[index] == aData[i]) {
                    aCount++;
                }
                if (bData[index] == bData[i]) {
                    bCount++;
                }
            }
            if (aCount!=bCount) {
                return false;
            }
            index++;
        }
        return true;
 
     }
 
     public static int mostVowels(String[] arrayOfStrings) {
        // check preconditions
        if (arrayOfStrings == null || arrayOfStrings.length == 0
                 || !atLeastOneNonNull(arrayOfStrings)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "mostVowels. parameter may not equal null and must contain " +
                    "at least one none null value.");
        }
         
        
        String vowels = "AaEeIiOoUu";
        int longestIndex = 0;
        int longestOccurence = -1;
        for (int index = 0; index < arrayOfStrings.length; index++) {
            String current = arrayOfStrings[index];
            if (current != null) {
                int searcher = 0;
                int currentLongest = 0;
                for (int c = 0; c < current.length(); c++) {
                    if (vowels.contains(current.substring(c, c+1))) {
                        searcher++;
                    }
                    else if (searcher > currentLongest) {
                        currentLongest = searcher;
                        searcher = 0;
                    }
                }
                if (currentLongest > longestOccurence) {
                    longestOccurence = currentLongest;
                    longestIndex = index;
                }
            }
        }
        return longestIndex;
     }
 
     public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople +
                    ", numDaysInYear: " + numDaysInYear);
        }
         

        int matches = 0;
        int[] birthdays = new int[numPeople];
        for (int i = 0; i < birthdays.length; i++) {
            birthdays[i] = (int)(Math.random()*numDaysInYear);
        }
        for (int a = 0; a < birthdays.length; a++) {
            for (int b = a+1; b < birthdays.length; b++) {
                if (birthdays[a] == birthdays[b]) {
                    matches++;
                }
            }
        }
        return matches;
     }

     public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board)
                || !onlyContains(board, validChars)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "queensAreSafe. The board may not be null, must be square, " +
                    "and may only contain 'q's and '.'s");
        }
 
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 'q') {
                    int hits = 0;
                    for (int direction = 1; direction <= 8; direction++) {
                        hits += recur(board, row, col, direction, true);
                    }
                    if (hits > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
     }

     private static int recur(char[][] board, int x, int y, int dir, boolean first) {
        if (x < board.length && x >= 0 && y < board[0].length && y >=0) {
            if (board[x][y] == 'q' && !first) {
                return 1;
            } else {
                first = false;
                switch(dir) {
                    case 1:
                        return recur(board, x, y-1, 1, first);
                    case 2:
                        return recur(board, x+1, y-1, 2, first);
                    case 3:
                        return recur(board, x+1, y, 3, first);
                    case 4:
                        return recur(board, x+1, y+1, 4, first);
                    case 5:
                        return recur(board, x, y+1, 5, first);
                    case 6:
                        return recur(board, x-1, y+1, 6, first);
                    case 7:
                        return recur(board, x-1, y, 7, first);
                    case 8:
                        return recur(board, x-1, y-1, 8, first);
                }
            }
        }
        return 0;
     }
 
     public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0
                || !isRectangular(city) ) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getValueOfMostValuablePlot. The parameter may not be null," +
                    " must value at least one row and at least" +
                    " one column, and must be rectangular.");
        }
         
        int largest = Integer.MIN_VALUE;
        for (int startX = 0; startX < city.length; startX++) {
            for (int startY = 0; startY < city[0].length; startY++) {
                for (int endX = startX; endX < city.length; endX++) {
                    for (int endY = startY; endY < city[0].length; endY++) {
                        int sum = 0;
                        for (int addX = startX; addX <= endX; addX++) {
                            for (int addY = startY; addY <= endY; addY++) {
                                sum += city[addX][addY];
                            }
                        }
                        if (sum > largest) {
                            largest = sum;
                        }
                    }
                }
            }
        }
        return largest;
     }
 
     //Experiments -------

     public static double millionTests() {
        double total = 0;
        //run 1 million tests with the given 182 people and standard year. Count how many total shared birthdays in ALL of the tests
        final int runs = 1000000;
        for (int run = 0; run < runs; run++) {
            total += sharedBirthdays(182, 365);
        }
        //return the average
        return (total/runs);
     }

     public static String birthdayExperiment(int people) {
        int oneOrMore = 0;
        //run 50000 tests of *people* people and count how many times someone shared a birthday
        final int runs = 50000;
        for (int run = 0; run < runs; run++) {
            if (sharedBirthdays(people, 365) >= 1) {
                oneOrMore++;
            }
        }
        //return full table row including the percent of the 50000 tests that had at least one shared birthday
        return ("Num people: " + people + ", number of experiments with one or more shared birthday: " + oneOrMore + ", percentage: " + ((double)oneOrMore/runs));
     }
 
 
     /*
      * pre: arrayOfStrings != null
      * post: return true if at least one element in arrayOfStrings is
      * not null, otherwise return false.
      */
     private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
         // check precondition
         if (arrayOfStrings == null) {
             throw new IllegalArgumentException("Violation of precondition: " +
                     "atLeastOneNonNull. parameter may not equal null.");
         }
         boolean foundNonNull = false;
         int i = 0;
         while( !foundNonNull && i < arrayOfStrings.length ) {
             foundNonNull = arrayOfStrings[i] != null;
             i++;
         }
         return foundNonNull;
     }
 
 
     /*
      * pre: mat != null
      * post: return true if mat is a square matrix, false otherwise
      */
     private static boolean isSquare(char[][] mat) {
         if (mat == null) {
             throw new IllegalArgumentException("Violation of precondition: " +
                     "isSquare. Parameter may not be null.");
         }
         final int numRows = mat.length;
         int row = 0;
         boolean isSquare = true;
         while (isSquare && row < numRows) {
             isSquare = ( mat[row] != null) && (mat[row].length == numRows);
             row++;
         }
         return isSquare;
     }
 
 
     /*
      * pre: mat != null, valid != null
      * post: return true if all elements in mat are one of the
      * characters in valid
      */
     private static boolean onlyContains(char[][] mat, char[] valid) {
         // check preconditions
         if (mat == null || valid == null) {
             throw new IllegalArgumentException("Violation of precondition: " +
                     "onlyContains. Parameters may not be null.");
         }
         String validChars = new String(valid);
         int row = 0;
         boolean onlyContainsValidChars = true;
         while (onlyContainsValidChars && row < mat.length) {
             int col = 0;
             while(onlyContainsValidChars && col < mat[row].length) {
                 int indexOfChar = validChars.indexOf(mat[row][col]);
                 onlyContainsValidChars = indexOfChar != -1;
                 col++;
             }
             row++;
         }
         return onlyContainsValidChars;
     }
 
 
     /*
      * pre: mat != null, mat.length > 0
      * post: return true if mat is rectangular
      */
     private static boolean isRectangular(int[][] mat) {
         // check preconditions
         if (mat == null || mat.length == 0) {
             throw new IllegalArgumentException("Violation of precondition: " +
                     "isRectangular. Parameter may not be null and must contain" +
                     " at least one row.");
         }
         boolean correct = mat[0] != null;
         int row = 0;
         while(correct && row < mat.length) {
             correct = (mat[row] != null)
                     && (mat[row].length == mat[0].length);
             row++;
         }
         return correct;
     }
 
     // make constructor private so no instances of CodeCamp can not be created
     private CodeCamp() {
 
     }
 }