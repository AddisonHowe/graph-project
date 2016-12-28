package objects;

import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/20/16.
 * A Firm is defined by its name.
 * Each has a list of Directors.
 * The size of a firm is the number of Directors
 */

public class Firm implements GraphComparable<Firm, Director> {

    private String name;
    private ArrayList<Director> directors;
    private int size;


    public Firm(String name) {
        this.name = name;
        this.directors = new ArrayList<Director>();
        this.size = 0;
    }

    public void addDirector(Director director) {
        directors.add(director);
        size += 1;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int getNumberOfLinks(Firm f) {
        int total = 0;
        for (Director d : directors) {
            if (f.getDirectors().contains(d)) {
                total += 1;
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firm firm = (Firm) o;
        return name != null ? name.equals(firm.name) : firm.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Firm b) {
        return name.compareTo(b.name);
    }
}

