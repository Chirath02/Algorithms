import java.util.Scanner;
import java.lang.Integer;
import java.util.Iterator;


class QueueLinkedList<Item> implements Iterable<Item> {
    private Node front = null, back = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (!isEmpty())
            back.next = newNode;
        back = newNode;
        if (isEmpty())
            front = back;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = front.item;
        if (front == back)
            front = back = null;
        else
            front = front.next;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = front;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        QueueLinkedList<Integer> queue = new QueueLinkedList<Integer>();

        for (int i = 0; i < 10; ++i)
            queue.enqueue(i);

        System.out.println("Queue");

        for (Integer i : queue)
            System.out.println(i);

        System.out.println("Dequeue");

        System.out.println("Queue after pop 5");

        for (int i = 0; i < 5; ++i)
            queue.dequeue();

        for (Integer i : queue)
            System.out.println(i);
    }
}
