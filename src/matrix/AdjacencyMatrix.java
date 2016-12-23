package matrix;

import objects.MatrixComparable;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by addisonhowe on 12/22/16.
 */
public class AdjacencyMatrix<Item extends MatrixComparable> {

    private TreeMap<Integer, Item> itemsMap;
    private int size;

    public AdjacencyMatrix(ArrayList<Item> itemList) {
        PriorityQueue<Item> pq = new PriorityQueue<Item>();
        for (Item item : itemList) {
            pq.add(item);
        }
        this.size = itemList.size() + 1;
        this.itemsMap = new TreeMap<Integer, Item>();
        /*
        int index = 1;
        for (Item item : pq) {
            itemsMap.put(index, item);
            index++;
        }
        */
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
        return itemsMap.get(r).getCommons(itemsMap.get(c));
    }

    public int size() {
        return size;
    }

}
