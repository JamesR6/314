/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
 *
 * Student information for assignment:
 *
 *  On my honor, JP Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  TA name: Eliza
 *  Number of slip days I am using: 0
 */

import java.util.HashMap;
import java.util.Map;

public class LetterInventory {
    //instance variable
    private int[] inventory;
    
    public LetterInventory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word can not be null");
        }

        //TODO probably rewrite all of this for efficiency, look at documentation
        final int alphabetLen = 26;
        inventory = new int[alphabetLen];
        String lowercase = word.toLowerCase();

        for (int i = 0; i < lowercase.length(); i++) {
            char letter = lowercase.charAt(i);
            if (Character.isAlphabetic(letter)) {
                inventory[letter - 'a'] += 1;
            }
        }
    }

    public int get(char letter) {
        if (letter < 'A' || letter > 'z' || (letter > 'Z' && letter < 'a')) {
            throw new IllegalArgumentException("character must be alphabetic");
        }

        if (letter >= 'a' && letter <= 'z') {
            return inventory[letter - 'A'];
        }
        return inventory[letter - 'a'];
    }

    public String toString() {
        int i = 1;
        for (int a : inventory) {
            System.out.println(i + " guykryfu" + a);
            i++;
        }
        return "";
    }
    
}
