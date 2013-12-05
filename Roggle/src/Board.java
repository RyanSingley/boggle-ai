import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jss2.ArrayStack;
import jss2.StackADT;


public class Board {
	private BoardNode[][] gameBoard;
	private int sizeX;	//0 based
	private int sizeY;  //0 based
	private static final int SIZEX=3;
	private static final int SIZEY=3;
	public BoardNode getNodeAt(int x,int y){// 1 based
		return gameBoard[x-1][y-1];
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
		List<BoardNode> startPoints=new ArrayList<BoardNode>();
		for(int x=0;x<SIZEX+1;x++){
			for(int y=0;y<SIZEY+1;y++){
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
	public void setContent(int x,int y,char c){
		gameBoard[x][y].setContents(c);
	}
	public Board(){
		//construct a board of size 4,4
		gameBoard=new BoardNode[4][4];

		char cont='a';
		//Fill board content
		for(int i=0;i<SIZEX+1;i++){
			for(int j=0;j<SIZEY+1;j++){
				cont=(char) ('C'+(i)*(SIZEX+1)+j);//TODO remove?  board being randomized elsewhere
				gameBoard[i][j]=new BoardNode(cont);
			}
		}
		//create the links between nodes - checks if the neighbor exists and adds it to the neighbor list
		for(int i=0;i<SIZEX+1;i++){
			for(int j=0;j<SIZEY+1;j++){
				//up and left
				if(i-1>=0&&j-1>=0){
					gameBoard[i][j].neighbors.add(gameBoard[i-1][j-1]);
				}
				//left
				if(i-1>=0){
					gameBoard[i][j].neighbors.add(gameBoard[i-1][j]);
				}
				//down and left
				if(i-1>=0&&j+1<=SIZEY){
					gameBoard[i][j].neighbors.add(gameBoard[i-1][j+1]);
				}
				//down
				if(j+1<=SIZEY){
					gameBoard[i][j].neighbors.add(gameBoard[i][j+1]);
				}
				//down and right
				if(i+1<=SIZEX&&j+1<=SIZEY){
					gameBoard[i][j].neighbors.add(gameBoard[i+1][j+1]);
				}
				//right
				if(i+1<=SIZEX){
					gameBoard[i][j].neighbors.add(gameBoard[i+1][j]);
				}
				//up and right
				if(i+1<=SIZEX&&j-1>=0){
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
		for(int x=0;x<SIZEX+1;x++){
			for(int y=0;y<SIZEY+1;y++){
				gameBoard[x][y].setSeen(false);
			}
		}
	}
	public String toString(){
		String out="";
		out=out+"                              ---------\n";
		for(int x=0;x<4;x++){
			out=out+"                              |";
			for(int y=0;y<4;y++){
				out=out+gameBoard[x][y].getContents()+"|";
			}
			out=out+"\n                              ---------";
			out=out+"\n";
			/*for(int r=0;r<sizeX*2;r++){
				out=out+"-";
			}
			out=out+"\n";*/
		}
		return out;

	}
}
