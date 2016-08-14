import java.util.Arrays;

class MergeSort {
    void merge(int arr[], int startPos, int pos, int endPos) {
        int i, j, k;
        int lengthOfleft = pos - startPos + 1;
        int lengthOfRight =  endPos - pos;

        /* create temp arrays */
        int Left[] = new int[lengthOfleft], Right[] = new int[lengthOfRight];

        /* Copy data to temp arrays Left[] and Right[]
        System.arraycopy(src, srcPos, dest, destPos, length) */
        System.arraycopy(arr, startPos, Left, 0, lengthOfleft);
        System.arraycopy(arr, pos + 1, Right, 0, lengthOfRight);

        /* Merge the temp arrays back into arr[l..r]*/
        i = 0; // Initial index of first subarray
        j = 0; // Initial index of second subarray
        k = startPos; // Initial index of merged subarray
        while (i < lengthOfleft && j < lengthOfRight)
            if (Left[i] <= Right[j])
                arr[k++] = Left[i++];
            else
                arr[k++] = Right[j++];

        /* Copy the remaining elements of Left[], if there
           are any */
        while (i < lengthOfleft)
            arr[k++] = Left[i++];

        /* Copy the remaining elements of Right[], if there
           are any */
        while (j < lengthOfRight)
            arr[k++] = Right[j++];
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
