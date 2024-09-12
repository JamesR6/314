import java.util.Arrays;

// MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, James Reeves, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: jsr3699
*  email address: jpascualsr06@gmail.com
*  Unique section number: 50259
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

    // instance variable
    private int[][] nums;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0 || !rectangularMatrix(mat)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "MathMatrix: mat can not be null. Neither dimension may have 0 length");
        }
        nums = new int[mat.length][mat[0].length]; 
        for (int y = 0; y < mat.length; y++) {
            for (int x = 0; x < mat[0].length; x++) {
                nums[y][x] = mat[y][x];
            }
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns.
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "MathMatrix: Neither dimension may have 0 or negative length");
        }
        nums = new int[numRows][numCols];
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                nums[y][x] = initialVal;
            }
        }
    }

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return nums.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns(){
        return nums[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        if (row < 0 || col < 0 || row >= getNumRows() || col >= getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getVal: row and col must be inside MathMatrix bounds");
        }
        return nums[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns
     * in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide){
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
            rightHandSide.getNumColumns() != getNumColumns()) {
                throw new IllegalArgumentException("Violation of precondition: " +
                    "add: rightHandSize must not be null and must have the same dimensions " + 
                    "as the other MathMatrix");
            }
        int[][] fin = new int[nums.length][nums[0].length];
        for (int y = 0; y < getNumRows(); y++){
            for (int x = 0; x < getNumColumns(); x++){
                fin[y][x] = (nums[y][x] + rightHandSide.getVal(y, x));
            }
        }
        return (new MathMatrix(fin));
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     * from this MathMatrix. The number of rows in the returned MathMatrix is equal to the number
     * of rows in this MathMatrix.The number of columns in the returned MathMatrix is equal to
     * the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide){
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
            rightHandSide.getNumColumns() != getNumColumns()) {
                throw new IllegalArgumentException("Violation of precondition: " +
                    "subtract: rightHandSize must not be null and must have the same dimensions " + 
                    "as the other MathMatrix");
            }
        int[][] fin = new int[nums.length][nums[0].length];
        for (int y = 0; y < getNumRows(); y++){
            for (int x = 0; x < getNumColumns(); x++){
                fin[y][x] = (nums[y][x] - rightHandSide.getVal(y, x));
            }
        }
        return (new MathMatrix(fin));
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying
     * this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows
     * in this MathMatrix. The number of columns in the returned MathMatrix is equal to the number
     * of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide){
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumColumns()) {
                throw new IllegalArgumentException("Violation of precondition: " +
                    "multiply: rightHandSize must not be null and must have the same number " + 
                    "of rows as the LEFT matrix has columns");
            }
        int[][] fin = new int[getNumRows()][rightHandSide.getNumColumns()];
        for (int y = 0; y < fin.length; y++) {
            for (int x = 0; x < fin[0].length; x++) {
                for (int tracer = 0; tracer < getNumColumns(); tracer++) {
                    fin[y][x] += (getVal(y, tracer) * rightHandSide.getVal(tracer, x));
                }
            }
        }
        return (new MathMatrix(fin));
    }


    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix
     * multiplied by factor.
     * In other words after this method has been called
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
        int[][] fin = new int[nums.length][nums[0].length];
        for (int y = 0; y < fin.length; y++) {
            for (int x = 0; x < fin[0].length; x++){
                fin[y][x] = nums[y][x] * factor;
            }
        }
        return (new MathMatrix(fin));
    }


    /**
     * accessor: get a transpose of this MathMatrix.
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        int[][] fin = new int[nums[0].length][nums.length];
        for (int y = 0; y < nums.length; y++) {
            for (int x = 0; x < nums[0].length; x++) {
                fin[x][y] = nums[y][x];
            }
        }
        return new MathMatrix(fin);
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide){
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         *
         * We use getClass instead of instanceof because we only want a MathMatrix to equal
         * another MathMatrix as opposed to any possible sub classes. We would
         * use instance of if we were implementing am interface and wanted to equal
         * other objects that are instances of that interface but not necessarily
         * MathMatrix objects.
         */

        if (rightHandSide == null || this.getClass() != rightHandSide.getClass()) {
            return false;
        }
        // We know rightHandSide refers to a non-null MathMatrix object, safe to cast.
        MathMatrix otherMathMatrix = (MathMatrix) rightHandSide;
        // Now we can access the private instance variables of otherMathMatrix
        // and / or call MathMatrix methods on otherMathMatrix.
        
        if (this.getNumColumns() != otherMathMatrix.getNumColumns() || this.getNumRows() != otherMathMatrix.getNumRows()) {
            return false;
        }
        for (int y = 0; y < this.getNumRows(); y++) {
            for (int x = 0; x < this.getNumColumns(); x++) {
                if (this.getVal(y, x) != otherMathMatrix.getVal(y, x)) {
                    return false;
                }
            }
        }
        return true;







        
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix.
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
        int spacer = longestNumber() + 1;
        StringBuilder fin = new StringBuilder("");
        for (int y = 0; y < nums.length; y++) {
            fin.append("|");
            for (int x = 0; x < nums[0].length; x++) {
                for (int spaces = 0; spaces < amountOfSpace(nums[y][x], spacer); spaces++) {
                    fin.append(" ");
                }
                fin.append(nums[y][x]);
            }
            
            fin.append("|\n");
        }
        return fin.toString();
    }

    private int amountOfSpace(int num, int spacer){
        int len = ("" + num).length();
        return spacer - len;
    }

    private int longestNumber(){
        if (nums.length == 0 || nums[0].length == 0) {
            return 0;
        }

        int largest = 0;
        for (int y = 0; y < nums.length; y++) {
            for (int x = 0; x < nums[0].length; x++) {
                int len = ("" + nums[y][x]).length();
                if (len > largest) {
                    largest = len;
                }
            }
        }
        return largest;
    }



    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise.
     */
    public boolean isUpperTriangular(){
        if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isUpperTriangular: must be a square matrix");
        }

        for (int y = 0; y < nums.length; y++) {
            for (int x = 0; x < y; x++) {
                if (nums[y][x] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ensure mat is a rectangular matrix. Method is public so
     * client classes can also use it.
     * @param mat mat != null, mat must have at least one row,
     * there must be at least one column in the first row, and
     * all rows in mat must have the same number of columns.
     * @return true if mat is rectangular, false otherwise.
     */
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        } else if (mat[0] == null) {
            throw new IllegalArgumentException("argument may not have null rows. "
                    + "mat = " + Arrays.toString(mat));
        } else if (mat[0].length == 0) {
            throw new IllegalArgumentException("argument mat must have at least "
                    + "one column per row.");
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            // Odd to put this here. Maybe do a two pass approach?
            if (mat[row] == null) {
                throw new IllegalArgumentException("argument may not have null rows. "
                        + "mat = " + Arrays.toString(mat));
            }
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}