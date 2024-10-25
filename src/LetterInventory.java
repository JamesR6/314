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


/*
 * Stores the number of times each english letter
 * a through z occur in a given word or phrase
 */
public class LetterInventory {
    //instance variables
    private int[] inventory;
    private int letterCount;
    final private int alphabetLen = 26;
    
    /*
     * return a new LetterInventory object with counted letters of 
     * the given word, regardless of case. Non alphabetic characters ignored.
     * pre: word != null
     */
    public LetterInventory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word can not be null");
        }
        
        inventory = new int[alphabetLen];
        letterCount = 0;
        String lowercased = word.toLowerCase();
        
        for (int i = 0; i < lowercased.length(); i++) {
            char letter = lowercased.charAt(i);
            //ignore if not alphabetic
            if (Character.isAlphabetic(letter)) {
                inventory[letter - 'a'] += 1;
                letterCount++;
            }
        }
    }
    
    
    /*
     * return a new LetterInventory object with the letter counts 
     * derived from a given int array
     * pre: counts != null, counts.length = alphabetLen
     */
    public LetterInventory(int[] counts) {
        if (counts == null || counts.length != alphabetLen) {
            throw new IllegalArgumentException("invalid counts");
        }

        letterCount = 0;
        inventory = new int[alphabetLen];
        for (int i = 0; i < alphabetLen; i++) {
            inventory[i] = counts[i];
            letterCount += counts[i];
        }
    }

    /*
     * return the frequency of a given letter. not case sensitive
     * pre: letter must be an english alphabetic character
     */
    public int get(char letter) {
        if (letter < 'A' || letter > 'z' || (letter > 'Z' && letter < 'a')) {
            throw new IllegalArgumentException("character must be alphabetic");
        }

        //separate by capitalization
        if (letter >= 'a' && letter <= 'z') {
            return inventory[letter - 'a'];
        }
        return inventory[letter - 'A'];
    }

    /*
     * return the total number of letters
     */
    public int size() {
        return letterCount;
    }

    /*
     * return true if size is 0, false otherwise
     */
    public boolean isEmpty() {
        return (letterCount == 0);
    }

    /*
     * return a new LetterInventory of letter counts from this and other
     * added together
     * pre: other != null
     * post: neither this nor other is altered and new LetterInventory has the added
     * counts for each letter
     */
    public LetterInventory add(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("other can not be null");
        }

        //keep track of added counts, use alternative constructor
        int[] result = new int[alphabetLen];
        for (int i = 0; i < alphabetLen; i++) {
            result[i] = inventory[i] + other.get(getLetter(i));
        }

        return new LetterInventory(result);
    }
    
    /*
     * return a new LetterInventory of letter counts from other subtracted from this
     * LetterInventory. If any letter counts would be less than zero, return null
     * pre: otehr != null
     * post: neither this nor other is altered and new LetterInventory has the
     * subtracted counts as described or returns null if any are less than 0
     */
    public LetterInventory subtract(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("other can not be null");
        }

        //keep track of subtracted counts, use alternate constructor
        int[] result = new int[alphabetLen];
        for (int i = 0; i < alphabetLen; i++) {
            int count = inventory[i] - other.get(getLetter(i));
            if (count < 0) {
                return null;
            }
            result[i] = count;
        }

        return new LetterInventory(result);
    }

    /*
     * helper method
     * returns the character at an index in the inventory through
     * ascii math
     */
    private char getLetter(int index) {
        //to avoid casting
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.charAt(index);
    }

    /*
     * Override equals
     * return true if this LetterInentory equals the other as described, false otherwise
     * Both must have the same frequencies of each letter
     * pre: other != null, other must be a LetterInventory
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof LetterInventory)) {
            return false;
        }

        //safe cast after checking instanceof
        LetterInventory o = (LetterInventory) other;

        for (int i = 0; i < alphabetLen; i++) {
            char letter = getLetter(i);
            if (inventory[i] != o.get(letter)) {
                return false;
            }
        }
        return true;
    }

    /*
     * return a string in alphabetical order of the letters present in this LetterInventory
     * repeating letters if need be
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < alphabetLen; i++) {
            for (int ct = 0; ct < inventory[i]; ct++) {
                result.append(getLetter(i));
            }
        }
        return result.toString();
    }    
}
