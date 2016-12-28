package objects;

import java.util.ArrayList;

/**
 * Created by addisonhowe on 12/22/16.
 */
public interface GraphComparable<Item, Link> extends Comparable<Item> {

    int getNumberOfLinks(Item item);

    ArrayList<Link> getLinks(Item item);

    @Override
    int compareTo(Item i);
}
