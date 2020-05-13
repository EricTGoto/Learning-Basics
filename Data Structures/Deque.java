import java.util.Iterator;

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
