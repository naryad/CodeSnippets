package yn.dynamicprogramming;

class SubseqCounter {

    String seq, subseq;
    int[][] tbl;

    public SubseqCounter(String seq, String subseq) {
        this.seq = seq;
        this.subseq = subseq;
    }

    public int countMatches() {
        tbl = new int[seq.length() + 1][subseq.length() + 1];

        for (int row = 0; row < tbl.length; row++)
            for (int col = 0; col < tbl[row].length; col++)
                tbl[row][col] = countMatchesFor(row, col);

        return tbl[seq.length()][subseq.length()];
    }

    private int countMatchesFor(int seqDigitsLeft, int subseqDigitsLeft) {
        if (subseqDigitsLeft == 0)
            return 1;

        if (seqDigitsLeft == 0)
            return 0;

        char currSeqDigit = seq.charAt(seq.length()-seqDigitsLeft);
        char currSubseqDigit = subseq.charAt(subseq.length()-subseqDigitsLeft);

        int result = 0;

        if (currSeqDigit == currSubseqDigit)
            result += tbl[seqDigitsLeft - 1][subseqDigitsLeft - 1];

        result += tbl[seqDigitsLeft - 1][subseqDigitsLeft];

        return result;
    }
    
    public static void main(String[] args) {
		SubseqCounter seqCtr = new SubseqCounter("3141592653331415926533", "123");
		//using dynamic programming
		System.out.println(seqCtr.countMatches());
		//using recursion
		System.out.println(seqCtr.countMatchesRecursively("3141592653331415926533", "123"));
	}

	private int countMatchesRecursively(String sequence, String subsequence) {
		if (subsequence.length() == 0) {
			return 1;
		}
		
		if (sequence.length() == 0) {
			return 0;
		}
		
		int result = 0;
		
		if (sequence.charAt(0) == subsequence.charAt(0)) {
			result += countMatchesRecursively(sequence.substring(1), subsequence.substring(1));
		}
		result += countMatchesRecursively(sequence.substring(1), subsequence);
		return result;
	}
}