
public class mat {
	char board[][] = new char[3][3];
	boolean full = false;
	int number = 0;
	int tiles = 0;
	char winner = '0';
	
    public mat (){
      //  System.out.println("play piece");
        board[0][0] = '1';
        board[0][1] = '2';
        board[0][2] = '3';
        board[1][0] = '4';
        board[1][1] = '5';
        board[1][2] = '6';
        board[2][0] = '7';
        board[2][1] = '8';
        board[2][2] = '9';
        printBoard();
    }
    
	public void printBoard() {
		System.out.println();
		System.out.println("Board: "+number);
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
	}
	
	public void playPiece(char location, player p) {//finds desired location and replaces array with player's piece.
        //System.out.println("called");
        //System.out.println(location);
        //System.out.println(p.getPiece());
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		if(location == board[i][j]) {
            			board[i][j] = p.getPiece();
            			tiles++;
            		}
            }
        }
		printBoard();
	}
	
	
}
