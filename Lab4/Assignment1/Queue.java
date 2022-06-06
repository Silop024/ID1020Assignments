package Lab4.Assignment1;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>
{
    private Node first;
    private Node last;
    private int N;

    private class Node
    {
        T item;
        Node next;
    }

    public Queue()
    {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() { return first == null; }

    public int size() { return N; }

    public void enqueue(T item) 
    {
        Node old = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            old.next = last;
        N++;
    }

    public T dequeue()
    {
        T item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
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