// implementation of a singly linked list and common operations
// common operations: remove and return head node, delete a node, check if certain data exists, add to node
public class SinglyLinkedList {
    private Node head;

    static class Node {
        Node next;
        String data;

        public Node(String data) {
            this.data = data;
            next = null;
        }

    }

    public void add(String data) {
        Node oldHead = head;
        head = new Node(data);
        head.next = oldHead;
    }

    //removes head from list and returns it
    public Node removeHead() {
        if (head == null) {
            return null;
        }
        return head.next;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.head = new Node("first");
        list.add("second");

        Node clone = list.head;
        while (clone != null) {
            System.out.print(clone.data + " ");
            clone = clone.next;
        }

        System.out.print(list.removeHead().data);
        System.out.print(list.removeHead().data);

    }
}
