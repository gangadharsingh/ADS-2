import java.util.Scanner;
import java.util.HashMap;
class Solution {
	private Solution() { }
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String type = scan.nextLine();
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		HashMap<Integer, String> map = new HashMap<>();
		AdjMatrixGraph matrix = new AdjMatrixGraph(vertices);
		ListGraph graph = new ListGraph(vertices);
		if (vertices == 0) {
			System.out.println(vertices + " vertices, " + edges + " edges");
			System.out.println("No edges");
			return;
		} else if (edges == 0) {
			System.out.println(vertices + " vertices, " + edges + " edges");
			System.out.println("No edges");
			return;
		} else if (vertices == 0 && edges == 0) {
			System.out.println(vertices + " vertices, " + edges + " edges");
			System.out.println("No edges");
			return;
		} else {
			String[] cities = scan.nextLine().split(",");
			if (type.equals("List")) {
				for (int i = 0; i < edges; i++) {
					String[] vert = scan.nextLine().split(" ");
					map.put(i, cities[i]);
					graph.addEdge(Integer.parseInt(vert[0]), Integer.parseInt(vert[1]));
				}
				String s = graph.toString();
			} else if (type.equals("Matrix")) {
				for (int i = 0; i < edges; i++) {
					String[] vert = scan.nextLine().split(" ");
					matrix.addEdge(Integer.parseInt(vert[0]), Integer.parseInt(vert[1]));
					map.put(i, cities[i]);
				}
				String s = matrix.toString();
			}
		}
	}
}