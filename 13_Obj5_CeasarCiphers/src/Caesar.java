
public class Caesar {
	char[] alphabet;
	char[] fixAlphabet;
	char[] fixAlphabet2;
	char[] capitalAlphabet;
	
	Caesar(String str) {
		System.out.println("Caesar object created");
		this.alphabet = str.toCharArray();
		this.fixAlphabet = str.toCharArray();
		this.capitalAlphabet = str.toCharArray();
		this.fixAlphabet2 = str.toCharArray();
		
		for(int i=0; i<this.capitalAlphabet.length;i++) {
			if(Character.isLetter(capitalAlphabet[i]) && Character.isLowerCase(capitalAlphabet[i])){
				capitalAlphabet[i] = Character.toUpperCase(capitalAlphabet[i]);
			}
		}
		
		/*for(char charac:this.alphabet) {
			System.out.println(charac);
		}*/
	}
	
	void setAlpha(String str) {
		this.alphabet = str.toCharArray();
		this.fixAlphabet = str.toCharArray();
		this.capitalAlphabet = str.toCharArray();
		this.fixAlphabet2 = str.toCharArray();
		
		for(int i=0; i<this.capitalAlphabet.length;i++) {
			if(Character.isLetter(capitalAlphabet[i]) && Character.isLowerCase(capitalAlphabet[i])){
				capitalAlphabet[i] = Character.toUpperCase(capitalAlphabet[i]);
			}
		}
		
		/*for(char charac:this.alphabet) {
			System.out.println(charac);
		}*/
	}
	
	String encode(String str, int shift) {
		char[] tempArray = this.alphabet;

		int slide = 0;
		boolean bool = true;
		
		// This code creates the coded alphabet
		for(int i=0;i<this.alphabet.length;i++){
			slide = i+shift;
			if((slide)>this.alphabet.length-1) {
				/*System.out.println("debug");*/
				this.alphabet = this.fixAlphabet;
				
				int x = i;
				int y = 0;
				do {
					/*System.out.println("Y: " + y);*/
					tempArray[x] = this.alphabet[y];
					x++;
					y++;
				}while(y<shift );
				bool = false;
				break;
			}
			if(bool) {
			/*System.out.println(i);*/
			tempArray[i] = this.alphabet[slide];
			
			/*for(char charac:tempArray) {
				System.out.println(charac);
			}
			System.out.println("");*/
			} else {break;}

		}
		
		//Debug to print out converted Alphabet
		/*for(char charac: tempArray) {
			System.out.println(charac);
		}*/
		char[] capitalTempArray = new char[10000]; 
		this.strcpy(tempArray, capitalTempArray);
		
		for(int i=0; i<capitalTempArray.length;i++) {
			if(Character.isLetter(capitalTempArray[i]) && Character.isLowerCase(capitalTempArray[i])){
				capitalTempArray[i] = Character.toUpperCase(capitalTempArray[i]);
			}
		}
		
		char[] word = str.toCharArray();
		String convertedString = "";
		int spot = -1;
		boolean isCapital = false;
		boolean isSpace = false;
		
		for(int r=0;r<word.length;r++) {
			
		
		
			for(int a=0;a<this.alphabet.length;a++) {
				if(word[r] == this.alphabet[a]) {
					spot = a;
					isCapital = false;
					isSpace = false;
				} else if(word[r] == this.capitalAlphabet[a]) {
					spot = a;
					isCapital = true;
					isSpace = false;
				} else if(word[r] == ' ') {
					isCapital = false;
					isSpace = true;
				}
			}
			if(isSpace) {
				convertedString = convertedString + " ";
			}else if(isCapital) {
				convertedString = convertedString + capitalTempArray[spot];
			} else {convertedString = convertedString + tempArray[spot];}
			
		}

		
		
		
		
		
		
		return(convertedString);
	}

	void strcpy(char [] in1, char [] in2) {
		for (int i=0; i<in1.length; i++) {
			in2[i] = in1[i];
			// System.out.println(in2[i]);
		}
		return;
	}
	
}
