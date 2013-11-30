import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class GameDictionary implements Iterable<String> {
    private String[][] words=new String[17][];//1st dimension is the number of letters in the word
    //Use this to predefine array sizes
    private static final int NUMWORDS2[]={0,0,0,972,4875-972,13510-4875,28742-13510,51851-28742,80270-51851,105063-80270,125260-105063,140667-125260,151915-140667,159651-151915,164710-159651,167867-164710,0};
    //private int NUMWORDS[]=new int[17];
    private static final String INFILE="trimmedList.txt";
	public GameDictionary() throws IOException {
		//initialize words array
		for(int x=3;x<=16;x++){
			words[x]=new String[NUMWORDS2[x]];
		}
	
		//load the file into memory
		FileReader file=new FileReader(INFILE);
		BufferedReader buffread=new BufferedReader(file);
		String buff;
		int x=3;
		int y=0;
		
		while((buff=buffread.readLine())!=null){//reads a line into buff and loops until it is null
			if(buff.length()>x){
				//NUMWORDS[x]=y-1;
				x++;
				y=0;
			}
			words[x][y]=buff;
			y++;
		}
		
		buffread.close();
	}
	public String getWord(int numLetters,int wordNumber){
		return words[numLetters][wordNumber];
	}
	public boolean isWord(String word){
		//searches for a word in the dictionary  TODO--binary search
		
		for(int y=0;y<words[word.length()].length;y++){
			if(word.equalsIgnoreCase(words[word.length()][y])){
				return true;
			}
		}
		return false;
	}
	@Override
	public Iterator<String> iterator() {
		
		return new WordIterator();
	}
	private class WordIterator implements Iterator<String>{
		//inline Iterator class to iterate over all words in the dictionary
		int posX=3;
		int posY=0;
		@Override
		public boolean hasNext() {
			if(posX==16&&posY==words[16].length){
			return false;
			}
			return true;
		}

		@Override
		public String next() {
			String ret=words[posX][posY];
			if(posY==words[posX].length-1){//end of this # of letters
				posX++;
				posY=0;
			} else {
				posY++;
			}
			return ret;
		}

		public void remove() {
			//UNIMPLEMENTED			
		}
		
	}
}
