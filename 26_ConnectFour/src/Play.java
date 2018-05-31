import java.util.Scanner;

public class Play {
	
	public char[][] board = new char[6][7];
	private int collumnIndex = 0;
	private int rowIndex = 0;
	
	public Play() {
		
		for(int r=0;r<6;r++) {
			for(int c=0;c<7;c++) {
				board[r][c] = '-';
			}
		}
	}
	
	public void setMove(char player) {
		Scanner keys = new Scanner(System.in);
		boolean canPlace = false;
		String[] validIn = {"1","2","3","4","5","6","7"};
		
		// Get collumn
		System.out.println("Player: " + player + " Please select a collumn: ");
		String sCollumn = keys.nextLine();
		
		for(String str: validIn) {
			if(sCollumn.equals(str)) {
				canPlace = true;
			}
		}
		
		if(!canPlace) {
			do {
				System.out.println("Invalid input, please try again: ");
				sCollumn = keys.nextLine();
				
				for(String str: validIn) {
					if(sCollumn.equals(str)) {
						canPlace = true;
					}
				}
			} while(!canPlace);
		}
		System.out.println("Collumn selected: " + sCollumn);
		collumnIndex = Integer.parseInt(sCollumn);
		collumnIndex--;
		
	}
	
	public boolean checkPossible() {
		
		for(int r=5;r>=0;r--) {
			if(board[r][collumnIndex] == '-') {
				rowIndex = r;
				//System.out.println("RowIndex: " + rowIndex);
				return(true);
			}
		}
		
		
		return(false);
	}
	
public boolean checkPossible(int column) {
		
		for(int r=5;r>=0;r--) {
			if(board[r][column] == '-') {
				rowIndex = r;
				collumnIndex = column;
				//System.out.println("RowIndex: " + rowIndex);
				return(true);
			}
		}
		
		
		return(false);
	}
	
	public void updateBoard(char player) {
		board[rowIndex][collumnIndex] = player;
	}
	
	
	
}
