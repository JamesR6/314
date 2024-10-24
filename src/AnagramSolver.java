/* CS 314 STUDENTS: FILL IN THIS HEADER.
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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnagramSolver {

    //instance variable
    Map<String, LetterInventory> dictionary;
    
    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        // CS314 Students, add your code here
        this.dictionary = new TreeMap<String, LetterInventory>();
        for (String word : dictionary) {
            this.dictionary.put(word, new LetterInventory(word));
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {

        LetterInventory target = new LetterInventory(s);
        if (maxWords < 0 || s == null || s.length() == 0 || target.isEmpty()) {
            throw new IllegalArgumentException("invalid arguements");
        }

        //TODO choice of map
        Map<String, LetterInventory> virtualDict = new TreeMap<String,LetterInventory>();
        for (String word : dictionary.keySet()) {
            if (target.substract(dictionary.get(word)) != null) {
                virtualDict.put(word, new LetterInventory(word));
            }
        }

        if (maxWords == 0 || maxWords > s.length()) {
            maxWords = s.length();
        }

        return recurAnagrams(target, maxWords, virtualDict, 0, new ArrayList<String>());
    }

    private List<List<String>> recurAnagrams(LetterInventory lettersLeft, int maxWords, 
    Map<String, LetterInventory> virtualDict, int index, ArrayList<String> curr) {
        //base cases
        if (whatever) {
            do whatever
        }

        List<String> result = new ArrayList<>();
        //recursive step
        return result;
    }
    //newest change

}