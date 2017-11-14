import java.util.Scanner;

public class Guess {
	private char[] lettersGuessed = new char[26];
	private char lastGuess;
	private Scanner keys = new Scanner(System.in);
	private int numGuesses = 0;
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	Guess() {
		for(int i=0;i<this.lettersGuessed.length;i++) {
			this.lettersGuessed[i] = ' ';
		}
	}
	
	void setGuess() {
		boolean haveGuessed = false;
		
		
		System.out.println("Guess a letter: ");
		this.lastGuess = this.keys.nextLine().toLowerCase().charAt(0);
		
		for(int r=0;r>=0;r++) {
		
			for(int i=0;i<this.lettersGuessed.length;i++) {
				if(this.lastGuess == this.lettersGuessed[i]) {
					haveGuessed = true;
					i=1000;
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
	
		this.lettersGuessed[this.alphabet.indexOf(this.lastGuess)] = this.lastGuess;
		
		
	}
	
	char getGuess() {
		return(this.lastGuess);
	}
	
	public void printLetters() {
		String lettersLeft = "";
		;		
		for(int i=0;i<this.lettersGuessed.length;i++) {
			if(this.lettersGuessed[i] == ' ') {
				lettersLeft = lettersLeft + " " + this.alphabet.charAt(i);
			} else {
				lettersLeft = lettersLeft + " _";
			}
		}
		System.out.println("\n Available letters: \n" + lettersLeft + "\n");
		
	}
	
}