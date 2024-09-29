import java.util.ArrayList;
import java.util.HashSet;

public class test {
    public static void main(String[] args) {
        HashSet<String> a = new HashSet<>();
        a.add("hello");
        HashSet<String> b = new HashSet<>(a);
        b.add("world");
        System.out.println(a);
        System.out.println(b);

    }
}