import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  Grader name: Eliza
 *  Number of slip days I am using: 0
 */

/* 
    Expiriment 1:
    Matrix addition N = 1000 : 2.77 seconds for 1000 repetitions
    Matrix addition N = 2000 : 10.80 seconds for 1000 repetitions
    Matrix addition N = 4000 : 40.45 seconds for 1000 repetitions

    Expiriment 2:
    Matrix multiplication N = 200 : 1.21 for 100 repetitions
    Matrix multiplication N = 400 : 8.28  100 repetitions
    Matrix multiplication N = 800 : 79.60 for 100 repetitions

    1. I would expect the time to be around 160 seconds because the pattern is N^2.
    2. Based on analysis, my code for addition seems to be N^2 and 
       that is consistent with my timing data.
    3. I would expect the time to be around 600 seconds or 10 minutes.
    4. Based on analysis, my code for multiplications seems to be 
       N^3 which is supported by the drastic timing changes in my data.
    5. My laptop was able to make a matrix under the given conditions
       with N = 32000 but not N = 33000. This translated to gigbytes is
       4.096 gigabytes. This is about 1/8 of my laptop's 32GB or RAM.
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = { { 1, 2, 3 },
                { 2, 3, 4 } };

        int[][] data2 = { { 2, 1, 1 },
                { 2, 3, 1 } };
        int[][] e1;

        // specify size and values constructor
        MathMatrix mat1 = new MathMatrix(2, 5, -1);
        e1 = new int[][] { { -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1 } };
        printTestResult(get2DArray(mat1), e1, 1,
                "Constructor with size and initial val specified.");
        mat1 = new MathMatrix(2, 5, 100);
        e1 = new int[][] { { 100, 100, 100, 100, 100 }, { 100, 100, 100, 100, 100 } };
        printTestResult(get2DArray(mat1), e1, 2,
                "Constructor with size and initial val specified.");

        // Constructor with one input
        e1 = new int[][] { { 2, 2, 3 }, { 2, 3, 4 } };
        mat1 = new MathMatrix(e1);
        printTestResult(get2DArray(mat1), e1, 3, "constructor with one parameter of type int[][]");
        e1 = new int[][] { { 0 } };
        mat1 = new MathMatrix(e1);
        printTestResult(get2DArray(mat1), e1, 4, "constructor with one parameter of type int[][]");

        // getNumRows
        data1 = new int[][] { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 }, { 1, 2, 3, 4 } };
        mat1 = new MathMatrix(data1);
        int expected = 4;
        if (mat1.getNumRows() == expected) {
            System.out.println("Passed test 5, getNumRows method.");
        } else {
            System.out.println("Failed test 5, getNumRows method.");
        }
        data1 = new int[][] { { 1, 2, 3, 0 } };
        mat1 = new MathMatrix(data1);
        expected = 1;
        if (mat1.getNumRows() == expected) {
            System.out.println("Passed test 6, getNumRows method.");
        } else {
            System.out.println("Failed test 6, getNumRows method.");
        }

        // getNumCols
        data1 = new int[][] { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 }, { 1, 2, 3, 4 } };
        mat1 = new MathMatrix(data1);
        expected = 4;
        if (mat1.getNumColumns() == expected) {
            System.out.println("Passed test 7, getNumColumns method.");
        } else {
            System.out.println("Failed test 7, getNumColumns method.");
        }
        data1 = new int[][] { { 1, 2, 3, 0 } };
        mat1 = new MathMatrix(data1);
        if (mat1.getNumColumns() == expected) {
            System.out.println("Passed test 8, getNumColumns method.");
        } else {
            System.out.println("Failed test 8, getNumColumns method.");
        }

        // getVal
        data1 = new int[][] { { 1, 2, 3, 0 } };
        mat1 = new MathMatrix(data1);
        expected = 3;
        if (mat1.getVal(0, 2) == expected) {
            System.out.println("Passed test 9, getVal method.");
        } else {
            System.out.println("Failed test 9, getVal method.");
        }
        expected = 1;
        if (mat1.getVal(0, 0) == expected) {
            System.out.println("Passed test 10, getVal method.");
        } else {
            System.out.println("Failed test 10, getVal method.");
        }

        // addition
        data1 = new int[][] { { 1, 2, 3 },
                { 2, 3, 4 } };

        data2 = new int[][] { { 2, 1, 1 },
                { 2, 3, 1 } };
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][] { { 3, 3, 4 },
                { 4, 6, 5 } };
        printTestResult(get2DArray(mat3), e1, 11, "add method. ");
        MathMatrix mat4 = mat3.add(mat3);
        e1 = new int[][] { { 6, 6, 8 },
                { 8, 12, 10 } };
        printTestResult(get2DArray(mat4), e1, 12, "add method. ");

        // subtract
        e1 = new int[][] { { 5, 4, 5 },
                { 6, 9, 6 } };
        mat3 = mat4.subtract(mat1);
        printTestResult(get2DArray(mat3), e1, 13, "subtract method. ");
        mat3 = mat3.subtract(mat3);
        MathMatrix mat5 = new MathMatrix(2, 3, 0);
        printTestResult(get2DArray(mat3), get2DArray(mat5), 14, "subtract method. ");

        // Multiply
        data1 = new int[][] { { 1, 2, 3 },
                { 2, 3, 4 } };
        data2 = new int[][] { { 2, 1, 1 },
                { 2, 3, 1 },
                { 4, 9, 6 } };
        int[][] result = new int[][] { { 18, 34, 21 },
                { 26, 47, 29 } };
        MathMatrix mult1 = new MathMatrix(data1);
        MathMatrix mult2 = new MathMatrix(data2);
        MathMatrix mult3 = mult1.multiply(mult2);
        printTestResult(get2DArray(mult3), result, 15, "multiply method. ");
        MathMatrix mult4 = new MathMatrix(3, 3, 0);
        MathMatrix mult5 = mult1.multiply(mult4);
        int[][] data3 = new int[][] { { 0, 0, 0 },
                { 0, 0, 0 } };
        printTestResult(get2DArray(mult5), data3, 16, "multiply method. ");

        // scaled
        MathMatrix scale1 = mult3.getScaledMatrix(100);
        result = new int[][] { { 1800, 3400, 2100 },
                { 2600, 4700, 2900 } };
        printTestResult(get2DArray(scale1), result, 17, "getScaledMatrix method. ");
        scale1 = scale1.getScaledMatrix(-1);
        result = new int[][] { { -1800, -3400, -2100 },
                { -2600, -4700, -2900 } };
        printTestResult(get2DArray(scale1), result, 18, "getScaledMatrix method. ");

        // toString
        MathMatrix toS = new MathMatrix(result);
        String answer = "| -1800 -3400 -2100|\n" +
                "| -2600 -4700 -2900|\n";
        if (toS.toString().equals(answer)) {
            System.out.println("passed test 19, toString method.");
        } else {
            System.out.println("failed test 19, toString method.");
        }
        result = new int[][] { { 0, -1, 0 },
                { 1, 4, 9 } };
        toS = new MathMatrix(result);
        answer = "|  0 -1  0|\n" +
                "|  1  4  9|\n";
        if (toS.toString().equals(answer)) {
            System.out.println("passed test 20, toString method.");
        } else {
            System.out.println("failed test 20, toString method.");
        }

        // isUpperTriangle
        MathMatrix tri1 = new MathMatrix(1, 1, 1);
        if (tri1.isUpperTriangular()) {
            System.out.println("passed test 21, isUpperTriangle method.");
        } else {
            System.out.println("failed test 21, isUpperTriangle method.");
        }
        result = new int[][] { { 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1 },
                { 0, 0, 1, 1, 1 },
                { 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1 }, };
        toS = new MathMatrix(result);
        if (toS.isUpperTriangular()) {
            System.out.println("passed test 22, isUpperTriangle method.");
        } else {
            System.out.println("failed test 22, isUpperTriangle method.");
        }

        // equals
        MathMatrix a = new MathMatrix(100, 100, -234);
        MathMatrix b = new MathMatrix(100, 100, -234);
        if (a.equals(b)) {
            System.out.println("passed test 23, equals method.");
        } else {
            System.out.println("failed test 23, equals method.");
        }
        a = new MathMatrix(1, 1, 1);
        int[][] test = { { 1 } };
        b = new MathMatrix(test);
        if (a.equals(b)) {
            System.out.println("passed test 24, equals method.");
        } else {
            System.out.println("failed test 24, equals method.");
        }

        // transpose
        MathMatrix tra = a.getTranspose();
        if (tra.equals(a)) {
            System.out.println("passed test 25, getTranspose method.");
        } else {
            System.out.println("failed test 25, getTranspose method.");
        }
        int[][] tra2 = new int[][] { { 1, 2, 3, 4, 5 } };
        int[][] tra3 = new int[][] { { 1 }, { 2 }, { 3 }, { 4 }, { 5 } };
        a = new MathMatrix(tra2);
        b = a.getTranspose();
        printTestResult(get2DArray(b), tra3, 26, "getTranspose method. ");

        // START EXPERIMENTS
        Random randNumGen = new Random(6201919);
        final int MAGIC_SUM = -1190513360;
        final int LIMIT = 1000;
        int N = 1000;
        mat1 = createMat(randNumGen, N, N, LIMIT);
        mat2 = createMat(randNumGen, N, N, LIMIT);
        Stopwatch st = new Stopwatch();

        // Expiriment 1
        // int TESTS = 1000;
        // st.start();
        // for (int x = 0; x < TESTS; x++) {
        // mat1.add(mat2);
        // }
        // st.stop();
        // System.out.println(st.time());

        // //double dimensions
        // N = 2000;
        // mat1 = createMat(randNumGen, N, N, LIMIT);
        // mat2 = createMat(randNumGen, N, N, LIMIT);
        // st.start();
        // for (int x = 0; x < TESTS; x++) {
        // mat1.add(mat2);
        // }
        // st.stop();
        // System.out.println(st.time());

        // //quadruple dimensions
        // N = 4000;
        // mat1 = createMat(randNumGen, N, N, LIMIT);
        // mat2 = createMat(randNumGen, N, N, LIMIT);
        // st.start();
        // for (int x = 0; x < TESTS; x++) {
        // mat1.add(mat2);
        // }
        // st.stop();
        // System.out.println(st.time());

        // Expiriment 2
        // N = 200;
        // int TESTS = 100;
        // mat1 = createMat(randNumGen, N, N, LIMIT);
        // mat2 = createMat(randNumGen, N, N, LIMIT);
        // st.start();
        // for (int x = 0; x < TESTS; x++) {
        // mat1.multiply(mat2);
        // }
        // st.stop();
        // System.out.println(st.time());

        // //Double dimensions
        // N = 400;
        // mat1 = createMat(randNumGen, N, N, LIMIT);
        // mat2 = createMat(randNumGen, N, N, LIMIT);
        // st.start();
        // for (int x = 0; x < TESTS; x++) {
        // mat1.multiply(mat2);
        // }
        // st.stop();
        // System.out.println(st.time());

        // //quadruple dimensions
        // N = 800;
        // mat1 = createMat(randNumGen, N, N, LIMIT);
        // mat2 = createMat(randNumGen, N, N, LIMIT);
        // st.start();
        // for (int x = 0; x < TESTS; x++) {
        // mat1.multiply(mat2);
        // }
        // st.stop();
        // System.out.println(st.time());

        // Biggest possible matrix experiment
        // N = 33000;
        // mat1 = createMat(randNumGen, N, N, LIMIT);

    }

    // ---------------------------------------------------------------------------------------

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        }
        int result = 0;
        final int ROWS = mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum,
            String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat + ". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        // check precondition
        if ((m == null) || (m.getNumRows() == 0)
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r, c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1
    // matrices
    // data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    // the same
    private static boolean equals(int[][] data1, int[][] data2) {
        // check precondition
        if ((data1 == null) || (data1.length == 0)
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                || (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }

    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null"
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}
