// a data structure similar to a queue or a stack but items are pulled out at random instead of in FIFO or FILO order
// problem from Princeton Coursera "Algorithms part 1"
// Since we want to randomly take out objects, we need to be able to access things quickly, therefore an array should be used
// If a linked list is used, the worst case scenario would be linear time, but an array can support queue operations at constant amortized time

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int currentSize;


    // Initializes an empty RandomizedQueue object
    public RandomizedQueue() {
        currentSize = 0;
        array = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items in the queue
    public int size() {
        return array.length;
    }

    // add item into queue
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Please enter a valid argument");
        if (currentSize == array.length) enlargeArray(2 * array.length);
        array[currentSize] = item;
        currentSize++;
    }


    // remove and return a random item
    // we want this to be constant amortized time
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            Item temp = head.item;
            head = null;
            size--;
            return temp;
        } else {
            int size = this.size;
            int randomIndex = StdRandom.uniform(size) + 1;
            int index = 1;

            // In general there will be three cases we will deal with:
            // Removing the first object, removing something in between the first and last object, and removing the last object

            // Case: Remove first object
            if (randomIndex == 1) {
                Item temp = head.item;
                head = head.next;
                this.size--;
                return temp;
            } else if (randomIndex == size) {
                // Case: Remove last object
                // Create a temporary copy of the head node and then go to the 2nd last node, extract item, change pointers
                Node clone = head;
                // Sifts through list until we hit the 2nd last node
                while (head.next.next != null) {
                    head = head.next;
                }

                Item temp = head.next.item;
                // Remove the last node
                head.next = null;
                this.size--;
                // Bring head back to the beginning
                head = clone;
                return temp;
            } else {
                // Case: Remove an object between the first and last object
                // stop at the node before the node we want to remove and make that node point two nodes forward
                Node clone = head;
                while (index + 1 < randomIndex) {
                    head = head.next;
                    index++;
                }
                Item temp = head.next.item;
                head.next = head.next.next;
                head = clone;
                this.size--;
                return temp;
            }
        }

    }

    // If the array is full, double the size
    private void enlargeArray(int newSize) {
        Item[] temp = array;
        array = (Item[]) new Object[array.length * 2];

        for (int k = 0; k < array.length; k++) {
            array[k] = temp[k];
        }

    }

    // return a random item without removing it
    public Item sample() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        } else if (this.size() == 1) {
            return head.item;
        } else {
            int size = this.size();
            int num = StdRandom.uniform(size) + 1;
            int index = 1;
            Node clone = head;
            while (index < num) {
                clone = clone.next;
                index++;
            }
            return clone.item;
        }
    }

    // return an iterator
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        // create a copy of the current list instead of giving access to the real list
        private Node clone = head;

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Item next() {
            if (size == 0) throw new NoSuchElementException("List is empty");

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

    }
}
