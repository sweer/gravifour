package oo;

import static oo.Symbol.*;

/**
 * @author Aleksejs Truhans Aleksejs.Truhans@gmail.com
 */
public class Game {
	private final Player[] players = new Player[2];
	private final Field field; 
	private final int columns;
	private final int rows;
	private static final int TO_WIN = 4;
	private static final Symbol[] PLAYER_SYMBOLS = { X, O };

	
	public Game(int columns, int rows, Player... players) {
		this.field = new Field(columns, rows);
		this.columns = columns;
		this.rows = rows;
		for (int i = 0; i < 2; i++) { 
			this.players[i] = players[i];
			this.players[i].setSymbol(PLAYER_SYMBOLS[i]);
			this.players[i].setField(field.getView());
		}
	}

	public void run() {
		field.initialize();

		int currentPlayer = 1;

		do {
			currentPlayer = (currentPlayer == 0) ? 1 : 0;
			makeValidMove(currentPlayer);
		} while (!isWon(PLAYER_SYMBOLS[currentPlayer]));

		players[0].announceWinner(PLAYER_SYMBOLS[currentPlayer]);
		players[1].announceWinner(PLAYER_SYMBOLS[currentPlayer]);
	}

	private void makeValidMove(int currentPlayer) {
		boolean validMove = false;
		do { 
			int move = players[currentPlayer].getMove();
			validMove = field.dropIntoColumn(move, PLAYER_SYMBOLS[currentPlayer]);
		} while (!validMove);
	}

	private boolean isWon(Symbol symbol) {
		
		return isWonHorizontal(symbol) || isWonVertical(symbol) || isWonDiagonal(symbol);
	}

	private boolean isWonDiagonal(Symbol symbol) {
		return isWonRightDiagonal(symbol) || isWonLeftDiagonal(symbol);
	}

	private boolean isWonLeftDiagonal(Symbol symbol) {
		int firstDiagonalStartX = TO_WIN - 1; 
		int lastDiagonalStartY = rows - TO_WIN; 
		
		for (int x = firstDiagonalStartX; x < columns; x++) { 
			for (int y = 0; y <= lastDiagonalStartY; y++) {
				if (isWonInLeftDiagonalAt(x, y, symbol)) 
					return true;
			}
		}
		
		return false;
	}

	private boolean isWonInLeftDiagonalAt(int startX, int startY, Symbol symbol) {
		int count = 0;
		
		for (int x = startX, y = startY; x >= 0 && y < rows; x--, y++) { 
			if (field.getCell(x, y) == symbol) { 
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
		int lastDiagonalStartX = columns - TO_WIN; 
		int lastDiagonalStartY = rows - TO_WIN; 
		
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
		
		for (int x = startX, y = startY; x < columns && y < rows; x++, y++) { 
			if (field.getCell(x, y) == symbol) { 
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
		for (int x = 0; x < columns; x++) { 
			if (isWonInColumn(x, symbol)) 
				return true;
		}
		
		return false;
	}

	private boolean isWonInColumn(int x, Symbol symbol) {
		int count = 0;
		
		for (int y = 0; y < rows; y++) { 
			if (field.getCell(x, y) == symbol) { 
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
		for (int y = 0; y < rows; y++) { 
			if (isWonInRow(y, symbol)) 
				return true;
		}
		
		return false;
	}

	private boolean isWonInRow(int y, Symbol symbol) {
		int count = 0;
		
		for (int x = 0; x < columns; x++) { 
			if (field.getCell(x, y) == symbol) { 
				count++;
			} else { 
				count = 0;
			}
			
			if (count == TO_WIN) 
				return true;
		}
		
		return false;
	}


	
	
}
