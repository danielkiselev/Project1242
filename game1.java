import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class game1 {
	player hum;
	player comp;
	mat m;
	char board [][]= new char[3][3];
	int tiles;
	int turn = 0;
	boolean end = false;
	
	public game1(player hum, player comp, mat m, int turn, int tiles) {
		this.tiles = tiles;//tiles played, once we hit 9 and no winner the game is a tie.
		this.hum = hum;
		this.comp = comp;
		this.m = m;
		this.turn = turn;
		if(turn == 1) {
			turn(hum);
		}
		else {
			turn(comp);
		}
	}
	
	private void turn(player curr) {
		if(curr.getPlayer() == 1) {
			System.out.println("What square would you like to go on? Enter 1-9");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			char c = input.charAt(0);
			m.playPiece(c, curr);
			tiles++;
			turn = 2;
		}
		else if(curr.getPlayer() == 2) {
			
			algo a = new algo(hum, comp, m);//generates best move (human, comp, current mat)
			m = a.m;//grabs resulting map after move is played
			tiles++;//increases tile count
			turn = 1;// sets turn to human
		}
		win(curr);//checks win conditions
		if(end == false) {
			game1 g1 = new game1(hum,comp,m, turn,tiles);//generates new game obj with the updated data(human, comp, mat, turn, tiles)
		}
	}
	
	public void win(player curr) {
		List<Integer> check = new ArrayList<Integer>();
		int counter = 0;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		counter++;
            		//System.out.println(m.board[i][j]);
            		if(m.board[i][j] == curr.getPiece()) {//finds all the current player's tiles
                		check.add(counter);//adds the tiles to list
                		//System.out.println("adding"+ counter);
            		}
            }
        }
		boolean winner = winCheck(check);
		if(winner) {
			if(curr.getPlayer() == 1) {
				System.out.println("You Win");
				end = true;
			}
			if(curr.getPlayer() == 2) {
				System.out.println("You Lose");
				end = true;
			}
		}
		if((winner == false) && tiles == 9) {
			System.out.println("TIE");
			end = true;
		}
		else if(end == false) {
			System.out.println("No win conditions met");
		}


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
	
	private boolean winCheck(List<Integer> check) {
		for (int i = 0; i < check.size(); i++) {
			 	//System.out.println(check.get(i));
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
}
