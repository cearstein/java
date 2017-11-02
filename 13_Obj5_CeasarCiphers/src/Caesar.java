
public class Caesar {
	char[] alphabet;
	char[] capitalAlphabet;
	
	Caesar(String str) {
		// System.out.println("Caesar object created");
		this.alphabet = str.toCharArray();
		this.capitalAlphabet = str.toCharArray();
		
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
		this.capitalAlphabet = str.toCharArray();

		
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
		char[] tempArray = new char[1000];
		this.strcpy(this.alphabet, tempArray);

		int slide = 0;
		boolean bool = true;
		
		// This code creates the coded alphabet
		for(int i=0;i<this.alphabet.length;i++){
			slide = i+shift;
			if((slide)>this.alphabet.length-1) {
				
				
				int x = i;
				int y = 0;
				do {
					tempArray[x] = this.alphabet[y];
					x++;
					y++;
				}while(y<shift );
				bool = false;
				break;
			}
			if(bool) {
	
			tempArray[i] = this.alphabet[slide];
			
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
	
	String decode(String str, int key) {
		char[] decodeArray = new char[1000];
		this.strcpy(this.alphabet, decodeArray);
		int slide;

		for(int i=0;i<this.alphabet.length;i++){
			
			int x = i-key;				
			decodeArray[i]=this.alphabet[this.alphabet.length-key];

		
			if(x >= 0) {
				int y = i;
				int r=0;
				do {
					// System.out.println("Y: " + y);
					// System.out.println("R: " + r);
					// System.out.println(this.alphabet[r]);
					decodeArray[y] = this.alphabet[r];
					y++;
					r++;
				}while(y<=this.alphabet.length-key);
				break;
			}		
			

		}
		
		
		char[] capitalDecodeArray = new char[10000]; 
		this.strcpy(decodeArray, capitalDecodeArray);
		
		
		for(int i=0; i<capitalDecodeArray.length;i++) {
			if(Character.isLetter(capitalDecodeArray[i]) && Character.isLowerCase(capitalDecodeArray[i])){
				capitalDecodeArray[i] = Character.toUpperCase(capitalDecodeArray[i]);
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
				convertedString = convertedString + capitalDecodeArray[spot];
			} else {convertedString = convertedString + decodeArray[spot];}
			
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
