/**
 * importing scanner.
 */
import java.util.Scanner;
/**
 * importing array list.
 */
import java.util.ArrayList;
import java.io.File;
/**
 * WordNet class.
 */
public class WordNet {
    /**
     * digraph g.
     */
    private Digraph g;
    /**
     * hash ST ht.
     */
private LinearProbingHashST<String, ArrayList<Integer>> ht;
    /**
     * hash ST ht.
     */
    private LinearProbingHashST<Integer, String> ht1;
    /**
     * v variable.
     */
    private int v;
    /**
     * SAP.
     */
    private SAP sap;
    /**
     * flag of bool.
     */
    private boolean flag = false;
    /**
     * constructor.
     * @param synsets synsets.
     * @param hypernyms hypernyms.
     * @throws Exception if null.
     */
    public WordNet(final String synsets, final String hypernyms)
    throws Exception {
        buildht(synsets);
        buildg(hypernyms);
    }
    /**
     * building graph.
     * @param hypernyms [description]
     * @throws Exception if null.
     */
    private void buildg(final String hypernyms)throws Exception {
        g = new Digraph(v);
        Scanner sc = new Scanner(new File(hypernyms));
        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split(",");
            if (tokens.length > 1) {
                for (int i = 1; i < tokens.length; i++) {
                    g.addEdge(Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[i]));
                }
            }
        }
        isrooteddigraph(g);
        iscycle(g);
    }
    /**
     * flag check.
     * @return bool[description]
     */
    private boolean isflag() {
        return flag;
    }
    /**
     * checks for cycles.
     * @param g1 [description]
     */
    private void iscycle(final Digraph g1) {
        DirectedCycle obj = new DirectedCycle(g1);
        if (obj.hasCycle()) {
            System.out.println("Cycle detected");
            flag = true;
            return;
        }
    }
    /**
     * rooted digraph check.
     * @param g2 [description]
     */
    private void isrooteddigraph(final Digraph g2) {
        int count = 0;
        for (int i = 0; i < g.V(); i++) {
            if (g2.outdegree(i) == 0) {
                count++;
            }
            if (count > 1) {
                System.out.println("Multiple roots");
                flag = true;
                return;
            }
        }
    }
    /**
     * build hash table.
     * @param synsets [description]
     * @throws Exception if null.
     */
    private void buildht(final String synsets)throws Exception {
        ht = new LinearProbingHashST<String, ArrayList<Integer>>();
        ht1 = new LinearProbingHashST<Integer, String>();
        Scanner sc = new Scanner(new File(synsets));
        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split(",");
            ht1.put(Integer.parseInt(tokens[0]), tokens[1]);
            String[] input = tokens[1].split(" ");
            for (int i = 0; i < input.length; i++) {
                if (ht.contains(input[i])) {
                    ArrayList<Integer> list = ht.get(input[i]);
                    list.add(Integer.parseInt(tokens[0]));
                    ht.put(input[i], list);
                } else {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(Integer.parseInt(tokens[0]));
                    ht.put(input[i], list);
                }

            }
            v++;
        }
    }

    /**
     *  returns all WordNet nouns.
     * @return [description]
     */
    public Iterable<String> nouns() {
        return null;
    }
    /**
     * // is the word a WordNet noun?.
     * @param word [description]
     * @return [description]
     */
    public boolean isNoun(final String word) {
        return false;
    }
    /**
     * distance between nounA and nounB (defined below).
     * @param nounA [description]
     * @param nounB [description]
     *
     * @return int [description]
     */
    public int distance(final String nounA, final String nounB) {
        sap = new SAP(g);
        int dist = sap.length(ht.get(nounA), ht.get(nounB));
        return dist;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * a synset (second field of synsets.txt)
     * that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     * @param nounA [description]
     * @param nounB [description]
     * @return String[description]
     */

    public String sap(final String nounA, final String nounB) {
        sap = new SAP(g);
        String str = "";
        int id = sap.ancestor(ht.get(nounA), ht.get(nounB));
        return ht1.get(id);
    }
    /**
     * prints.
     */

    public void print() {
        System.out.println(g);
    }
    /**
     * do unit testing of this class.
     * @param args [description]
     */

    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String file1 = "Files" + "\\" + sc.nextLine();
        String file2 = "Files" + "\\" + sc.nextLine();
        String input = sc.nextLine();
        try {
            WordNet obj = new WordNet(file1, file2);
            if (input.equals("Graph")) {
                if (!obj.isflag()) {
                    obj.print();
                }
            } else if (input.equals("Queries")) {
                while (sc.hasNextLine()) {
                    String[] tokens = sc.nextLine().split(" ");
                    String str = obj.sap(tokens[0], tokens[1]);
                    int dis = obj.distance(tokens[0], tokens[1]);
                    System.out.println("distance = " + dis
                        + ", ancestor = " + str);

                }
            }
        } catch (Exception e) {
            System.out.println("IllegalArgumentException");
        }

    }
}



