import java.util.Iterator;
import java.util.NoSuchElementException;

// Implementation of a double ended queue
public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;

    public Deque() {
        this.head = null;
        this.tail = null;
    }

    private class Node {
        Node next;
        Item item;

        public Node(Item item) {
            this.item = item;
            next = null;
        }

    }

    public boolean isEmpty() {
        if (head == null || tail == null) return true;
        return false;
    }

    public int size() {
        if (head == null) return 0;
        int items = 0;

        Node clone = head;
        while (clone != null) {
            items++;
            clone = clone.next;
        }
        return items;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Null item addition not supported");
        Node newHead = new Node(item);
        newHead.next = head;
        head = newHead;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null item addition not supported");
        Node newTail = new Node(item);
        newTail.next = tail;
        tail = newTail;
    }

    public Item removeFirst() {
        if (this.size() == 0) throw new NoSuchElementException("The deque is empty");
        else if (this.size() == 1) {
            head = null;
            tail = null;
        } else {
            Item temp = head.item;
            head = head.next;
            return temp;
        }
    }

    public Item removeLast() {
        if (this.size() == 0) throw new NoSuchElementException("The deque is empty");
        else if (this.size() == 1) {
            head = null;
            tail = null;
        } else {
            Item item = tail.item;
            tail = null;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {
        Deque d = new Deque();
        // check if size properly returns 0
        System.out.print(d.size());
    }
}
