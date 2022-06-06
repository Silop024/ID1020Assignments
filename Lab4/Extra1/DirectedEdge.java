package Lab4.Extra1;

public class DirectedEdge implements Comparable<DirectedEdge>
{
    private final int V1;
    private final int V2;
    private final double WEIGHT;

    public DirectedEdge(int v1, int v2, double weight)
    {
        this.V1 = v1;
        this.V2 = v2;
        this.WEIGHT = weight;
    }
    
    public double getWeight() { return WEIGHT; }

    public int from() { return V1; }

    public int to() { return V2; }


    @Override
    public int compareTo(DirectedEdge other) {
        if(this.WEIGHT == other.WEIGHT) return 0;

        return this.WEIGHT < other.WEIGHT ? -1 : 1;
    }
}