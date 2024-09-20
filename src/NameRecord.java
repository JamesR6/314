import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord>{
    
    //instance variables
    private String name;
    private int base;
    ArrayList<Integer> ranks = new ArrayList<Integer>();

    /*
     * overrides compareTo
     * compares this NameRecord to another based on the name they are keeping track of
     * pre: other != null
     * post: returns 1 if this.name is alphabetically before other, -1
     * if other comes first, and 0 otherwise.
     */
    @Override
    public int compareTo(NameRecord other) {
        if (other == null) {
            throw new IllegalArgumentException("o can not be null");
        }
        return name.compareTo(other.getName());
    }

    /*
     * Create a new NameRecord, converting string[] ranks into an arrayList
     * pre: ranks != null
     * post: initiates a new NameRecord with the given parameters
     */
    public NameRecord(String name, int base, String[] ranks) {
        if (ranks == null) {
            throw new IllegalArgumentException("ranks can not be null");
        }
        this.name = name;
        this.base = base;
        for (int i = 0; i < ranks.length; i++) {
            this.ranks.add(Integer.parseInt(ranks[i]));
        }
    }

    /*
     * returns the name of the NameRecord
     * pre: None
     * post: returns the name of the NameRecord
     */
    public String getName() {
        return name;
    }

    /*
     * returns the base decade of the NameRecord
     * pre: None
     * post: returns the base decade in year format
     */
    public int getBase() {
        return base;
    }

    /*
     * returns the amount of decades recorded in this NameRecord
     * pre: none;
     * post: returns the amount of recorded decades
     */
    public int getDecades() {
        return ranks.size();
    }

    /*
     * returns the rank of this name at the given index, decade
     * pre: decade >= 0, decade < ranks.size()
     * post: returns the rank of the given decade
     */
    public int getGivenDecade(int decade) {
        if (decade < 0 || decade >= ranks.size()) {
            throw new IllegalArgumentException("decade must be within bounds");
        }
        return ranks.get(decade);
    }

    /*
     * returns the decade that this name had the highest ranking
     * pre: None
     * post: returns in year format the decade that this name ranked the highest
     */
    public int getBestDecade() {
        int indexTracker = 0;
        //initialized as 1001 so any rank is better than the initial value
        int best = 1001;
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) < best && ranks.get(i) != 0) {
                best = ranks.get(i);
                indexTracker = i;
            }
        }
        return (10 * ranks.indexOf(best)) + base;
    }

    /*
     * returns the number of decades this name has been in the top thousand
     * pre: None
     * post: returns an int of how many decades this name ranked
     */
    public int topThousand() {
        int count = 0;
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) != 0) {
                count++;
            }
        }
        return count;
    }

    /*
     * returns if this NameRecord ranked in every decade
     * pre: None
     * post: returns true if this name is always ranked, false otherwise
     */
    public boolean alwaysPopular() {
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * returns if this name only ranked in a single decade
     * pre: None
     * post: returns true if this name ranked exactly once, false otherwise
     */
    public boolean rankedOnce() {
        int count = 0;
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) != 0) {
                count++;
            }
        }
        return (count == 1);
    }

    /*
     * returns if this name has been getting more popular every single year
     * pre: None
     * post: returns true if this name's rank has been monotinically getting better, false otherwise
     */
    public boolean increasing() {
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (betterOrWorse(ranks.get(i), ranks.get(i+1)) < 1) {
                return false;
            }
        }
        return true;
    }

    /*
     * returns if this name has been getting less popular every single year
     * pre: None
     * post: returns true if this name's rank has been monotinically getting worse, false otherwise
     */
    public boolean decreasing() {
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (betterOrWorse(ranks.get(i), ranks.get(i+1)) > -1) {
                return false;
            }
        }
        return true;
    }

    /*
     * returns if first is a better or worse rank than second
     * pre: none
     * post: returns 1 if first is better, -1 if second is better, 0 otherwise
     */
    private int betterOrWorse(int first, int second) {
        if (first == 0) {
            first = 1001;
        }
        if (second == 0) {
            second = 1001;
        }
        if (first > second) {
            return 1;
        }
        if (first < second) {
            return -1;
        }
        return 0;
    }

    /*
     * overrides toString
     * returns a string with the name, decades, and corresponding ranks of this NameRecord.
     * Each decade is on a separate line.
     */
    public String toString() {
        StringBuilder result = new StringBuilder(name + "\n");
        for (int i = 0; i < ranks.size(); i++) {
            result.append((base + (i*10)) + ": " + ranks.get(i) + "\n");
        }
        return result.toString();
    }
}
