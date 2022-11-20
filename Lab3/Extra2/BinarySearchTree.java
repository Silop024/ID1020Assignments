package Lab3.Extra2;

import Lab3.Assignment2.Fifo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearchTree<Key extends Comparable<Key>, Value extends Comparable<Value>>
{
    private Node root;

    private class Node
    {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value value, int N)
        {
            this.key = key;
            this.value = value;
            this.N = N;
            left = null;
            right = null;
        }
    }

    public int size()
    {
        return size(root);
    }

    private int size(Node x)
    {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    public Value get(Key key)
    {
        return get(root, key);
    }

    private Value get(Node x, Key key)
    {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.value;
    }

    public void put(Key key, Value value)
    {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val)
    {
        if (x == null)
            return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.value = val;

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key)
    {
        return get(key) != null;
    }

    public Key min()
    {
        return min(root).key;
    }

    private Node min(Node x)
    {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max()
    {
        return max(root).key;
    }

    private Node max(Node x)
    {
        if (x.right == null)
            return x;
        return max(x.right);
    }

    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi)
    {
        Fifo<Key> q = new Fifo<>();

        keys(root, q, lo, hi);

        return q;
    }

    private void keys(Node x, Fifo<Key> q, Key lo, Key hi)
    {
        if (x == null)
            return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0)
            keys(x.left, q, lo, hi);

        if (cmplo <= 0 && cmphi >= 0)
            q.enqueue(x.key);

        if (cmphi > 0)
            keys(x.right, q, lo, hi);
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        Scanner in = new Scanner(System.in);

        int minlen = 1;
        int uniqueWords = 0;

        BinarySearchTree<String, Integer> st = new BinarySearchTree<>();

        while (sc.hasNext()) {
            String word = sc.next();

            if (word.length() < minlen)
                continue;

            if (!st.contains(word)) {
                st.put(word, 1);
                uniqueWords++;
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        String[] intervalArray = new String[uniqueWords];
        int k = 0;

        for (String word : st.keys()) {
            intervalArray[k] = word;
            k++;
        }

        Sorter.sort(intervalArray, st);

        while (true) {
            System.out.println("\nPress 1 for interval, press 2 for a single rank [or 0 to exit]: ");
            int n = in.nextInt();
            System.out.println();

            if (n == 0)
                System.exit(0);

            if (n == 1) {
                System.out.println("Set the interval of the most frequent words between a and b");
                System.out.println("k:th word:");
                int a = in.nextInt();
                System.out.println("k+n:th word:");
                int b = in.nextInt();

                //prints out the given interval
                a = (intervalArray.length) - a;
                b = (intervalArray.length) - b;
                System.out.println();
                while (a >= b) {
                    System.out.println('\n');
                    System.out.print("'" + intervalArray[a] + "'");
                    System.out.print(" with the frequency of:  " + st.get(intervalArray[a]));
                    a--;
                }
            } else {
                System.out.println("Set the rank you want: ");
                int c = in.nextInt();
                c = (intervalArray.length) - c;
                System.out.println('\n');
                System.out.print("'" + intervalArray[c] + "'");
                System.out.print(" with the frequency of:  " + st.get(intervalArray[c]));
            }
        }
    }
}
