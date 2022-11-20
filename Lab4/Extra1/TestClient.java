package Lab4.Extra1;

import Lab4.Assignment1.Stack;

import java.io.File;
import java.util.Scanner;

public class TestClient
{
    public static void main(String[] args) throws Exception
    {
        File text = new File(args[0]);
        Scanner sc = new Scanner(text);
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(sc);

        Scanner in = new Scanner(System.in);

        System.out.println("Enter A");
        int a = in.nextInt();
        System.out.println("Enter B");
        int b = in.nextInt();

        ShortestPath sp;

        System.out.println("Enter C or 0 if not wanted");
        int c = in.nextInt();


        if (c != 0) {
            sp = new ShortestPath(graph, c);
        } else {
            sp = new ShortestPath(graph, a);
        }

        in.close();

        if (sp.hasPathTo(a) && sp.hasPathTo(b)) {
            if (c != 0) {
                System.out.print(a + " to " + b + " through " + c);
                System.out.printf(" (%4.2f): ", sp.distTo(a) + sp.distTo(b));
                Stack<DirectedEdge> path = new Stack<>();
                for (DirectedEdge e : sp.pathTo(a))
                    path.push(e);
                for (DirectedEdge e : path)
                    path.pop();
                for (DirectedEdge e : sp.pathTo(b))
                    System.out.print(e + "-");
            } else {
                System.out.print(a + " to " + b);
                System.out.printf(" (%4.2f): ", sp.distTo(b));
            }
        }
    }
}
