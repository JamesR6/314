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


 public class CodeCamp {
    
    /*
     * takes two int arrays and returns how many indexes do not contain
     * the same value
     * pre : aData != null, bData != null, aData.length = bData.length
     *       throws IllegalArgumentException if preconditions are violated
     * post : returns the number of indexes that don't match
     */
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

    /*
     * Takes two int arrays as parameters and determines if they contain the 
     * same values regardless of order
     * pre : aData != null, bData != null
     *       throws IllegalArgumentException if preconditions are violated
     * post : returns true if both arrays contain the same values, false otherwise
     */
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
 
    /*
     * Takes an array of strings and returns the index of the string
     * that contains the longest 'string' of vowels.
     * pre : arrayOfStrings != null, arrayOfStrings.length != 0, atLeastOneNonNull(arrayOfStrings) = true
     *       throws IllegalArgumentException if preconditions are violated
     * post : 
     */
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
                    } else if (searcher > currentLongest) {
                        currentLongest = searcher;
                        searcher = 0;
                    }
                    if (c == current.length() - 1 && searcher > currentLongest) {
                        currentLongest = searcher;
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
    
    /*
     * Takes a number of people and number of days in a theoretical year
     * and returns the amount of birthday pairs in that experiment.
     * pre : numPeople > 0, numDaysInYear > 0
     *       throws IllegalArgumentException if preconditions are violated
     * post : returns the amount of birthday pairs
     */
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

    /*
     * Given a chess board (2d char array) with only queens 'q' and
     * empty spaces '.', returns true if no queen can attack another
     * and false otherwise
     * pre : board != null, board.length != 0, isSquare(board) = true,
     *       onlyContains(board, validChars) = true
     *       throws IllegalArgumentException if preconditions are violated
     * post : returns true if all queens are safe, false otherwise
     */
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
                    if (!thisQueenIsSafe(board, row, col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * private helper method for queensAreSafe. Returns false if the queen at
     * 'x' 'y' is in danger, true otherwise.
     */
    private static boolean thisQueenIsSafe(char[][] board, int x, int y){
        final int xAnchor = x;
        final int yAnchor = y;
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                boolean first = true;
                while (x >= 0 && x < board.length && y >= 0 && y < board[0].length){
                    if (a == 0 && b == 0){
                        break;
                    }
                    if (board[x][y] == 'q' && !first){
                        return false;
                    }
                    first = false;
                    x += a;
                    y += b;
                }
                x = xAnchor;
                y = yAnchor;
            }
        }
        return true;
    }

    /*
     * Given a 2d array of ints, returns the highest sum of a 
     * rectangular plot.
     * pre : city != null, city.length != 0; city[0].length != 0,
     *       isRectangular(city) = true
     *       throws IllegalArgumentException if preconditions are violated
     * post : returns the value of the most valuable rectangular plot
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0
                || !isRectangular(city) ) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getValueOfMostValuablePlot. The parameter may not be null," +
                    " must value at least one row and at least" +
                    " one column, and must be rectangular.");
        }
         
        int largest = city[0][0];
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

    /*
     * Takes no paremeters and returns the average amount
     * of shared birthdays 182 people would have from one
     * million tests
     */
    public static double millionTests() {
        double total = 0;
        final int runs = 1000000;
        for (int run = 0; run < runs; run++) {
            total += sharedBirthdays(182, 365);
        }
        //return the average
        return (total/runs);
    }

    /*
     * Takes an integer "people" and determines how many 'rooms'
     * of that many people would have at least one birthday pair
     * out of 50,000 tests.
     * pre : people >= 2
     * post : returns a formatted string of the rate of birthday pairs
     */
    public static String birthdayExperiment(int people) {
        if(people < 2){
            return("Insufficient amount of people");
        }
        int oneOrMore = 0;
        final int runs = 50000;
        for (int run = 0; run < runs; run++) {
            if (sharedBirthdays(people, 365) >= 1) {
                oneOrMore++;
            }
        }
        return ("Num people: " + people + ", number of experiments with one or more shared birthday: " + oneOrMore + ", percentage: " + ((double)oneOrMore/runs));
    }

    //End Experiments --------
 
 
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