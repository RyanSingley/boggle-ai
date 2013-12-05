import java.util.ArrayList;


public class ConsoleView implements RoggleView {
	private static final int DISPLAY_ROWS=15;
	public ConsoleView() {//needs the game object to pass to the controller
		
	}
	public void update(ArrayList<String> playerWords,int playerScore,String board,int cpuScore,ArrayList<String> cpuWords) {
		//displays the gameboard, scores, words, etc.
		int num=0;
		for (int i = 0; i < 50; ++i) System.out.println(); // clear the screen the nice multiplatform way
		System.out.println("Roggle:");
		System.out.println(" PLAYER WORDS:                             COMPUTER WORDS:");
		displayWords(playerWords,cpuWords);
		
	
		System.out.println(" PLAYER SCORE:                             COMPUTER SCORE:");
		System.out.println("     "+playerScore+"                                      "+cpuScore+"       ");
		System.out.println("                             GAME BOARD:");
		System.out.println(board);
	}
	private void displayWords(ArrayList<String> playerWords,
			ArrayList<String> cpuWords) {
		// Displays player and cpu words in arranged columns
		
		//determine how many columns we need for player and cpu words
		int cpuCols=(cpuWords.size()/DISPLAY_ROWS)+1;//DISPLAY_ROWS is the max number of rows
		int playerCols=(playerWords.size()/DISPLAY_ROWS)+1;
		
		//now the arrays have the appropriate amount of padding, display them
		for(int x=0;x<DISPLAY_ROWS;x++){
			for(int y=0;y<playerCols;y++){
				System.out.print(" ");
				if(playerWords.size()>(y*DISPLAY_ROWS+x)){
					//print the word and padding spaces
					System.out.print(playerWords.get(y*DISPLAY_ROWS+x));
					for(int z=0;z<(16-playerWords.get(y*DISPLAY_ROWS+x).length());z++){
						System.out.print(" ");
					}
					
				} else {
					System.out.print("                ");
				}
			}
			for(int z=4;z>cpuCols;z--){
				//variable padding between player and cpu words
				System.out.print("  ");
			}
			System.out.print("|");
			for(int y=0;y<cpuCols;y++){
				System.out.print(" ");
				if(cpuWords.size()>(y*DISPLAY_ROWS+x)){
					//print the word and padding spaces
					System.out.print(cpuWords.get(y*DISPLAY_ROWS+x));
					for(int z=0;z<(16-cpuWords.get(y*DISPLAY_ROWS+x).length());z++){
						System.out.print(" ");
					}
					
				} else {
					System.out.print("                ");
				}
			}
			System.out.println();
		}
	}
	public void newGame() {
		for (int i = 0; i < 50; ++i) System.out.println(); // clear the screen the nice multiplatform way
		System.out.println("Welcome to Roggle, a Boggle-like game");
		//System.out.print("Player, enter your first word: ");
		
	}
	public void displayHelp(){
		//TODO
	}
	public void messageToPlayer(String message){
		System.out.println(message);
	}
	/*public void badWord(String word) {
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
	}*/

}
