import java.io.FileNotFoundException;

public class HangmanRunner {

	public static void main(String[] args) throws FileNotFoundException{
		
		
		Word hangmanWord = new Word("HangmanEasy"); 
		hangmanWord.selectWord();
		Match game1 = new Match(hangmanWord.getWordLength());
		Guess gues = new Guess();
		
		
		
		System.out.println("Welcome to Hangman");
		System.out.println("You are allowed 6 wrong guesses");
		System.out.println("Your word has " + hangmanWord.getWordLength() + " letters");
		   System.out.println("DEBUG: WORD == " + hangmanWord.getWord());
		game1.displayWord();
		gues.setGuess();
		game1.compare(hangmanWord.getWord(), gues.getGuess());

	}

}
