package Lab4.Assignment1;

public class BreadthFirstSearch implements SearchInterface
{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstSearch(Graph g, int s)
    {
        this.marked = new boolean[g.getVertices()];
        this.edgeTo = new int[g.getVertices()];
        this.s = s;
        bfs(g, s);
    }

    private void bfs(Graph G, int s)
    {
	    Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.enqueue(s);

        while(!queue.isEmpty()) {
	        int v = queue.dequeue();
            for(int w : G.adj(v)) {
                if(!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int vertex)
    {
        if(!hasPathTo(vertex)) return null;

        Stack<Integer> path = new Stack<Integer>();
        for(int v = vertex; v != s; v = edgeTo[v]) {
            path.push(v);
        }
        path.push(s);
        
        return path;
    }
}
