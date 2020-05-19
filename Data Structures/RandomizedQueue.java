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
        return currentSize == 0;
    }

    // return the number of items in the queue
    public int size() {
        return currentSize;
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
        if (currentSize == 0) throw new NoSuchElementException("The queue is empty");

        if (currentSize == array.length / 4) shrinkArray(array.length / 2);
        int random = StdRandom.uniform(currentSize);
        Item item = array[random];
        arrayWithoutRemovedItem(random);
        currentSize--;
        return item;
    }


    // If the array is full, double the size
    private void enlargeArray(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];

        for (int k = 0; k < array.length; k++) {
            temp[k] = array[k];
        }
        array = temp;

    }

    // If the array has lots of empty space (3/4) then halve the size
    private void shrinkArray(int newSize) {
        Item[] temp = array;
        array = (Item[]) new Object[newSize];

        for (int k = 0; k < array.length; k++) {
            array[k] = temp[k];
        }
    }

    // Make another array without the removed element
    private void arrayWithoutRemovedItem(int removedIndex) {
        // if array length is 1, we shouldn't make it 0
        if (array.length == 1) {
            array[0] = null;
        } else {
            Item[] temp = (Item[]) new Object[array.length - 1];
            int tempIndex = 0;
            for (int k = 0; k < array.length; k++) {
                if (k != removedIndex) {
                    temp[tempIndex] = array[k];
                    tempIndex++;
                }
            }
            array = temp;
        }
    }

    // return a random item without removing it
    public Item sample() {
        if (currentSize == 0) throw new NoSuchElementException();
        int random = StdRandom.uniform(currentSize);
        return array[random];
    }

    // return an iterator
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<Item> {
        // create a copy of the current list instead of giving access to the real list


        @Override
        public boolean hasNext() {
            return currentSize != 0;
        }

        @Override
        public Item next() {
            if (currentSize == 0) throw new NoSuchElementException("List is empty");
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("XD");
        rq.dequeue();
        rq.enqueue("x");
    }
}
