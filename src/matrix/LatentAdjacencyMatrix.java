package matrix;

import objects.GraphComparable;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by addisonhowe on 12/22/16.
 */

public class LatentAdjacencyMatrix<Item extends GraphComparable<Item, Link>, Link extends GraphComparable<Link, Item>> {

    private TreeMap<Integer, Item> itemsMap;
    private int size;

    public LatentAdjacencyMatrix(ArrayList<Item> itemList) {
        PriorityQueue<Item> pq = new PriorityQueue<Item>(itemList);
        this.size = itemList.size() + 1;
        this.itemsMap = new TreeMap<Integer, Item>();
        for (int index = 1; index < size; index++) {
            itemsMap.put(index, pq.poll());
        }
    }

    public String getName(int index) {
        if (index < 1 || index > itemsMap.size()) {
            throw new IndexOutOfBoundsException();
        }
        return itemsMap.get(index).toString();
    }

    public int getValue(int r, int c) {
        Item first = itemsMap.get(r);
        Item second = itemsMap.get(c);
        return first.getNumberOfLinks(second);
    }

    public int size() {
        return size;
    }

}
