
import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/20/16.
 * A Bank is defined by its name.
 * Each has a list of Directors.
 * The size of a bank is the number of Directors
 */

public class Bank implements Comparable<Bank> {

    private String name;
    private ArrayList<Director> directors;
    private int size;


    public Bank(String name) {
        this.name = name;
        this.directors = new ArrayList<Director>();
        this.size = 0;
    }

    public void addDirector(Director director) {
        directors.add(director);
        size += 1;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfCommonDirectors(Bank b) {
        int total = 0;
        for (Director d : directors) {
            if (b.getDirectors().contains(d)) {
                total += 1;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return name != null ? name.equals(bank.name) : bank.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Bank b) {
        return name.compareTo(b.name);
    }
}

