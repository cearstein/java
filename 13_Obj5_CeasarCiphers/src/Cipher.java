import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Cipher {

	public static void main(String[] args) throws FileNotFoundException{
		String simpleText = "abcdefghijklmnopqrstuvwxyz";
		String simpleTextPunc = "abcdefghijklmnopqrstuvwxyz.,'!?";
		String allTextPunc = "abcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()-_=+[{]}|;:'\",<.>/?\\";
		
		/*
		// Get a filename from the user
		String fileName = JOptionPane.showInputDialog("Enter the file name");
		
		// Open the file
		Scanner file = new Scanner(new File("/home/student/" + fileName));
		String aLine = file.nextLine();
		System.out.println(aLine);
		
		// Call your encode on the caesar Cipher class
		Caesar fileCode = new Caesar(allTextPunc);
		System.out.println(fileCode.encode(aLine, 5));
		
		
		// Read another line from the file
		aLine = file.nextLine();
		System.out.println(aLine);
		
		System.out.println(file.hasNextLine());
		*/
		
		
		Caesar code = new Caesar(allTextPunc);
		Object[] modes = {"exit", "encode", "decode", "crack"}; 
		Object[] keys = {1, 5, 10, 15, 20};
		
		
		for(int i=1;i>0;i++) {
				
			Object modeSelection = JOptionPane.showInputDialog(null, "Pick a mode", "Mode Selection", JOptionPane.PLAIN_MESSAGE, null, modes, modes[0]);
			
			if(modeSelection == "encode") {
				String preEncoded = JOptionPane.showInputDialog("Enter the string you want to encode: ");
				Object keyPicked = JOptionPane.showInputDialog(null, "Pick a key: ", "Key Menu", JOptionPane.PLAIN_MESSAGE, null,keys, keys[0]);
				int key = (int) keyPicked;
				JOptionPane.showMessageDialog(null, code.encode(preEncoded, key));
			} else if(modeSelection == "decode") {
				String preDecoded = JOptionPane.showInputDialog("Enter the string you want to decode: ");
				Object decodeKey = JOptionPane.showInputDialog(null,"Pick the key to decode with", "Key Menu", JOptionPane.PLAIN_MESSAGE, null, keys, keys[0]);
				int keyPick = (int) decodeKey;
				JOptionPane.showMessageDialog(null, code.decode(preDecoded, keyPick));
			} else if(modeSelection == "crack") {
				JOptionPane.showMessageDialog(null, "Not yet implemented");
			} else {
				i=-1;
			}
		
			
		}
		
		
		
		
		
		
		/*
		JOptionPane.showMessageDialog(null, "Hello  World");
		
		String answer = JOptionPane.showInputDialog("Enter your name: ");
		System.out.println("The user entered: " + answer);
		JOptionPane.showMessageDialog(null, "The user entered: " + answer);
		
		// Get input from user in a drop-down selection menu
		Object[] options = {"option 1", "option 2", "option 3"};
		Object optionPicked = JOptionPane.showInputDialog(null, "Pick an option", "Option menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		JOptionPane.showMessageDialog(null, "The Option Picked was: " + optionPicked);
		*/
	}

}
