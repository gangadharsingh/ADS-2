/**.
 * Class for primitive mst.
 */
class PrimMST {
    /**.
     * { var_description }
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**.
     * { var_description }
     */
    private Edge[] edgeTo;        // edgeTo[v] = shortest edge
    //from tree vertex to non-tree vertex
    /**.
     * { var_description }
     */
    private double[] distTo;      // distTo[v] = weight
    //of shortest such edge
    /**.
     * { var_description }
     */
    private boolean[] marked;     // marked[v] = true if v
    //on tree, false otherwise
    /**.
     * { var_description }
     */
    private IndexMinPQ<Double> pq;

    /**
     * . Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     *

     * @param      graph  The graph
     *
     * Complexity: O(E*log(V))
     */
    PrimMST(final EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.vert()];
        distTo = new double[graph.vert()];
        marked = new boolean[graph.vert()];
        pq = new IndexMinPQ<Double>(graph.vert());
        for (int v = 0; v < graph.vert(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        for (int v = 0; v < graph.vert(); v++) {
            if (!marked[v]) {
                prim(graph, v);      // minimum spanning forest
            }
        }
    }
    // run Prim's algorithm in graph G, starting from vertex s

    /**
     * . { function_description }
     *
     * Complexity: O(E*log(V))
     *
     * @param      graph  The graph
     * @param      s      { parameter_description }
     */
    private void prim(final EdgeWeightedGraph graph,
                      final int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(graph, v);
        }
    }
    // scan vertex v

    /**
     * . { function_description }
     *
     * Complexity: O(E)
     *
     * @param      graph  The graph
     * @param      v      { parameter_description }
     */
    private void scan(final EdgeWeightedGraph graph, final int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.decreaseKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    /**.
     * Returns the edges in a minimum spanning tree (or forest).
     *
     * Complexity: O(E)
     *
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    /**.
     * Returns the sum of the edge weights in a minimum
     * spanning tree (or forest).
     *
     * Complexity: O(E)
     *
     * @return the sum of the edge weights in a minimum
     * spanning tree (or forest)
     */
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight / 1.0000;
    }

    // check optimality conditions (takes time proportional to E V lg* V)

    /**
     * . { function_description }
     *
     * @param      graph  The graph
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph graph) {

        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight())
                > FLOATING_POINT_EPSILON) {
            System.err.printf(
                "Weight of edges does not equal weight(): %f vs. %f\n",
                totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(graph.vert());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : graph.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest
        //(cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(graph.vert());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : graph.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println(
                            "Edge " + f
                            + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}
