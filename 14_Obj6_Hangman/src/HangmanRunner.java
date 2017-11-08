import java.io.FileNotFoundException;
import java.util.Scanner;

public class HangmanRunner {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to Hangman");
		System.out.println("Please choose a difficulty - Easy, Medium, Hard, Impossible: ");
		char dif = keyboard.nextLine().toLowerCase().charAt(0);
		
		String filename;
		
		if(dif == 'e') {
			filename = "HangmanEasy";
		} else if(dif == 'm') {
			filename = "HangmanMed";
		} else if(dif == 'h') {
			filename = "HangmanHard";
		} else {
			filename = "HangmanExpert";
		}
		
		Word hangmanWord = new Word(filename); 
		hangmanWord.selectWord();
		Match game1 = new Match(hangmanWord.getWordLength());
		Guess gues = new Guess();
		
		
		
		
		System.out.println("You are allowed 6 wrong guesses");
		System.out.println("Your word has " + hangmanWord.getWordLength() + " letters");
		   // System.out.println("DEBUG: WORD == " + hangmanWord.getWord());
		game1.displayWord();
		
		for(int i=0;i>=0;i++) {
			if(!game1.win && !game1.lose) {
				gues.setGuess();
				game1.compare(hangmanWord.getWord(), gues.getGuess());
				game1.displayWord();
			} else if(game1.win) {
				System.out.println("You Win!");
				i=-10;
			} else {
				System.out.println("You lose, how embarrassing");
				i=-10;
			}
		}

	}

}
