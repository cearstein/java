import java.util.Scanner;

public class Guess {
	private char[] lettersGuessed = new char[26];
	private char lastGuess;
	private Scanner keys = new Scanner(System.in);
	private int numGuesses = 0;
	
	Guess() {
		
	}
	
	void setGuess() {
		boolean haveGuessed = false;
		
		
		System.out.println("Guess a letter: ");
		this.lastGuess = this.keys.nextLine().toLowerCase().charAt(0);
		
		for(int r=0;r>=0;r++) {
		
			for(int i=0;i<this.lettersGuessed.length;i++) {
				if(this.lastGuess == this.lettersGuessed[i]) {
					haveGuessed = true;
					break;
				} else {
					haveGuessed = false;
				}
			}
			if(haveGuessed) {
				System.out.println("You have already guessed '" + this.lastGuess + "'");
				System.out.println("Guess a letter: ");
				this.lastGuess = this.keys.nextLine().charAt(0);
			} else {
				r=-10;
			}
		}
	
		this.lettersGuessed[numGuesses] = this.lastGuess;
		this.numGuesses++;
		
		
	}
	
	char getGuess() {
		return(this.lastGuess);
	}
	
}
