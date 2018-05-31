
public class Game {
	private char[] players = {'X', 'O'};
	private int turn = 0;
	private boolean hasWon = false;
	private boolean isCats = false;
	

	public Game() {		
		Play play = new Play();
		Display display = new Display(play);
		Win win = new Win(play);
		
		do {
			//Blank Board
			display.printBoard();
			//Get valid move
			play.setMove(players[turn]);
			if(!play.checkPossible()) {
				do {
					System.out.println("That collumn is full! Try again");
					play.setMove(players[turn]);
				} while(!play.checkPossible());
			}
			//Update stuff and display new stuff
			play.updateBoard(players[turn]);
			display.updateDisplayBoard();
			display.printBoard();
			
			//Check Wins
			if(win.checkRowWin() || win.checkColWin() || win.checkDiagWin()) {
				hasWon = true;
			}
			
			//Check Cats
			if(win.checkCatsGame()) {
				isCats = true;
			}
			
			//Update Turn
			if(turn == 0) {
				turn = 1;
			} else {
				turn = 0;
			}
		} while(!hasWon && !isCats);
		
		if(hasWon) {
			int[][] winIndex = win.getWinIdexArray();
			display.updateDisplayBoard(winIndex);
			display.printBoard();
			System.out.println("Winner: " + win.getWinner());
		} else {
			display.updateDisplayBoard("#");
			display.printBoard();
			System.out.println("Cats game, no Winner!");
		}
		
	}
}
