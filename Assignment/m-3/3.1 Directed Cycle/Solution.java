import java.util.Scanner;
/**.
 * Solution class
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    private Solution() { }
    /**.
     * { function_description }
     * Complexity: O(E + V)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = Integer.parseInt(scan.nextLine());
        int edge = Integer.parseInt(scan.nextLine());
        Digraph graph = new Digraph(vertex);
        for (int i = 0; i < edge; i++) {
            String[] inp = scan.nextLine().split(" ");
            graph.addEdge(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]));
        }
        DirectedCycle cycledetect = new DirectedCycle(graph);
        if (cycledetect.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}
