import java.util.Scanner;
import java.util.Arrays;

class MergeSort {
    void merge(int arr[], int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 =  r - m;

        /* create temp arrays */
        int L[] = new int[n1], R[] = new int[n2];

        /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1+ j];

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0; // Initial index of first subarray
        j = 0; // Initial index of second subarray
        k = l; // Initial index of merged subarray
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy the remaining elements of L[], if there
           are any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy the remaining elements of R[], if there
           are any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void mergeSort(int Array[], int startPos, int endPos) {
        if(endPos - startPos < 1)
            return;
        int pos = (startPos + (endPos-1))/2;
        mergeSort(Array, startPos, pos);
        mergeSort(Array, pos + 1, endPos);
        merge(Array, startPos, pos, endPos);
        }
    public static void main(String[] args) {
        int Array[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        MergeSort M = new MergeSort();
        M.mergeSort(Array, 0, 9);
        System.out.println(Arrays.toString(Array));
    }
}
