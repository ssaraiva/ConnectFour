/**
 *  Sergio Saraiva
 * 	111950948
 *      sergio.saraiva@stonybrook.edu
 *	Game Project
 *	CSE214 
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFour {
	public static void main(String[] args) {
		new Game();	
		Game.play();
	}	
}

class Game {
	Game() { }
	/* WELCOME TO CONNECT FOUR!
	 * 
	 * How to play: 
	 * 1. Red player is first. Choose a column to drop disk.
	 * 2. The console will check if the drop was the winning drop.
	 * 3. If not, it will switch players.
	 * 4. Current player will choose a column to drop disk.
	 * 5. Steps will repeat from Step 2.
	 */
	public static void play() {
		Scanner input = new Scanner(System.in);
	
		int choice;
		char currentPlayer = 'R';
		char[][] slots = new char[6][7];

//------ Set all elements in array to ' '.
		for(int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				slots[i][j] = ' ';
			}
		}
		
		System.out.println("----- WELCOME TO CONNECT FOUR! -----");
		System.out.println("Created by: Sergio Saraiva 111950948");
		updateBoard(slots);
		
//----- Drop disk, check for winner, and swap players after each turn.
		boolean continueInput = true;
		do {
		try {
		while(continueInput) {
			if (currentPlayer == 'R') { 
				System.out.print("\nDrop a red disk at column (0 - 6): "); 
				choice = input.nextInt(); // If playing with CPU, add comment.
				//choice = (int) (Math.random() * 6); // Remove comment to play against CPU.
				//System.out.println("Choice: " + choice); 
			} else { 
				System.out.print("\nDrop a yellow disk at column (0 - 6): ");
				choice = input.nextInt(); // If playing with CPU, add comment.
				//choice = (int) (Math.random() * 6); // Remove comment to play against CPU.
				//System.out.println("Choice: " + choice); 
			}
			
//--------- Input is either less than 0 or greater than 6. Redo disk drop.
//--------- If column is full, redo disk drop.
			if(choice >= 0 && choice <= 6) { 
				if (slots[0][choice] == ' ') {
					dropDisk(currentPlayer, slots, choice); 
				} else {
					System.out.println("\nColumn " + choice + " is full!");
					currentPlayer = swapPlayers(currentPlayer);	
				}
			} else {
				System.out.println("\nInvalid input. Must be between columns 0 - 6. ");
				currentPlayer = swapPlayers(currentPlayer);	
			}

			updateBoard(slots);
			winner(slots, currentPlayer);
			currentPlayer = swapPlayers(currentPlayer);	
			
		}
		} catch (InputMismatchException ex) {
			System.out.println("\nInvalid input. Must be between columns 0 - 6. ");
			updateBoard(slots);
			input.nextLine();
		}
		} while (continueInput);
		input.close();
		
	}
	
//- Swap Players
	public static char swapPlayers(char currentPlayer) {
		if(currentPlayer == 'R') { currentPlayer = 'Y'; }
		else { currentPlayer = 'R'; }
		return currentPlayer;
	}

//- Drop Disk
	public static void dropDisk(char currentPlayer, char[][] slots, int choice) {
		switch(choice) {
			case 0:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][0] == ' ') {
						slots[i][0] = currentPlayer;
						break; }
				}
				break;
			case 1:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][1] == ' ') {
						slots[i][1] = currentPlayer;
						break; }
				}
				break;
			case 2:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][2] == ' ') {
						slots[i][2] = currentPlayer;
						break; }
				}
				break;
			case 3:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][3] == ' ') {
						slots[i][3] = currentPlayer;
						break; }
				}
				break;
			case 4:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][4] == ' ') { 
						slots[i][4] = currentPlayer;
						break; }
				}
				break;
			case 5:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][5] == ' ') {
						slots[i][5] = currentPlayer;
						break; }
				}
				break;
			case 6:
				for (int i = 5; i >= 0; i--) {
					if(slots[i][6] == ' ') {
						slots[i][6] = currentPlayer;
						break; }
				}
				break;
		}
			
	}
	
//- Update the board
	public static void updateBoard(char[][] slots) {
		for(int i = 0; i < slots.length; i++) {
			System.out.print("\n          |");
			for (int j = 0; j < 7; j++) {
				System.out.print(slots[i][j]);
				System.out.print("|");
			}
		}
		System.out.print("\n          _______________");
		System.out.print("\n          |0|1|2|3|4|5|6|");
		System.out.println("\n          ...............");	
			
	}

//- Check for winner
	public static void winner(char[][] slots, char currentPlayer) {
		boolean won = false;
		boolean isFull = true; 
		int count = 0;
		
//----- Horizontal check
		for (int i = 5; i >= 0; i--)
		{
			for (int j = 0; j < 7; j++) {
				if (slots[i][j] == currentPlayer) { count++; }
				else { count = 0; }
				if (count >= 4) { won = true; }
			}
			count = 0;
		}
		
//----- Vertical check
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (slots[j][i] == currentPlayer) { count++; }
				else { count = 0; }
				if (count >= 4) { won = true; }
			}
			count = 0;
		}
		
//----- Upwards diagonal check
		for (int i = 3; i <= 5; i++){
	        for (int j = 0; j <= 3; j++){
	            if (slots[i][j] == currentPlayer && 
	            	slots[i-1][j+1] == currentPlayer && 
	            	slots[i-2][j+2] == currentPlayer && 
	            	slots[i-3][j+3] == currentPlayer)
	            won = true;
	        }
	        count = 0;
	    }
		
//----- Downwards diagonal check
	    for (int i = 3; i <= 5; i++){
	        for (int j = 3; j <= 6; j++){
	            if (slots[i][j] == currentPlayer && 
	            	slots[i-1][j-1] == currentPlayer && 
	            	slots[i-2][j-2] == currentPlayer && 
	           		slots[i-3][j-3] == currentPlayer)
	            won =  true;
	        }
	        count = 0;
	    }
	    
//----- Display winner
		if(won) {
			if(currentPlayer == 'R') { System.out.print("\nWinner is RED!"); }
			else  { System.out.print("\nWinner is YELLOW!"); }
		System.exit(1);
		}
		
//----- Display draw
		for(int i = 0; i <= 5; i++) {
			for (int j = 0; j <= 6; j++) { 
				if(slots[i][j] == ' ') { isFull = false; }
			}	
		}
		if(isFull) {
			System.out.println("It is a draw!");
			System.exit(1); 
		}
	}
	
}