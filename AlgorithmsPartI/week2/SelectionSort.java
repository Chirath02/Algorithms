import java.util.Scanner;


public class SelectionSort {

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
			int min = i;
			for (int j = i + 1; j < N; ++j)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min);
		}
	}

	public static void main(String[] args) {
		SelectionSort selectionSort = new SelectionSort();
		Integer[] a = new Integer[100];

		for (int i = 0; i < 100; ++i)
			a[i] = 100 - i;

		selectionSort.sort(a);

		for (int i: a)
			System.out.println(i);
	}
}
