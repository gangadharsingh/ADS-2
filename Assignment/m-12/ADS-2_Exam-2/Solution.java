import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner s = new Scanner(System.in);
		int cities = Integer.parseInt(s.nextLine());
		int roadLines = Integer.parseInt(s.nextLine());
		EdgeWeightedGraph graph = new EdgeWeightedGraph(cities);
		for (int i = 0; i < roadLines; i++) {
			String[] inp = s.nextLine().split(" ");
			graph.addEdge(new Edge(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]), Double.parseDouble(inp[2])));
		}
		DijkstraSP shortestPath;
		String caseToGo = s.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(graph);
			//Print the Graph Object.
			break;

		case "DirectedPaths":
			String[] path = s.nextLine().split(" ");
			shortestPath = new DijkstraSP(graph, Integer.parseInt(path[0]));
			if (shortestPath.hasPathTo(Integer.parseInt(path[1]))) {
				System.out.println(shortestPath.distTo(Integer.parseInt(path[1])));
			} else {
				System.out.println("No Path Found.");
			}
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			String[] viaPath = s.nextLine().split(" ");
			double dist = 0.0;
			boolean flag = true;
			shortestPath = new DijkstraSP(graph, Integer.parseInt(viaPath[0]));
			for (int i = 1; i < viaPath.length; i++) {
				if (shortestPath.hasPathTo(Integer.parseInt(viaPath[i]))) {
					dist += shortestPath.distTo(Integer.parseInt(viaPath[i]));
					if (i == 2) {
						shortestPath = new DijkstraSP(graph, Integer.parseInt(viaPath[1]));
						dist += shortestPath.distTo(Integer.parseInt(viaPath[i]));
					}
				} else {
					flag = false;
				}
			}
			if (flag) {
				System.out.println(shortestPath.pathTo(Integer.parseInt(viaPath[2])));
				System.out.println(dist);
			} else {
				System.out.println("No Path Found.");
			}
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}