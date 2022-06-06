package Lab4.Extra1;

import java.util.Scanner;

import Lab4.Assignment1.Bag;

public class EdgeWeightedDigraph 
{
    private final int VERTICES;
    private int edges;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int nrOfVertices)
    {  
        this.VERTICES = nrOfVertices;
        this.edges = 0;
        this.adj = new Bag[nrOfVertices];
        
        for(int v = 0; v < VERTICES; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(Scanner in)
    {
        this(Integer.parseInt(in.nextLine()));
        int e = Integer.parseInt(in.nextLine());

        System.out.println(edges);
        System.out.println(VERTICES);

        for(int i = 0; i < e && in.hasNextLine(); i++) {
            String[] line = in.nextLine().split(" ");
            int v = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            double weight = Double.parseDouble(line[2]);
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public int getVertices() { return VERTICES; }

    public int getEdges() { return edges; }

    public void addEdge(DirectedEdge e) 
    {
        adj[e.from()].add(e);
        edges++;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(VERTICES + " vertices " + edges + " edges\n");
        for(int v = 0; v < VERTICES; v++) {
            sb.append(v + " ");
            for(DirectedEdge w : adj(v)) {
                sb.append(w.from() + ":" + w.to() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();

        for(int v = 0; v < VERTICES; v++) {
            for(DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }
}
