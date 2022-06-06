package Lab4.Assignment1;

import java.io.File;
import java.util.Scanner;

public class TestClient
{
    public static String source;
    public static String destination;
    public static int x;
    public static int y;
    public static SymbolGraph symbolGraph;
    public static void main(String[] args) throws Exception
    {
        File text = new File("C:\\Users\\Silop\\Desktop\\ID1020Assignments\\Lab4\\Assignment1\\Database.txt");
        String delim = " ";

        symbolGraph = new SymbolGraph(text, delim);

        Graph graph = symbolGraph.getGraph();

        System.out.println("Enter vertex X");
        Scanner in = new Scanner(System.in);
        source = in.next();
        x = symbolGraph.indexOf(source);

        System.out.println("Enter vertex Y");
        destination = in.next();
        y = symbolGraph.indexOf(destination);

        DepthFirstSearch deapthFirstPath = new DepthFirstSearch(graph, x);
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(graph, x);


        System.out.println("Do you want to use DeapthFirstPath or BreadthFirstSearch?");
        System.out.println("1 for DFP 2 for BFS 3 for both");
        int ans = in.nextInt();

        in.close();

        switch(ans)
        {
            case 3:
                printSearch(deapthFirstPath, y);
            case 2:
                printSearch(breadthFirstSearch, y);
                break;
            case 1:
                printSearch(deapthFirstPath, y);
                break;
        }

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