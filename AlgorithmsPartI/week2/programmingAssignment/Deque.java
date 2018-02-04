import java.lang.Integer;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int numberOfNodes = 0;

    public Deque() {
        // construct an empty deque
        first = null; last = null;
    }

    private class Node {
        // A generic node class
        public Node(Item item, Node next, Node previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
        Item item;
        Node next, previous;
    }

    public boolean isEmpty() {
        // is the deque empty?
        return first == null;
    }

    public int size() {
        // return the number of items on the deque
        return numberOfNodes;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null)
            throw new java.lang.IllegalArgumentException();
        Node newNode = new Node(item, first, null);
        if (first == null)
            first = last = newNode;
        else {
            first.previous = newNode;
            first = newNode;
        }
        numberOfNodes++;
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null)
            throw new java.lang.IllegalArgumentException();
        Node newNode = new Node(item, null, null);
        if (first == null)
            first = last = newNode;
        else {
            newNode.previous = last;
            last.next = newNode;
            last = newNode;
        }
        numberOfNodes++;
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        if (first == last)
            first = last = null;
        else {
            first = first.next;
            first.previous = null;
        }
        numberOfNodes--;
        return item;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        if (first == last)
            first = last = null;
        else {
            last = last.previous;
            last.next = null;
        }
        numberOfNodes--;
        return item;
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new ListIterator<Item>();
    }

    public static void main(String[] args) {
        // unit testing (optional)
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.removeLast();
    }
}
