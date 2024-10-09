public class Testing {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        LL314<Integer> debbie = new LL314<>();
        debbie.add(0);
        debbie.add(1);
        debbie.add(2);
        debbie.add(3);
        debbie.add(4);

        System.out.println(debbie.toString());

        debbie.removeLast();

        System.out.println(debbie.toString());
    }
}
