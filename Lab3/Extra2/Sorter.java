package Lab3.Extra2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter
{
    private static void shuffle(String[] a)
    {
        List<String> shuffled = new ArrayList<>(List.of(a));
        Collections.shuffle(shuffled);
        shuffled.toArray(a);
    }

    public static void sort(String[] a, BinarySearchTree<String, Integer> st)
    {
        shuffle(a);
        sort(a, 0, a.length - 1, st);
    }

    private static void sort(String[] a, int lo, int hi, BinarySearchTree<String, Integer> st)
    {
        if (hi <= lo)
            return;

        int p = partition(a, lo, hi, st);

        sort(a, lo, p - 1, st);

        sort(a, p + 1, hi, st);
    }

    private static int partition(String[] a, int lo, int hi, BinarySearchTree<String, Integer> st)
    {
        int i = lo;
        int j = hi + 1;

        Integer v = st.get(a[lo]);
        while (true) {

            while (less(st.get(a[++i]), v)) {
                if (i == hi) break;
            }

            while (less(v, st.get(a[--j]))) {
                if (j == lo) break; //redundant
            }

            if (i >= j) break;

            exch(a, i, j);
        }

        exch(a, lo, j);

        return j;
    }

    public static Comparable select(String[] a, int k, BinarySearchTree<String, Integer> st)
    {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi, st);

            if (i > k)
                hi = i - 1;
            else if (i < k)
                lo = i + 1;
            else
                return a[i];
        }
        return a[lo];
    }

    private static boolean less(Comparable v, Comparable w)
    {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j)
    {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a)
    {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi)
    {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static void show(Comparable[] a)
    {
        for (Comparable comparable : a) {
            System.out.println(comparable);
        }
    }
}
