import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jss2.ArrayStack;
import jss2.StackADT;


public class Board {
	private BoardNode[][] gameBoard;
	private int sizeX;	//0 based
	private int sizeY;  //0 based
	public BoardNode getNodeAt(int x,int y){// 1 based
		return gameBoard[x-1][y-1];
	}
	public BoardNode[] findAllPaths(int x,int y){

		//returns a list of BoardNode arrays, each element in the array is a reference to a BoardNode in the path
		//TODO this is seeming crazy complicated, it has an O(n^3) complexity operation on every 2 points on the graph, making it at least O(n^5)


		return null;
	}
	public boolean isOnBoard(String word){

		if(findWord(word)!=null){
			return true;
		} else {
			return false;
		}
	}
	public BoardNode[] findWord(String word){
		//Method finds any occurrence of a word on the board, returning an array of the path for first found

		//Complexity analysis -- finding start points is O(n), the search is more difficult to analyze, absolute worst case is 
		//where word is all one character, n/2 chars long and board is all the same char.  in this instance it is O(8^n) based on a
		//branching factor of 8 and a depth of n/2 done from each of the n startpoints.
		//Because searching stops at the first incorrect character, this will run significantly faster in the average case.
		//With a random character set, most searches will have only 1 startpoint, and the possible search paths will be pruned by
		//about 80% with each character searched.
		char firstChar=word.charAt(0);
		BoardNode[] path=null;
		//list of startpoints
		List<BoardNode> startPoints=new ArrayList<BoardNode>();//TODO different type of list?
		for(int x=0;x<sizeX+1;x++){
			for(int y=0;y<sizeY+1;y++){
				if(gameBoard[x][y].getContents()==firstChar){
					startPoints.add(gameBoard[x][y]);
				}
			}
		}
		Iterator<BoardNode> iter=startPoints.iterator();
		//dfs on each startpoint, returning the first successful one
		while(iter.hasNext()){
			path=findWordFrom(iter.next(),word);
			if(path!=null){
				return path;
			}
		}
		//word not found
		return null;
	}
	private BoardNode[] findWordFrom(BoardNode start, String word){
		//dfs like search to find a word from the start point
		StackADT<BoardNode> searchStack=new ArrayStack<BoardNode>();
		int depth=1;
		boolean found=false;

		BoardNode current=start;
		BoardNode next;
		//push the startpoint on the stack
		searchStack.push(start);
		start.setSeen(true);

		while(!found){
			//find a neighbor that isn't seen and is the next letter
			next=getUnseenNeighbor(current,word.charAt(depth));
			if(next==null){
				//no more on this path
				if(depth==1){
					//no paths, return null
					//reverse side effect, set all unseen
					setAllUnseen();
					return null;

				}
				searchStack.pop();
				current=searchStack.peek();
				depth--;

			} else {
				searchStack.push(next);
				next.setSeen(true);
				depth++;
				current=next;
				if(depth==word.length()){
					//path found, return the array
					BoardNode[] temp=new BoardNode[word.length()];
					BoardNode[] path=new BoardNode[word.length()];
					for(int x=0;x<word.length();x++){
						temp[x]=searchStack.pop();
					}
					//reverse it
					for(int x=0;x<word.length();x++){
						path[x]=temp[(word.length()-1)-x];
					}
					// reverse side effect, set all unseen
					setAllUnseen();
					return path;
				}
			}
		}
		System.out.println("ERROR HIT UNREACHABLE CODE");
		return null;//should never get here

	}
	private BoardNode getUnseenNeighbor(BoardNode toCheck,char toFind){
		//returns the first unseen neighbor of toCheck that matches the char toFind, returning null if nothing is found
		//this method helps the search algorithm
		Iterator<BoardNode> iter;
		iter=toCheck.neighbors.iterator();
		BoardNode curr;
		while(iter.hasNext()){
			curr=iter.next();
			if(!curr.isSeen()&&curr.getContents()==toFind){
				return curr;
			}
		}
		return null;
	}
	public String[] findAllDictWords(GameDictionary dict,String[] excluded){
		//iterates through all words in the dictionary, checking them against excluded and checking if they are on the board
		Iterator<String> iter=dict.iterator();
		String word;
		List<String> foundWords=new ArrayList<String>();
		boolean isExcluded;
		while(iter.hasNext()){
			word=iter.next();
			//check against excluded
			isExcluded=false;
			for(int x=0;x<excluded.length;x++){
				if(excluded[x].equals(word)){
					isExcluded=true;
					break;//stop checking the excluded list
				}
			}
			if(!isExcluded){

				if(this.isOnBoard(word)){

					foundWords.add(word);
				}
			}
		}

		return foundWords.toArray(new String[foundWords.size()]);
	}
	public Board(int x,int y){
		//construct a board of size x,y
		gameBoard=new BoardNode[x][y];
		// x and y are 1 based convert them so they denote the right and bottom edges
		x--;
		y--;
		sizeX=x;
		sizeY=y;
		char cont='a';
		//Fill board content
		for(int i=0;i<x+1;i++){
			for(int j=0;j<y+1;j++){
				cont=(char) ('C'+(i)*(x+1)+j);// filler  TODO random fill
				gameBoard[i][j]=new BoardNode(cont);
			}
		}
		//debug remove
		gameBoard[3][3].setContents('A');
		//create the links between nodes - checks if the neighbor exists and adds it to the neighbor list
		for(int i=0;i<x+1;i++){
			for(int j=0;j<y+1;j++){
				//up and left
				if(i-1>=0&&j-1>=0){
					gameBoard[i][j].neighbors.add(gameBoard[i-1][j-1]);
				}
				//left
				if(i-1>=0){
					gameBoard[i][j].neighbors.add(gameBoard[i-1][j]);
				}
				//down and left
				if(i-1>=0&&j+1<=y){
					gameBoard[i][j].neighbors.add(gameBoard[i-1][j+1]);
				}
				//down
				if(j+1<=y){
					gameBoard[i][j].neighbors.add(gameBoard[i][j+1]);
				}
				//down and right
				if(i+1<=x&&j+1<=y){
					gameBoard[i][j].neighbors.add(gameBoard[i+1][j+1]);
				}
				//right
				if(i+1<=x){
					gameBoard[i][j].neighbors.add(gameBoard[i+1][j]);
				}
				//up and right
				if(i+1<=x&&j-1>=0){
					gameBoard[i][j].neighbors.add(gameBoard[i+1][j-1]);
				}
				//up
				if(j-1>=0){
					gameBoard[i][j].neighbors.add(gameBoard[i][j-1]);
				}
			}
		}
	}
	private void setAllUnseen(){
		for(int x=0;x<sizeX+1;x++){
			for(int y=0;y<sizeY+1;y++){
				gameBoard[x][y].setSeen(false);
			}
		}
	}
	public String toString(){
		String out="";
		for(int x=0;x<sizeX+1;x++){
			for(int y=0;y<sizeY+1;y++){
				out=out+gameBoard[x][y].getContents();
			}
			out=out+"\n";
			/*for(int r=0;r<sizeX*2;r++){
				out=out+"-";
			}
			out=out+"\n";*/
		}
		return out;

	}
}
