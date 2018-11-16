public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TrieSET suffix;
	public BoggleSolver(String[] dictionary) {
		suffix = new TrieSET();
		for (String s: dictionary) {
			 suffix.add(s);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		SET<String> valid = new SET<String>();
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] mark = new boolean[board.rows()][board.cols()];
			}
		}
		return new Bag<String>();
	}
	void collect(BoggleBoard board, int row, int col, boolean[][] mark, String pref, SET<String> set) {
		if (mark[row][col]) {
			return;
		}
		char letter =  board.getLetter(row, col);
		String word = pref;
		if (letter == 'Q') {
			word += "QU";
		} else {
			word += letter;
		}
		if (!suffix.hasPrefix(word) && word.length() < 2) {
			return;
		} else {
			set.add(word);
			mark[row][col] = true;
		}
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;		
				}
				if ((row + i >= 0) && (row+ i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
					collect(board, row+ i, col+ j, mark, word,set);
				}
			}
		}

	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		return 0;
	}
}