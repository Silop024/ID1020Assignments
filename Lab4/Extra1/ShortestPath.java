package Lab4.Extra1;

import Lab4.Assignment1.Stack;

public class ShortestPath 
{
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> pq;

    public ShortestPath(EdgeWeightedDigraph g, int s)
    {
        this.distTo = new double[g.getVertices()];
        this.edgeTo = new DirectedEdge[g.getVertices()];
        this.pq = new IndexMinPQ<Double>(g.getVertices());

        for(int v = 0; v < g.getVertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;

        pq.insert(s, 0.0);
        while(!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    //To relax a vertex, means to test whether the best known way from s
    //to w is to go from s to v, then take the edge from v to w, and, if
    //so, update our data structures to indicate that to be the case
    private void relax(EdgeWeightedDigraph g, int v)
    {
        for(DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if(distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;

                if(pq.contains(w)) {
                    //pq.decrease(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) { return distTo[v]; }
    //
    public DirectedEdge edgeTo(int v) { return edgeTo[v]; }

    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY; }

    public Iterable<DirectedEdge> pathTo(int v)
    {
        if(!hasPathTo(v)) return null;

        Stack<DirectedEdge> path = new Stack<DirectedEdge>();

        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
