/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone.
 *
 * On my honor, <NAME>, this programming assignment is my own work
 * and I have not provided this code
 * to any other student.
 *
 * UTEID: jsr3699
 * email address: jpascualsr06@gmail.com
 * Number of slip days I am using: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class NameSurfer {

    // CS314 students, explain your menu option 7 here:
    /*
     * My interesting search looks for names that existed in the top
     * thousand, left the top thousand, then came back. In other words,
     * names that 'disappeared' then 'reappeared'.
     */

    // CS314 students, Explain your interesting search / trend here:
    /*
     * Originally I intended on looking for proof of a common etymology
     * principle that humans like to shorten words or phrases and often even
     * drop the original in favor of efficiency. (Ex: "When in Rome", "Hippo", and
     * "Speak of the devil" are all shorthand for their original phrases that some
     * might not even know). What I ended up finding was that babies being legally
     * named
     * a nickname (a shorter version of an originally longer name) was actually more
     * common
     * in the early 1900s compared to more recent decades. I think this could be
     * for a number of reasong including a resurfacing of wanting traditional names
     * or
     * changes in cultural formality. I am curious to see how this data would look
     * if
     * it were on a much bigger timescale, maybe even back to when the names
     * originally
     * showed up.
     * Beth 592 425 373 359 254 133 69 116 192 705 0
     * Mike 182 139 188 203 82 82 49 217 476 583 595
     * Jess 243 268 406 511 505 486 544 521 545 992 0
     * Matt 456 534 788 1000 729 414 292 372 651 0 0
     * Sam 58 69 99 131 168 236 278 380 467 408 466
     * Will 162 275 353 368 455 559 590 528 573 616 509
     * Pat 524 452 443 177 157 367 326 892 0 0 0
     */

    // CS314 students, add test code for NameRecord class here:
    public static void testing() {
        Names names = new Names(getFileScannerForNames("names.txt"));
        NameRecord Allison = names.getName("Allison");
        NameRecord Daniel = names.getName("Daniel");

        // compareTo Tests
        boolean CTExpected = true;
        if (Allison.equals(Allison) == CTExpected) {
            System.out.println("Passed test 1: compareTo");
        } else {
            System.out.println("failed test 1: compareTo");
        }
        CTExpected = false;
        if (Allison.equals(Daniel) == CTExpected) {
            System.out.println("Passed test 2: compareTo");
        } else {
            System.out.println("failed test 2: compareTo");
        }

        // Constructor Tests
        String[] Sample1 = { "30", "0", "5", "999", "6" };
        String[] Sample2 = { "1", "2", "3", "400", "500" };
        int base = 1800;
        NameRecord S1 = new NameRecord("sample1", base, Sample1);
        NameRecord S2 = new NameRecord("sample2", base, Sample2);
        // no errors confirms a successful construction of namerecords
        System.out.println("Passed test 3: constructor");
        System.out.println("Passed test 4: constructor");
        // confirmations tested later in "toString Tests"

        // getName Tests
        String GNExpected = "Daniel";
        if (Daniel.getName().equals(GNExpected)) {
            System.out.println("Passed test 5: getName");
        } else {
            System.out.println("failed test 5: getName");
        }
        GNExpected = "Allison";
        if (Allison.getName().equals(GNExpected)) {
            System.out.println("Passed test 6: getName");
        } else {
            System.out.println("failed test 6: getName");
        }

        // getBase Tests
        int GBExpected = 1800;
        if (S1.getBase() == GBExpected) {
            System.out.println("Passed test 7: getBase");
        } else {
            System.out.println("failed test 7: getBase");
        }
        GBExpected = 1900;
        if (Allison.getBase() == GBExpected) {
            System.out.println("Passed test 8: getBase");
        } else {
            System.out.println("failed test 8: getBase");
        }

        // getDecades Tests
        int GDExpected = 5;
        if (S1.getDecades() == GDExpected) {
            System.out.println("Passed test 9: getDecades");
        } else {
            System.out.println("failed test 9: getDecades");
        }
        GDExpected = 11;
        if (Allison.getDecades() == GDExpected) {
            System.out.println("Passed test 10: getDecades");
        } else {
            System.out.println("failed test 10: getDecades");
        }

        // getGivenDecades Tests
        int GGDExpected = 867;
        int year = 0;
        if (Allison.getGivenDecade(year) == GGDExpected) {
            System.out.println("Passed test 11: getGivenDecade");
        } else {
            System.out.println("failed test 11: getGivenDecade");
        }
        GGDExpected = 44;
        year = 10;
        if (Allison.getGivenDecade(year) == GGDExpected) {
            System.out.println("Passed test 12: getGivenDecade");
        } else {
            System.out.println("failed test 12: getGivenDecade");
        }

        // getBestDecade Tests
        int GBDExpected = 1990;
        if (Allison.getBestDecade() == GBDExpected) {
            System.out.println("Passed test 13: getBestDecade");
        } else {
            System.out.println("failed test 13: getBestDecade");
        }
        GBDExpected = 1820;
        if (S1.getBestDecade() == GBDExpected) {
            System.out.println("Passed test 14: getBestDecade");
        } else {
            System.out.println("failed test 14: getBestDecade");
        }

        // topThousand Tests
        int TTExpected = 10;
        if (Allison.topThousand() == TTExpected) {
            System.out.println("Passed test 15: topThousand");
        } else {
            System.out.println("failed test 15: topThousand");
        }
        TTExpected = 4;
        if (S1.topThousand() == TTExpected) {
            System.out.println("Passed test 16: topThousand");
        } else {
            System.out.println("failed test 16: topThousand");
        }

        // alwaysPopular Tests
        boolean APExpected = false;
        if (Allison.alwaysPopular() == APExpected) {
            System.out.println("Passed test 17: alwaysPopular");
        } else {
            System.out.println("failed test 17: alwaysPopular");
        }
        APExpected = true;
        if (S2.alwaysPopular() == APExpected) {
            System.out.println("Passed test 18: alwaysPopular");
        } else {
            System.out.println("failed test 18: alwaysPopular");
        }

        // rankedOnce Tests
        NameRecord Almeta = names.getName("Almeta");
        boolean ROExpected = false;
        if (Allison.rankedOnce() == ROExpected) {
            System.out.println("Passed test 19: rankedOnce");
        } else {
            System.out.println("failed test 19: rankedOnce");
        }
        ROExpected = true;
        if (Almeta.rankedOnce() == ROExpected) {
            System.out.println("Passed test 20: rankedOnce");
        } else {
            System.out.println("failed test 20: rankedOnce");
        }

        // increasing Tests
        NameRecord Luis = names.getName("Luis");
        boolean IExpected = false;
        if (Allison.increasing() == IExpected) {
            System.out.println("Passed test 21: increasing");
        } else {
            System.out.println("failed test 21: increasing");
        }
        IExpected = true;
        if (Luis.increasing() == IExpected) {
            System.out.println("Passed test 22: increasing");
        } else {
            System.out.println("failed test 22: increasing");
        }

        // decreasing Tests
        boolean DExpected = false;
        if (Allison.decreasing() == DExpected) {
            System.out.println("Passed test 23: decreasing");
        } else {
            System.out.println("failed test 23: decreasing");
        }
        DExpected = true;
        if (S2.decreasing() == DExpected) {
            System.out.println("Passed test 24: decreasing");
        } else {
            System.out.println("failed test 24: decreasing");
        }

        // toString Tests

    }

    // One of the basic data files given on the assignment.
    // Alter this to try different data files.
    private static final String NAME_FILE = "names.txt";

    // A few simple tests for the Names and NameRecord class.
    public static void simpleTest() {
        // raw data for Jake. Alter as necessary for your NameRecord
        String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
        int baseDecade = 1900;
        String[] jakeRanks = jakeRawData.substring(5, jakeRawData.length()).split(" ");
        NameRecord jakeRecord = new NameRecord("Jake", baseDecade, jakeRanks);
        String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
                + "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: "
                + "117\n2000: 97\n";
        String actual = jakeRecord.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if (expected.equals(actual)) {
            System.out.println("passed Jake toString test.");
        } else {
            System.out.println("FAILED Jake toString test.");
        }

        // Some getName Tests
        Names names = new Names(getFileScannerForNames(NAME_FILE));
        String[] testNames = { "Isabelle", "isabelle", "sel",
                "X1178", "ZZ", "via", "kelly" };
        boolean[] expectNull = { false, false, true, true, true, true, false };
        for (int i = 0; i < testNames.length; i++) {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }
    }

    // Checks if given name is present in Names.
    private static void performGetNameTest(Names names, String name,
            boolean expectNull) {

        System.out.println("Performing test for this name: " + name);
        if (expectNull) {
            System.out.println("Expected return value is null");
        } else {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ((expectNull && result == null) || (!expectNull && result != null)) {
            System.out.println("PASSED TEST.");
        } else {
            System.out.println("Failed test");
        }
    }

    // main method. Driver for the whole program
    public static void main(String[] args) {
        // Delete the following line in the final version of your program.
        // testing();
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    /*
     * pre: namesDatabase != null
     * Ask user for options to perform on the given Names object.
     * Creates a Scanner connected to System.in.
     */
    private static void runOptions(Names namesDatabase) {
        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if (menuChoice == MenuChoices.SEARCH) {
                search(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                alwaysMore(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                alwaysLess(namesDatabase);
            } else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                resurfaces(namesDatabase);
            }
        } while (menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    /*
     * Create a Scanner and return connected to a File with the given name.
     * pre: fileName != null
     * post: Return a Scanner connected to the file or null
     * if the File does not exist in the current directory.
     */
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("\n***** ERROR IN READING FILE ***** ");
            System.out.println("Can't find this file "
                    + fileName + " in the current directory.");
            System.out.println("Error: " + e);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Be sure " + fileName + " is in this directory: ");
            System.out.println(currentDir);
            System.out.println("\nReturning null from method.");
            sc = null;
        }
        return sc;
    }

    /*
     * Display the names that had at least one gap year (resurfaced after
     * disappearing)
     * pre: n != null
     * post: print out names that have left the top thousand then came back
     */
    private static void resurfaces(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        ArrayList<String> people = namesDatabase.resurfaced();
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    /*
     * Display the names that have appeared in every decade.
     * pre: n != null
     * post: print out names that have appeared in ever decade
     */
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        ArrayList<String> people = namesDatabase.rankedEveryDecade();
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    /*
     * Display the names that have appeared in only one decade.
     * pre: n != null
     * post: print out names that have appeared in only one decade
     */
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> people = namesDatabase.rankedOnlyOneDecade();
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    /*
     * Display the names that have gotten more popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten more popular in each decade
     */
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> people = namesDatabase.alwaysMorePopular();
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    /*
     * Display the names that have gotten less popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten less popular in each decade
     */
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> people = namesDatabase.alwaysLessPopular();
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    /*
     * Display the data for one name or state that name has never been ranked.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: print out the data for n or a message that n has never been in the
     * top 1000 for any decade
     */
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        String name = getString(keyboard, "Enter a name: ");
        NameRecord result = namesDatabase.getName(name);
        if (result == null) {
            System.out.println(name + " does not appear in any decade");
            return;
        }
        System.out.println(result.toString());
    }

    /*
     * Display all names that contain a substring from the user
     * and the decade they were most popular.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: display the data for each name.
     */
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        String name = getString(keyboard, "Enter part of a name: ");
        ArrayList<NameRecord> people = namesDatabase.getMatches(name);
        System.out.println("\nThere are " + people.size() + " matches for " + name + ".");
        if (people.size() == 0) {
            return;
        }
        System.out.println("\nThe matches with their highest ranking decade are:");
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).getName() + " " + people.get(i).getBestDecade());
        }
    }

    /*
     * Get choice from the user keyboard != null and is connected to System.in
     * return an int that is >= MenuChoices.SEARCH.ordinal()
     * and <= MenuChoices.QUIT.ordinal().
     */
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1 || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    /*
     * Ensure an int is entered from the keyboard.
     * pre: s != null and is connected to System.in
     * post: return the int typed in by the user.
     */
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    /*
     * Returns the value entered from the keyboard s
     * pre: s != null and is connected to System.in
     * post: return the string typed in by the user
     */
    private static String getString(Scanner s, String prompt) {
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNext()) {
            s.next();
            System.out.println("Invalid Input");
            System.out.print(prompt);
        }
        return s.next();
    }

    // Show the user the menu.
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to display all names that reappeared after"
                + " leaving the top thousand.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

    /**
     * An enumerated type to hold the menu choices
     * for the NameSurfer program.
     */
    private static enum MenuChoices {
        SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE,
        ALWAYS_LESS, STUDENT_SEARCH, QUIT;
    }
}