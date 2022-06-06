package Lab4.Assignment1;

import java.util.Iterator;

public class Bag<T> implements Iterable<T>
{
    private Node first;

    private class Node
    {
        T item;
        Node next;
    }

    public Bag()
    {
        first = null;
    }

    public void add(T item) 
    {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = first;

            public boolean hasNext() { return current != null; }

            public void remove() {}

            public T next()
            {
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }
}
