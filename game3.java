import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class game3 {
	player hum;
	player comp;
	matA mA;
	mat currM;
	mat boards [][]= new mat[3][3];
	int tiles = 0;
	int turn = 0;
	boolean end = false;
	
	public game3(player hum, player comp, matA mA, int turn, mat lastM) {//(human, comp, matA, turn, curr mat) 

		currM = lastM;
		//this.tiles = tiles;//tiles played, once we hit 9 and no winner the game is a tie.
		this.hum = hum;
		this.comp = comp;
		this.mA = mA;
		this.turn = turn;

		if(end != true) {
			setup();
			if(turn == 1) {
				turn(hum);
			}
			else {
				turn(comp);
			}
		}

	}
	
	private void turn(player curr) {
		mA.printBoard();
		if(curr.getPlayer() == 1) {
			while(currM == null) {
				System.out.println("Which game board would you like to go on? Enter 1-9");
				Scanner in = new Scanner(System.in);
				int matIn = in.nextInt();
				playBoard(matIn);
			}
		}
		else if(curr.getPlayer() == 2) {
			if(currM == null) {
				mat currMa;
				currMa = convertMap(mA);
				currMa.printBoard();
				algo2 a = new algo2(hum, comp, currMa);//generates best move (human, comp, current mat)
				currMa = a.m;
				currM = convertMove(currMa);
			}
		}
		playPiece(curr);
	}
	
	
	private mat convertMove(mat getMap) {
		mat res = null;
	    	for (int i = 0; i < 3; i++) {
	    		for (int j = 0; j < 3; j++) {
	            	if(getMap.board[i][j] == comp.getPiece()) {
	            			return boards[i][j];
	            	}
	        }
	    	}
	    	return res;
	}

	
	private mat convertMap(matA gameMa) {
		mat resM = new mat();
	    	for (int i = 0; i < 3; i++) {
	    		for (int j = 0; j < 3; j++) {
	    			int temp = gameMa.masterB[i][j].number;
	    			char boardc = (char)(temp+ '0');
	            	if(boardc == resM.board[i][j]) {
	            			resM.board[i][j] = gameMa.masterB[i][j].winner;
	            	}
	        }
	    	}
	    	return resM;
	}

	
	
	private void playPiece(player curr) {
		if(curr.getPlayer() == 1) {
			System.out.println("We are playing on board: " + currM.number);
			System.out.println("What square would you like to go on? Enter 1-9");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			char c = input.charAt(0);
			currM.playPiece(c, curr);
			mA.update(currM.number, currM);//mat number, mat
			turn = 2;
		}
		else if(curr.getPlayer() == 2) {
			
			algo2 a = new algo2(hum, comp, currM);//generates best move (human, comp, current mat)
			currM = a.m;//grabs resulting map after move is played
			mA.update(currM.number, currM);//mat number, mat
			turn = 1;// sets turn to human
		}
		win(curr);//checks win conditions
		if(end == false) {
			game3 g3 = new game3(hum,comp,mA, turn, currM);
		}
	}
	
	public void win(player curr) {
		List<Integer> check = new ArrayList<Integer>();
		int counter = 0;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		counter++;
            		//System.out.println(m.board[i][j]);
            		if(currM.board[i][j] == curr.getPiece()) {//finds all the current player's tiles
                		check.add(counter);//adds the tiles to list
                		//System.out.println("adding"+ counter);
            		}
            }
        }
		boolean winner = winCheck(check);
		List<Integer> checkMS = new ArrayList<Integer>();
		if(winner) {
			if(curr.getPlayer() == 1) {
				tiles++;
				System.out.println("You win Board");
				currM.winner = curr.getPiece();
				currM.full = true;
				mA.update(currM.number, currM);
				int counter2 = 0;
				for (int i = 0; i < 3; i++) {
		            for (int j = 0; j < 3; j++) {
		            		counter2++;
		            		if(boards[i][j].winner == curr.getPiece()) {
		            			checkMS.add(counter2);
		            		}
		            }
		        }
				currM = null;
			}
			if(curr.getPlayer() == 2) {
				tiles++;
				System.out.println("You lose Board");
				currM.full = true;
				currM.winner = curr.getPiece();
				mA.update(currM.number, currM);
				int counter2 = 0;
				for (int i = 0; i < 3; i++) {
		            for (int j = 0; j < 3; j++) {
		            		counter2++;
		            		if(boards[i][j].winner == curr.getPiece()) {
		            			checkMS.add(counter2);
		            		}
		            }
		        }
				currM = null;
			}
		}
		if((winner == false) && currM.tiles == 9) {
			tiles++;
			System.out.println("TIE BOARD");
			currM.full = true;
			currM.winner = '-';
			mA.update(currM.number, currM);
			currM = null;
		}
		boolean winnerMS = false;
		if(!checkMS.isEmpty()) {
			winnerMS = winCheck(checkMS);
		}
		if(winnerMS && curr.getPlayer() == 1) {
			System.out.println("YOU WIN");
			end = true;
		}
		if(winnerMS && curr.getPlayer() == 2) {
			System.out.println("YOU LOSE");
			end = true;
		}
		if(tiles == 9) {
			System.out.println("TIE GAME");
			end = true;
		}
	}
	
	private boolean winCheck(List<Integer> check) {
		for (int i = 0; i < check.size(); i++) {
			 	System.out.println(check.get(i));
		}
		System.out.println("turn ended checking winner");
		boolean res = false;
		if(check.contains(1) && check.contains(2) && check.contains(3)) {
			res = true;
		}
		else if(check.contains(4) && check.contains(5) && check.contains(6)) {
			res = true;
		}
		else if(check.contains(7) && check.contains(8) && check.contains(9)) {
			res = true;
		}
		else if(check.contains(1) && check.contains(4) && check.contains(7)) {
			res = true;
		}
		else if(check.contains(2) && check.contains(5) && check.contains(8)) {
			res = true;
		}
		else if(check.contains(3) && check.contains(6) && check.contains(9)) {
			res = true;
		}
		else if(check.contains(1) && check.contains(5) && check.contains(9)) {
			res = true;
		}
		else if(check.contains(3) && check.contains(5) && check.contains(7)) {
			res = true;
		}
		return res;
	}
	
	private void playBoard(int matIn) {
		int counter = 0;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		counter++;
            		if(matIn == counter) {
            			if(boards[i][j] != null) {
                			currM = boards[i][j];
            			}
            			else {
            				System.out.println("Invalid board");
            			}
            		}
            }
        }
	}
	
	private void setup() {
		int counter = 0;
		String temp = "Boards left: ";
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		counter++;
            		if(!mA.masterB[i][j].full){
            			boards[i][j] = mA.masterB[i][j];
            			temp+=Integer.toString(counter)+" | ";
            		}
            		else {
            			boards[i][j] = null;
            		}
            }
        }
		System.out.println(temp);
	}
	
}
