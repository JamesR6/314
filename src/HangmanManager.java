/*  Student information for assignment:
 *
 *  On my honor, JP Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: JP Reeves
 *  email address: jpascualsr06@gmail.com
 *  UTEID: jsr3699
 *  Section 5 digit ID: 50259
 *  Grader name: Eliza
 *  Number of slip days used on this assignment: 0
 */

// add imports as necessary

import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {
 
    // instance variables / fields
    private int StartNumGuesses;
    private boolean debugging;
    private HangmanDifficulty difficulty;
    private StringBuilder currPattern;
    private HashSet<String> Dictionary;
    private TreeSet<Character> guesses;
    private ArrayList<String> activeDict;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        if (words == null || words.size() == 0) {
            throw new IllegalArgumentException("constructor: words must have length");
        }
        Dictionary = new HashSet<>(words);
        debugging = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        if (words == null || words.size() == 0) {
            throw new IllegalArgumentException("constructor: words must have length");
        }
        Dictionary = new HashSet<>(words);
        debugging = false;
    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary
     * with the given length
     */
    public int numWords(int length) {
        int count = 0;
        for (String word : Dictionary) {
            if (word.length() == length) {
                count++;
            }
        }
        return count;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a
     * complete game of Hangman.
     * @param wordLen the length of the word to pick this time.
     * numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the
     * player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        if (wordLen == 0 || numGuesses == 0) {
            throw new IllegalArgumentException("wordLen and numGuesses must be greater than 0");
        }
        difficulty = diff;
        StartNumGuesses = numGuesses;
        activeDict = new ArrayList<>();
        guesses = new TreeSet<>();
        currPattern = new StringBuilder();
        for (int a = 0; a < wordLen; a++) {
            currPattern.append("-");
        }
        for (String word : Dictionary) {
            if (word.length() == wordLen) {
                activeDict.add(word);
            }
        }
    }


    /**
     * The number of words still possible (live) based on the guesses so far.
     *  Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the
     * original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return activeDict.size();
    }


    /**
     * Get the number of wrong guesses the user has left in
     * this round (game) of Hangman.
     * @return the number of wrong guesses the user has left
     * in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return StartNumGuesses - guesses.size();
    }


    /**
     * Return a String that contains the letters the user has guessed
     * so far during this round.
     * The characters in the String are in alphabetical order.
     * The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user
     * has guessed so far during this round.
     */
    public String getGuessesMade() {
        return guesses.toString();
    }


    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     * false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return guesses.contains(guess);
    }


    /**
     * Get the current pattern. The pattern contains '-''s for
     * unrevealed (or guessed) characters and the actual character 
     * for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {
        return currPattern.toString();
    }
//----------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        if (alreadyGuessed(guess)) {
            throw new IllegalArgumentException("Must choose only new characters");
        }

        TreeMap<String, ArrayList<String>> families = new TreeMap<>();
        guesses.add(guess);
        for (String word : activeDict) {
            String wordPat = getPattern(word, "" + guess);
            putInFamilies(families, wordPat, word);
        }
        String chosenPattern = chooseList(families);
        activeDict = new ArrayList<>(families.get(chosenPattern));
        adjustPattern(chosenPattern, "" + guess);
        return convertToStrInt(families);
    }

    /* 
     * given a word and a specific letterm guess, returns a pattern of that word.
     * matching letters will show up in the pattern while anything else will be a wildcard, "-"
     */
    private String getPattern(String word, String guess) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            //the added character is either the guessed letter or a wildcard
            String add = (word.substring(i, i+1).equals(guess)) ? guess : "-";
            sb.append(add);
        }
        return sb.toString();
    }
    /*
     * inserts a given word into its associated family or makes a new family for it and
     * places it there
     */   
    private void putInFamilies(TreeMap<String, ArrayList<String>> families, String pattern, String word) {
        if (!families.containsKey(pattern)) {
            families.put(pattern, new ArrayList<>());
        }
        families.get(pattern).add(word);
    }

    /*
     * chooses which list is most difficult and returns it as an ArrayList<String>
     */
    private String chooseList(TreeMap<String, ArrayList<String>> families) {
        ArrayList<FamilyChooser> ordered = new ArrayList<>();
        Set<String> keys = families.keySet();
        for (String key : keys) {
            FamilyChooser temp = new FamilyChooser(key, families.get(key).size());
            ordered.add(temp);
        }
        //TODO difficulty
        Collections.sort(ordered);
        String chosenPattern = ordered.get(0).getPattern();
        return chosenPattern;
    }

    /*
     * returns the given families treemap but with Integers as values, corresponding to 
     * how many words were in the key's arraylist
     */
    private TreeMap<String, Integer> convertToStrInt(TreeMap<String, ArrayList<String>> families) {
        TreeMap<String, Integer> result = new TreeMap<>();
        Set<String> keys = families.keySet();
        for (String key : keys) {
            Integer value = families.get(key).size();
            result.put(key, value);
        }
        return result;
    }

    private void adjustPattern(String add, String letter) {
        for (int i = 0; i < add.length(); i++) {
            if (add.substring(i, i + 1).equals(letter)) {
                currPattern.replace(i, i + 1, letter);
            }
        }
    }

    
//------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if (activeDict.size() == 0) {
            throw new IllegalStateException("active dictionary must have length 1 or more");
        }
        int index = (int)(Math.random()*(activeDict.size()));
        return activeDict.get(index);
    }


    public class FamilyChooser implements Comparable<FamilyChooser> {
        private String pattern;
        private int wordCount;

        public FamilyChooser(String pattern, int wordCount) {
            this.pattern = pattern;
            this.wordCount = wordCount;
        }

        public String getPattern() {
            return pattern;
        }


        @Override
        public int compareTo(FamilyChooser o) {
            if (wordCount != o.wordCount) {
                return o.wordCount - wordCount;
            } else {
                if (countDashes(pattern) != countDashes(o.pattern)) {
                    return countDashes(o.pattern) - countDashes(pattern);
                } else { //TODO style
                    return o.pattern.compareTo(pattern);
                }
            }
        }

        private int countDashes(String pat) {
            int count = 0;
            for (int i = 0; i < pat.length(); i++) {
                if (pat.substring(i, i + 1).equals("-")) {
                    count++;
                }
            }
            return count;
        }
        
    }
}