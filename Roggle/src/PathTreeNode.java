import java.util.ArrayList;
import java.util.Iterator;


public class PathTreeNode {
	private PathTreeNode parent;
	private BoardNode contents;
	private ArrayList<PathTreeNode> children;
	public PathTreeNode(BoardNode content) {
		// TODO Auto-generated constructor stub
		contents=content;
		children=new ArrayList<PathTreeNode>();
	}
	public PathTreeNode getParent() {
		return parent;
	}
	public void setParent(PathTreeNode parent) {
		this.parent = parent;
	}
	public BoardNode getContents() {
		return contents;
	}
	public void setContents(BoardNode contents) {
		this.contents = contents;
	}
	public void addChild(PathTreeNode child){
		children.add(child);
	}
	public boolean upSearch(BoardNode elem){
		//recursively searches from this node to root, returning true if it finds the element
		if(this.getParent()==null){
			return false;
		}
		if(this.getParent().getContents()==elem){
			return true;
		}
		return this.getParent().upSearch(elem);
		
	}
	public Iterator<PathTreeNode> childIterator(){
		return children.iterator();
	}
}
