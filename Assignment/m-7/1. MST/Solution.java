import java.util.Scanner;
/**.
 * Class for solution.
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    private Solution() { }
    /**.
     * { item_description }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        int vert = Integer.parseInt(in.nextLine());
        int testcases = Integer.parseInt(in.nextLine());
        EdgeWeightedGraph wtEdge = new EdgeWeightedGraph(vert);
        for (int i = 0; i < testcases; i++) {
            String[] inp = in.nextLine().split(" ");
            wtEdge.addEdge(new Edge(Integer.parseInt(
                                        inp[0]), Integer.parseInt(
                                        inp[1]), Double.valueOf(inp[2])));
        }
        PrimMST p =  new PrimMST(wtEdge);
        // System.out.println(p.edges());
        System.out.format("%.5f", p.weight());
    }
}
