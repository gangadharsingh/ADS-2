import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;
class PageRank {
    Digraph dg;
    double[] prevPR;
    double[] newPR;
    PageRank(Digraph g) {
        dg = new Digraph(g);
        prevPR = new double[dg.V()];
        newPR  = new double[dg.V()];
        for (int j = 0; j < dg.V(); j++) {
            if (dg.outdegree(j) == 0) {
                for (int k = 0; k < dg.V(); k++) {
                    if (k != j) {
                        dg.addEdge(j, k);
                    }
                }
            }
            prevPR[j] = 1.0 / dg.V();
        }
        getPR(0);
        // System.out.println(prevPR[0] + " prevPR[0]");
    }
    double[] getPR(int v) {
        int vertex = 0;
        int outdegree = 0;
        double[] prevPR = null;
        double[] pageranker = null;
        for (int k = 1; k <= 1000; k++) {
            prevPR = new double[dg.V()];
            for (int i = 0; i < dg.V(); i++) {
                // list = dg.getindegree(i);
                double pagerank = 0.0;
                // System.out.println(dg.indegree(i) + " indegree");
                pageranker = new double[dg.V()];
                pageranker = newPR;
                for (int j : dg.reverse().adj(i)) {
                    vertex = j;
                    outdegree = dg.outdegree(vertex);
                    pagerank += (pageranker[vertex] / outdegree);
                }
                prevPR[i] = pagerank;
            }
            // System.out.println(Arrays.toString(prevPR));
            // System.out.println(Arrays.toString(prevPR));
            newPR[k] = prevPR[k];
        }
        return newPR;
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i  = 0 ; i < dg.V() ; i++) {
            str.append(i + " - " + newPR[i]);
            str.append("\n");
        }
        str.toString();
        return str.toString();
    }
}


public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // read the first line of the input to get the number of vertices
        int vert = Integer.parseInt(scan.nextLine());
        Digraph dig = new Digraph(vert);
        // iterate count of vertices times
        double[] d = new double[vert];
        for (int i = 0; i < vert; i++) {
            String[] edge = scan.nextLine().split(" ");
            if (edge.length > 1) {
                for (int j = 1; j < edge.length; j++) {
                    dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[j]));
                }
            } else if (edge.length == 2) {
                dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
            }
        }
        // String s =  dig.toString();
        System.out.println(dig);
        System.out.println();
        PageRank pr = new PageRank(dig);
        System.out.println(pr);
        // to read the adjacency list from std input
        // and build the graph


        // Create page rank object and pass the graph object to the constructor

        // print the page rank object

        // This part is only for the final test case

        // File path to the web content
        // String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}

class WebSearch {
    WebSearch(Double Pr, String filename) {

    }
    int iAmFeelingLucky(String query) {
        return 0;
    }
}
