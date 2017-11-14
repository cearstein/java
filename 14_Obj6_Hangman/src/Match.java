

public class Match {
	String displayword = "";
	int wrgGuess = 0;
	boolean win = false;
	boolean lose = false;
	
	Match(int length)  {
		for(int i=0;i<length;i++) {
			this.displayword = this.displayword + " _";
		}
		
	}
	
	void displayWord() {
		System.out.println(this.displayword);
		System.out.println("");
	}
	
	void compare(String word, char guess) {
		int numCorrect = 0;
		int[] locations = new int[word.length()];
		String newDisplay = "";
		for(int a=0;a<locations.length;a++) {
			locations[a] = -1;
		}
		
		for(int i=0;i<word.length();i++) {
			if(word.charAt(i) == guess) {
				locations[i] = i;
				numCorrect++;
			}
		}
		/*
		 * DISPLAYS HOW MANY YOU GOT RIGHT
		 */
		if(numCorrect==0) {
			this.wrgGuess++;
			System.out.println("There is no '" + guess + "'. " + this.wrgGuess + " wrong guesses");
			if(this.wrgGuess >=6) {
				this.lose = true;
			}
			return;
		} else if(numCorrect > 1){
			System.out.println("There are " + numCorrect + " '" + guess + "'s");
		} else {
			System.out.println("There is 1 '" + guess + "'");
		}
		/*
		 * UPDATES this.displayword
		 */
		for(int i=0;i<locations.length;i++) {
				if(i == locations[i]) {
					newDisplay = newDisplay + " "+ guess;
				} else {
					newDisplay = newDisplay + " _";
				}
			
		}
		
		char[] displayArray = this.displayword.toCharArray();
		char[] newDisplayArray = newDisplay.toCharArray();
	
		
		String convString = "";
		
		for(int i=0;i<displayArray.length;i++) {
			if(displayArray[i] != '_' && displayArray[i] != ' ') {
				convString = convString + displayArray[i];
			} else {
				convString  = convString + newDisplayArray[i];
			} 
		}
		
		boolean check = false;
		for(int i=0;i<convString.length();i++) {
			if(convString.charAt(i) == '_') {
				check = true;
			}
		}
		
		if(!check) {
			this.win = true;
		}
		this.displayword = convString;
	
	}
}