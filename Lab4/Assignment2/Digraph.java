package Lab4.Assignment2;

import java.util.Scanner;

import Lab4.Assignment1.Bag;

public class Digraph 
{
    private final int VERTICES;
    private int edges;
    private Bag<Integer>[] adj;

    public Digraph(int nrOfVertices)
    {  
        this.VERTICES = nrOfVertices;
        this.edges = 0;
        this.adj = new Bag[nrOfVertices];
        
        for(int v = 0; v < VERTICES; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Digraph(Scanner in)
    {
        this(in.nextInt());
        int e = in.nextInt();

        for(int i = 0; i < e; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(v, w);
        }
    }

    public int getVertices() { return VERTICES; }

    public int getEdges() { return edges; }

    public void addEdge(int v, int w) 
    {
        adj[v].add(w);
        edges++;
    }

    public Digraph reverse()
    {
        Digraph reversed = new Digraph(VERTICES);
        //For all vertices in digraph, and for all their adjacent vertices
        //add that edge but as w -> v instead of v -> w into new
        for(int v = 0; v < VERTICES; v++) {
            for(int w : adj(VERTICES)) {
                reversed.addEdge(w, v);
            }
        }
        return reversed;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(VERTICES + " vertices " + edges + " edges\n");
        for(int v = 0; v < VERTICES; v++) {
            sb.append(v + " ");
            for(int w : adj(v)) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
}
