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
        head = new Node("data");
        head.next = oldHead;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.head = new Node("head");
        list.add("second");


        while (list.head != null) {
            System.out.print(list.head.data + " ");
            list.head = list.head.next;
        }
    }
}
