import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
public final class Solution{
	private Solution() { }
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] rail = in.nextLine().split(" ");
		HashMap<Integer, String> stationName = new HashMap<Integer, String>();
		String[] station = in.nextLine().split(" ");
		for (int i = 0; i < Integer.parseInt(rail[0]); i++) {
			stationName.put(i, station[i]);
		}
		EdgeWeightedGraph edge = new EdgeWeightedGraph(Integer.parseInt(rail[0]));
		// Set<Integer> a = stationName.keySet();
		// System.out.println("\nKEy Set "+ a);
		for (int i = 0; i < Integer.parseInt(rail[1]); i++) {
			String[] c = in.nextLine().split(" ");
			int vert1 = 0;
			int vert2 = 0;
			for (int j = 0; j < Integer.parseInt(rail[0]); j++) {
				if (stationName.get(j).equals(c[0])) {
					vert1 = j;
				}
				if (stationName.get(j).equals(c[1])) {
					vert2 = j;
				}
			}
			edge.addEdge(new Edge(vert1, vert2, Double.parseDouble(c[2])));
		}
		int queries = Integer.parseInt(in.nextLine());
		DijkstraSP dj;
		for (int i = 0; i < queries; i++) {
			String[] q = in.nextLine().split(" ");
			int source = 0;
			int destination = 0;
			for (int j = 0; j < stationName.size(); j++) {
				if (stationName.get(j).equals(q[0])) {
					source = j;
				}
				if (stationName.get(j).equals(q[1])) {
					destination = j;
				}
			}
			dj = new DijkstraSP(edge, source);
			// System.out.println("Source: "+stationName.get(source));
			// for (int k = 0; k < stationName.size(); k++) {
				// System.out.println(dj.pathTo(k));
				// System.out.println("Source: "+stationName.get(source));
			// }
			System.out.println((int)dj.distTo(destination));
		}
	}
}