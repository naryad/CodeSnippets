package yn.core;

public class TripleT {

	enum State {
		Blank (0), X(1), O(2);
		int value;
		State(int value) {
			this.value = value;
		}
	}

	enum Result {
		X_WIN, O_WIN, INCOMPLETE, DRAW
	}

	static int n = 3;
	static State[][] board = new State[n][n];

	public static void main(String[] args) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = State.Blank;
			}
		}
		
		board[0][2] = State.X;
		board[1][0] = State.O;
		board[1][1] = State.X;
		board[2][1] = State.O;
		board[2][0] = State.X;

		System.out.println(positionValue());

	}

	private static boolean isBoardFull() {
		for (int row = 0; row < 3; row++)
			for (int column = 0; column < 3; column++)
				if (board[row][column] == State.Blank)
					return false;
		return true;
	}

	private static boolean isAWin(State side) {
		int row, column;

		/* Look for all in a row */
		for (row = 0; row < n; row++) {
			for (column = 0; column < n; column++)
				if (board[row][column] != side)
					break;
			if (column >= n)
				return true;
		}

		/* Look for all in a column */
		for (column = 0; column < n; column++) {
			for (row = 0; row < n; row++)
				if (board[row][column] != side)
					break;
			if (row >= n)
				return true;
		}

		/* Look on diagonals */
		for (int i = 0; i < n; i++) {
			if (board[i][i] != side) {
				break;
			}
			if (i == n-1) {
				return true;
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (board[i][n-i-1] != side) {
					break;
			}
			if (i == n-1) {
				return true;
			}
		}

		return false;
	}

	// Compute static value of current position (win, draw, etc.)
	private static Result positionValue() {
		return isAWin(State.X) ? Result.X_WIN : isAWin(State.O) ? Result.O_WIN
				: isBoardFull() ? Result.DRAW : Result.INCOMPLETE;
	}
}