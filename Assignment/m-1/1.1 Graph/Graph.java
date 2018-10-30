/**.
 * Class for ListGraph.
 */
class ListGraph {
    /**.
     * { var_description }
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**.
     * { var_description }
     */
    private final int vertices;
    /**.
     * { var_description }
     */
    private int edges;
    /**.
     * { var_description }
     */
    private Bag<Integer>[] adj;
    /**.
     * Constructs the object.
     *
     * @param      vert  The vertices.
     */
    ListGraph(final int vert) {
        if (vert < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.vertices = vert;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vert];
        for (int v = 0; v < vert; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**.
     * Returns the number of vertices in this ListGraph.
     *
     * @return the number of vertices in this ListGraph
     */
    public int vertices() {
        return vertices;
    }

    /**.
     * Returns the number of edges in this ListGraph.
     *
     * @return the number of edges in this ListGraph
     */
    public int edges() {
        return edges;
    }

    /**.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0");
        }
    }

    /**.
     * Adds the undirected edge v-w to this ListGraph.
     *
     * @param      v     one vertex in the edge
     * @param      w     the other vertex in the edge
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        adj[w].add(v);
        edges++;
    }


    /**.
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**.
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }


    /**.
     * Returns a string representation of this ListGraph.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices, " + edges + " edges" + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ":");
            for (int w : adj[v]) {
                s.append(w + ",");
            }
            // s.append(E());
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
