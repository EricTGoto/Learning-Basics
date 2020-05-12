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

    // insert a node at the head of a list and return the new head
    public Node add(String data) {
        Node newNode = new Node(data);
        newNode.next = head;
        return newNode;
    }

    //removes head from list and returns new head
    public Node removeHead() {
        if (head == null) {
            return null;
        }
        // store the new head so we can detach the head
        Node temp = head.next;
        // detach head from list
        head.next = null;
        // set next Node as the new head
        head = temp;
        return head;
    }

    // removes the tail node and returns the head
    public Node deleteAtTail() {
        // check if list is empty or there is only one node in the list
        if (head == null || head.next == null) return null;
        // create a Node that points at the head
        Node temp = head;
        // move along the list until we are at the second last node
        while (head.next.next != null) {
            head = head.next;
        }
        // set the node after the second last node to null to cut off the last node
        head.next = null;
        return temp;

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
