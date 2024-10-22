import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        LetterInventory one = new LetterInventory("aaabbbba");
        LetterInventory two = new LetterInventory("aaaaabbbb");
        System.out.println(one.substract(two).toString());

    }
}
