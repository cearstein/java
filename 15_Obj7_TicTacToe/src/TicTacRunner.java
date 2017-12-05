
public class TicTacRunner {

	public static void main(String[] args) {
		Input game1 = new Input();
		Display display = new Display();
		display.displayBoard();
		
		for(int i=0;i>-1;i++) {
			char row = game1.setRow();
			int column = game1.setColumn();
			
			for(int r=0;r>-1;r++) {
				if(!display.updateDisplay(game1.player, row, column)) {
					System.out.println("Someone has already gone there, try again: \n");
					 display.displayBoard();
					 row = game1.setRow();
					 column = game1.setColumn();
				} else {
					r=-10;
				}
			}
			
			display.displayBoard();
			
			int win = display.checkWin();
			if(win == 1) {
				System.out.println("Player 1 Wins!");
				i=-10;
			} else if(win == 2) {
				System.out.println("Player 2 Wins!");
				i=-10;
			} else if(win == 3) {
				System.out.println("Cats Game! No Winner!");
				i=-10;
			}
			
			game1.nextTurn();
			
		}
		
		System.out.println("Thanks for playing!");

	}

}
