import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
// import java.util.equals;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * Graph OBJ.
     */
    private Digraph graph;
    private Double[] pageranks;
    private Double[][] tpageranks;
    private List<Integer> list;
    private int k;
    PageRank(Digraph digraph) {
        graph = digraph;
        k = 0;
        pageranks = new Double[graph.V()];
        tpageranks = new Double[1002][graph.V()];
        Double firstpr = 1.0 / graph.V();
        for (int i = 0; i < graph.V(); i++) {
            pageranks[i] = firstpr;
        }
        tpageranks[0] = pageranks;
        // System.out.println(Arrays.toString(pageranks));
        checkCorner();
        // graph = new Digraph(graph);
        compPageRank();
    }

    public void checkCorner() {
        for (int i = 0; i < graph.V(); i++) {
            if (graph.outdegree(i) == 0) {
                for (int j = 0; j < graph.V(); j++) {
                    if (i != j) {
                        graph.addEdge(i, j);
                    }
                }
            }
        }
    }

    public void compPageRank() {
        int vertex = 0;
        int outdegree = 0;
        Double[] pageranking = null;
        Double[] pageranker = null;
        for (k = 1; k <= 1000; k++) {
            pageranking = new Double[graph.V()];
            for (int i = 0; i < graph.V(); i++) {
                // list = graph.getindegree(i);
                Double pagerank = 0.0;
                // System.out.println(graph.indegree(i) + " indegree");
                pageranker = new Double[graph.V()];
                pageranker = tpageranks[k - 1];
                for (int j : graph.reverse().adj(i)) {
                    vertex = j;
                    outdegree = graph.outdegree(vertex);
                    pagerank += (pageranker[vertex] / outdegree);
                }
                pageranking[i] = pagerank;
            }
            // System.out.println(Arrays.toString(pageranking));
            // System.out.println(Arrays.toString(pageranking));
            tpageranks[k] = pageranking;
        }
    }

    public double getPR(int v) {
        pageranks = tpageranks[1000];
        return pageranks[v];
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < graph.V(); i++) {
            str += i + " - " + getPR(i) + "\n";
        }
        return str;
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