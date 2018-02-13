
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class algo {
	int d;
	mat m;
	player hum;
	player comp;
	char board[][] = new char[3][3];//holds game board
	HashMap<Integer,Integer> hm = new HashMap<>();//location, current occupancy used to build game board 
	Integer selectMove = null;//move we want to play
	int mod = 0;
	
	public algo (player hum, player comp, mat m){
		//System.out.println(hum.);
		this.m = m;
		this.hum = hum;
		this.comp = comp;
		board = m.board;
		setup();//rebuilds game board into hashmap to fetch values in O(1)
		start();
    }
	
	private Integer[] parser(String data) {//parses string into array
		Integer sz = data.length();
		Integer res[] = new Integer [sz];
		for(Integer i = 0; i<sz; i++) {
			res[i] = Character.getNumericValue((data.charAt(i)));
		}
		return res;
	}
	
	public Integer deterVal(algoTree nodes[],int depth) {
		Integer val= null;
		int tval = 0;
		for(int i = 0; i<nodes.length; i++) {
			if(depth == 0 ) {
				System.out.println("New move = "+ nodes[i].move +" | New val =  " + nodes[i].val +"  | Depth"+depth + "  |term" + nodes[i].term);
			}
			if(depth%2 == 0) {//grabs max val
				if(val == null) {
					val = 0;
					val = nodes[i].val;
				}
				else if(nodes[i].val> val) {
					val = nodes[i].val;
				}
			}
			
			else if(depth%2 == 1) {//grabs min val
				if(val == null) {
					val = 0;
					val = nodes[i].val;
				}
				else if(nodes[i].val< val) {
					val = nodes[i].val;
				}
			}
		}

		return val;
	}
	
	
	public int deterMove(algoTree nodes[]) {//returns int of the best move
	//	System.out.println("determining");
		Integer ovr = null;
		int move= 0;
		int move1 = 0;
		Integer bestChild = -1000;
		for(int i = 0; i<nodes.length; i++) {
			//System.out.println("moving to"+nodes[i].move+"   value = " + nodes[i].val);
			if(nodes[i].term) {
				System.out.println("found term:");
				System.out.print("moving to"+nodes[i].move+"   value = " + nodes[i].val);
				if(ovr == null) {
					ovr = 0;
					ovr = nodes[i].val;
					move = nodes[i].move;
				}
				else if(ovr<nodes[i].val) {
					ovr = nodes[i].val;
					move = nodes[i].move;
				}
				System.out.println("term = " + ovr);
			}
			else if(nodes[i].val!= null && nodes[i].val>bestChild) {
				System.out.print("moving to"+nodes[i].move+"   value = " + nodes[i].val);
					bestChild = nodes[i].val;
					move1 = nodes[i].move;
					//System.out.println("fun Deter move" + move);
			}

		}
		if(ovr != null) {
			selectMove = move;
			return move;
		}
		else {
			selectMove = move1;
			System.out.println("Deter move returned" + move1);
			return move1;
		}

	}
	
	//(parent node, available spots, human spots, computer spots, depth of search)
	public algoTree actions(algoTree root,Integer [] aT, Integer[] eT, Integer [] mT, int depth) {
		algoTree rootCopy = root;
		int szA = aT.length;
		int newMsz = mT.length+1;
		int newEsz = eT.length+1;
		int newAsz = szA-1;
		algoTree nodes [] = new algoTree[szA];//creates a node for every available spot
		for(Integer i = 0; i<szA; i++) {
			if(depth%2 == 0) {//computer simulated move
				Integer [] newA = new Integer[newAsz];//stores available spots, removing the played move.
				Integer [] newM = new Integer[newMsz];//stores computer spots, adding the played move.
				newA = newAval(aT,aT[i]);//fills array
				newM = newUsed(mT,aT[i]);//fills array
				nodes [i] = new algoTree(depth, newA, eT, newM, aT[i]);//Integer depth, Integer[] a, Integer[] enemy, Integer[] mine, Integer move
				Integer value = checkTerm(nodes[i]);//checks if played move is terminal

				if (value != null) {//if terminal, assigns terminal identifier 
						nodes[i].val = value;
						nodes[i].term = true;
					}
				else {//if not terminal we take the played node and repeat
					nodes[i].setVal(actions(nodes[i],newA, eT, newM, depth+1).val);
				}
			}
			else if(depth%2 == 1) {//human
				Integer [] newA = new Integer[newAsz];
				Integer [] newE = new Integer[newEsz];
				newA = newAval(aT,aT[i]);
				newE = newUsed(eT,aT[i]);
				nodes [i] = new algoTree(depth, newA, newE, mT, aT[i]);
				Integer value = checkTerm(nodes[i]);//checks if played move is terminal
				if (value != null) {//if terminal, assigns terminal identifier 
					nodes[i].val = value;
					nodes[i].term = true;
				}
				else{
					nodes[i].setVal(actions(nodes[i],newA, newE, mT, depth+1).val);
				}
			}
			rootCopy.addChild(nodes[i]);
		}
		
		rootCopy.val = deterVal(nodes, depth);
		//System.out.println("rootcopy.val: "+ rootCopy.val);
		if(depth != 0) {
			return rootCopy;
		}
		else {
			System.out.println("setting move");
			Integer val= null;
			int move = 0;
			for(int i = 0; i<nodes.length; i++) {
					if(val == null) {
						val = nodes[i].val;
						move = nodes[i].move;
					}
					else if(nodes[i].val> val) {
						val = nodes[i].val;
						move = nodes[i].move;
					}
				}
			rootCopy.setMove(move);
			System.out.println("ROOT.move to:"+ rootCopy.move);
			return rootCopy;
		}
			
		

		//checks the nodes for all the avaliable spots. 
		//uses depth value to determine if min or max.
		//returns the value of the root node

		/*
		if(depth == 0) {//root depth
			System.out.println("setting move");
			rootCopy.setMove(deterMove(nodes));//when recursed back to top, we pass all possible nodes in to determine best move
			System.out.println("ROOT.move to:"+ rootCopy.move);
			
			for(Integer i = 0; i<szA; i++) {
				if (nodes[i].term == true) {
					System.out.print(" TERMS FINAL moving to="+nodes[i].move+"   value = " + nodes[i].val);
				}
			}
			
			return rootCopy;
			//assign integer of best move
		}
		
		if(res != null) {
			rootCopy.val= res;//assigns value to root node object
			return rootCopy;
		}
		else {
			if(depth%2 == 0) {
				Integer temp = null;
				for(Integer i = 0; i<szA; i++) {
					if(temp == null) {
						temp = 0;
						temp = nodes[i].val;
					}
					else if(nodes[i].val>temp) {
						temp = nodes[i].val;
					}
				}
				rootCopy.val = temp;
			}
			if(depth%2 == 1) {
				Integer temp = null;
				for(Integer i = 0; i<szA; i++) {
					if(temp == null) {
						temp = 0;
						temp = nodes[i].val;
					}
					else if(nodes[i].val<temp) {
						temp = nodes[i].val;
					}
				}
				rootCopy.val = temp;
			}
			
		}
		*/

	}
	
	public Integer checkTerm(algoTree node) {//returns value if terminal
		Integer res = null;//not terminal
		List<Integer> listE = new ArrayList<Integer>();
		List<Integer> listM = new ArrayList<Integer>();
		Integer enemy [] = node.enemy;
		Integer mine [] = node.mine;
		Integer move = node.move;
		
		for(int i = 0; i<enemy.length; i++) {
			listE.add(enemy[i]);
		}
		for(int i = 0; i<mine.length; i++) {
			listM.add(mine[i]);
		}
		
		if(win(listE)) {
			//System.out.println("found -1");
			return -1;
		}
		else if(win(listM)) {
			//System.out.println("found +1");
			return 1;
		}
		else if(enemy.length + mine.length == 9){
			return 0;
		}
		return null;
	}
	
	public boolean win(List<Integer> check) {//checks if win or loss
		boolean res = false;
		if(check.contains(1) && check.contains(2) && check.contains(3)) {
			res = true;
			//System.out.println("found win cond123");
		}
		else if(check.contains(4) && check.contains(5) && check.contains(6)) {
			res = true;
			//System.out.println("found win cond457");
		}
		else if(check.contains(7) && check.contains(8) && check.contains(9)) {
			res = true;
			//System.out.println("found win cond789");
		}
		else if(check.contains(1) && check.contains(4) && check.contains(7)) {
			res = true;
			//System.out.println("found win cond147");
		}
		else if(check.contains(2) && check.contains(5) && check.contains(8)) {
			res = true;
			//System.out.println("found win cond258");
		}
		else if(check.contains(3) && check.contains(6) && check.contains(9)) {
			res = true;
			//System.out.println("found win cond369");
		}
		else if(check.contains(1) && check.contains(5) && check.contains(9)) {
			res = true;
			//System.out.println("found win cond159");
		}
		else if(check.contains(3) && check.contains(5) && check.contains(7)) {
			res = true;
			//System.out.println("found win cond357");
		}
		
		return res;
	}
	
	private Integer [] newAval(Integer [] avaliable, Integer move) {
		if(d==0){
			//System.out.println("aL = "+avaliable.length+"  move"+move);	
			}
		Integer sz = avaliable.length;
		Integer [] newA = new Integer[sz-1];
		Integer counter = 0;
		for(Integer i = 0; i<sz; i++) {
			if(avaliable[i] == move ) {
				if(d==0){
					//System.out.println("new aval iter   "+ i+ "  removed" +avaliable[i]);	
					}
				
			}else {
				newA[counter] = avaliable[i] ;
				counter++;
				if(d==0){
				//System.out.println("new aval iter"+ i+ "  adding" +avaliable[i]+"  counter "+counter);	
				}
			}

		}
		return newA;	
	}
	
	
	private Integer [] newUsed(Integer [] used, Integer move) {		
		if(d==0){
			//System.out.println("usedL = "+used.length+"  move"+move);	
			}
		Integer sz = used.length;
		Integer [] newU = new Integer[sz+1];
		for(Integer i = 0; i<=sz; i++) {
			if(i==sz) {
				newU[i] = move;
				if(d==0){
					//System.out.println("Used iter  "+ i+ "  added" +newU[i]);	
					}
				
			}else {
				newU[i] = used[i] ;
				if(d==0){
					//System.out.println("Used iter  "+ i+ "  added" +newU[i]);	
					}
				
			}
		}
		return newU;
	}
	
	private void start() {//converts hm into Strings
		//stores all tiles # in string into each respective catagory
		StringBuilder avaliable = new StringBuilder(); 
		StringBuilder enemy = new StringBuilder(); 
		StringBuilder mine = new StringBuilder(); 
		for(Integer i = 1; i<=9;i++) {
			Integer occupant;
			if(hm.containsKey(i)) {
				occupant = hm.get(i);
				if(occupant == 0) {
					avaliable.append(Integer.toString(i));
				}
				else if(occupant == 1) {
					enemy.append(Integer.toString(i));
				}
				else if(occupant == 2) {
					mine.append(Integer.toString(i));
				}
			}
		}
		Integer [] avaA = parser(avaliable.toString());
		Integer [] eneA = parser(enemy.toString());
		Integer [] mineA = parser(mine.toString());
		System.out.println("ava: "+avaliable.toString());
		System.out.println("ene: "+enemy.toString());
		System.out.println("mine: "+mine.toString());
		//parses strings into integer arrays and passes data into algo 
		algoTree root = new algoTree(0, avaA , eneA, mineA , 0);//Integer depth, Integer[] a, Integer[] enemy, Integer[] mine, Integer move
		int moveT = (actions(root,avaA,eneA,mineA ,0)).move;//
		//System.out.println("root = "+ moveT);
		char move = (char)(moveT+ '0');//converts chosen move into char so we can play in mat
		//System.out.println("move to:"+ selectMove);
		m.playPiece(move, comp);//plays move onto mat
		

	}
	
	private Integer occupant(char c) {
		Integer ans = 0;
		if(c == hum.getPiece()) {
			ans = 1;
		}
		else if(c == comp.getPiece()) {
			ans = 2;
		}
		return ans;
	}
	
	public void setup() {
		Integer counter = 0;
		for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 3; j++) {
            		counter++;//spot #
            		//uses 1 for human piece, 2 for computer piece, 0 for avaliable
            		hm.put(counter, occupant(board[i][j]));//puts each spot and the occupant into hashmap
            }
        }
	}
}
