// a data structure similar to a queue or a stack but items are pulled out at random instead of in FIFO or FILO order
// problem from Princeton Coursera "Algorithms part 1"

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Node head;
    private Node tail;

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
        if (item == null) throw new IllegalArgumentException("Please enter a valid argument");
        Node newItem = new Node(item);
        if (this.size() == 0) {
            head = newItem;
            tail = newItem;
            size++;
        } else {
            tail.next = newItem;
            tail = newItem;
            size++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        } else if (this.size() == 1) {
            Item temp = head.item;
            head = null;
            size--;
            return temp;
        } else {
            int size = this.size();
            int num = StdRandom.uniform(size) + 1;
            int index = 1;
            Node clone = head;

            if (size == 2) {
                if (num == 2) {
                    Item temp = clone.next.item;
                    head.next = null;
                    tail = head;
                    this.size--;
                    return temp;
                } else {
                    Item temp = clone.item;
                    head = tail;
                    return temp;
                }
            }

            // if we are trying to remove the last item go to the 2nd last item and then make it point to null
            if (num == size) {

                while (clone.next.next != null) {
                    clone = clone.next;
                }
                Item temp = clone.next.item;
                clone.next = null;
                this.size--;
                return temp;
            } else {
                // stop at the node before the node we want to remove and make that node point two nodes forward
                while (index + 1 < num) {
                    clone = clone.next;
                    index++;
                }
            }
            Item temp = clone.next.item;
            clone.next = clone.next.next;
            this.size--;
            return temp;
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
            int num = StdRandom.uniform(size + 1);
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
        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Item next() {
            if (size == 0) throw new NoSuchElementException("List is empty");
            return dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("ding");
        rq.enqueue("dong");
        rq.enqueue("1111");
        rq.enqueue("2222");
        System.out.println(rq.sample());
        //System.out.println(rq.sample());
        //System.out.println(rq.sample());
        System.out.println(rq.dequeue());
        for (String string : rq) {
            System.out.println(string);
        }
        System.out.println(rq.dequeue()); // should give NoSuchElementException
    }
}
