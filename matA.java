
public class matA {
mat masterB[][] = new mat[3][3];
	
    public matA (mat m1, mat m2, mat m3, mat m4, mat m5, mat m6, mat m7, mat m8, mat m9){
      //  System.out.println("play piece");
        masterB[0][0] = m1;
        masterB[0][1] = m2;
        masterB[0][2] = m3;
        masterB[1][0] = m4;
        masterB[1][1] = m5;
        masterB[1][2] = m6;
        masterB[2][0] = m7;
        masterB[2][1] = m8;
        masterB[2][2] = m9;
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	counter++;
            	char counterC = (char)(counter+ '0');
            	masterB[i][j].number = counter;
            	masterB[i][j].winner = counterC;
            }
        }
    }
    
    
    public void update(int index ,mat updated) {
    	for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		if(masterB[i][j].number == index) {
            			masterB[i][j] = updated;
            		}
            }
        }
	}
    

    
    
	public void printBoard() {
		System.out.println("------MASTER BOARD BELOW-----");
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(masterB[i][j].winner + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
	}
    
	
	public void playBoard(char location, player p) {//finds desired location and replaces array with player's piece.
        //System.out.println("called");
        //System.out.println(location);
        //System.out.println(p.getPiece());
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		if(masterB[i][j].full == true) {
            			//masterB[i][j] = p.getPiece();
            		}
            }
        }
		//printBoard();
	}
	
	public boolean checkFull(mat check) {
		boolean fullT = false;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            		if(masterB[i][j] == check) {
            			return masterB[i][j].full;
            		}
            }
        }
		
		return fullT;
	}
}
