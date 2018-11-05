import java.util.Scanner;
import java.util.Arrays;
class Solution {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int vert = Integer.parseInt(in.nextLine());
		int testcases = Integer.parseInt(in.nextLine());
		EdgeWeightedGraph wtEdge = new EdgeWeightedGraph(vert);
		for (int i = 0; i < testcases; i++) {
			String[] inp = in.nextLine().split(" ");
			wtEdge.addEdge(new Edge(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]), Double.valueOf(Integer.parseInt(inp[0]))));
		}
		PrimMST p =  new PrimMST(wtEdge);
		System.out.println(p.edges());
	}
}
