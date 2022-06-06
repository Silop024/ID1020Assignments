package Lab3.Assignment2;

public class SearchTree<Key extends Comparable<Key>, Value>
{
    private Key[] keys;
    private Value[] values;
    private int N;

    public SearchTree(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        N = 0;
    }

    public int size() { return N; }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(Key key) { return get(key) != null; }

    public int rank(Key key)
    {
        int low = 0;
        int high = N - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            int compare = key.compareTo(keys[mid]);

            if(compare < 0) {
                high = mid - 1;
            } else if(compare > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    public Value get(Key key)
    {
        if(isEmpty()) return null;

        int i = rank(key);

        //If key is inside table (i < size) & if key in i is equal to given key
        //return its value. Else return null.
        if(i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    public void put(Key key, Value value)
    {
        int i = rank(key);

        //If key already exists in the table, update value and go out of method
        if(i < N && keys[i].compareTo(key) == 0)
        {
            values[i] = value;
            return;
        }
        for(int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Iterable<Key> keys(Key low, Key high)
    {
        Fifo<Key> queue = new Fifo<Key>();

        for(int i = rank(low); i < rank(high); i++) {
            queue.enqueue(keys[i]);
        }
        if(contains(high)) {
            queue.enqueue(keys[rank(high)]);
        }
        return queue;
    }
    public Iterable<Key> keys()
    {
        return keys(keys[0], keys[N-1]);
    }
}
