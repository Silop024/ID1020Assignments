package Lab4.Assignment1;

public class SymbolTree<Key, Value>
{
    private Node first;
    private int N;

    private class Node
    {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SymbolTree()
    {
        first = null;
        N = 0;
    }

    public void put(Key key, Value val)
    {
        for(Node n = first; n != null; n = n.next) {
            if(key.equals(n.key)) {
                n.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        N++;
    }

    public Value get(Key key)
    {
        for(Node n = first; n != null; n = n.next) {
            if(key.equals(n.key)) {
                return n.val;
            }
        }
        return null;
    }

    public boolean contains(Key key) { return get(key) != null; }

    public int size() { return N; }

    public Iterable<Key> keys()
    {
        Queue<Key> q = new Queue<Key>();
        for(Node x = first; x != null; x = x.next)
            q.enqueue(x.key);
        return q;
    }
}
