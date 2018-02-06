

public class MergeSort {
    public boolean isSorted(Comparable a[], int lo, int hi) {
        for (int i = lo; i < hi; ++i)
            if (!less(a[i], a[i + 1]))
                return false;
        return true;
    }

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        for (int k = lo; k <= hi; ++k)
            aux[k] = a[k];

        int i = lo; int j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
        assert isSorted(a, lo, hi);
    }

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (less(a[mid], a[mid + 1]))
            return;
        merge(a, aux, lo, mid, hi);
    }

    public void sortIterative(Comparable[]a, Comparable[] aux) {
        int N = a.length;
        for (int size = 1; size < N; size *= 2) {
            for (int lo = 0; lo < N - size; lo += (size * 2)) {
                merge(a, aux, lo, lo + size - 1, Math.min(lo + (size * 2) - 1, N-1));
            }
        }
    }

    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        // sort(a, aux, 0, a.length - 1);
        sortIterative(a, aux);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 10; i > 0; --i)
            a[10 - i] = i;

        MergeSort mSort = new MergeSort();
        mSort.sort(a);

        for (int i = 0; i < 10; ++i)
            System.out.println(a[i] + " ");
    }
}
