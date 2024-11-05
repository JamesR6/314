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
import java.util.Collections;
import java.util.Comparator;
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

        //only add valid words to virtual dictionary (preprocessing)
        ArrayList<String> virtualDict = new ArrayList<>();
        for (String word : dictionary.keySet()) {
            if (target.subtract(dictionary.get(word)) != null) {
                virtualDict.add(word);
            }
        }

        //max words tops at the length of s; words can not have length 0
        if (maxWords == 0 || maxWords > s.length()) {
            maxWords = s.length();
        }

        ArrayList<List<String>> results = new ArrayList<>();
        recurAnagrams(target, maxWords, virtualDict, 0, new ArrayList<>(), results);
        Collections.sort(results, new AnagramComparator());
        return results;
    }

    /*
     * recursive method for getAnagrams
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    private void recurAnagrams(LetterInventory lettersLeft, int maxWords, 
    ArrayList<String> virtualDict, int index, ArrayList<String> curr, 
    ArrayList<List<String>> anagrams) {
        //base cases
        //stop recursion if either are true, but only add if both are true
        if (curr.size() == maxWords || lettersLeft.isEmpty()) {
            if (lettersLeft.isEmpty() && curr.size() <= maxWords) {
                anagrams.add(new ArrayList<>(curr));
            }
        } else {
            //recursive step, loop from index to account for duplicate words
            for (int i = index; i < virtualDict.size(); i++) {
                String word = virtualDict.get(i);
                LetterInventory test = lettersLeft.subtract(dictionary.get(word));
                //if the word can be chosen given lettersLeft, initiate recursion
                if (test != null) {
                    curr.add(word);
                    recurAnagrams(test, maxWords, virtualDict, i, curr, anagrams);
                    curr.remove(word);
                }
            }
        }
    }


    /*
     * comparator class to sort each anagram arrayList
     * First by number of words (less words takes priority)
     * Then by the words themselves
     */
    private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> a1, List<String> a2) {
            int sizes = a1.size() - a2.size();
            if (sizes != 0) {
                return sizes;
            } else {
                //each string compared to corresponding index
                for (int i = 0; i < a1.size(); i++) {
                    int compareTo = a1.get(i).compareTo(a2.get(i));
                    if (compareTo != 0) {
                        return compareTo;
                    }
                }
            }
            return 0;
        }
    }

}