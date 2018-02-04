import java.lang.Integer;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int end = -1;
    private Item[] items;

    public RandomizedQueue() {
        // construct an empty randomized queue
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        // is the randomized queue empty?
        return end < 0;
    }

    public int size() {
        // return the number of items on the randomized queue
        return end + 1;
    }

    private boolean isFull() {
        return end == items.length - 1;
    }

    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i <= end; ++i)
            newItems[i] = items[i];
        items = newItems;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null)
            throw new java.lang.IllegalArgumentException();
        if (isFull())
            resize(2 * items.length);
        items[++end] = item;
    }

    public Item dequeue() {
        // remove and return a random item
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(end + 1);
        Item item = items[randomIndex];
        items[randomIndex] = items[end];
        items[end--] = null;
        if (end > 2 && end < items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return items[StdRandom.uniform(end + 1)];
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Item[] listItems;
        private int index = 0;

        public ListIterator() {
            listItems = (Item[]) new Object[end + 1];
            for (int i = 0; i <= end; ++i)
                listItems[i] = (Item) items[i];
            StdRandom.shuffle(listItems);
        }

        public boolean hasNext() {
            return index <= end;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return listItems[index++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new ListIterator<Item>();
    }

    public static void main(String[] args) {
        // unit testing (optional)
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(873);
        rq.isEmpty();
        rq.size();
        rq.size();
        StdOut.println(rq.size());
        StdOut.println(rq.dequeue());
        rq.enqueue(935);
        StdOut.println(rq.dequeue());
        rq.enqueue(916);
        rq.sample();
    }
}
