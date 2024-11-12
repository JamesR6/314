
/*
 * Student information for assignment:
 *
 * Number of slip days used: 2
 * Student 1 (Student whose turnin account is being used)
 *  UTEID: jsr3699
 *  email address: jpascualsr06@gmail.com
 *  Grader name: Eliza
 *
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.UIManager;

import javax.swing.JFileChooser;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * Each implementation of the 3 methods in each subclass will have their own
 * efficiencies and complications that would make it most ideal to require
 * it be implemented but not have a super/general implementation. This would
 * also require explicit instantiations of subclasses.
 */

/*
 * Unsorted
 * File       Size  Total Words  Inc prev  Unique words   incr prev  actual time  incr prev
 * d2.txt      1       3927           -        3927           -          0.05        -
 * Alice.txt  171     29564        7.52x       5972          1.52x       0.25       5x
 * rainb.txt  1061    188919       4.02x      22038          3.69x       4.48      17.9x
 * ciaFB.txt  7263   1027612       5.44x      66552          3.02x       85.26      19.03x
 * 
 * Sorted
 * File       Size  Total Words  Inc prev  Unique words   incr prev  actual time  incr prev
 * d2.txt      1       3927           -        3927           -         0.03         -
 * Alice.txt  171     29564        7.52x       5972          1.52x      0.07        2.33
 * rainb.txt  1061    188919       4.02x      22038          3.69x      0.22         3.14x
 * ciaFB.txt  7263   1027612       5.44x      66552          3.02x      0.89         4.05x
 * 
 * TreeSet
 * File       Size  Total Words  Inc prev  Unique words   incr prev  actual time  incr prev
 * d2.txt      1       3927           -        3927           -          0.005       -
 * Alice.txt  171     29564        7.52x       5972          1.52x       0.023       4.6x
 * rainb.txt  1061    188919       4.02x      22038          3.69x       0.099       4.3x
 * ciaFB.txt  7263   1027612       5.44x      66552          3.02x       0.67        6.77x
 * 
 * HashSet
 * File       Size  Total Words  Inc prev  Unique words   incr prev  actual time  incr prev
 * d2.txt      1       3927           -        3927           -         0.01         -
 * Alice.txt  171     29564        7.52x       5972          1.52x      0.015        1.5x
 * rainb.txt  1061    188919       4.02x      22038          3.69x      0.097        6.47
 * ciaFB.txt  7263   1027612       5.44x      66552          3.02x      0.50         5.15
 * 
 * I think the big O of the processText methods are O(N * (time complexity of given add method))
 * The time takes longer if there are more distinct words because the time complexity of add
 * depends on if this word is already in the set
 * 
 * My add mathods have Sorted: O(N) and Unsorted: O(N) time complexity
 * I think HashSet has a big O of O(1) for add and TreeSet has O(logN) for add
 * treeSet prints the words in an ordered fashion while hashset prints what 
 * seems to be random to the user.
 * 
 */

public class SetTester {

