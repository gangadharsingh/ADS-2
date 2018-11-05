import java.util.Scanner;
import java.util.Arrays;
class Solution {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int vert = in.nextInt();
		int testcases = in.nextInt();
		EdgeWeightedGraph wtEdge = new EdgeWeightedGraph(vert);
		for (int i = 0; i < testcases; i++) {
			String[] inp = in.nextLine().split(" ");
			int[] inp1 = Arrays.asList(inp).stream().mapToInt(Integer::parseInt).toArray();
			wtEdge.addEdge(new Edge(inp1[0], inp1[1], Double.valueOf(inp1[3])));
		}
		PrimMST p =  new PrimMST(wtEdge);
		System.out.println(p.edges());
	}
}
