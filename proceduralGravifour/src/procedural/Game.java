package procedural;

import static procedural.Symbol.*;

import java.util.Random;
import java.util.Scanner;

/**
 * Should work, except when the field gets full :) 
 * Full rows and columns do not disappear in this implementation. 
 * Player can be a human or the random dummy algorithm implemented in the code.
 * 
 * @author Aleksejs Truhans Aleksejs.Truhans@gmail.com
 **/
public class Game {
	private static final int COLUMNS = 7;
	private static final int ROWS = 5;
	private static final int TO_WIN = 4;

	/* On the screen x increases to the right, y increases downwards, e.g.: 
	 * 0,0 1,0
	 * 0,1 1,1
	 */
	private Symbol[][] field = new Symbol[COLUMNS][ROWS];
	private Symbol[] playerSymbol = { X, O };
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

	private void run() {
		initializeField();

		int currentPlayer = 1;

		do {
			currentPlayer = (currentPlayer == 0) ? 1 : 0;
			int move = getMove(currentPlayer);
			storePiece(move, playerSymbol[currentPlayer]);
		} while (!isWon(playerSymbol[currentPlayer]));

		System.out.println("*****************");
		printField();
		System.out.println("Player " + playerSymbol[currentPlayer].toChar() + " won the game!");
	}

	private boolean isWon(Symbol symbol) {
		
		return isWonHorizontal(symbol) || isWonVertical(symbol) || isWonDiagonal(symbol);
	}

	private boolean isWonDiagonal(Symbol symbol) {
		return isWonRightDiagonal(symbol) || isWonLeftDiagonal(symbol);
	}

	private boolean isWonLeftDiagonal(Symbol symbol) {
		int firstDiagonalStartX = TO_WIN - 1; 
		int lastDiagonalStartY = ROWS - TO_WIN; 
		
		for (int x = firstDiagonalStartX; x < COLUMNS; x++) { 
			for (int y = 0; y <= lastDiagonalStartY; y++) {
				if (isWonInLeftDiagonalAt(x, y, symbol)) 
					return true;
			}
		}
		
		return false;
	}

	private boolean isWonInLeftDiagonalAt(int startX, int startY, Symbol symbol) {
		int count = 0;
		
		for (int x = startX, y = startY; x >= 0 && y < ROWS; x--, y++) { 
			if (field[x][y] == symbol) { 
				count++;
			} else { 
				count = 0;
			}
			
			if (count == TO_WIN) 
				return true;
		}
		
		return false;
	}

	private boolean isWonRightDiagonal(Symbol symbol) {
		int lastDiagonalStartX = COLUMNS - TO_WIN; 
		int lastDiagonalStartY = ROWS - TO_WIN; 
		
		for (int x = 0; x <= lastDiagonalStartX; x++) { 
			for (int y = 0; y <= lastDiagonalStartY; y++) {
				if (isWonInRightDiagonalAt(x, y, symbol)) 
					return true;
			}
		}
		
		return false;
	}

	private boolean isWonInRightDiagonalAt(int startX, int startY, Symbol symbol) {
		int count = 0;
		
		for (int x = startX, y = startY; x < COLUMNS && y < ROWS; x++, y++) { 
			if (field[x][y] == symbol) { 
				count++;
			} else { 
				count = 0;
			}
			
			if (count == TO_WIN) 
				return true;
		}
		
		return false;
	}

	private boolean isWonVertical(Symbol symbol) {
		for (int x = 0; x < COLUMNS; x++) { 
			if (isWonInColumn(x, symbol)) 
				return true;
		}
		
		return false;
	}

	private boolean isWonInColumn(int x, Symbol symbol) {
		int count = 0;
		
		for (int y = 0; y < ROWS; y++) { 
			if (field[x][y] == symbol) { 
				count++;
			} else { 
				count = 0;
			}
			
			if (count == TO_WIN) 
				return true;
		}
		
		return false;
	}

	private boolean isWonHorizontal(Symbol symbol) {
		for (int y = 0; y < ROWS; y++) { 
			if (isWonInRow(y, symbol)) 
				return true;
		}
		
		return false;
	}

	private boolean isWonInRow(int y, Symbol symbol) {
		int count = 0;
		
		for (int x = 0; x < COLUMNS; x++) { 
			if (field[x][y] == symbol) { 
				count++;
			} else { 
				count = 0;
			}
			
			if (count == TO_WIN) 
				return true;
		}
		
		return false;
	}

	private int getMove(int player) {
		if (player == 0) { 
			return inputHumanMove(playerSymbol[player]); 
		} else { 
			return computeComputerMove();
		}
	}

	private int computeComputerMove() {
		int move;
		
		do { 
			move = random.nextInt(COLUMNS); 
		} while (field[move][0] != EMPTY);
		
		return move; 
	}

	private int inputHumanMove(Symbol symbol) {
		printField();
		System.out.print("Player " + symbol.toChar() + ", make your move:");
		return inputValidMove();
	}

	private void storePiece(int move, Symbol symbol) {
		int y = 0;
		while (y < ROWS && field[move][y] == EMPTY) { 
			y++;
		}
		
		field[move][y - 1] = symbol;
	}

	private int inputValidMove() {
		int move = -1;
		
		do { 
			try { 
				move = Integer.parseInt(scanner.nextLine());
			} 
			catch (Exception e) {
				System.out.print(e.getMessage() + ". Input column number please: ");
			}
			
			if (move >= 0 && move < COLUMNS && field[move][0] != EMPTY) { 
				System.out.print("The column must be empty. Try again please:");
			}
		} while (move < 0 || move >= COLUMNS || field[move][0] != EMPTY);
		
		return move; 
	}

	private void initializeField() {
		for (int x = 0; x < COLUMNS; x++) {
			for (int y = 0; y < ROWS; y++) {
				field[x][y] = EMPTY;
			}
		}
	}

	private void printField() {
		for (int x = 0; x < COLUMNS; x++) { 
			System.out.print(x + " ");
		}
		System.out.println();
		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				System.out.print(field[x][y].toChar() + " "); 
			}
			System.out.println();
		}
	}

}
