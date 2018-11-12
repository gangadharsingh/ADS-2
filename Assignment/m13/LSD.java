/**
 * Class for lsd.
 */
class LSD {
    /**
     * Constructs the object.
     */
    protected LSD() {

    }
    /**
     * private bits.
     */
    private static final int BITS_PER_BYTE = 8;
    /**
     * tfs.
     */
    public static final int T_FS = 256;

   /**
     * Rearranges the array of W-character strings in ascending order.
     * complexity O(2W(N + R))
     * @param      a     the array to be sorted
     * @param      w     the number of characters per string
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] sort(final String[] a, final int w) {
        int n = a.length;
        int ra = T_FS;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[ra + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < ra; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
        return a;
    }
}
