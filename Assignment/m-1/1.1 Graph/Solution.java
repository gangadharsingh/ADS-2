import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;

/**.
 * Class for solution.
 */
class Solution {
	/**.
	 * Constructs the object.
	 */
	private Solution() { }

	/**.
	 * { function_description }
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String type = scan.nextLine();
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		HashMap<Integer, String> map = new HashMap<>();
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
				String[] numVer = s.split("\\r?\\n");
				System.out.println(numVer[0]);
				for (int i = 1; i < numVer.length; i++) {
					int j = 0;
					String[] c = numVer[i].replaceAll("[:,]", ";").split(";");
					for (int k = 0; k < c.length; k ++) {
						int a = Integer.parseInt(c[k]);
						if (j == 0) {
							System.out.print(map.get(a) + ": ");
						} else {
							System.out.print(map.get(a) + " ");
						}
						j++;
					}
					System.out.println();
				}

			} else if (type.equals("Matrix")) {
				AdjMatrixGraph m = new AdjMatrixGraph(vertices, edges, cities);
				for (int k = 0; k < edges; k++) {
					String[] tokens = scan.nextLine().split(" ");
					m.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
				}
				System.out.println(m.V() + " vertices, " + m.E() + " edges");
				m.print();
			}
		}
	}
}
