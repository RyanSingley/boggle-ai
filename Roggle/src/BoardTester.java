import java.io.IOException;
import java.util.Iterator;


public class BoardTester {

	public static void main(String[] args) throws IOException {
		Board gB=new Board(4,4);
		System.out.print(gB);
		//System.out.print(gB.getNodeAt(2, 2));
		//BoardNode[] test=gB.findWord("akgc");
		GameDictionary dict=new GameDictionary();
		//System.out.println("word 3,14: "+dict.getWord(3, 971));
		//System.out.println("word 13,15954: "+dict.getWord(13, 15951));
		//print out all words in dict
		/*Iterator<String> iter=dict.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		} */
		
		String[] excluded={"ANA"};
		String[] found=gB.findAllDictWords(dict, excluded);
		System.out.println("found words:");
		for(int x=0;x<found.length;x++){
			System.out.println(found[x]);
		}
		
		
		
		
		//to test, isOnBoard, findAllDictWords,
		//make a setboard method, a random board method(using more boggle-like letter distribution)
		//remember to make all words uppercase somewhere
	}

}
