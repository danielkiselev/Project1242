
import java.util.ArrayList;
import java.util.List;

public class algoTree {
	Integer turn;
	algoTree parent;
	Integer[] a;//available spots
	Integer[] enemy;//player controlled tiles
	Integer[] mine;//computer controller tiles
	Integer move;//where on board do we move
	Integer depth;//depth of tree
	Integer val;//node value
	Boolean term = false;//true if terminal node
	List<algoTree> child = new ArrayList<algoTree>();
	

	public algoTree (Integer depth, Integer[] a, Integer[] enemy, Integer[] mine, Integer move){
		this.turn = turn;
		this.parent = parent;
		this.depth = depth;
		this.a = a;
		this.enemy = enemy;
		this.mine = mine;
		this.move = move;
	}
	
	public void addChild(algoTree c) {
		child.add(c);
	}
	
	public List<algoTree> getChildren() {
		return child;
	}
	

	public void setTerm(boolean setter) {
		term = setter;
		//System.out.println("set True");
	}
	public Boolean getTerm() {
		return term;
	}
	
	public void setVal(Integer v) {
		val = v;
	}
	public Integer getVal() {
		return val;
	}
	
	public void setMove(Integer m) {
		move = m;
		//System.out.println("set move " + m);
	}
	public Integer getMove() {
		return move;
	}

}
