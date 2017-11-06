

public class Match {
	String displayword = "";
	
	Match(int length)  {
		for(int i=0;i<length;i++) {
			this.displayword = this.displayword + "_ ";
		}
		
	}
	
	void displayWord() {
		System.out.println(this.displayword);
	}
	
	void compare(String word, char guess) {
		int numCorrect = 0;
		int[] locations = new int[word.length()];
		for(int a=0;a<locations.length;a++) {
			locations[a] = -1;
		}
		
		for(int i=0;i<word.length();i++) {
			if(word.charAt(i) == guess) {
				locations[numCorrect] = i;
				numCorrect++;
			}
		}
		/*
		 * DISPLAY HOW MANY YOU GOT RIGHT THEN UPDATE DISPLAYWORD
		 */
	
	}
}
