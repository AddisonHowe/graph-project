package objects;

/**
 * Created by addisonhowe on 12/22/16.
 */
public interface MatrixComparable<Item> extends Comparable<Item> {

    int getCommons(Item i);

    @Override
    int compareTo(Item i);
}
