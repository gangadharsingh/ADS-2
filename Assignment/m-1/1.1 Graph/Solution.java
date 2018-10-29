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
		if (vertices == 0 && edges != 0) {
			System.out.println(vertices + " vertices, " + edges + " edges");
			System.out.println("No edges");
			return;
		}
		if (edges == 1 && vertices == 1) {
			System.out.println(vertices + " vertices, " + 0 + " edges");
			System.out.println("No edges");
			return;
		}
		if (vertices == 0 && edges == 0) {
			System.out.println(vertices + " vertices, " + edges + " edges");
			System.out.println("No edges");
			return;
		}
		if (vertices != 0 && edges != 0) {
			String[] cities = scan.nextLine().split(",");
			if (type.equals("List")) {
				for (int i = 0; i < vertices; i++) {
					String[] vert = scan.nextLine().split(" ");
					map.put(i, cities[i]);
					graph.addEdge(Integer.parseInt(vert[0]), Integer.parseInt(vert[1]));
				}
				String s = graph.toString();
				// System.out.println(s);
				String[] numVer = s.split("\\r?\\n");
				System.out.println(numVer[0]);
				for (int i = 1; i < numVer.length; i++) {
					int j = 0;
					for(char c: numVer[i].toCharArray()) {
						// c = Integer.parseInt("c");
						if(Character.isDigit(c)) {
							// System.out.print(c+ ":");
							if (j == 0) {
								System.out.print(map.get(Character.getNumericValue(c))+": ");
							}
							System.out.print(map.get(Character.getNumericValue(c))+" ");
							j++;
						}
					}
					System.out.println();
				}

			} else if (type.equals("Matrix")) {
				for (int i = 0; i < vertices; i++) {
					String[] vert = scan.nextLine().split(" ");
					matrix.addEdge(Integer.parseInt(vert[0]), Integer.parseInt(vert[1]));
					map.put(i, "i");
				}
				String s = matrix.toString();
				String[] numVer = s.split("\\r?\\n");
				for (String str: numVer) {
					System.out.println(str);
				}
			}
		}
	}
}