package Lab2.Assignment3;

import java.util.Arrays;
import java.util.Scanner;

public class Inversions 
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

    public static int inversion(int[] array) 
    {
        int inversions = 0;

        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if(array[i] > array[j]) {
                    inversions++;
                    System.out.printf("[%d, %d], [%d, %d]", i, array[i], j, array[j]);
                }
            }
        }
        return inversions;
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
        System.out.println("Number of inversions: " + inversion(array));

        augmentSort(array);

        System.out.println("After insertion sort:");
        System.out.println(Arrays.toString(array));
    }
}
