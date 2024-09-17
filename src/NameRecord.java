import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord>{
    
    //instance variables
    private String name;
    private int base;
    ArrayList<Integer> ranks;

    @Override
    public int compareTo(NameRecord o) {
        return name.compareTo(o.getName());
    }

    //constructor
    public NameRecord(String name, int base, String[] ranks) {
        this.name = name;
        this.base = base;
        for (int i = 0; i < ranks.length; i++) {
            this.ranks.add(Integer.parseInt(ranks[i]));
        }
    }

    //getName
    public String getName() {
        return name;
    }

    //getBase
    public int getBase() {
        return base;
    }

    //getDecades
    public int getDecades() {
        return ranks.size();
    }

    //getGivenDecade
    public int getGivenDecade(int decade) {
        return ranks.get(decade);
    }

    //getBestDecade
    public int getBestDecade() {
        int largest = 0;
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) > largest) {
                largest = ranks.get(i);
            }
        }
        return ranks.indexOf(largest);
    }

    //TopThousand
    public int topThousand() {
        int count = 0;
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) != 0) {
                count++;
            }
        }
        return count;
    }

    //alwaysPopular
    public boolean alwaysPopular() {
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    //rankedOnce
    public boolean rankedOnce() {
        int count = 0;
        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) != 0) {
                count++;
            }
        }
        return (count == 1);
    }

    //morePopular
    public boolean increasing() {
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (betterOrWorse(ranks.get(i), ranks.get(i+1)) < 1) {
                return false;
            }
        }
        return true;
    }

    //lessPopular
    public boolean decreasing() {
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (betterOrWorse(ranks.get(i), ranks.get(i+1)) > -1) {
                return false;
            }
        }
        return true;
    }

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

    //toString
    public String toString() {
        StringBuilder result = new StringBuilder(name + "\n");
        for (int i = 0; i < ranks.size(); i++) {
            result.append((base + (i*10)) + ": " + ranks.get(i) + "\n");
        }
        return result.toString();
    }
}
