package Lab4.Assignment2;

import java.io.File;
import java.util.Scanner;

import Lab4.Assignment1.SymbolTree;

public class SymbolDigraph 
{
    private SymbolTree<String, Integer> symbolTree;
    private String[] keys;
    private Digraph digraph;

    public SymbolDigraph(File text, String delim) throws Exception
    {
        // Init tree
        symbolTree = new SymbolTree<>();

        Scanner in = new Scanner(text);
        while(in.hasNextLine()) {
            String[] a = in.nextLine().split(delim);
            for(int i = 0; i < a.length; i++) {
                if(!symbolTree.contains(a[i])) {
                    symbolTree.put(a[i], symbolTree.size());
                }
            }
        }
        in.close();

        // Init keys
        keys = new String[symbolTree.size()];

        for(String name : symbolTree.keys()) {
            keys[symbolTree.get(name)] = name;
        }

        // Init graph
        digraph = new Digraph(symbolTree.size());

        in = new Scanner(text);
        while(in.hasNextLine())
        {
            String[] a = in.nextLine().split(delim);
            int v = symbolTree.get(a[0]);
            for(int i = 1; i < a.length; i++)
                digraph.addEdge(v, symbolTree.get(a[i]));
        }
        in.close();
    }

    public boolean contains(String s) { return symbolTree.contains(s); }

    public int indexOf(String s) { return symbolTree.get(s); }

    public String name(int v) { return keys[v]; }

    public Digraph getGraph() { return digraph; }
}
