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
    public static int iterator = 100;
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
    }
    double getPR(int v) {
        double pr = 0.0;
        while(iterator > 0) {
            String[] s = dg.indegree(v).split(",");
            int[] array = Arrays.asList(s).stream().mapToInt(Integer::parseInt).toArray();
            for (int j: array) {
                pr += prevPR[j] / dg.outdegree(j);
            }
            newPR[v] = pr;
            if(iterator == 10) {
                prevPR[v] = pr;
            }
            if (iterator == 100) {
                if (prevPR[v] == newPR[v]) {
                    break;
                }
            }
            iterator--;
        }
        return newPR[v];
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i  = 0 ; i < dg.V() ; i++) {
            // str.append(i + " - " + prevPR.get(i));
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
        // HashMap<Integer, Double> prVert = new HashMap<Integer, Double>();
        pr.getPR(0);
        // System.out.println(pr);
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
