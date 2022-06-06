package Lab2.Assignment5;

import java.util.Arrays;
import Lab2.Assignment1.InsertionSort;

public class MergeSorter 
{
    private static int[] merge(int[] left, int[] right) 
    {
        int[] merged = new int[left.length + right.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int i = 0;

        while(leftIndex < left.length && rightIndex < right.length) {
            if(left[leftIndex] <= right[rightIndex]) {
                merged[i] = left[leftIndex++];
            } else {
                merged[i] = right[rightIndex++];
            }
            i++;
        }

        while(leftIndex < left.length)
            merged[i++] = left[leftIndex++];

        while(rightIndex < right.length)
            merged[i++] = right[rightIndex++];

        return merged;
    }

    public static int[] mergeSort(int[] array)
    {
        if(array.length < 2) return array;

        int midIndex = array.length / 2;

        int[] left = Arrays.copyOfRange(array, 0, midIndex);
        int[] right = Arrays.copyOfRange(array, midIndex, array.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static void main(String[] args) 
    {
        int[] array1 = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] array2 = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        long mergeStart = System.nanoTime();
        mergeSort(array1);
        long mergeEnd = System.nanoTime();
        System.out.println("Time to execute mergesort: " + (mergeEnd - mergeStart));

        long insertionStart = System.nanoTime();
        InsertionSort.insertionSort(array2);
        long insertionEnd = System.nanoTime();
        System.out.println("Time to execute insertionsort: " + (insertionEnd - insertionStart));
    }
}
