public class Testing {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        LL314<Integer> debbie = new LL314<>();

        for (int a = 0; a < Math.pow(2, 2); a++) {
            debbie.add(a);
        }

        Stopwatch watch = new Stopwatch();
        for (int run = 23; run < 27; run++) {
            watch.start();
            debbie.getSubList(0, (int) Math.pow(2, run) - 1);
            watch.stop();
            System.out.println(Math.pow(2, run) + "  " + watch.time());
        }
    }
}
