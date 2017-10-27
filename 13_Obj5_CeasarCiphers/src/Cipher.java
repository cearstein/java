
public class Cipher {

	public static void main(String[] args) {
		String simpleText = "abcdefghijklmnopqrstuvwxyz";
		String simpleTextPunc = "abcdefghijklmnopqrstuvwxyz.,'!?";
		String allTextPunc = "abcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()-_=+[{]}|;:'\",<.>/?\\";
		
		
		Caesar code = new Caesar(allTextPunc);
		/*code.setAlpha(allTextPunc);*/
		System.out.println(code.encode("ANYTHING should 74129083743091 WorrkK noo55w...", 12));
		

	}

}
