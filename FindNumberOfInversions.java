import java.util.Arrays;
import java.util.Scanner;
// Find the number of Inversions from a list of elements

class FindNumberOfInversions {
    long countSplitInverse(int arr[], int startPos, int pos, int endPos) {
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
        long splitCount = 0;
        while (i < lengthOfleft && j < lengthOfRight)
            if (Left[i] <= Right[j]) {
                arr[k++] = Left[i++];
            }
            else {
                arr[k++] = Right[j++];
                splitCount += (lengthOfleft - i);
            }

        /* Copy the remaining elements of Left[], if there
           are any */
        while (i < lengthOfleft)
            arr[k++] = Left[i++];

        /* Copy the remaining elements of Right[], if there
           are any */
        while (j < lengthOfRight)
            arr[k++] = Right[j++];

        return splitCount;
    }

    long sortAndCount(int Array[], int startPos, int endPos) {
        if(endPos - startPos < 1)
            return 0;
        int pos = (startPos + (endPos-1))/2;
        long x = sortAndCount(Array, startPos, pos);
        long y = sortAndCount(Array, pos + 1, endPos);
        long z = countSplitInverse(Array, startPos, pos, endPos);
        return x+y+z;
        }
    public static void main(String[] args) {
        /* Input the IntegerArray.txt file as
        "java FindNumberOfInversions < IntegerArray.txt" */
        FindNumberOfInversions M = new FindNumberOfInversions();
        int Array[] = new int[100000];
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i< 100000; ++i)
            Array[i]=scanner.nextInt();

        long number = M.sortAndCount(Array, 0, 99999);
        System.out.println(number);
    }
}
