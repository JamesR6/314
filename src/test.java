import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        SortedSet<Integer> s1 = new SortedSet<>();
        UnsortedSet<Integer> s2 = new UnsortedSet<>();

        s1.add(5);
        s1.add(54);
        s1.add(2);
        s1.add(456736);
        s1.add(9);

        s2.add(7);
        s2.add(5);
        s2.add(2);
        s2.add(45373);
        s2.add(0);

        System.out.println(s1.toString() + "\n" + s2.toString());
        System.out.println(s1.addAll(s2));
        System.out.println(s1.toString());

    }
}
