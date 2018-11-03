import java.util.Scanner;
import java.util.HashMap;
class PageRank {
	Digraph dg;
	HashMap<Integer, Double> map;
	public static int iterator = 1000;
	PageRank(Digraph g) {
		dg = new Digraph(g);
		map = new HashMap<Integer, Double>();
	}
	double getPR(int vert) {
		double initialPR = 1/dg.V();
		HashMap<Integer, Double> prevPR = new HashMap<Integer, Double>();
		int[] adj=new int[dg.outdegree(vert)];
		int size = 0;
		for (int i: dg.adj(vert)) {
			adj[size++] = i;
		}
		while(iterator > 0) {
			for (int j = 0; j < dg.V(); j++) {
				prevPR.put(j, initialPR);	
			}
			iterator--;
		}
		return 0.0;
	}
	public String toString() {
		StringBuilder str = new StringBuilder();
		return "";
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
					dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[i]));
				}
			} else {
				dig.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
			}
		}
		dig.toString();
		PageRank pr = new PageRank(dig);
		pr.getPR(vert);
		// to read the adjacency list from std input
		// and build the graph
		
		
		// Create page rank object and pass the graph object to the constructor
		
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

class WebSearch {
	WebSearch(Double Pr, String filename) {

	}
	int iAmFeelingLucky(String query) {
		return 0;
	}
}
