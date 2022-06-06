package Lab4.Assignment1;

import java.util.Scanner;

public class Graph 
{
    private final int VERTICES;
    private int edges;
    private Bag<Integer>[] adj;

    public Graph(int vertices)
    {
        this.VERTICES = vertices;
        this.edges = 0;

        adj = new Bag[vertices];

        for(int v = 0 ; v < vertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(Scanner in)
    {
        //Reads V and calls constructor 1 with it
        this(in.nextInt());
        //Reads E
        int E = in.nextInt();
        //For all edges
        for(int i = 0; i < E; i++)
        {
            //Add an edge by reading two vertices and connecting them
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
        adj[w].add(v);
        edges++;
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
