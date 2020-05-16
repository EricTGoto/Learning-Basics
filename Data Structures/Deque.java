import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// Implementation of a double ended queue
// problem from Princeton Coursera course "Algorithms part 1"

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // node to create the linked list based data structure
    // use a doubly linked list
    private class Node {
        Node next;
        Node before;
        Item item;

        public Node(Item item) {
            this.item = item;
            next = null;
            before = null;
        }

    }

    // checks if the deque is empty or not
    public boolean isEmpty() {
        return size() == 0;
    }

    // returns the number of items in the deque
    public int size() {
        return size;
    }

    // adds an item to the front of the queue
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Null item addition not supported");

        Node newHead = new Node(item);
        if (size == 0) {
            head = newHead;
            tail = newHead;
            size++;
        } else {
            newHead.next = head;
            newHead.next.before = newHead;
            newHead.before = null;
            head = newHead;
            size++;
        }
    }

    // adds an item to the back of the queue
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null item addition not supported");

        Node newTail = new Node(item);
        if (size == 0) {
            tail = newTail;
            head = newTail;
            size++;
        } else {
            tail.next = newTail;
            newTail.before = tail;
            tail = newTail;
            size++;
        }
    }

    // removes and returns the item from the front
    public Item removeFirst() {
        if (this.size == 0) throw new NoSuchElementException("The deque is empty");
        else if (this.size == 1) {
            Item item = head.item;
            head = null;
            tail = null;
            size--;
            return item;
        } else {
            Item temp = head.item;
            head = head.next;
            size--;
            return temp;
        }
    }

    // removes and returns the item from the back
    public Item removeLast() {
        if (this.size == 0) throw new NoSuchElementException("The deque is empty");
        else if (this.size == 1) {
            Item item = head.item;
            head = null;
            tail = null;
            size--;
            return item;
        } else {
            Item item = tail.item;
            tail = tail.before;
            size--;
            return item;
        }
    }

    // returns an iterator
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>() {
            @Override
            public boolean hasNext() {
                return head != null;
            }

            @Override
            public Item next() {
                if (size == 0) throw new NoSuchElementException("List is empty");
                return removeFirst();
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Item previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(Item item) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(Item item) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        // check if size properly returns 0
        System.out.println(d.size());
        // add two items into the list
        d.addFirst("xd");
        d.addFirst("LUL");
        d.addFirst("dong");
        d.addFirst("ping");
        d.addFirst("jing");
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        //d.addLast("slam");
        // d.removeLast();
        // this should print out ping dong LUL XD hello ding pong
        //for (String item : d) {
        // System.out.println(item);
        //}
        //System.out.println(d.isEmpty());
        // System.out.print(d.removeFirst()); if uncommented will produce NoSuchElementException


    }
}
