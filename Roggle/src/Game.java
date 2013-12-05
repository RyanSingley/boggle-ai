import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Game {
		Board gB;
		GameDictionary dict;
		ArrayList<String> playerWords;
		ConsoleView view;
		KeyboardInput control;
		int playerScore;
		ArrayList<String> cpuWords;
		int cpuScore;
		private static final char[][] LETTERS={{'V','I','T','E','G','N'},{'A','C','E','S','L','R'},{'V','A','Z','E','D','N'},{'I','C','A','T','A','O'},{'N','O','D','U','K','T'},{'E','N','I','P','H','S'},{'O','R','I','F','B','X'},{'K','U','L','E','G','Y'},{'E','Y','I','E','H','F'},{'E','S','U','T','L','P'},{'E','W','O','S','D','N'},{'P','E','C','A','D','M'},{'A','L','I','B','T','Y'},{'S','A','H','O','M','R'},{'J','A','B','O','M','F'},{'U','R','I','G','L','W'}};
	public Game() {
		// TODO
		//create the board, propagate it,
		gB=new Board(4,4);
		//create dictionary
		try {
			dict=new GameDictionary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		view=new ConsoleView();
		control=new KeyboardInput();
		
	}
	public void computerPlay(){
		
	}
	public ArrayList<String> findAllDictWords(ArrayList<String> excluded){
		//iterates through all words in the dictionary, checking them against excluded and checking if they are on the board
		Iterator<String> iter=dict.iterator();
		String word;
		ArrayList<String> foundWords=new ArrayList<String>();
		boolean isExcluded;
		while(iter.hasNext()){
			word=iter.next();
			//check against excluded
			isExcluded=false;
			for(int x=0;x<excluded.size();x++){
				if(excluded.get(x).equals(word)){
					isExcluded=true;
					break;//stop checking the excluded list
				}
			}
			if(!isExcluded){
	
				if(gB.isOnBoard(word)){
	
					foundWords.add(word);
				}
			}
		}
	
		return foundWords;
	}
	public void startNewGame() {
		//reset scores, etc
		playerWords=new ArrayList<String>();
		playerScore=0;
		cpuWords=new ArrayList<String>();
		cpuScore=0;
		randomizeBoard();
		view.newGame();
		//update the view
		view.update(playerWords,playerScore,gB.toString(),cpuScore,cpuWords);
		playerTurn();
		//cpu turn
		cpuTurn();
		
		
	}
	private void cpuTurn() {
		// Computer players turn
		cpuWords=findAllDictWords(playerWords);
		for(int x=0;x<cpuWords.size();x++){
			cpuScore=cpuScore+cpuWords.get(x).length();//add size of each word to score
		}
		view.update(playerWords,playerScore,gB.toString(),cpuScore,cpuWords);
	}
	private void playerTurn() {
		String word="";
		//the game loop happens here
		while(true){//loop until return
		view.wordInputMessage();
		word=control.getWord();
		if(word.equals("?")){
			//special input for help
			//TODO
		} else if (word.equals("!")){
			//special input for done entering words
			return;
		}
		if(wordValidator(word)){
			//passes basic checks
			if(dict.isWord(word)){
				//it's in the dictionary--see if it's on the board
				if(gB.isOnBoard(word)){
				playerWords.add(word);
				playerScore=playerScore+word.length();
				view.update(playerWords,playerScore,gB.toString(),cpuScore,cpuWords);
				} else {
					//not on board
					view.notOnBoard(word);
				}
			} else {
				//not in dictionary
				view.notInDict(word);
			}
		} else {
			view.badWord(word);
		}
		}
	}
	private void addPlayerWord(String word) {
		// TODO Auto-generated method stub
		
	}
	public boolean wordValidator(String input) {
		//performs basic validation on the word
		if(input.length()<3){
			return false;
		}
		for(int x=0;x<input.length();x++){
			
			if(!Character.isLetter(input.charAt(x))){
				//not a valid letter
				return false;
			}
		}
		for(int x=0;x<playerWords.size();x++){
			if(input.equals(playerWords.get(x))){
				//word already played
				return false;
			}
		}
		return true;
		
	}
	private void randomizeBoard(){
		//in an effort to make the board more friendly to the player, 
		//I'm randomizing similar to Boggle, with each tile
		//having 6 possible letters, and then shuffling their order on the board
		ArrayList<Character> tiles=new ArrayList<Character>();
		Random r=new Random();
		for(int x=0;x<16;x++){
			tiles.add(LETTERS[x][r.nextInt(6)]);
		}
		Collections.shuffle(tiles);
		for(int x=0;x<16;x++){
			gB.setContent(x/4, x%4, tiles.get(x));
		}
		
	}

}
