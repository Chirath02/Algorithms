import java.util.Random;


public class QuickSort {
    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;

        while (true) {
            // Increment the i pointer
            while (less(a[++i], a[lo]))
                if (i == hi)
                    break;
            while (less(a[lo], a[--j]))
                if (j == lo)
                    break;

            if (j <= i)
                break;
            else
                exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int index = partition(a, lo, hi);
        sort(a, lo, index - 1);
        sort(a, index + 1, hi);
    }

    private void shuffle(Comparable[] a) {
        Random rand = new Random();
        for (int i = 1; i < a.length; ++i) {
            int index = rand.nextInt(i);
            exch(a, index, i);
        }
    }

    public void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        Integer[] a = new Integer[]{5, 7, 4, 8, 10, 9, 2, 6, 3, 1};
        for (int i: a)
            System.out.print(i + " ");
        System.out.println();
        qs.sort(a);
        for (int i: a)
            System.out.print(i + " ");
        System.out.println();
    }
}
