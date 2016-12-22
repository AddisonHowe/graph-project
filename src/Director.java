
import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/20/16.
 */
public class Director {

    private String first;
    private String middle;
    private String last;
    private String suffix;
    private boolean isFirstInitial = false;
    private boolean isMiddleInitial = false;
    private boolean isMiddleNull = false;
    private ArrayList<Bank> banks;

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
        this.banks = new ArrayList<Bank>();
    }

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return lastEquals(director) && suffixEquals(director) && firstEquals(director) && middleEquals(director);
    }

    private boolean firstEquals(Director d) {
        if (first.equals(d.first)) {
            // if strings equal, assume same
            return true;
        } else if (isFirstInitial || d.isFirstInitial) {
            return first.charAt(0) == d.first.charAt(0);
        } else {
            return false;
        }
    }

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

    private boolean lastEquals(Director d) {
        return last.equals(d.last);
    }

    private boolean suffixEquals(Director d) {
        return suffix.equals(d.suffix);
    }

    @Override
    public String toString() {
        return "Director{" +
                "first='" + first + '\'' +
                ", middle='" + middle + '\'' +
                ", last='" + last + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
