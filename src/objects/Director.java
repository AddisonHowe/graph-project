package objects;

import java.util.ArrayList;
import edu.princeton.cs.algs4.BST;

/**
 * Created by addisonhowe on 12/20/16.
 * A Director has a first, middle, last name, and suffix.
 * Directors are not uniquely defined by name.
 * A Director has a list of Firms on which it appears.
 * The size of this list defines the size integer value.
 */

public class Director implements GraphComparable<Director, Firm> {

    private String first;
    private String middle;
    private String last;
    private String suffix;
    private boolean isFirstInitial = false;
    private boolean isMiddleInitial = false;
    private boolean isMiddleNull = false;

    private ArrayList<Firm> firms;
    private BST<String, Firm> firmTree;
    private ArrayList<String> aliases;
    private int size;
    private String fullName;

    public Director(String first, String middle, String last, String suffix) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.suffix = suffix;
        if (first.length() == 1) {
            isFirstInitial = true;
        }
        if (middle.length() == 0) {
            isMiddleNull = true;
        } else if (middle.length() == 1) {
            isMiddleInitial = true;
        }
        this.fullName = generateFullName(first, middle, last, suffix);
        this.firms = new ArrayList<Firm>();
        this.firmTree = new BST<String, Firm>();
        this.size = 0;
        this.aliases = new ArrayList<String>();
        aliases.add(fullName);
    }

    public void addFirm(Firm firm) {
        firms.add(firm);
        firmTree.put(firm.toString(), firm);
        size += 1;
    }

    @Override
    public int getNumberOfLinks(Director d) {
        int total = 0;
        for (Firm f : firms) {
            if (d.firmTree.contains(f.toString())) {
                total += 1;
            }
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return lastEquals(director) && suffixEquals(director) && firstEquals(director) && middleEquals(director);
    }


    /*
    First names are equal if strings match or if one
    is an initial and matches first letter of other.
     */
    private boolean firstEquals(Director d) {
        if (first.equals(d.first)) {
            return true;
        } else if (isFirstInitial || d.isFirstInitial) {
            return first.charAt(0) == d.first.charAt(0);
        } else {
            return false;
        }
    }

    /*
    Middle names are equal if strings match or if one
    is an initial and matches first letter of other.
    Equality is excluded if exactly one is not given.
     */
    private boolean middleEquals(Director d) {
        if (middle.equals(d.middle)) {
            // if strings equal, assume same
            return true;
        } else if (isMiddleNull || d.isMiddleNull) {
            // if one has middle null, assume different
            return false;
        } else if (isMiddleInitial || d.isMiddleInitial) {
            // if one initial, decide based on first character
            return middle.charAt(0) == d.middle.charAt(0);
        } else {
            return false;
        }
    }

    /*
    Last names are equal iff strings match.
     */
    private boolean lastEquals(Director d) {
        return last.equals(d.last);
    }

    /*
    Suffixes are equal iff strings match.
     */
    private boolean suffixEquals(Director d) {
        return suffix.equals(d.suffix);
    }

    @Override
    public String toString() {
        return fullName;
    }

    private String generateFullName(String first, String middle, String last, String suffix) {
        String fullName = last;
        if (!suffix.equals("")) {
            fullName += " " + suffix + ", " + first;
        } else {
            fullName += ", " + first;
        }
        if (!isMiddleNull) {
            fullName += " " + middle;
        }
        return fullName;
    }

    @Override
    public int compareTo(Director d) {
        if (last.compareTo(d.last) != 0) {
            return last.compareTo(d.last);
        } else if (suffix.compareTo(d.suffix) != 0) {
            return suffix.compareTo(d.suffix);
        } else if (first.compareTo(d.first) != 0) {
            return first.compareTo(d.first);
        } else {
            return middle.compareTo(d.middle);
        }
    }

    public void addAlias(String first, String middle, String last, String suffix) {
        String fullName = generateFullName(first, middle, last, suffix);
        if (!aliases.contains(fullName)) {
            aliases.add(fullName);
        }
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }


}
