import java.util.Scanner;
import java.util.ArrayList;
/**.
 * { item_description }
 */
public final class Solution {
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
        Scanner in = new Scanner(System.in);
        String[] rail = in.nextLine().split(" ");
        ArrayList<String> stationName =
            new ArrayList<String>();
        String[] station = in.nextLine().split(" ");
        for (int i = 0; i < Integer.parseInt(rail[0]); i++) {
            stationName.add(station[i]);
        }
        EdgeWeightedGraph edge = new EdgeWeightedGraph(
            Integer.parseInt(rail[0]));
        // Set<Integer> a = stationName.keySet();
        // System.out.println("\nKEy Set "+ a);
        for (int i = 0; i < Integer.parseInt(rail[1]); i++) {
            String[] c = in.nextLine().split(" ");
            int vert1 = stationName.indexOf(c[0]);
            int vert2 = stationName.indexOf(c[1]);
            edge.addEdge(new Edge(
                             vert1, vert2, Double.parseDouble(
                                 c[2])));
        }
        int queries = Integer.parseInt(in.nextLine());
        DijkstraSP dj;
        for (int i = 0; i < queries; i++) {
            String[] q = in.nextLine().split(" ");
            int source = stationName.indexOf(q[0]);
            int destination = stationName.indexOf(q[1]);
            dj = new DijkstraSP(edge, source);
            System.out.println((int) dj.distTo(destination));
        }
    }
}
