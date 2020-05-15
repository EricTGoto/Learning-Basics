// a data structure similar to a queue or a stack but items are pulled out at random instead of in FIFO or FILO order

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;

    private class Node {
        Node next;
        Item item;

        public Node(Item item) {
            this.next = null;
            this.item = item;
        }
    }

    public RandomizedQueue() {
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items in the queue
    public int size() {
        return size;
    }

    // add item into queue
    public void enqueue(Item item) {

    }

    // remove and return a random item
    public Item dequeue() {

    }

    // return a random item without removing it
    public Item Sample() {

    }

    // return an iterator
    public Iterator<Item> iterator() {

    }

    public static void main(String[] args) {

    }
}
