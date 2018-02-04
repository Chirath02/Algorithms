import java.util.Scanner;
import java.util.Iterator;
import java.lang.Integer;


class QueueResizingArray<Item> implements Iterable<Item> {
    private Item[] items;
    private int front, back;

    public QueueResizingArray() {
        items = (Item[]) new Object[2];
        front = back = -1;

        for(int i = 0; i < 10; ++i)
            System.out.println();
    }

    public boolean isEmpty() {
        return front == -1 && back == -1;
    }

    private boolean isFull() {
        return back == front || (back == items.length && front == 0);
    }

    public void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        int i = 0, current = front;
        while(current !=  back) {
            if (current >= items.length)
                current = 0;
            newArray[i++] = items[current++];
        }

        items = newArray;

        front = 0;
        back = i;
    }

    public void enqueue(Item item) {
        if (isEmpty())
            back = front = 0;
        items[back++] = item;

        if (isFull())
            resize(2 * items.length);
        if (back >= items.length)
            back = 0;


    }

    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = items[front++];
        if (front == items.length)
            front = 0;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private int current = front;

        public boolean hasNext() {
            return current != back;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = (Item) items[current++];
            if (current >= items.length)
                current = 0;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // Tests
        
        QueueResizingArray<Integer> queue = new QueueResizingArray<Integer>();

        for (int i = 0; i < 10; ++i)
            queue.enqueue(i);

        System.out.println("Queue");

        for (Integer i : queue)
            System.out.println(i);

        System.out.println("Dequeue");

        System.out.println("Queue after pop 5");

        for (int i = 0; i < 5; ++i)
            queue.dequeue();

        for (int i = 0; i < 10; ++i)
            queue.enqueue(i + 10);

        for (int i = 0; i < 10; ++i)
            queue.enqueue(i + 10);

        for (Integer i : queue)
            System.out.println(i);
    }
}
