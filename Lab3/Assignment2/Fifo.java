package Lab3.Assignment2;

import java.util.Iterator;

public class Fifo<T> implements Iterable<T>
{
    private class Node
    {
        public Node(T item) 
        {
            this.item = item;
        }

        T item;
        Node next;
        Node prev;
    }

    private int queueLength;
    private Node first;
    private Node last;

    public Fifo()
    {
        first = null;
        last = null;
        queueLength = 0;
    }

    public void enqueue(T item) 
    {
        if(isEmpty()) {
            first = new Node(item);
            last = first;
            first.next = last;
            last.prev = first;
        } else {
            Node temp = first;
            first = new Node(item);
            first.next = temp;
        }
        first.prev = last;
        last.next = first;

        queueLength++;
    }

    public T dequeue()
    {
        if(isEmpty()) return null;

        T item = first.item;
        first = first.next;
        first.prev = last;
        last.next = first;

        queueLength--;

        return item;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = iterator();

        while(iterator.hasNext()) {
            sb.append("[" + iterator.next() + "]");
        }
        return sb.toString();
    }

    public int size() { return queueLength; }

    public boolean isEmpty() { return queueLength == 0; }

    @Override
    public Iterator<T> iterator() 
    {
        return new Iterator<T>() {
            private Node current = first;
            private int index = 0;

            public boolean hasNext() { return index != queueLength; }

            public void remove() {}

            public T next() 
            {
                T item = current.item;
                current = current.next;
                index++;
                return item;
            }
        };
    }
}
