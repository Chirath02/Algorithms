import java.util.Scanner;

class MergeSort {
    void merge(int Array[], int startPos, int endPos, int pos) {
        int i=0, j=0, lengthleft = pos - startPos+1, lengthright = endpos - pos;
        int left[] = new int[lengthleft];
        int right[] = new int[lengthright];
        //System.arraycopy(src, srcPos, dest, destPos, length)
        System.arraycopy(Array, startPos, left, 0, lengthleft);
        System.arraycopy(Array, pos, right, 0, lengthright);
        for(int k=startPos; k<endPos; ++k) {
            if(left[i] < right[j])
                Array[k] = left[i++];
            else
                Array[k] = right[j++];
        }
    }

    void mergeSort(int Array, int startPos, int endPos) {
        if(endPos > startPos)
            return;
        int pos = (endPos -startPos)/2;
        mergeSort(Array, startPos, pos);
        mergeSort(Array, pos +1, endPos);
        merge(Array, startPos, endPos, pos);
    }
    public static void main(String[] args) {
        int Array[] = new Array[10];
        Array = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        MergeSort M = new MergeSort();
        M.mergeSort(Array, 0, 9);
    }
}
