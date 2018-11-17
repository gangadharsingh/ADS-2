import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
/**.
 * Class for solution.
 */
public final class Solution {

	// Don't modify this method.
	/**.
	 * { function_description }
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNext()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	/**.
	 * { function_description }
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static String[] toReadFile(final String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	/**.
	 * Loads a dictionary.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static BinarySearchST<String, Integer> loadDictionary(final String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] read = toReadFile(file);
		for (int j = 0; j < read.length; j++) {
			// String s = read[j];
			String inp = read[j].toLowerCase();
			if (!st.contains(inp)) {
				st.put(inp, 1);
			} else {
				int i = st.get(inp);
				st.put(inp, i + 1);
			}
		}
		return st;
	}

}

/**.
 * Class for t 9.
 */
class T9 {
	/**.
	 * { var_description }
	 */
	private final TST<Integer> tst;
	/**.
	 * { var_description }
	 */
	private HashMap<String, String> map;
	/**.
	 * Constructs the object.
	 *
	 * @param      st    { parameter_description }
	 */
	public T9(final BinarySearchST<String, Integer> st) {
		// your code goes here
		tst = new TST<Integer>();
		Iterable<String> s = st.keys();
		for (String str : s) {
			tst.put(str, st.get(str));
		}
		map = new HashMap<String, String>();
		map.put("1", "");
		map.put("2", "abc");
		map.put("3", "def");
		map.put("4", "ghi");
		map.put("5", "jkl");
		map.put("6", "mno");
		map.put("7", "pqrs");
		map.put("8", "tuv");
		map.put("9", "wxyz");
		map.put("*", "*");
		map.put(" ", " ");
		map.put("#", "#");
	}

	// get all the prefixes that match with given prefix.
	/**.
	 * Gets all words.
	 *
	 * @param      prefix  The prefix
	 *
	 * @return     All words.
	 */
	public Iterable<String> getAllWords(final String prefix) {
		// your code goes here
		if (tst.hasPrefix(prefix)) {
			Iterable<String> s = tst.keysWithPrefix(prefix);
			return s;
		}
		return null;
	}

	/**.
	 * { function_description }
	 *
	 * @param      t9Signature  The t 9 signature
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> potentialWords(final String t9Signature) {
		// your code goes here
		String[] pattern = t9Signature.split("");
		StringBuilder concat = new StringBuilder();
		for (int i = 0; i < pattern.length; i++) {
			String[] inp = map.get(pattern[i]).split("");
			for (String s : inp) {
				if (tst.hasPrefix(s)) {
					concat.append(s);
				}
			}
		}
		System.out.println(concat);
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	/**.
	 * Gets the suggestions.
	 *
	 * @param      words  The words
	 * @param      k      { parameter_description }
	 *
	 * @return     The suggestions.
	 */
	public Iterable<String> getSuggestions(final Iterable<String> words, final int k) {
		// your code goes here
		BinarySearchST<Integer, String> bst = new BinarySearchST<Integer, String>();
		for (String s : words) {
			Integer freq = tst.get(s);
			bst.put(freq, s);
		}
		Bag<String> b = new Bag<String>();
		String[] arr = new String[k];
		for (int j = 0; j < k; j++) {
			Integer i = bst.max();
			arr[j] = bst.get(i);
			bst.deleteMax();
		}
		Arrays.sort(arr);
		for (int m = k; m > 0; m--) {
			b.add(arr[m - 1]);
		}
		return b;
	}

	
	// final output
	// Don't modify this method.
	/**.
	 * { function_description }
	 *
	 * @param      t9Signature  The t 9 signature
	 * @param      k            { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> t9(final String t9Signature, final int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
