package objects;

import java.util.ArrayList;
import edu.princeton.cs.algs4.BST;

/**
 * Created by addisonhowe on 12/20/16.
 * A objects.Director has a first, middle, last name, and suffix.
 * Directors are not uniquely defined by name.
 * A objects.Director has a list of Banks on which it appears.
 * The size of this list defines the bankCount integer value.
 */

public class Director implements MatrixComparable<Director> {

    private String first;
    private String middle;
    private String last;
    private String suffix;
    private boolean isFirstInitial = false;
    private boolean isMiddleInitial = false;
    private boolean isMiddleNull = false;
    private ArrayList<Bank> banks;
    private BST<String, Bank> bankTree;
    private int bankCount;
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
        this.banks = new ArrayList<Bank>();
        this.bankTree = new BST<String, Bank>();
        this.bankCount = 0;
    }

    public void addBank(Bank bank) {
        banks.add(bank);
        bankTree.put(bank.getName(), bank);
        bankCount += 1;
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public int getBankCount() {
        return bankCount;
    }

    public String getFullName() {
        return fullName;
    }

    public int getCommons(Director d) {
        int total = 0;
        for (Bank b : banks) {
            if (d.bankTree.contains(b.getName())) {
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
    is an initial and mathces first letter of other.
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

}
