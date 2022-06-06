package Lab4.Assignment2;

import Lab4.Assignment1.Stack;
import Lab4.Assignment1.SearchInterface;

public class DirectedDepthFirstSearch implements SearchInterface
{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DirectedDepthFirstSearch(Digraph g, int s)
    {
        this.marked = new boolean[g.getVertices()];
        this.edgeTo = new int[g.getVertices()];
        this.s = s;

        dfs(g, s);
    }

    private void dfs(Digraph g, int v)
    {
        marked[v] = true;
        for(int w : g.adj(v)) {
            if(!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int vertex)
    {
        if(!hasPathTo(vertex)) return null;

        Stack<Integer> path = new Stack<>();

        for(int v = vertex; v != s; v = edgeTo[v]) {
            path.push(v);
        }
        path.push(s);
        
        return path;
    }
}
