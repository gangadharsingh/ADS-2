import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;
class PageRank {
    Digraph dg;
    HashMap<Integer, Double> prevPR;
    public static int iterator = 1000;
    PageRank(Digraph g) {
        dg = new Digraph(g);
        prevPR = new HashMap<Integer, Double>();
    }
    HashMap<Integer, Double> getPR(int vert) {
        double initialPR = 1.0 / dg.V();
        int size = 0;
        for (int j = 0; j < dg.V(); j++) {
            prevPR.put(j, initialPR);
        }
        Double x = 0.0;
        Double d = 0.0;
        while (iterator > 0) {
            for (int i = 0; i < dg.V(); i++) {
                x = d;
                String[] resultsStr = dg.indegree(i).split(",");
                int[] array = Arrays.stream(resultsStr).mapToInt(Integer::parseInt).toArray();
                for (int j : array) {
                    // System.out.println(prevPR.get(j)+" prevPR.get(j), outdegree "+dg.outdegree(j));
                    d += prevPR.get(j) / dg.outdegree(j);
                }
                // System.out.println(d+" d, i "+i);
                prevPR.put(i,d);
            }
            if (iterator > 50) {
                if (x == d) {
                    // System.out.println(x+" X");
                    break;
                }
            }
            iterator--;
        }
        return prevPR;
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i  = 0 ; i < dg.V() ; i++) {
            str.append(i + " - " + prevPR.get(i));
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
            } else {
                dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
            }
        }
        // String s =  dig.toString();
        System.out.println(dig);
        // System.out.println();
        PageRank pr = new PageRank(dig);
        // HashMap<Integer, Double> prVert = new HashMap<Integer, Double>();
        pr.getPR(0);
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
