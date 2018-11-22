/**
 * Class for percolation.
 */
class Percolation {
    /**
     * boolean string 2d array.
     */
    private boolean[][] grid;
    /**
     * { var_description }
     */
    private Uf uf;
    /**
     * { var_description }
     */
    private int size;
    /**
     * Constructs the object.
     * 
     *Complexity: O(1)
     *
     *
     * @param      n     { parameter_description }
     */
    public Percolation(final int n) { // create n-by-n grid, with all sites blocked.
        grid = new boolean[n][n];
        uf = new Uf(n * n + 2);  // to create the one dimension array of size +
        size = n;
    }
    /**
     * { function_description }
     * 
     *Complexity: O(1)
     *
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) { // open site (row, col) if it is not open already
        if (grid[row][col] == false) {
            grid[row][col] = true;
        }
        if (row == 0) {
            uf.union(convert1D(row, col), size * size);
        } 
        if (row == size - 1) {
            uf.union(convert1D(row, col), size * size + 1);
        }
        if (row > 0 && grid[row - 1][col]) {    //top element.
            uf.union(convert1D(row, col), convert1D(row - 1, col));
        }
        if (row < size - 1 && grid[row + 1][col]) { //bottom element.
            uf.union(convert1D(row, col), convert1D(row + 1, col));
        }
        if (col > 0 && grid[row][col - 1]) {    //left element.
            uf.union(convert1D(row, col), convert1D(row, col - 1));
        }
        if (col < size - 1 && grid[row][col + 1]) { //right element.
            uf.union(convert1D(row, col), convert1D(row, col + 1));
        }
    }
    /**
     * { function_description }.
     * 
     *Complexity: O(1)
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     { description_of_the_return_value }
     */
    public int convert1D(final int row, final int col) {
        return row * size + col;
    }
    /**
     * 
     *Complexity: O(1)
     *
     * @return     { description_of_the_return_value }
     */
    public boolean percolates() {             // does the system percolate?
        return uf.connected(size * size, size * size + 1);
    }
}
