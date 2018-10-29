import java.util.Scanner;
import java.util.HashMap;
class Solution{
	private Solution() { }
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String type = scan.nextLine();
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		HashMap<Integer, String> map = new HashMap<>();
		AdjMatrixGraph matrix = new AdjMatrixGraph(vertices);
		ListGraph graph = new ListGraph(vertices);
		String[] cities = scan.nextLine().split(",");
		if (type.equals("List")) {
			for (int i = 0; i < edges; i++) {
				int v = Integer.parseInt(scan.nextLine());
				int w = Integer.parseInt(scan.nextLine());
				map.put(i, cities[i]);
				graph.addEdge(v, w);
			}
			System.out.println(graph);
		} else if (type.equals("Matrix")) {
			for (int i = 0; i < edges; i++) {
				int v = Integer.parseInt(scan.nextLine());
				int w = Integer.parseInt(scan.nextLine());
				matrix.addEdge(v, w);	
				map.put(i, cities[i]);
			}
			System.out.println(matrix);
		}
	}
}