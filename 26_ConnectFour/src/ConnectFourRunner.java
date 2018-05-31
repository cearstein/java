import java.util.Scanner;

public class ConnectFourRunner {

	public static void main(String[] args) {
		/*
		boolean validIn = false;
		String[] yn = {"y","n"};
		Scanner keyboard = new Scanner(System.in);
		String answer = "y";
		
		do {	
			System.out.println("Welcome to Connect Four!\n");
			
			Game game = new Game();
			
			System.out.println("Would you like to play again? y/n");
			answer = keyboard.nextLine();
			for(String str: yn) {
				if(answer.equals(str)) {
					validIn = true;
				}
			}
			if(!validIn) {
				do {
					System.out.println("Invalid input, please try again: ");
					answer = keyboard.nextLine();
					
					for(String str: yn) {
						if(answer.equals(str)) {
							validIn = true;
						}
					}
				} while(!validIn);
			}
		} while(answer.equals("y"));
		
		System.out.println("Thanks for Playing!");
		*/
		Play play = new Play();
		Win win = new Win(play);
		ConnectSwing swing = new ConnectSwing(win);
		do {
			
		}while(true);

	}

}