        public static void main(String[] args) {

                ISet<Integer> s1 = new SortedSet<>();
                ISet<Integer> s2 = new SortedSet<>();
                ISet<Integer> u1 = new UnsortedSet<>();
                ISet<Integer> u2 = new UnsortedSet<>();

                s1.add(1);
                s1.add(2);
                s1.add(3);

                u1.add(4);
                u1.add(5);
                u1.add(6);

                s2.add(7);
                s2.add(8);
                s2.add(9);

                u2.add(10);
                u2.add(11);
                u2.add(12);

                // A addAll
                u1.addAll(u2);
                int intExpected = 6;
                test(u1.size() == intExpected, "Abstract addAll");

                // A contains
                int find = 4;
                test(u1.contains(find), "Abstract contains");

                // A containsAll
                test(u1.containsAll(u2), "Abstract containsAll");

                // A equals
                u2.addAll(u1);
                test(u1.equals(u2), "Abstract equals");

                // A remove
                u1.remove(find);
                intExpected = 5;
                test(u1.size() == intExpected, "Abstract remove");

                // U constructor tested at top
                // U add tested at top

                // U clear
                u1.clear();
                intExpected = 0;
                test(u1.size() == intExpected, "Unsorted clear");

                // U difference
                u1.add(4);
                u1.add(5);
                u1.add(6);
                ISet<Integer> UDiff = u2.difference(u1);
                intExpected = 3;
                test(UDiff.size() == intExpected, "Unsorted difference");

                // U intersection
                ISet<Integer> UInter = u2.intersection(u1);
                test(UInter.size() == intExpected, "Unsorted intersection");

                // U iterator
                Iterator<Integer> iter = u1.iterator();
                int counter = 0;
                while (iter.hasNext()) {
                        counter++;
                        iter.next();
                }
                test(counter == intExpected, "Unsorted iterator");

                // U size tested through abstract tests

                // U union
                ISet<Integer> UUnion = u2.union(u1);
                intExpected = 6;
                test(UUnion.size() == intExpected, "Unsorted union");

                // S constructor tested at top

                // S constructor from ISet
                ISet<Integer> s3 = new SortedSet<>(s2);
                intExpected = 3;
                test(s3.size() == 3, "Sorted constructor from ISet");

                // S add tested at top

                // S addAll
                s2.addAll(s1);
                intExpected = 6;
                test(s2.size() == intExpected, "Sorted addAll");

                // S clear
                s1.clear();
                intExpected = 0;
                test(s1.size() == intExpected, "Sorted clear");

                // S contains
                test(s2.contains(1), "Sorted contains");

                // S containsAll
                test(s2.containsAll(s1), "Sorted containsAll");

                // S difference
                s1 = new SortedSet<>();
                s2 = new SortedSet<>();

                s1.add(1);
                s1.add(2);
                s1.add(3);

                s2.add(3);
                s2.add(4);
                s2.add(5);

                intExpected = 2;
                ISet<Integer> SDiff = s1.difference(s2);
                test(SDiff.size() == intExpected, "Sorted difference");

                // S equals
                test(!s1.equals(s2), "Sorted equals");

                // S intersection
                ISet<Integer> SInter = s1.intersection(s2);
                intExpected = 1;
                test(SInter.size() == intExpected, "Sorted intersection");

                // S iterator
                iter = s1.iterator();
                counter = 0;
                while (iter.hasNext()) {
                        counter++;
                        iter.next();
                }
                intExpected = 3;
                test(counter == intExpected, "Sorted iterator");

                // S remove
                s1.remove(1);
                intExpected = 2;
                test(s1.size() == intExpected, "Sorted remove");

                // S size tested through previous tests
                ISet<Integer> SUnion = s1.union(s2);
                intExpected = 4;
                test(SUnion.size() == intExpected, "Sorted union");

                //S max
                SortedSet<Integer> s4 = new SortedSet<>();
                s4.add(3);
                s4.add(4);
                s4.add(5);

                intExpected = 5;
                test(s4.max() == intExpected, "Sorted max");

                //S min
                intExpected = 3;
                test(s4.min() == intExpected, "Sorted min");

                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                        System.out.println("Unable to change look and feel");
                }
                Scanner sc = new Scanner(System.in);
                String response = "";
                do {
                        largeTest();
                        System.out.print("Another file? Enter y to do another file: ");
                        response = sc.next();
                } while (response != null && response.length() > 0
                                && response.substring(0, 1).equalsIgnoreCase("y"));

        }

        // print out results of test
        private static void test(boolean result, String prompt) {
                String print = (result) ? "PASSED" : "FAILED";
                System.out.println(prompt + " " + print);
        }

        /*
         * Method asks user for file and compares run times to add words from file
         * to various sets, including CS314 UnsortedSet and SortedSet and Java's
         * TreeSet and HashSet.
         */
        private static void largeTest() {
                System.out.println();
                System.out.println("Opening Window to select file. "
                                + "You may have to minimize other windows.");
                String text = convertFileToString();
                Scanner keyboard = new Scanner(System.in);
                System.out.println();
                System.out.println("***** CS314 SortedSet: *****");
                processTextCS314Sets(new SortedSet<String>(), text, keyboard);
                System.out.println("****** CS314 UnsortedSet: *****");
                processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
                System.out.println("***** Java HashSet ******");
                processTextJavaSets(new HashSet<String>(), text, keyboard);
                System.out.println("***** Java TreeSet ******");
                processTextJavaSets(new TreeSet<String>(), text, keyboard);
        }

        /*
         * pre: set != null, text != null Method to add all words in text to the
         * given set. Words are delimited by white space. This version for CS314
         * sets.
         */
        private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
                Stopwatch s = new Stopwatch();
                Scanner sc = new Scanner(text);
                int totalWords = 0;
                s.start();
                while (sc.hasNext()) {
                        totalWords++;
                        set.add(sc.next());
                }
                s.stop();

                showResultsAndWords(set, s, totalWords, set.size(), keyboard);
        }

        /*
         * pre: set != null, text != null Method to add all words in text to the
         * given set. Words are delimited by white space. This version for Java
         * Sets.
         */
        private static void processTextJavaSets(Set<String> set, String text,
                        Scanner keyboard) {
                Stopwatch s = new Stopwatch();
                Scanner sc = new Scanner(text);
                int totalWords = 0;
                s.start();
                while (sc.hasNext()) {
                        totalWords++;
                        set.add(sc.next());
                }
                s.stop();
                sc.close();

                showResultsAndWords(set, s, totalWords, set.size(), keyboard);
        }

        /*
         * Show results of add words to given set.
         */
        private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
                        int setSize, Scanner keyboard) {

                System.out.println("Time to add the elements in the text to this set: " + s.toString());
                System.out.println("Total number of words in text including duplicates: " + totalWords);
                System.out.println("Number of distinct words in this text " + setSize);

                System.out.print("Enter y to see the contents of this set: ");
                String response = keyboard.next();

                if (response != null && response.length() > 0
                                && response.substring(0, 1).equalsIgnoreCase("y")) {
                        for (Object o : set) {
                                System.out.println(o);
                        }
                }
                System.out.println();
        }

        /*
         * Ask user to pick a file via a file choosing window and convert that file
         * to a String. Since we are evaluating the file with many sets convert to
         * string once instead of reading through file multiple times.
         */
        private static String convertFileToString() {
                // create a GUI window to pick the text to evaluate
                JFileChooser chooser = new JFileChooser(".");
                StringBuilder text = new StringBuilder();
                int retval = chooser.showOpenDialog(null);

                chooser.grabFocus();

                // read in the file
                if (retval == JFileChooser.APPROVE_OPTION) {
                        File source = chooser.getSelectedFile();
                        Scanner s = null;
                        try {
                                s = new Scanner(new FileReader(source));

                                while (s.hasNextLine()) {
                                        text.append(s.nextLine());
                                        text.append(" ");
                                }

                                s.close();
                        } catch (IOException e) {
                                System.out.println("An error occured while trying to read from the file: " + e);
                        } finally {
                                if (s != null) {
                                        s.close();
                                }
                        }
                }

                return text.toString();
        }
}