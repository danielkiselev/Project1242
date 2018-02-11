
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class algo2 {
	int Gcounter = 0;
	int Gcounter1 = 0;
	int alphaG = -100;
	int betaG = 100;
	int d;
	int depthL;
	mat m;
	player hum;
	player comp;
	char board[][] = new char[3][3];//holds game board
	HashMap<Integer,Integer> hm = new HashMap<>();//location, current occupancy used to build game board 
	Integer selectMove = null;//move we want to play
	
	public algo2 (player hum, player comp, mat m){
		this.m = m;
		this.hum = hum;
		this.comp = comp;
		board = m.board;
		setup();//rebuilds game board into hashmap to fetch values in O(1)
		start();
    }
	
	public int dLimit(Integer [] mine, Integer [] ava ) {
		if (mine == null) {
			return 5;//the most you can ever have is 5 total turns to win.
		}
		int szM = mine.length;
		int szA = ava.length;
		Integer limit = 5;
		List<Integer> listM = new ArrayList<Integer>();
		List<Integer> listA = new ArrayList<Integer>();
		for(int i = 0; i<szM; i++) {
			listM.add(mine[i]);
		}
		for(int i = 0; i<szA; i++) {
			listA.add(ava[i]);
		}
		
		if(listM.contains(1) || listM.contains(2) || listM.contains(3)) {
			Integer temp = dLimitChecker(1, 2, 3,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(4) || listM.contains(5) || listM.contains(6)) {
			Integer temp = dLimitChecker(4, 5, 6,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(7) || listM.contains(8) || listM.contains(9)) {
			Integer temp = dLimitChecker(7, 8, 9,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(1) || listM.contains(4) || listM.contains(7)) {
			Integer temp = dLimitChecker(1, 4, 7,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(2) || listM.contains(5) || listM.contains(8)) {
			Integer temp = dLimitChecker(2, 5, 8,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(3) || listM.contains(6) || listM.contains(9)) {
			Integer temp = dLimitChecker(3, 6, 9,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(1) || listM.contains(5) || listM.contains(9)) {
			Integer temp = dLimitChecker(1, 5, 9,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		if(listM.contains(3) || listM.contains(5) || listM.contains(7)) {
			Integer temp = dLimitChecker(3, 5, 7,listM,listA);
			if(temp != null) {
				if(temp < limit) {
					limit = temp;
				}
			}
		}
		System.out.println("Limit is: " +limit);
		return limit;
	}
	
	public Integer dLimitChecker(int x1, int x2, int x3,List<Integer> listM, List<Integer> listA) {
		Integer val = null;
		boolean good = true;
		int needed = 0;
		List<Integer> checker = new ArrayList<Integer>();
		if(listM.contains(x1)){
			checker.add(x1);
		}
		if(listM.contains(x2)){
			checker.add(x2);
		}
		if(listM.contains(x3)){
			checker.add(x3);
		}
		if(!checker.contains(x1)) {
			if(listA.contains(x1)) {
				needed++;
			}
			else {
				good = false;
			}
		}
		if(!checker.contains(x2)) {
			if(listA.contains(x2)) {
				needed++;
			}
			else {
				good = false;
			}
		}
		if(!checker.contains(x3)) {
			if(listA.contains(x3)) {
				needed++;
			}
			else {
				good = false;
			}
		}
		if(good) {
			val = (needed*2)-1;
			System.out.println("Spots: "+x1+""+x2+""+x3+"  VAL=" +val);
		}
		return val;
	}
	
	private Integer[] parser(String data) {//parses string into array
		Integer sz = data.length();
		Integer res[] = new Integer [sz];
		for(Integer i = 0; i<sz; i++) {
			res[i] = Character.getNumericValue((data.charAt(i)));
		}
		return res;
	}
	
	public Integer deterVal(algoTree root,Integer depth, int pruneT, boolean good) {
		List<algoTree> children = root.child;
		Integer val= null;
		for (algoTree child: children) {
			if(depth == 0 ) {
				//System.out.println("New move = "+ nodes[i].move +" | New val =  " + nodes[i].val +"  | Depth"+depth + "  |term" + nodes[i].term);
			}
			//System.out.println("New move = "+ nodes[i].move +" | New val =  " + nodes[i].val +"  | Depth"+depth + "  |term" + nodes[i].term);
			if(child.term) {
				if(val == null) {
					val = 0;
				}
				val += child.val;
			}

		}

		if(val != null) {
			return val;
		}
		
		//System.out.println("No value assigned");
		if(good) {
			return null;
		}
		return pruneT;
	}
	
	
	public int deterMove(algoTree root) {//returns int of the best move
		List<algoTree> children = root.child;
	//	System.out.println("determining");
		Integer ovr = null;
		int move= 0;
		int move1 = 0;
		Integer bestChild = null;
		for (algoTree child: children) {
			//System.out.println("moving to"+nodes[i].move+"   value = " + nodes[i].val);
			if(child.term) {
				//System.out.println("found term:");
				//System.out.print("moving to"+nodes[i].move+"   value = " + nodes[i].val);
				if(ovr == null) {
					ovr = 0;
					ovr = child.val;
					move = child.move;
				}
				else if(ovr<child.val) {
					ovr = child.val;
					move = child.move;
				}
				System.out.println("term = " + ovr);
			}
				if(bestChild==null) {
					bestChild = 0;
					bestChild = child.val;
					move1 = child.move;
					//System.out.println("fun Deter move" + move);
					
				}
				else if(child.val!= null && child.val>bestChild) {
					bestChild = child.val;
					move1 = child.move;
					//System.out.println("fun Deter move" + move);
				}

		}
		if(ovr != null) {
			selectMove = move;
			return move;
		}
		else {
			selectMove = move1;
			//System.out.println("Deter move returned" + move);
			return move1;
		}

	}
	
	//(parent node, available spots, human spots, computer spots, depth of search)
	public algoTree actions(algoTree root,Integer [] aT, Integer[] eT, Integer [] mT, int depth, int alpha, int beta) {
		boolean prune = false;
		algoTree rootCopy = root;
		Integer res = null;
		int szA = aT.length;
		int newMsz = mT.length+1;
		int newEsz = eT.length+1;
		int newAsz = szA-1;
		d = depth;
		//algoTree nodes  = new algoTree[szA];//creates a node for every available spot
		int alphaT = alpha;
		int betaT = beta;
		for(Integer i = 0; i<szA; i++) {
			algoTree child = null;
			if(depth%2 == 0) {//computer simulated move
				Integer [] newA = new Integer[newAsz];//stores available spots, removing the played move.
				Integer [] newM = new Integer[newMsz];//stores computer spots, adding the played move.
				newA = newAval(aT,aT[i]);//fills array
				newM = newUsed(mT,aT[i]);//fills array
				child = new algoTree(depth, newA, eT, newM, aT[i]);//Integer depth, Integer[] a, Integer[] enemy, Integer[] mine, Integer move
				Integer value = null;
				value = checkTerm(child);//checks if played move is terminal
				Gcounter1++;
				if (value != null) {//if terminal, assigns terminal identifier 
					child.setVal(value);//assigns value of node
					child.setTerm(true);
				}
				else if (depth<depthL) {//if not terminal we take the played node and repeat
					child.setVal((actions(child,newA, eT, newM, depth+1, alphaT, betaT)).getVal());
					if(child.val != null) {
						if(child.val > beta ) {
							Gcounter++;
							prune = true;
							//System.out.println("breaking: "+child.move+"   value = " + child.val +"  beta" + beta+"  depth="+depth);
							break;
						}
						else {
							if(alphaT < child.val) {
								alphaT = child.val;
							}
						}
					}
				}
			}
			else if(depth%2 == 1) {//human
				Integer [] newA = new Integer[newAsz];
				Integer [] newE = new Integer[newEsz];
				newA = newAval(aT,aT[i]);
				newE = newUsed(eT,aT[i]);
				child = new algoTree(depth, newA, newE, mT, aT[i]);//Integer depth, Integer[] a, Integer[] enemy, Integer[] mine, Integer move
				Integer value = null;
				value = checkTerm(child);//checks if played move is terminal
				Gcounter1++;
				if (value != null) {
					child.setVal(value);
					child.setTerm(true);
				}
				else if (depth<depthL) {
					child.setVal((actions(child,newA, newE, mT, depth+1,alphaT,betaT)).getVal());
					if(child.val != null) {
						if(child.val < alpha) {
							Gcounter++;
							prune = true;
							break;
						}
						else {
							if(child.val < betaT) {
								betaT = child.val;
							}
						}
					}
				}
			}
			if(child !=null) {
				root.addChild(child);
			}
		}
		
		
		//checks the nodes for all the avaliable spots. 
		//uses depth value to determine if min or max.
		//returns the value of the root node
		
		
		if(depth%2 == 1) {
			res = deterVal(root, depth, betaT,prune);
			root.setVal(res);
		}
		
		if(depth%2 == 0) {
			res = deterVal(root, depth, alphaT, prune);
			root.setVal(res);
		}
		

		
		if(depth == 0) {//root depth
			System.out.println("setting move");
			root.setMove(deterMove(root));//when recursed back to top, we pass all possible nodes in to determine best move
			System.out.println("ROOT.move to:"+ root.move);
			printTree(root, depth);

			
			return root;
			//assign integer of best move
		}
		return root;//return root with newly assigned move.
	}
	
	public void printTree(algoTree root, int depth ) {
			List<algoTree> children = root.child;
			for (algoTree child: children) {
				System.out.println("printTree: "+child.move+"   value = " + child.val + " depth"+depth + "  root" + root.move);
			}
			/*
			depth++;
			for (algoTree child: children) {
				if(child.child != null) {
					printTree(child, depth);
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
		listE.add(move);
		listM.add(move);
		for(int i = 0; i<enemy.length; i++) {
			listE.add(enemy[i]);
		}
		for(int i = 0; i<mine.length; i++) {
			listM.add(mine[i]);
		}
		if(win(listE)) {
			//System.out.println("found -1");
			res = -1;
		}
		else if(win(listM)) {
			//System.out.println("found +1");
			res = 1;
		}
		else if(node.a.length == 0){
			res =0;
		}
		return res;
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
	
	public void start() {//converts hm into Strings
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
		depthL = dLimit(mineA,avaA);
		
		//parses strings into integer arrays and passes data into algo 
		algoTree root = new algoTree(0, avaA , eneA, mineA , 0);//Integer depth, Integer[] a, Integer[] enemy, Integer[] mine, Integer move
		int moveT = (actions(root,avaA,eneA,mineA ,0,-100,100)).move;//
		//System.out.println("root = "+ moveT);
		char move = (char)(moveT+ '0');//converts chosen move into char so we can play in mat
		System.out.println("Times breaking loop:"+ Gcounter);
		System.out.println("Iterations:"+ Gcounter1);
		m.playPiece(move, comp);//plays move onto mat
		

	}
	
	private Integer occupant(char c) {
		Integer ans = 0;
		if(c == '-' || c == '0') {
			ans = 3;
		}
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
