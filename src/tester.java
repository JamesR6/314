public class tester {
    public static void main(String[] args) {
        int[][] world = {   { 5, 5, 5, 5, 5, 5 },
                            { 4, 5, 5, 5, 5, 5 },
                            { 4, 5, 5, 5, 5, 5 },
                            { 5, 5, 4, 4, 5, 5 },
                            { 5, 5, 3, 3, 5, 5 },
                            { 5, 5, 2, 2, 5, 5 },
                            { 5, 5, 5, 1, 5, 5 },
                            { 5, 5, 5, -2, 5, 5 } };

        System.out.println(Recursive.canFlowOffMap(world, 1, 1));
    }
}
