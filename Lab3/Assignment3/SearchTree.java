package Lab3.Assignment3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Lab3.Assignment2.Fifo;

public class SearchTree<Key, Value>
{
    private int M;
    private int N;

    private class Node
    {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node[] tree;

    public SearchTree(int m)
    {
        this.M = m;
        this.tree = (Node[]) Array.newInstance(Node.class, m);
    }

    public int size() { return N; }

    public int hash(Key key) { return (key.hashCode() & 0x7fffffff) % M; }

    public void put(Key key, Value value) 
    {
        int i = hash(key);
        for(Node n = tree[i]; n != null; n = n.next) {
            if(n.key.equals(key)) {
                n.value = value;
                return;
            }
        }
        tree[i] = new Node(key, value, tree[i]);
        N++;
    }

    public Value get(Key key)
    {
        int i = hash(key);
        for(Node n = tree[i]; n != null; n = n.next) {
            if(n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    public void delete(Key key) { put(key, null); }

    public boolean contains(Key key) { return get(key) != null; }

    public boolean isEmpty() { return N == 0; }

    public void printSize()
    {
        for(Node n : tree) {
            int size = 0;

            while(n != null && n.key != null) {
                n = n.next;
                size++;
            }
            System.out.println("Size: " + size);
        }
    }

    public Iterable<Key> keys()
    {
        Fifo<Key> queue = new Fifo<>();

        for(int i = 0; i < M; i++) {
            for(Node n = tree[i]; n != null; n = n.next) {
                queue.enqueue(n.key);
            }
        }
        return queue;
    }
}
