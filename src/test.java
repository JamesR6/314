import java.util.ArrayList;
import java.util.HashSet;

public class test {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        a.add("6");
        System.out.println(a);
        int index = (int) (Math.random()*a.size());
        System.out.println(a.get(index));
        

    }
}