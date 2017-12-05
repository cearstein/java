
public class Display {
	public static char[][] board = new char[3][3];
	
	Display() {
		for(int r=0;r<3;r++) {
			for(int i=0;i<3;i++) {
				Display.board[r][i] = ' ';
			}
		}
		
		System.out.println("Welcome to TicTacToe!");		
	}
	
	public void displayBoard() {
		System.out.println("\n   0   1   2");
		System.out.println("A  " + Display.board[0][0] + " | " + Display.board[0][1] + " | " + Display.board[0][2]);
		System.out.println("  -----------");
		System.out.println("B  " + Display.board[1][0] + " | " + Display.board[1][1] + " | " + Display.board[1][2]);
		System.out.println("  -----------");
		System.out.println("C  " + Display.board[2][0] + " | " + Display.board[2][1] + " | " + Display.board[2][2] + "\n");
	}
	
	public boolean updateDisplay(int player, char row, int column) {
		
		char XorO = ' ';
		int rowNum = -1;
		
		if(player == 1) {
			XorO = 'X';
		} else {
			XorO = 'O';
		}
		
		if(row == 'A') {
			rowNum = 0;
		} else if(row == 'B') {
			rowNum = 1;
		} else {
			rowNum = 2;
		}

		if(Display.board[rowNum][column] != ' ') {
			return(false);
		}
		
		Display.board[rowNum][column] = XorO;	
		return(true);
	}
	
	
	/*
	 * 0 = No Win
	 * 1 = P1 won
	 * 2 = P2 won
	 * 3 = Cats game
	 */
	public int checkWin() {
		
		
		for(int r=0;r<3;r++) {
			int xs = 0;
			int os = 0;
			for(int i=0;i<3;i++) {
				if(Display.board[r][i] == 'X') {
					xs++;
				} else if(Display.board[r][i] == 'O') {
					os++;
				}
				if(xs == 3) {
					return(1);
				} else if(os == 3) {
					return(2);
				}
			}
		}
		
		for(int r=0;r<3;r++) {
			int xs = 0;
			int os = 0;
			for(int i=0;i<3;i++) {
				if(Display.board[i][r] == 'X') {
					xs++;
				} else if(Display.board[i][r] == 'O') {
					os++;
				}
				if(xs == 3) {
					return(1);
				} else if(os == 3) {
					return(2);
				}
			}
		}
		
		
			int xs = 0;
			int os = 0;
			int r = 0;
			for(int i=2;i>=0;i--) {
				if(Display.board[r][i] == 'X') {
					xs++;
				} else if(Display.board[r][i] == 'O') {
					os++;
				}
				if(xs == 3) {
					return(1);
				} else if(os == 3) {
					return(2);
				}
				r++;
			}
		
		 xs = 0;
		 os = 0;
		for(int i=0;i<3;i++) {
			if(Display.board[i][i] == 'X') {
				xs++;
			} else if(Display.board[i][i] == 'O') {
				os++;
			}
			if(xs == 3) {
				return(1);
			} else if(os == 3) {
				return(2);
			}
		}		
		
		int amnt = 0;
		for(int a=0;a<3;a++) {
			for(int i=0;i<3;i++) {
				if(Display.board[a][i] != ' ') {
					amnt++;
				}
				if(amnt == 9) {
					return(3);
				}
			}
		}
		
		return(0);
	}
}
