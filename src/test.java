import java.util.ArrayList;
import java.util.HashSet;


public class test { 
    public static void main(String[] args) {
        aaaa();
    }

    public static void aaaa() {
        ArrayList<classs> result = new ArrayList<>();
        for (int a = 0; a < 100; a++) {
            classs ad = new classs(a);
            result.add(ad);
        }
        Collections.sort(result);
        System.out.println(result.toString());
    }

    public static class classs implements Comparable<classs> {
        private int app;

        public classs (int a) {
            app = a;
        }

        @Override
        public int compareTo(classs o) {
            return o.app - app;
        }

        public String toString() {
            return "" + app;
        }
        
    }
}