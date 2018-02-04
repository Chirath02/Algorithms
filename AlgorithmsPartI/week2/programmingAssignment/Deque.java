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
        else
            first = newNode;
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
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < 100; ++i)
            d.addFirst(i);
        for (int i = 100; i < 200; ++i)
            d.addLast(i);

        boolean correct = true;

        for (int s : d)
            StdOut.print(s + " ");
        StdOut.println();

        for (int i = 99; i >= 0; --i)
            if (i != d.removeFirst())
                correct = false;

        if (correct)
            StdOut.println("correct for addFirst() and addLast()");
        else
            StdOut.println("Incorrect for addFirst() and addLast()");

        for (int s : d)
            StdOut.print(s + " ");
        StdOut.println();

        for (int i = 199; i >= 100; --i)
            if (i != d.removeLast())
                correct = false;

        if (correct)
            StdOut.println("correct for addFirst() and addLast()");
        else
            StdOut.println("Incorrect for addFirst() and addLast()");

        for (int s : d)
            StdOut.print(s + " ");
        StdOut.println();
    }
}
