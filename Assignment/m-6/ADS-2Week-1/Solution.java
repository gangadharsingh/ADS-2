import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * Graph OBJ.
     */
    private Digraph dg;
    private Double[] newPR;
    private Double[][] prevPR;
    private int k;
    PageRank(Digraph digraph) {
        dg = digraph;
        k = 0;
        newPR = new Double[dg.V()];
        prevPR = new Double[1000][dg.V()];
        Double firstpr = 1.0 / dg.V();
        for (int i = 0; i < dg.V(); i++) {
            newPR[i] = firstpr;
        }
        prevPR[0] = newPR;
        for (int i = 0; i < dg.V(); i++) {
            if (dg.outdegree(i) == 0) {
                for (int j = 0; j < dg.V(); j++) {
                    if (i != j) {
                        dg.addEdge(i, j);
                    }
                }
            }
        }
        compPageRank();
    }

    public void compPageRank() {
        int v = 0;
        int outdg = 0;
        Double[] pageranking = null;
        Double[] pageranker = null;
        for (k = 1; k <= 1000; k++) {
            pageranking = new Double[dg.V()];
            for (int i = 0; i < dg.V(); i++) {
                // list = dg.getindegree(i);
                Double pagerank = 0.0;
                // System.out.println(dg.indegree(i) + " indegree");
                pageranker = new Double[dg.V()];
                pageranker = prevPR[k - 1];
                for (int j : dg.reverse().adj(i)) {
                    v = j;
                    outdg = dg.outdegree(v);
                    pagerank += (pageranker[v] / outdg);
                }
                pageranking[i] = pagerank;
            }
            prevPR[k] = pageranking;
        }
    }

    public double getPR(int v) {
        newPR = prevPR[1000];
        return newPR[v];
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < dg.V(); i++) {
            str.append(i + " - " + getPR(i) + "\n");
        }
        return str.toString();
    }
}

class WebSearch {

}


public class Solution {
    public static void main(String[] args) {
        // read the first line of the input to get the number of vertices
        Scanner sc = new Scanner(System.in);
        int verticescount = Integer.parseInt(sc.nextLine());
        // iterate count of vertices times
        // HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        String[] tokens = null;
        int first = 0;
        int second = 0;
        Digraph digraph = new Digraph(verticescount);
        for (int i = 0; i < verticescount; i++) {
            tokens = sc.nextLine().split(" ");
            first = Integer.parseInt(tokens[0]);
            // list = new ArrayList<Integer>();
            for (int j = 1; j < tokens.length; j++) {
                second = Integer.parseInt(tokens[j]);
                digraph.addEdge(first, second);
            }
        }
        System.out.println(digraph);
        // to read the adjacency list from std input
        // and build the graph
        // Create page rank object and pass the graph object to the constructor
        PageRank probj = new PageRank(digraph);
        System.out.println(probj);
        // print the page rank object
        
        // This part is only for the final test case
        
        // File path to the web content
        
        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
        
    }
}
