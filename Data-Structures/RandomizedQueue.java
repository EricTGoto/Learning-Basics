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
        // Since we don't want random null spots in our array, we can fill what we take out with the tail
        if (random != array.length - 1) array[random] = array[currentSize - 1];
        else array[random] = null;
        currentSize--;
        return item;
    }

    // Dequeue method for the iterator as it needs to access the copied array
    private Item dequeue(Item[] array, int iteratorSize) {
        if (iteratorSize == 0) throw new NoSuchElementException("The queue is empty");

        if (iteratorSize == array.length / 4) shrinkArray(array, array.length / 2);
        int random = StdRandom.uniform(iteratorSize);
        Item item = array[random];
        if (random != array.length - 1) array[random] = array[iteratorSize - 1];
        else array[random] = null;
        iteratorSize--;
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

    private void shrinkArray(Item[] array, int newSize) {
        Item[] temp = array;
        array = (Item[]) new Object[newSize];

        for (int k = 0; k < array.length; k++) {
            array[k] = temp[k];
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
        private Item[] arrayCopy = (Item[]) new Object[array.length];
        private int iteratorSize = currentSize;

        public listIterator() {
            System.arraycopy(array, 0, arrayCopy, 0, arrayCopy.length);
        }

        @Override
        public boolean hasNext() {
            return iteratorSize != 0;
        }

        @Override
        public Item next() {
            if (iteratorSize == 0) throw new NoSuchElementException("List is empty");
            return dequeue(arrayCopy, iteratorSize--);

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("1");
        //System.out.println(rq.dequeue());
        rq.enqueue("2");
        rq.enqueue("3");
        //System.out.println(rq.dequeue());
        rq.enqueue("4");
        rq.enqueue("5");
        //System.out.println(rq.dequeue());
        rq.enqueue("6");
        Iterator<String> i = rq.iterator();
        Iterator<String> j = rq.iterator();
        while (i.hasNext()) System.out.println(i.next());
        System.out.print(j.hasNext());
    }
}
