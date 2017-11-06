import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Word {
	private String[] wordArray = new String[8];
	private Random rand = new Random();
	private String word;
	
	Word(String filename) throws FileNotFoundException {
		Scanner file = new Scanner(new File("/home/student/JavaTxt/" + filename));
		
		for(int i=0; file.hasNextLine();i++) {
			this.wordArray[i] = file.nextLine();
		}
		
	}
	
	void selectWord() {
		int location = this.rand.nextInt(this.wordArray.length);
		
		this.word = this.wordArray[location];
		
	}
	
	String getWord() {
		return(this.word);
	}
	
	int getWordLength() {
		return(this.word.length());
	}
	
	
}
