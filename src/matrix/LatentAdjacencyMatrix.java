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
        System.out.println(checkSymmetric());
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

    public boolean checkSymmetric() {
        boolean sym = true;
        for (int r = 1; r < size; r++) {
            for (int c =1; c <= r; c++) {
                if (getValue(r, c) != getValue(c, r)) {
                    System.out.println("Values do not coincide! r: " + r + " c: " + c );
                    System.out.println(itemsMap.get(r));
                    System.out.println(itemsMap.get(c));
                    sym = false;
                }
            }
        }
        if (sym) System.out.println("Matrix is symmetric");
        return sym;
    }
}
