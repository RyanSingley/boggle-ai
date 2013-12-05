import java.util.ArrayList;


public class ConsoleView {
	public ConsoleView() {//needs the game object to pass to the controller
		
	}
	public void update(ArrayList<String> playerWords,int playerScore,String board,int cpuScore,ArrayList<String> cpuWords) {
		//displays the gameboard, scores, words, etc.
		int num=0;
		for (int i = 0; i < 50; ++i) System.out.println(); // clear the screen the nice multiplatform way
		System.out.println("Roggle:");
		System.out.println("     PLAYER WORDS:                                   COMPUTER WORDS:");
		//determine which word list is larger, to make sure we display all possible
		if(playerWords.size()>cpuWords.size()){
			num=playerWords.size();
		} else {
			num=cpuWords.size();
		}
		
		for(int x=0;x<num;x++){
			System.out.print("     ");
			if(x<playerWords.size()){
				System.out.print(playerWords.get(x));
				//words are a max length of 16, so insert 16- the length of the player's word
				for(int y=0;y<(16-playerWords.get(x).length());y++){
					System.out.print(' ');
				}
			} else {
				System.out.print("                ");
			}
			
			if(x<cpuWords.size()){
				System.out.print(cpuWords.get(x));
			} 
			System.out.println();
		}
		System.out.println("     PLAYER SCORE:                                   COMPUTER SCORE:");
		System.out.println("         "+playerScore+"                                            "+cpuScore+"       ");
		System.out.println("                             GAME BOARD:");
		System.out.println(board);
		
		
	}
	public void newGame() {
		for (int i = 0; i < 50; ++i) System.out.println(); // clear the screen the nice multiplatform way
		System.out.println("Welcome to Roggle, a Boggle-like game");
		//System.out.print("Player, enter your first word: ");
		
	}
	public void badWord(String word) {
		//displays message about bad input
		System.out.println(word+" is not a valid word in this game.");
		System.out.println("Words must be at least 3 letters, with no punctuation or special characters.");
		System.out.println("Words cannot be played twice, even if they appear on the board twice.");
		System.out.println();
	}
	public void notInDict(String word) {
		System.out.println(word+" is not in the dictionary.");
		System.out.println("Dictionary used is the Open Word List, complain to them if your word isn't allowed:)");
		System.out.println();
	}
	public void wordInputMessage() {
		System.out.println("You may enter ? for help, or enter ! when done entering words.");
		System.out.print("Enter a word:");
		
	}
	public void notOnBoard(String word) {
		System.out.println(word+" is not on the board.");
		System.out.println("Words must be made from sequential adjacent letter tiles.");
		System.out.println("Words cannot use the same letter tile twice. ");
		System.out.println();
	}

}
