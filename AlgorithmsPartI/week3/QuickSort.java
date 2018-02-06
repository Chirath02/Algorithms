
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

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        Integer[] a = new Integer[]{5, 7, 4, 8, 10, 9, 2, 6, 3, 1};
        for (int i: a)
            System.out.print(i + " ");
        System.out.println();
        System.out.println(qs.partition(a, 0, a.length - 1));
        for (int i: a)
            System.out.print(i + " ");
        System.out.println();

    }
}
