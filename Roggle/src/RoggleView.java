import java.util.ArrayList;


public interface RoggleView {
	void messageToPlayer(String word);
	void update(ArrayList<String> playerWords,int playerScore,String board,int cpuScore,ArrayList<String> cpuWords);
	void newGame();
	void displayHelp();
}
