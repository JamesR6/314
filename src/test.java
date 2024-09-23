import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add("hello");
        a.add(new ArrayList());
        a.add(new String[4]);
        System.out.println(a.toString());
    }
}