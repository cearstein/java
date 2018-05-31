
public class Display {
	Play input;
	String displayBoard;
	
	public Display(Play play) {
		
		input = play;
		
		displayBoard =  " 1 2 3 4 5 6 7 \n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
	}
	
	public void resetBoard() {
		displayBoard =  " 1 2 3 4 5 6 7 \n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"|-|-|-|-|-|-|-|\n" +
						"‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
	}
	
	public void printBoard() {
		System.out.println(displayBoard);
	}
	
	public void updateDisplayBoard() {
		String stuff = "";
		for(int r=0;r<input.board.length;r++) {
			for(int c=0;c<input.board[r].length;c++) {
				stuff = stuff + "|" + input.board[r][c];
			}
			stuff = stuff + "|\n";
		}
		
		displayBoard = " 1 2 3 4 5 6 7 \n" + stuff + "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
	}
	
	public void updateDisplayBoard(int[][] locations) {
		String stuff = "";
		int t=0;
		for(int r=0;r<input.board.length;r++) {
			for(int c=0;c<input.board[r].length;c++) {
				if(t<=3 && locations[0][t] == r && locations[1][t] == c) {
					stuff = stuff + "|#";
					t++;
				} else {
					stuff = stuff + "|" + input.board[r][c];
				}
			}
			stuff = stuff + "|\n";
		}
		
		displayBoard = " 1 2 3 4 5 6 7 \n" + stuff + "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
	}
	
	public void updateDisplayBoard(String str) {
		String stuff = "";
		for(int r=0;r<input.board.length;r++) {
			for(int c=0;c<input.board[r].length;c++) {
				stuff = stuff + "|" + "#";
			}
			stuff = stuff + "|\n";
		}
		
		displayBoard = " 1 2 3 4 5 6 7 \n" + stuff + "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
	}
}
