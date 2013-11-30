import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class BoardNode {
	public List<BoardNode> neighbors=new ArrayList<BoardNode>();
	private char contents;
	private boolean seen;
	public char getContents(){
		return contents;
	}
	public void setContents(char contents) {
		this.contents = contents;
	}
	public BoardNode(char cont){
		contents=cont;
		
	}
	public String toString(){
		String out="";
		out=out+"Contents: "+contents+"\n";
		Iterator<BoardNode> iter=neighbors.iterator();
		while(iter.hasNext()){
			
			out=out+"Neighbor: "+iter.next().getContents()+"\n";
		}
		return out;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
}
