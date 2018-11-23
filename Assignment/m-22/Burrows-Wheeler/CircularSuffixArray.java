import java.util.Arrays;
/**
 * CircularSuffixArray.
 */
public class CircularSuffixArray {
    /**
     * { var_description }
     */
    private Suffix[] suffixes;
    /**
     * { var_description }
     */
    private String text;
    /**
     * Initializes a suffix array for the given {@code text} string.
     * @param text the input string
     */
    public CircularSuffixArray(String t) {
        this.text = t;
        int n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; i++)
            suffixes[i] = new Suffix(i);
        Arrays.sort(suffixes);
    }
    private class Suffix implements Comparable<Suffix> {

        private final int index;

        /**
         * Constructs the object.
         * Complexity: O(1)
         * @param      index  The index
         */
        private Suffix(int index) {

            this.index = index;
        }
        /**
         * { function_description }
         * Complexity: O(1)
         * @return     { description_of_the_return_value }
         */
        private int length() {
            return text.length();
        }
        /**
         * { function_description }
         * Complexity: O(1)
         * @param      i     { parameter_description }
         *
         * @return     { description_of_the_return_value }
         */
        private char charAt(int i) {
            i = (index + i) % length();
            return text.charAt(i);
        }

        /**
         * { function_description }
         * Complexity: O(n), where n is min length of either this or that Suffix
         * @param      that  The that
         *
         * @return     { description_of_the_return_value }
         */
        public int compareTo(Suffix that) {
            if (this == that) return 0;
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        /**
         * Returns a string representation of the object.
         * Complexity: O(1)
         * @return     String representation of the object.
         */
        public String toString() {
            return text.substring(index) + text.substring(0, index);
        }
    }

    /**
     * Returns the length of the input string.
     *  Complexity: O(1)
     * @return the length of the input string
     */
    public int length() {
        return suffixes.length;
    }


    /**
     * Returns the index into the original string of the <em>i</em>th smallest suffix.
     * That is, {@code text.substring(sa.index(i))} is the <em>i</em>th smallest suffix.
     *  Complexity: O(1)
     * @param i an integer between 0 and <em>n</em>-1
     * @return the index into the original string of the <em>i</em>th smallest suffix
     * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
     */
    public int index(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].index;
    }


    /**
     * Returns the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     *  Complexity: O(1)
     * @param i an integer between 1 and <em>n</em>-1
     * @return the length of the longest common prefix of the <em>i</em>th
     * smallest suffix and the <em>i</em>-1st smallest suffix.
     * @throws java.lang.IllegalArgumentException unless {@code 1 <= i < n}
     */
    private int lcp(int i) {
        if (i < 1 || i >= suffixes.length) throw new IllegalArgumentException();
        return lcpSuffix(suffixes[i], suffixes[i - 1]);
    }


    /**
     * { function_description }
     * Complexity: O(n), min length of suffix s or t
     * @param      s     { parameter_description }
     * @param      t     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private static int lcpSuffix(Suffix s, Suffix t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }

    /**
     * Returns the <em>i</em>th smallest suffix as a string.
     *  Complexity: O(1)
     * @param i the index
     * @return the <em>i</em> smallest suffix as a string
     * @throws java.lang.IllegalArgumentException unless {@code 0 <= i < n}
     */
    private String select(int i) {
        if (i < 0 || i >= suffixes.length) throw new IllegalArgumentException();
        return suffixes[i].toString();
    }

    /**
     * Returns the number of suffixes strictly less than the {@code query} string.
     * We note that {@code rank(select(i))} equals {@code i} for each {@code i}
     * between 0 and <em>n</em>-1.
     *  Complexity: O(hi), hi is the length of query
     * @param query the query string
     * @return the number of suffixes strictly less than {@code query}
     */
    private int rank(String query) {
        int lo = 0, hi = suffixes.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = compare(query, suffixes[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }


    /**
     * { function_description }
     * Complexity: O(n), min length of either query or suffix
     * @param      query   The query
     * @param      suffix  The suffix
     *
     * @return     { description_of_the_return_value }
     */
    private static int compare(String query, Suffix suffix) {
        int n = Math.min(query.length(), suffix.length());
        for (int i = 0; i < n; i++) {
            if (query.charAt(i) < suffix.charAt(i)) return -1;
            if (query.charAt(i) > suffix.charAt(i)) return +1;
        }
        return query.length() - suffix.length();
    }
}
