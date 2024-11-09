import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(365);
        list.add(456);
        list.add(2345);
        list.add(6676);
        list.add(23452);
        list.add(5);
        list.add(6);
        list.add(57673);
        SortedSet<Integer> a = new SortedSet<>();
        a.quicksort(list, 0, list.size() - 1);
        System.out.println(list);
    }
}
