
public class Win {
	Play input;
	private char PlayerWin = 'X';
	private int[][] winLocations = new int[2][4];
	
	public Win(Play play) {
		input = play;
	}
	
	public char getWinner() {
		return(PlayerWin);
	}
	
	public int[][] getWinIdexArray(){
		return(winLocations);
	}
	
	public boolean checkRowWin() {
		for(int r=0;r<6;r++) {
			for(int c=0;c<4;c++) {
				char start = input.board[r][c];
				if(start == 'X' || start == 'O') {
					if(start == input.board[r][c+1] && start == input.board[r][c+2] && start == input.board[r][c+3]) {
						
						for(int t=0;t<4;t++) {
							winLocations[0][t] = r;
							winLocations[1][t] = c + t;
						}
						PlayerWin = start;
						return(true);
					}
				}
			}
		}
		
		return(false);
	}
	
	public boolean checkColWin() {
		for(int c=0;c<7;c++) {
			for(int r=0;r<3;r++) {
				char start = input.board[r][c];
				if(start == 'X' || start == 'O') {
					if(start == input.board[r+1][c] && start == input.board[r+2][c] && start == input.board[r+3][c]) {
						
						for(int t=0;t<4;t++) {
							winLocations[0][t] = r + t;
							winLocations[1][t] = c;
						}
						PlayerWin = start;
						return(true);
					}
				}
			}
		}
		
		return(false);
	}
	
	public boolean checkDiagWin() {
		//Diag \ check
		for(int r=0;r<3;r++) {
			for(int c=0;c<4;c++) {
				char start = input.board[r][c];
				if(start == 'X' || start == 'O') {
					if(start == input.board[r+1][c+1] && start == input.board[r+2][c+2] && start == input.board[r+3][c+3]) {
						
						for(int t=0;t<4;t++) {
							winLocations[0][t] = r + t;
							winLocations[1][t] = c + t;
						}
						
						PlayerWin = start;
						return(true);
					}
				}
			}
		}
		
		//Diag / check
		for(int r=0;r<3;r++) {
			for(int c=3;c<7;c++) {
				char start = input.board[r][c];
				if(start == 'X' || start == 'O') {
					if(start == input.board[r+1][c-1] && start == input.board[r+2][c-2] && start == input.board[r+3][c-3]) {
					
						for(int t=0;t<4;t++) {
							winLocations[0][t] = r + t;
							winLocations[1][t] = c - t;
						}
						
						PlayerWin = start;
						return(true);
					}
				}
			}
		}
		
		return(false);
	}
	
	public boolean checkCatsGame() {
		for(int r=0;r<6;r++) {
			for(int c=0;c<7;c++) {
				if(input.board[r][c] == '-') {
					return(false);
				}
			}
		}
		
		return(true);
	}

}
