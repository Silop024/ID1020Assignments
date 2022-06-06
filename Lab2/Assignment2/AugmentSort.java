package Lab2.Assignment2;

import java.util.Arrays;
import java.util.Scanner;

// Same as Lab2.Assignment1.InsertionSort.java except it keeps track of # of swaps.
public class AugmentSort
{
    public static void augmentSort(int[] array)
    {
        int swaps = 0;
        int hole;
        int number;

        for(int current = 1; current < array.length; current++)
        {
            number = array[current];
            hole = current;

            while((0 < hole) && (number < array[hole - 1]))
            {
                array[hole] = array[hole - 1];
                hole--;
                swaps++;
            }
            array[hole] = number;
        }
        System.out.println("Number of swaps: " + swaps);
    }

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);

        System.out.println("How many integers do you want to input?");
        int size = in.nextInt();

        int[] array = new int[size];

        System.out.println("Input:");
        for(int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        in.close();
        
        System.out.println("Before insertion sort:");
        System.out.println(Arrays.toString(array));

        augmentSort(array);

        System.out.println("After insertion sort:");
        System.out.println(Arrays.toString(array));
    }
}