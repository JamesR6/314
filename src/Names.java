/*  Student information for assignment:
*
*  On my honor, James Reeves, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: jsr3699
*  email address: jpascualsr06@gmail.com
*  Number of slip days I am using: 0
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * A collection of NameRecords.
 * Stores NameRecord objects and provides methods to select
 * NameRecords based on various criteria.
 */
public class Names {

    //Instance variable
    private ArrayList<NameRecord> names = new ArrayList<NameRecord>(); 

    /**
     * Construct a new Names object based on the data source the Scanner
     * sc is connected to. Assume the first two lines in the
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded
     * and are not part of the resulting Names object.
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * 
     * @param sc Is connected to a data file with baby names
     *           and positioned at the start of the data source.
     */
    public Names(Scanner sc) {
        int base = Integer.parseInt(sc.nextLine());
        int decades = Integer.parseInt(sc.nextLine());
        while( sc.hasNextLine() ){
            String name = sc.next();
            String[] strRanks = sc.nextLine().substring(1).split(" ");
            if (passes(strRanks, decades)) {
                names.add(new NameRecord(name, base, strRanks));
            }
        }
        Collections.sort(names);
    }

    private boolean passes(String[] ranks, int decades) {
        if (ranks.length != decades) {
            return false;
        }
        for (int i = 0; i < ranks.length; i++) {
            if (!ranks[i].equals("0")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an ArrayList of NameRecord objects that contain a
     * given substring, ignoring case. The names must be in sorted order based
     * on the names of the NameRecords.
     * 
     * @param partialName != null, partialName.length() > 0
     * @return an ArrayList of NameRecords whose names contains
     *         partialName. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<NameRecord> getMatches(String partialName) {
        ArrayList<NameRecord> result = new ArrayList<NameRecord>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getName().toLowerCase().contains(partialName.toLowerCase())) {
                result.add(names.get(i));
            }
        }
        return result;
    }
    
    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better for every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * 
     * @return A list of the names that have been ranked in the top
     *         1000 or better in every decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> rankedEveryDecade() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            if (alwaysRanked(names.get(i))) {
                result.add(names.get(i).getName());
            }
        }
        return result;
    }

    private boolean alwaysRanked(NameRecord nr) {
        for (int i = 0; i < nr.getDecades(); i++) {
            if (nr.getGivenDecade(i) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better in exactly one decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * 
     * @return A list of the names that have been ranked in the top
     *         1000 or better in exactly one decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> rankedOnlyOneDecade() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).rankedOnce()) {
                result.add(names.get(i).getName());
            }
        }
        return result;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting more
     * popular every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * 
     * @return A list of the names that have been getting more popular in
     *         every decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> alwaysMorePopular() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).increasing()) {
                result.add(names.get(i).getName());
            }
        }
        return result;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting less
     * popular every decade. The Strings must be in sorted order based
     * on the name of the NameRecords.
     * 
     * @return A list of the names that have been getting less popular in
     *         every decade. The list is in sorted ascending
     *         order. If there are no NameRecords that meet this
     *         criteria returns an empty list.
     */
    public ArrayList<String> alwaysLessPopular() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).decreasing()) {
                result.add(names.get(i).getName());
            }
        }
        return result;
    }

    /**
     * Return the NameRecord in this Names object that matches the given String
     * ignoring case.
     * <br>
     * <tt>pre: name != null</tt>
     * 
     * @param name The name to search for.
     * @return The name record with the given name or null if no NameRecord in this
     *         Names
     *         object contains the given name.
     */
    public NameRecord getName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The parameter name cannot be null");
        }
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return names.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> resurfaced() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            if (isResurfaced(names.get(i))) {
                result.add(names.get(i).getName());
            }
        }
        return result;
    }

    private boolean isResurfaced(NameRecord nr) {
        boolean started = false;
        boolean disapp = false;
        for (int i = 0; i < nr.getDecades(); i++) {
            if (nr.getGivenDecade(i) != 0 && started == false) {
                started = true;
            }
            if (started == true && nr.getGivenDecade(i) == 0) {
                disapp = true;
            }
            if (started && disapp && nr.getGivenDecade(i) != 0) {
                return true;
            }
        }
        return false;
    }
}