import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\jpasc\\VsCodeProj\\UTCS\\314\\src\\names.txt"));
        int base = Integer.parseInt(sc.nextLine());
        int decades = Integer.parseInt(sc.nextLine());
        while (sc.hasNextLine()) {
            String name = sc.next();
            String[] strRanks = sc.nextLine().substring(1).split(" ");
            if (passes(strRanks, decades)) {
                System.out.println(name);
            }
        }
    }

    private static boolean passes(String[] ranks, int decades) {
        if (ranks.length != decades) {
            return false;
        }
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals("0")) {
                return true;
            }
        }
        return false;
    }
}
