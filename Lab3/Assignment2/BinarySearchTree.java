package Lab3.Assignment2;

public class BinarySearchTree<Key extends Comparable<Key>, Value>
{
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
        }
    }

    private Node root;

    private int size(Node node) { return node != null ? node.N : 0; }

    public int size() { return size(root); }

    private int rank(Key key, Node node)
    {
        if(node == null) return 0;

        int compare = key.compareTo(node.key);

        if(compare == 0) return size(node.left);

        return compare < 0 ? rank(key, node.left) : size(node.left) + rank(key, node.right) + 1;
    }

    public int rank(Key key) { return rank(key, root); }

    private Value get(Key key, Node node)
    {
        if(node == null) return null;

        int compare = key.compareTo(node.key);

        if(compare == 0) return node.value;

        return compare < 0 ? get(key, node.left) : get(key, node.right);
    }

    public Value get(Key key) { return get(key, root); }

    private Node put(Key key, Value value, Node node)
    {
        if(node == null) return new Node(key, value, 1);

        int compare = key.compareTo(node.key);

        if(compare < 0) {
            node.left = put(key, value, node.left);
        } else if(compare > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.value = value;
        }

        node.N = size(node.left) + size(node.right) + 1;

        return node;
    }

    public void put(Key key, Value value)
    {
        root = put(key, value, root);
    }

    private Node min(Node node)
    {
        return node.left == null ? node : min(node.left);
    }

    public Key min() { return min(root).key; }

    private Node max(Node node)
    {
        return node.right == null ? node : max(node.right);
    }

    public Key max() { return max(root).key; }

    private void keys(Node node, Fifo<Key> queue, Key low, Key high)
    {
        if(node == null) return;

        int compareLow  = low.compareTo(node.key);
        int compareHigh = high.compareTo(node.key);

        if(compareLow < 0) {
            keys(node.left, queue, low, high);
        } if(compareLow <= 0 && compareHigh >= 0) {
            queue.enqueue(node.key);
        } if(compareHigh > 0) {
            keys(node.right, queue, low, high);
        }
    }

    public Iterable<Key> keys(Key low, Key high)
    {
        Fifo<Key> queue = new Fifo<>();
        keys(root, queue, low, high);
        return queue;
    }

    public Iterable<Key> keys() { return keys(min(), max()); }
}
