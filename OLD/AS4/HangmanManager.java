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
     * 
     * @param words   A set with the words for this instance of Hangman.
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
     * 
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
     * 
     * @param length The given length to check.
     * @return the number of words in the original Dictionary
     *         with the given length
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
     * 
     * @param wordLen    the length of the word to pick this time.
     *                   numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the
     *                   player loses the round. numGuesses >= 1
     * @param diff       The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        if (wordLen == 0 || numGuesses == 0) {
            throw new IllegalArgumentException("wordLen and numGuesses must be greater than 0");
        }
        // empty all lists and variables
        difficulty = diff;
        StartNumGuesses = numGuesses;
        activeDict = new ArrayList<>();
        guesses = new TreeSet<>();
        currPattern = new StringBuilder();
        // currPattern reset to only wildcards, length = wordLen
        for (int a = 0; a < wordLen; a++) {
            currPattern.append("-");
        }
        // reset active Dictionary to have every word with the correct length
        for (String word : Dictionary) {
            if (word.length() == wordLen) {
                activeDict.add(word);
            }
        }
    }

    /**
     * The number of words still possible (live) based on the guesses so far.
     * Guesses will eliminate possible words.
     * 
     * @return the number of words that are still possibilities based on the
     *         original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return activeDict.size();
    }

    /**
     * Get the number of wrong guesses the user has left in
     * this round (game) of Hangman.
     * 
     * @return the number of wrong guesses the user has left
     *         in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        int wrongGuesses = 0;
        for (char letter : guesses) {
            if (currPattern.indexOf("" + letter) == -1) {
                wrongGuesses++;
            }
        }
        return StartNumGuesses - wrongGuesses;
    }

    /**
     * Return a String that contains the letters the user has guessed
     * so far during this round.
     * The characters in the String are in alphabetical order.
     * The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * 
     * @return a String that contains the letters the user
     *         has guessed so far during this round.
     */
    public String getGuessesMade() {
        return guesses.toString();
    }

    /**
     * Check the status of a character.
     * 
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     *         false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return guesses.contains(guess);
    }

    /**
     * Get the current pattern. The pattern contains '-''s for
     * unrevealed (or guessed) characters and the actual character
     * for "correctly guessed" characters.
     * 
     * @return the current pattern.
     */
    public String getPattern() {
        return currPattern.toString();
    }

    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * 
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     *         words in each of the new patterns.
     *         The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        if (alreadyGuessed(guess)) {
            throw new IllegalArgumentException("Must choose only new characters");
        }

        // put every word and its pattern into a family
        TreeMap<String, ArrayList<String>> families = new TreeMap<>();
        for (String word : activeDict) {
            String wordPat = getPattern(word, "" + guess);
            putInFamilies(families, wordPat, word);
        }

        // update necessary lists and variables
        guesses.add(guess);
        currPattern = new StringBuilder(chooseList(families));
        activeDict = new ArrayList<>(families.get(currPattern.toString()));
        // convert families to correct return type
        return convertToInt(families);
    }

    /*
     * given a word and a chosen letter, return a pattern of that word.
     * matching letters will show up in the pattern while anything else will be
     * the associated character in currPattern
     */
    private String getPattern(String word, String guess) {
        // sb starts as currPattern
        StringBuilder sb = new StringBuilder(currPattern);
        for (int i = 0; i < word.length(); i++) {
            // any letters to add replaces a wildcard in sb
            if (word.substring(i, i + 1).equals(guess)) {
                sb.replace(i, i + 1, guess);
            }
        }
        return sb.toString();
    }

    /*
     * inserts a given word into its associated family or makes a new family for it
     * and
     * places it there
     */
    private void putInFamilies(TreeMap<String, ArrayList<String>> families,
            String pattern, String word) {
        if (!families.containsKey(pattern)) {
            families.put(pattern, new ArrayList<>());
        }
        families.get(pattern).add(word);
    }

    /*
     * chooses which pattern is to be chosen out of families and returns it as an
     * String.
     * Determines which list based on difficulty and round.
     */
    private String chooseList(TreeMap<String, ArrayList<String>> families) {
        // make a difficulty ordered list
        ArrayList<FamilyChooser> ordered = new ArrayList<>();
        Set<String> keys = families.keySet();
        for (String key : keys) {
            FamilyChooser temp = new FamilyChooser(key, families.get(key).size());
            ordered.add(temp);
        }
        Collections.sort(ordered);

        // determine if most or second most pattern is chosen
        int chosenIndex = 0;
        if (ordered.size() > 1 && !diffTracker()) {
            chosenIndex = 1;
        }
        String chosenPattern = ordered.get(chosenIndex).getPattern();

        // print debugging lines
        if (debugging) {
            if (ordered.size() == 1 && !diffTracker()) {
                System.out.println("\nDEBUGGING: Should pick second hardest pattern " +
                        "this turn, but only one pattern available.");
            }
            printDebug(chosenPattern, chosenIndex == 0, families.get(chosenPattern).size());
        }

        return chosenPattern;
    }

    /*
     * if debugging is activated, prints out what pattern is being chosen for that
     * round
     * and other information like the number of words associated with that pattern.
     */
    private void printDebug(String chosenPattern, boolean hardest, int numWords) {
        if (hardest) {
            System.out.println("\nDEBUGGING: Picking hardest list.");
        } else {
            System.out.println("\nDEBUGGING: Difficulty second hardest pattern and list.\n");
        }
        System.out.println("DEBUGGING: New pattern is: " + chosenPattern +
                ". New family has " + numWords + " words.\n");
    }

    /*
     * based on the difficulty chosen at the beginning of the game, determines
     * if the current round should choose the most difficuly familiy or the second
     * post: returns true if most difficult and false otherwise
     */
    private boolean diffTracker() {
        int round = guesses.size();
        if (difficulty == HangmanDifficulty.HARD) {
            return true;
        } else if (difficulty == HangmanDifficulty.MEDIUM && (round % 4 == 0)) {
            return false;
        } else if (difficulty == HangmanDifficulty.EASY && round % 2 == 0) {
            return false;
        }
        return true;
    }

    /*
     * returns the given families treemap but with Integers as values, corresponding
     * to
     * how many words were in the key's arraylist
     */
    private TreeMap<String, Integer> convertToInt(TreeMap<String, ArrayList<String>> families) {
        TreeMap<String, Integer> result = new TreeMap<>();
        Set<String> keys = families.keySet();
        for (String key : keys) {
            // get amount of words in associated pattern's family
            Integer value = families.get(key).size();
            result.put(key, value);
        }
        return result;
    }

    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br>
     * pre: numWordsCurrent() > 0
     * 
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if (activeDict.size() == 0) {
            throw new IllegalStateException("active dictionary must have length 1 or more");
        }

        int index = (int) (Math.random() * (activeDict.size()));
        return activeDict.get(index);
    }

    /*
     * Converts families into comparable objects with their pattern and
     * amount of associated words as variables.
     */
    private class FamilyChooser implements Comparable<FamilyChooser> {
        private String pattern;
        private int wordCount;

        /*
         * construct a new FamilyChooser object. wordCount is the amount of words
         * in the pattern's family
         */
        public FamilyChooser(String pattern, int wordCount) {
            this.pattern = pattern;
            this.wordCount = wordCount;
        }

        /*
         * return the pattern of this FamilyChooser
         */
        public String getPattern() {
            return pattern;
        }

        /*
         * override of compareTo
         * sorts first by amount of words (more words take priority),
         * then by letters revealed (less reveals takes priority),
         * then by lexicographically comparing the patterns.
         */
        @Override
        public int compareTo(FamilyChooser o) {
            // first priority: amount of words
            if (wordCount != o.wordCount) {
                return o.wordCount - wordCount;
            } else if (countDashes(pattern) != countDashes(o.pattern)) {
                return countDashes(o.pattern) - countDashes(pattern);
            } else {
                return pattern.compareTo(o.pattern);
            }
        }

        /*
         * return the number of dashes (wildcards) in the given pattern, pat
         */
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