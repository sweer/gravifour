package oo;

import java.util.Scanner;

import oo.Symbol;
import static oo.Symbol.*;

public class HumanConsolePlayer extends Player {

	private Scanner scanner = new Scanner(System.in);

	@Override
	public int getMove() {
		printField();
		System.out.print("Player " + getSymbol().toChar() + ", make your move:");
		return inputValidMove();
	}
	
	private void printField() {
		Field.View field = getField();
		for (int x = 0; x < field.getColumns(); x++) { 
			System.out.print(x + " ");
		}
		System.out.println();
		for (int y = 0; y < field.getRows(); y++) {
			for (int x = 0; x < field.getColumns(); x++) {
				System.out.print(field.getCell(x, y).toChar() + " "); 
			}
			System.out.println();
		}
	}

	@Override
	public void announceWinner(Symbol symbol) {
		System.out.println("Player " + symbol.toChar() + " won the game!");
		printField();
	}
	
	private int inputValidMove() {
		int move = -1;
		int columns = getField().getColumns();
		
		do { 
			try { 
				move = Integer.parseInt(scanner.nextLine());
			} 
			catch (Exception e) {
				System.out.print(e.getMessage() + ". Input column number please: ");
			}
			
			if (move >= 0 && move < columns && getField().getCell(move, 0) != EMPTY) { 
				System.out.print("The column must be empty. Try again please:");
			}
		} while (move < 0 || move >= columns || getField().getCell(move, 0) != EMPTY);
		
		return move; 
	}


}
