// import java.util.Scanner;
// import java.util.HashMap;
// import java.util.Arrays;
// import java.lang.Math;
// import java.math.RoundingMode;
// import java.text.DecimalFormat;
// class PageRank {
//     Digraph dg;
//     double[] prevPR;
//     double[] newPR;
//     PageRank(Digraph g) {
//         dg = new Digraph(g);
//         prevPR = new double[dg.V()];
//         newPR  = new double[dg.V()];
//         for (int j = 0; j < dg.V(); j++) {
//             if (dg.outdegree(j) == 0) {
//                 for (int k = 0; k < dg.V(); k++) {
//                     if (k != j) {
//                         dg.addEdge(j, k);
//                     }
//                 }
//             }
//             prevPR[j] = 1.0 / dg.V();
//         }
//         System.out.println(prevPR[0] + " prevPR[0]");
//     }
//     double getPR(int v) {
//         double newPr = 0.0;
//         double oldPr = 0.0;
//         int iterator = 5;
//         while(iterator > 0) {
//             System.out.print(dg.indegree(v)+" -- ");
//             String[] s = dg.indegree(v).split(",");
//             int[] array = Arrays.asList(s).stream().mapToInt(Integer::parseInt).toArray();
//             for (int j: array) {
//                 // System.out.println(j+" Jac");
//                 oldPr = prevPR[j] / dg.outdegree(j);
//                 System.out.println("j "+ j);
//                 System.out.println(prevPR[j]+" prevPR[j], dg.outdegree(j) "+dg.outdegree(j));
//                 newPr += oldPr;
//                 // System.out.println(oldPr+ " OldPr, j "+ j + " newPr "+ newPr);
//                 prevPR[j] = oldPr;
//             }
//             newPR[v] = newPr;
//             // System.out.println(newPr+" newPr");
//             // if (iterator == 3) {
//             //     if (prevPR[v] == newPR[v]) {
//             //         break;
//             //     }
//             // }
//             prevPR[v] = newPr;
//             iterator--;
//         }
//         System.out.println();
//         System.out.println(newPR[v]+" newPR[v], v "+ v);
//         return newPR[v];
//     }
//     public String toString() {
//         StringBuilder str = new StringBuilder();
//         for (int i  = 0 ; i < dg.V() ; i++) {
//             // str.append(i + " - " + prevPR.get(i));
//             str.append("\n");
//         }
//         str.toString();
//         return str.toString();
//     }
// }


// public class Solution {
//     public static void main(String[] args) {
//         Scanner scan = new Scanner(System.in);
//         // read the first line of the input to get the number of vertices
//         int vert = Integer.parseInt(scan.nextLine());
//         Digraph dig = new Digraph(vert);
//         // iterate count of vertices times
//         double[] d = new double[vert];
//         for (int i = 0; i < vert; i++) {
//             String[] edge = scan.nextLine().split(" ");
//             if (edge.length > 1) {
//                 for (int j = 1; j < edge.length; j++) {
//                     dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[j]));
//                 }
//             } else if (edge.length == 2) {
//                 dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
//             }
//         }
//         // String s =  dig.toString();
//         System.out.println(dig);
//         System.out.println();
//         PageRank pr = new PageRank(dig);
//         for (int i = 0; i < vert; i++) {
//             d[i] = pr.getPR(i);
//         }
//         // to read the adjacency list from std input
//         // and build the graph


//         // Create page rank object and pass the graph object to the constructor

//         // print the page rank object

//         // This part is only for the final test case

//         // File path to the web content
//         // String file = "WebContent.txt";

//         // instantiate web search object
//         // and pass the page rank object and the file path to the constructor

//         // read the search queries from std in
//         // remove the q= prefix and extract the search word
//         // pass the word to iAmFeelingLucky method of web search
//         // print the return value of iAmFeelingLucky

//     }
// }

// class WebSearch {
//     WebSearch(Double Pr, String filename) {

//     }
//     int iAmFeelingLucky(String query) {
//         return 0;
//     }
// }
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
        String file = "WebContent.txt";
        
        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
        
    }
}
