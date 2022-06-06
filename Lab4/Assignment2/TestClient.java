package Lab4.Assignment2;

import java.io.File;
import java.util.Scanner;
import Lab4.Assignment1.SearchInterface;

public class TestClient
{
    public static String source;
    public static String destination;
    public static int x;
    public static int y;
    public static SymbolDigraph symbolGraph;
    public static void main(String[] args) throws Exception
    {
        File text = new File("C:\\Users\\Silop\\Desktop\\ID1020Assignments\\Lab4\\Assignment1\\Database.txt");
        String delim = " ";

        symbolGraph = new SymbolDigraph(text, delim);

        Digraph graph = symbolGraph.getGraph();

        System.out.println("Enter vertex X");
        Scanner in = new Scanner(System.in);
        source = in.next();
        x = symbolGraph.indexOf(source);

        System.out.println("Enter vertex Y");
        destination = in.next();
        y = symbolGraph.indexOf(destination);
        in.close();
        DirectedDepthFirstSearch deapthFirstPath = new DirectedDepthFirstSearch(graph, x);
        printSearch(deapthFirstPath, y);

    }

    public static void printSearch(SearchInterface dfp, int y)
    {
        if(dfp.hasPathTo(y)) {
            System.out.print(source + " to " + destination + ": ");
            for(int v : dfp.pathTo(y)) {
                if(v == x) {
                    System.out.print(symbolGraph.name(v));
                } else {
                    System.out.print("-" + symbolGraph.name(v));
                }
            }
            System.out.println();
        }
        else {
            System.out.println("There exists no such path");
        }
    }
}