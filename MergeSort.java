import java.util.Scanner;
import java.util.Arrays;

class MergeSort {
    void merge(int Array[], int startPos, int endPos, int pos) {
        int i=0, j=0, lengthleft = pos - startPos+1, lengthright = endPos - pos;
        int left[] = new int[lengthleft];
        int right[] = new int[lengthright];
        //System.arraycopy(src, srcPos, dest, destPos, length)
        System.arraycopy(Array, startPos, left, 0, lengthleft);
        System.arraycopy(Array, pos, right, 0, lengthright);
        int k=startPos;
        while(i < left.length && j < right.length) {
            if(left[i] < right[j])
                Array[k++] = left[i++];
            else
                Array[k++] = right[j++];
        }
        while(i < left.length)
            Array[k++] = left[i++];
        while(j < right.length)
            Array[k++] = right[j++];
    }

    void mergeSort(int Array[], int startPos, int endPos) {
        if(Math.abs(endPos - startPos) < 2)
            return;
        int pos = (endPos + startPos)/2;
        mergeSort(Array, startPos, pos);
        mergeSort(Array, pos + 1, endPos);
        merge(Array, startPos, endPos, pos);
        System.out.println(Arrays.toString(Array));
    }
    public static void main(String[] args) {
        int Array[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        MergeSort M = new MergeSort();
        M.mergeSort(Array, 0, 9);
        System.out.println(Arrays.toString(Array));
    }
}
