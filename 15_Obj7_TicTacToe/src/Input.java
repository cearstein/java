import java.util.Scanner;

public class Input {
	int player = 1;
	Scanner keys = new Scanner(System.in);
	private int rowIn = 0;
	private int columnIn = 0;
	
	Input() {
		
	}
	
	public char setRow() {
		System.out.println("Player " + this.player + " Enter Row: ");
		
		String tempRow = this.keys.nextLine().toUpperCase();
		char row = ' ';
		boolean isValid = false;
		
		
		do {
			if((tempRow.charAt(0) == 'A' || tempRow.charAt(0) == 'B' || tempRow.charAt(0) == 'C') && tempRow.length() == 1) {
				row = tempRow.charAt(0);
				isValid = true;
			} else {
				System.out.println("Invalid Input, Enter Again: ");
				tempRow = this.keys.nextLine().toUpperCase();
			}
		}while(!isValid);
		
		return(row);
	}
	
	public int setColumn() {
		System.out.println("Player " + this.player + " Enter Column: ");
		
		String tempColumn = this.keys.nextLine().toUpperCase();
		char column = ' ';
		boolean isValid = false;
		
		
		do {
			if((tempColumn.charAt(0) == '0' || tempColumn.charAt(0) == '1' || tempColumn.charAt(0) == '2') && tempColumn.length() == 1) {
				column = tempColumn.charAt(0);
				isValid = true;
			} else {
				System.out.println("Invalid Input, Enter Again: ");
				tempColumn = this.keys.nextLine().toUpperCase();
			}
		}while(!isValid);
		
		return(Integer.parseInt(Character.toString(column)));
	}
	
	public void nextTurn() {
		if(this.player == 1) {
			this.player = 2;
		} else {
			this.player = 1;
		}
	}
}


