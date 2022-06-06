package Lab2.Extra2;

public class QuickSorter 
{
    private static void swap(int[] array, int i, int j) 
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int partition(int[] array, int low, int high) 
    {
        int pivot = array[high];
        int i = low;

        for(int j = low; j < high; j++) {
            if(array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, high);

        return i;
    }

    public static void quickSort(int[] array, int low, int high) 
    {
        if(low < high)
        {
            int p = partition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
        }
    }

    public static void main(String[] args) 
    {
        int[] array = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        long mergeStart = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        long mergeEnd = System.nanoTime();
        System.out.println("Time to execute quicksort: " + (mergeEnd - mergeStart));
    }
}
