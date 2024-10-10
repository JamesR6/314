public class Testing {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        LL314<Integer> debbie = new LL314<>();

        System.out.println(debbie.toString());

        debbie.addFirst(45);

        System.out.println(debbie.toString());
    }
}
