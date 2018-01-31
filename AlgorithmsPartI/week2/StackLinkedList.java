import java.util.Scanner;
import java.util.Iterator;
import java.lang.Integer;

class StackLinkedList<Item> implements Iterable<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Item pop() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

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
        StackLinkedList<Integer> stack = new StackLinkedList<Integer>();

        for (int i = 0; i < 10; ++i)
            stack.push(i);

        System.out.println("Stack");

        for (Integer i : stack)
            System.out.println(i);

        System.out.println("Pop");

        System.out.println("Stack after pop 5");

        for (int i = 0; i < 5; ++i)
            stack.pop();

        for (Integer i : stack)
            System.out.println(i);
    }
}
