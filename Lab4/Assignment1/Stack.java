package Lab4.Assignment1;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>
{
    private Node first;

    private int N;

    private class Node
    {
        T item;
        Node next;
    }

    public Stack()
    {
        first = null;
        N = 0;
    }

    public boolean isEmpty() { return first == null; }

    public int size() { return N; }

    public void push(T item)
    {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        N++;
    }

    public T pop()
    {
        T item = first.item;
        first = first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = first;

            public boolean hasNext()
            {
                return current != null;
            }
    
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