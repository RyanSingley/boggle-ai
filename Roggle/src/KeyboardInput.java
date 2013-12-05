import java.util.Scanner;


public class KeyboardInput {
	Scanner keyInput;
	Game game;
	public KeyboardInput() {
		keyInput=new Scanner(System.in);
			
	}

	public String getWord() {
		String input=keyInput.nextLine();
		if(input==null){
			System.out.println("null input somehow");
			return null;
		}
		//remove whitespace
		input=input.trim();
		
		//clean up the input, make it uppercase etc.
		input=input.toUpperCase();
		
		
	    return input;
	}

}
