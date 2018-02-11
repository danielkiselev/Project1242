import java.util.*;
public class main {

	static int complete = 0;
	static int mode = 0;

	
	
	public static void main(String[] args) {
		System.out.println("For normal Tic Tac Toe enter 1. For advanced enter 2. For Super Advanced enter 3");
		while(mode == 0) {
			modeSet();
		}
		System.out.println("Would you like to play with X or O ?");
		while(complete == 0) {
			if(mode == 1) {
				start();
			}
			if(mode ==2) {
				startA();
			}
			if(mode ==3) {
				startB();
			}
			if(complete == 0) {
				System.out.println("Invalid Choice, please type x or o");
			}
		}
	}
	
	static void modeSet() {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		char c = input.charAt(0);
		
		if(c == '1') {
			System.out.println("Ok, normal");
			mode = 1;//
		}
		if(c == '2') {
			System.out.println("Ok, advanced");
			mode = 2;//
		}
		if(c == '3') {
			System.out.println("Ok, advanced");
			mode = 3;//
		}
	}

	
	static void start() {//assigns who goes first and 2nd
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		char c = input.charAt(0);

		if(c == 'x' ||c == 'X' ) {
			System.out.println("Ok, you go 1st");
			player human = new player(1 , 1, 'X');
			player comp = new player(2 , 2, 'O');
			mat m = new mat();//creates a new board/mat with all available slot
			game1 g = new game1(human, comp, m, 1,0);//creates a new game (human, comp, map, turn, #tiles played) 
			complete = 1;//
		}
		if(c == 'O'|| c == 'o' ) {
			System.out.println("Ok, you go 2nd");
			player human = new player(1 , 2, 'O');
			player comp = new player(2 , 1, 'X');
			mat m = new mat();
			game1 g = new game1(human, comp, m, 2,0);
			complete = 1;
		}
	}
	
	static void startA() {//assigns who goes first and 2nd
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		char c = input.charAt(0);

		if(c == 'x' ||c == 'X' ) {
			System.out.println("Ok, you go 1st");
			player human = new player(1 , 1, 'X');
			player comp = new player(2 , 2, 'O');
			mat m1 = new mat();
			mat m2 = new mat();
			mat m3 = new mat();
			mat m4 = new mat();
			mat m5 = new mat();
			mat m6 = new mat();
			mat m7 = new mat();
			mat m8 = new mat();
			mat m9 = new mat();

			matA mA = new matA(m1, m2, m3, m4, m5, m6, m7, m8, m9);
			
			
			game2 g2 = new game2(human, comp, mA, 1, null);//creates a new game (human, comp, map, turn) 
			complete = 1;//
		}
		if(c == 'O'|| c == 'o' ) {
			System.out.println("Ok, you go 2nd");
			player human = new player(1 , 2, 'O');
			player comp = new player(2 , 1, 'X');
			mat m1 = new mat();
			mat m2 = new mat();
			mat m3 = new mat();
			mat m4 = new mat();
			mat m5 = new mat();
			mat m6 = new mat();
			mat m7 = new mat();
			mat m8 = new mat();
			mat m9 = new mat();

			matA mA = new matA(m1, m2, m3, m4, m5, m6, m7, m8, m9);
			
			
			game2 g2 = new game2(human, comp, mA, 2, null);//creates a new game (human, comp, map, turn) 
			complete = 1;//

		}
	}
	
	static void startB() {//assigns who goes first and 2nd
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		char c = input.charAt(0);

		if(c == 'x' ||c == 'X' ) {
			System.out.println("Ok, you go 1st");
			player human = new player(1 , 1, 'X');
			player comp = new player(2 , 2, 'O');
			mat m1 = new mat();
			mat m2 = new mat();
			mat m3 = new mat();
			mat m4 = new mat();
			mat m5 = new mat();
			mat m6 = new mat();
			mat m7 = new mat();
			mat m8 = new mat();
			mat m9 = new mat();

			matA mA = new matA(m1, m2, m3, m4, m5, m6, m7, m8, m9);
			
			
			game3 g3 = new game3(human, comp, mA, 1, null);//creates a new game (human, comp, map, turn) 
			complete = 1;//
		}
		if(c == 'O'|| c == 'o' ) {
			System.out.println("Ok, you go 2nd");
			player human = new player(1 , 2, 'O');
			player comp = new player(2 , 1, 'X');
			mat m1 = new mat();
			mat m2 = new mat();
			mat m3 = new mat();
			mat m4 = new mat();
			mat m5 = new mat();
			mat m6 = new mat();
			mat m7 = new mat();
			mat m8 = new mat();
			mat m9 = new mat();

			matA mA = new matA(m1, m2, m3, m4, m5, m6, m7, m8, m9);
			
			
			game3 g3 = new game3(human, comp, mA, 2, null);//creates a new game (human, comp, map, turn) 
			complete = 1;//

		}
	}
}
