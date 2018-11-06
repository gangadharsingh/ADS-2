import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
/**
 * WordNet class.
 **/
public class WordNet {
    /**
     * Digraph object.
     */
    private Digraph g;
    /**
     * LinearprobinghashST object creation.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> ht;
    /**
     * LinearprobinghashST object creation.
     */
    private LinearProbingHashST<Integer, String> ht1;
    /**
     * Integer v.
     */
    private int v;
    /**
     * SAP object.
     */
    private SAP sap;
    /**.
    boolean type variable.
    */
    private boolean flag = false;
    /**
     * Constructor is used to construct the object.
     * @param synsets String.
     * @param hypernyms String.
     * @throws Exception for null.
     */
    public WordNet(final String synsets,
     final String hypernyms) throws Exception {
        buildht(synsets);
        buildg(hypernyms);
    }
/**
 * to build a digraph.
 * @param hypernyms String type.
 * @throws Exception if input is null.
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
     * To mark a flag.
     * @return flag.
     */
    private boolean isflag() {
        return flag;
    }
    /**
     * To check if there is a cycle.
     * @param g1 Digraph object.
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
     * To check if the digraph is rooted.
     * @param g2 Digraph object.
     */
    private void isrooteddigraph(final Digraph g2) {
        int count = 0;
        for (int i = 0; i < g2.V(); i++) {
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
 * Used to build the hash table.
 * @param synsets String.
 * @throws Exception for null.
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
* Iterable object.
 * @return iterable.
 */
    public Iterable<String> nouns() {
        return null;
    }
    /**
     * isNoun method returns the boolena value.
     * if the word is noun or not.
     * @param word String type.
     * @return boolean.
     */
    public boolean isNoun(final String word) {
        return false;
    }
/**
 * distance method.
 * @param nounA String Type.
 * @param nounB String Type.
 * @return distance.
 */
    public int distance(final String nounA, final String nounB) {
        sap = new SAP(g);
        int dist = sap.length(ht.get(nounA), ht.get(nounB));
        return dist;
    }
    /**
     * Used to find the ancestor of the synset.
     * @param nounA String type.
     * @param nounB String type.
     * @return String.
     */
    public String sap(final String nounA, final String nounB) {
        sap = new SAP(g);
        String str = "";
        int id = sap.ancestor(ht.get(nounA), ht.get(nounB));
        return ht1.get(id);
    }
/**
 Used to print the output.
 */
    public void print() {
        System.out.println(g);
    }
/**
 * Main method.
 * @param args String type.
 */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String file1 = "Files" + "\\" + sc.nextLine();
        String file2 = "Files" + "\\" + sc.nextLine();
        String input = sc.nextLine();
        boolean f = false;
        try {
            WordNet obj = new WordNet(file1, file2);
            if (input.equals("Graph")) {
                if (obj.isflag() == f) {
                    obj.print();
                }
            } else if (input.equals("Queries")) {
                while (sc.hasNextLine()) {
                    String[] tokens = sc.nextLine().split(" ");
                    String str = obj.sap(tokens[0], tokens[1]);
                    int dis = obj.distance(
                        tokens[0], tokens[1]);
                    System.out.println("distance = " + dis
                        + ", ancestor = " + str);
                }
            }
        } catch (Exception e) {
            System.out.println("IllegalArgumentException");
        }

    }
}
