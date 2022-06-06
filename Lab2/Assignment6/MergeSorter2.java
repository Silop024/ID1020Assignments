package Lab2.Assignment6;

import java.util.Arrays;
import java.util.Random;

import Lab2.Assignment1.InsertionSort;

// Same mergesort as Assignmen5 but with a cutoff where the speed of insertion sort is greater than mergesort to utilise both strengths.
public class MergeSorter2 
{
    private static final int CUTOFF = 30;

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

        if(array.length < CUTOFF) {
            InsertionSort.insertionSort(array);
            return array;
        }

        int midIndex = array.length / 2;

        int[] left = Arrays.copyOfRange(array, 0, midIndex);
        int[] right = Arrays.copyOfRange(array, midIndex, array.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static void main(String[] args) 
    {
        Random prng = new Random();

        int[] array1 = prng.ints(-99, 99).limit(1000000).toArray();

        long mergeStart = System.nanoTime();
        mergeSort(array1);
        long mergeEnd = System.nanoTime();
        System.out.println("Execution time of mergesort2: " + (mergeEnd - mergeStart));
    }
}
