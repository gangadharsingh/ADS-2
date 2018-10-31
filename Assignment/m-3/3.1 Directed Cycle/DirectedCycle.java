/**.
 * Class for directed cycle.
 */
class DirectedCycle {
    /**.
     * { var_description }
     */
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    /**.
     * { var_description }
     */
    private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    /**.
     * { var_description }
     */
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    /**.
     * { var_description }
     */
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    /**.
     * Determines whether the digraph {@code G} has a directed cycle and, if so,
     * finds such a cycle.
     * @param G the digraph
     */
    public DirectedCycle(final Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    /**.
     * { function_description }
     *
     * @param      G     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Digraph G, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

            // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**.
     * Does the digraph have a directed cycle?
     *
     * @return     {@code true} if the digraph has a directed cycle, {@code
     *             false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**.
     * Returns a directed cycle if the digraph has a directed cycle, and {@code
     * null} otherwise.
     *
     * @return     a directed cycle (as an iterable) if the digraph has a
     *             directed cycle, and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
    // certify that digraph has a directed cycle if it reports one
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }
        return true;
    }
}
