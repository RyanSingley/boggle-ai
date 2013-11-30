import java.io.IOException;
import java.util.Iterator;


public class GameTester {

	public static void main(String[] args) throws IOException {
		GameDictionary dict=new GameDictionary();
		System.out.println(dict.getWord(4, 4875-973));
		System.out.println(dict.isWord("enzyMe"));
		Iterator<String> iter=dict.iterator();
		for(int i=0;i<1000;i++){
			System.out.println(iter.next());
		}
	}

}
