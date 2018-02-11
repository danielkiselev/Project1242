
public class player {
	private int player;//1 for human, 2 for AI
	private int turn;//1 for 1st, 2 for 2nd
	private char piece;
	
	public player(int player, int turn, char piece) {
		this.player = player;
		this.turn = turn;
		this.piece = piece;
		
	}
	
	public int getPlayer(){
		return player;
	}
	
	public int getTurn(){
		return turn;
	}
	public char getPiece() {
		return piece;
	}

}
