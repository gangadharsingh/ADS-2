/**.
 * Class for adj matrix graph.
 */
class AdjMatrixGraph {
    /**.
     * cities list.
     */
    private String[] cities;
    /**.
     * matix.
     */
    private int[][] matrix;
    /**.
     * no of vertices.
     */
    private int vertices;
    /**.
     * edges count.
     */
    private int edges;
    /**.
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     * @param      e     { parameter_description }
     * @param      c     { parameter_description }
     */
    AdjMatrixGraph(final int v, final int e, final String[] c) {
        cities = c;
        matrix = new int[v][v];
        this.vertices = v;
        this.edges = 0;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    /**.
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w || hasEdge(v, w)) {
            return;
        }
        matrix[v][w] = 1;
        matrix[w][v] = 1;
        edges++;
    }
    /**.
     * adj.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int[] adj(final int v) {
        return matrix[v];
    }
    /**.
     * no of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return this.vertices;
    }
    /**.
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int edges() {
        return this.edges;
    }
    /**.
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return matrix[v][w] == 1;
    }
    /**.
     * prints.
     */
    public void print() {
        if (edges == 0) {
            System.out.println("No edges");
        } else {
            for (int i = 0; i < vertices - 1; i++) {
                String s = "";
                for (int each : matrix[i]) {
                    s += each + " ";
                }
                System.out.println(s);
            }
            String s = "";
            for (int each : matrix[vertices - 1]) {
                s += each + " ";
            }
            System.out.println(s.substring(0, s.length() - 1));
        }
    }
}
