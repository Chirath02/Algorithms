import java.util.Scanner;
import java.util.Iterator;
import java.lang.Integer;


class StackResizingArray<Item> /*implements Iterable<Item>*/ {
    private Item[] items;
    private int index = 0;

    public StackResizingArray() {
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public void push(Item item) {
        if (index == items.length)
            resize(items.length * 2);
        items[index++] = item;
    }

    public Item pop() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        if (items.length / 4 == index)
            resize(items.length / 2);
        Item item = items[--index];
        return item;
    }

    public void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i < index; ++i)
            newArray[i] = items[i];
        items = newArray;
    }

    // public Iterator<Item> iterator() {
    //     return new ListIterator();
    // }
    //
    // private class ListIterator<Item> implements Iterator<Item> {
    //     private int current = 0;
    //
    //     public boolean hasNext() {
    //         return current <= index;
    //     }
    //
    //     public Item next() {
    //         if (!hasNext())
    //             throw new java.util.NoSuchElementException();
    //         return items[current++];
    //     }
    //
    //     public void remove() {
    //         throw new java.lang.UnsupportedOperationException();
    //     }
    // }

    public static void main(String[] args) {
        StackResizingArray<Integer> stack = new StackResizingArray<Integer>();

        for (int i = 0; i < 10; ++i)
            stack.push(i);

        System.out.println("Stack");

        // for (Integer i : stack)
        //     System.out.println(i);

        System.out.println("Pop");

        for (int i = 0; i < 10; ++i)
            System.out.println(stack.pop());

        // for (Integer i : stack)
        //     System.out.println(i);
    }

}
