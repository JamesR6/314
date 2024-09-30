/*  Student information for assignment:
 *
 *  On my honor, JP Reeves, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: JP Reeves
 *  email address: jpascualsr06@gmail.com
 *  UTEID: jsr3699
 *  Section 5 digit ID: 
 *  Grader name: Eliza
 *  Number of slip days used on this assignment:
 */

// add imports as necessary

import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {
 
    // instance variables / fields
    private int wordLen;
    private int StartNumGuesses;
    private boolean debugging;
    private HangmanDifficulty diff;
    private StringBuilder currPattern;
    private HashSet<String> Dictionary;
    private HashSet<Character> guesses;
    private ArrayList<String> activeDict;
    private TreeMap<String, ArrayList<String>> families;

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
        this.Dictionary = new HashSet<>(words);
        this.debugging = debugOn;
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
        this.Dictionary = new HashSet<>(words);
        this.debugging = false;
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
        this.wordLen = wordLen;
        this.StartNumGuesses = numGuesses;
        this.diff = diff;
        this.activeDict = new ArrayList<>(this.Dictionary);
        this.guesses = new HashSet<>();
        this.families = new TreeMap<>();
        for (int a = 0; a < wordLen; a++) {
            this.currPattern.append('-');
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
        return !guesses.contains(guess);
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


    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        return null;
    }


    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        if (this.activeDict.size() == 0) {
            throw new IllegalStateException("active dictionary must have length 1 or more");
        }
        int index = (int)(Math.random()*(activeDict.size()));
        return activeDict.get(index);
    }
}