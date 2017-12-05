
public class TicTacRunner {

	public static void main(String[] args) {
		Input game1 = new Input();
		Display display = new Display();
		display.displayBoard();
		
		int win = 0;
		for(int i=0;win == 0;i++) {
			char row = game1.setRow();
			int column = game1.setColumn();
			
			boolean canPlay = display.updateDisplay(game1.player, row, column);
			for(int r=0;!canPlay;r++) {
				if(!canPlay) {
					System.out.println("Someone has already gone there, try again: \n");
					 display.displayBoard();
					 row = game1.setRow();
					 column = game1.setColumn();
					 canPlay = display.updateDisplay(game1.player, row, column);
				} else {
					canPlay = true;
				}
			}
			
			display.displayBoard();
			
			win = display.checkWin();
			if(win == 1) {
				System.out.println("Player 1 Wins!");
			} else if(win == 2) {
				System.out.println("Player 2 Wins!");
			} else if(win == 3) {
				System.out.println("Cats Game! No Winner!");
			}
			
			game1.nextTurn();
			
		}
		
		System.out.println("Thanks for playing!");

	}

}
