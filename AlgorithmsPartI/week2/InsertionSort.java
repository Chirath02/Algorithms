import java.util.Scanner;
import java.lang.Integer;


public class InsertionSort {

	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void sort(Comparable a[]) {
		int N = a.length;
		for (int i = 0; i < N; ++i) {
			for (int j = i; j > 0; --j)
				if (less(a[j], a[j - 1]))
					exch(a, j, j - 1);
				else
					break;
		}
	}

	public static void main(String[] args) {
		InsertionSort insertionSort = new InsertionSort();
		Integer[] a = new Integer[100];

		for (int i = 0; i < 100; ++i)
			a[i] = 100 - i;

		insertionSort.sort(a);

		for (int i: a)
			System.out.println(i);
	}
}
