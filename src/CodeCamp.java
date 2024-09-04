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
 
     /**
      * Determine the Hamming distance between two arrays of ints.
      * Neither the parameter <tt>aData</tt> or
      * <tt>bData</tt> are altered as a result of this method.<br>
      * @param aData != null, aData.length == aData.length
      * @param bData != null
      * @return the Hamming Distance between the two arrays of ints.
      */
     public static int hammingDistance(int[] aData, int[] bData) {
         // check preconditions
         if (aData == null || bData == null || aData.length != bData.length) {
             throw new IllegalArgumentException("Violation of precondition: " +
                     "hammingDistance. neither parameter may equal null, arrays" +
                     " must be equal length.");
         }
 
        int hd = 0;
        for(int a = 0; a < aData.length; a++){
            if(aData[a] != bData[a]){
                hd++;
            }
        }
        return hd;
        
     }
 
 
     /**
      * Determine if one array of ints is a permutation of another.
      * Neither the parameter <tt>aData</tt> or
      * the parameter <tt>bData</tt> are altered as a result of this method.<br>
      * @param aData != null
      * @param bData != null
      * @return <tt>true</tt> if aData is a permutation of bData,
      * <tt>false</tt> otherwise
      *
      */
     public static boolean isPermutation(int[] aData, int[] bData) {
         // check preconditions
         if (aData == null || bData == null) {
             throw new IllegalArgumentException("Violation of precondition: " +
                     "isPermutation. neither parameter may equal null.");
         }

         //check for different array lengths: automatically impossible
         if(aData.length != bData.length){
            return false;
         }
         //loop through aData
         int index = 0;
         while(index < aData.length){
            int aCount = 0;
            int bCount = 0;
            //check for # of occurences of each value
            for(int a = 0; a < aData.length; a++){
                if(aData[index] == aData[a]){
                    aCount++;
                }
                if(bData[index] == bData[a]){
                    bCount++;
                }
            }
            //if any of these differ the answer must be false regardless of order
            if(aCount!=bCount){
                return false;
            }
            index++;
         }
         return true;
 
     }
 
 
     /**
      * Determine the index of the String that
      * has the largest number of vowels.
      * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o',
      * 'U', and 'u'</tt>.
      * The parameter <tt>arrayOfStrings</tt> is not altered as a result of this method.
      * <p>pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>,
      * there is an least 1 non null element in arrayOfStrings.
      * <p>post: return the index of the non-null element in arrayOfStrings that has the
      * largest number of characters that are vowels.
      * If there is a tie return the index closest to zero.
      * The empty String, "", has zero vowels.
      * It is possible for the maximum number of vowels to be 0.<br>
      * @param arrayOfStrings the array to check
      * @return the index of the non-null element in arrayOfStrings that has
      * the largest number of vowels.
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
        //loop through arrayOfStrings
        for(int index = 0; index < arrayOfStrings.length; index++){
            String current = arrayOfStrings[index];
            //check for null strings
            if(current != null){
                int searcher = 0;
                int currentLongest = 0;
                //loop through each individual string looking for the longest vowel series in just that string
                for(int c = 0; c < current.length(); c++){
                    if(vowels.contains(current.substring(c, c+1))){
                        searcher++;
                    }
                    else if(searcher > currentLongest){
                        currentLongest = searcher;
                        searcher = 0;
                    }
                }
                //if that string contained a new longest occurence, update the index and length of that occurence
                if(currentLongest > longestOccurence){
                    longestOccurence = currentLongest;
                    longestIndex = index;
                }
            }
        }
        return longestIndex;
     }
 
 
 
     /**
      * Perform an experiment simulating the birthday problem.
      * Pick random birthdays for the given number of people.
      * Return the number of pairs of people that share the
      * same birthday.<br>
      * @param numPeople The number of people in the experiment.
      * This value must be > 0
      * @param numDaysInYear The number of days in the year for this experiement.
      * This value must be > 0
      * @return The number of pairs of people that share a birthday
      * after running the simulation.
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
         //make an array to keep track of the peoples birthdays and fill with random birthdays
         int[] birthdays = new int[numPeople];
         for(int i = 0; i < birthdays.length; i++){
            birthdays[i] = (int)(Math.random()*numDaysInYear);
         }

         //find people that are matching with no vice versa repeats
         for(int a = 0; a < birthdays.length; a++){
            for(int b = a+1; b < birthdays.length; b++){
                if(birthdays[a] == birthdays[b]){
                    matches++;
                }
            }
         }
         return matches;
     }
 
 
     /**
      * Determine if the chess board represented by board is a safe set up.
      * <p>pre: board != null, board.length > 0, board is a square matrix.
      * (In other words all rows in board have board.length columns.),
      * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
      * represent open spaces.<br>
      * <p>post: return true if the configuration of board is safe,
      * that is no queen can attack any other queen on the board.
      * false otherwise.
      * the parameter <tt>board</tt> is not altered as a result of
      * this method.<br>
      * @param board the chessboard
      * @return true if the configuration of board is safe,
      * that is no queen can attack any other queen on the board.
      * false otherwise.
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
 
         
         for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                if(board[x][y] == 'q'){
                    int hits = 0;
                    for(int direction = 1; direction <= 8; direction++){
                        hits += recur(board, x, y, direction, true);
                    }
                    if (hits > 0){
                        return false;
                    }
                }
            }
         }
         return true;
     }

     private static int recur(char[][] board, int x, int y, int dir, boolean first){
        if(x < board.length && x >= 0 && y < board[0].length && y >=0){
            if(board[x][y] == 'q' && !first){
                return 1;
            }
            else{
                first = false;
                switch(dir){
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
 
 
     /**
      * Given a 2D array of ints return the value of the
      * most valuable contiguous sub rectangle in the 2D array.
      * The sub rectangle must be at least 1 by 1.
      * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
      * mat</tt> is a rectangular matrix.
      * <p>post: return the value of the most valuable contiguous sub rectangle
      * in <tt>city</tt>.<br>
      * @param city The 2D array of ints representing the value of
      * each block in a portion of a city.
      * @return return the value of the most valuable contiguous sub rectangle
      * in <tt>city</tt>.
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
         
         int largest = Integer.MIN_VALUE;
         //generate every UPPER LEFT (Start) coordinate
         for(int startX = 0; startX < city.length; startX++){
            for(int startY = 0; startY < city[0].length; startY++){
                //for each of these coordinates, generate every possible BOTTOM RIGHT (end) coordinate
                for(int endX = startX; endX < city.length; endX++){
                    for(int endY = startY; endY < city[0].length; endY++){
                        //find sum of this plot from start to end
                        int sum = 0;
                        for(int addX = startX; addX <= endX; addX++){
                            for(int addY = startY; addY <= endY; addY++){
                                sum += city[addX][addY];
                            }
                        }
                        //if this plot is more valuable than the previous most valuable plot, change the value of most valuable
                        if(sum > largest){
                            largest = sum;
                        }
                    }
                }
            }
         }
        return largest;
     }
 
 
     // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
     // CS314 STUDENTS: Put your birthday problem experiment code here:
     public static double millionTests(){
        double preFinal = 0;
        for(int run = 0; run < 1000000; run++){
            preFinal += sharedBirthdays(182, 365);
        }
        return (preFinal/1000000);
     }

     public static String birthdayExperiment(int people){
        //one or more = OOM
        int OOM = 0;
        for(int run = 0; run < 50000; run++){
            if(sharedBirthdays(people, 365) >= 1){
                OOM++;
            }
        }
        return ("Num people: " + people + ", number of experiments with one or more shared birthday: " + OOM + ", percentage: " + ((double)OOM/50000));
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