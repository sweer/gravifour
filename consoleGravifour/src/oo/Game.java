package oo;

import static oo.Symbol.*;

/**
 * @author Aleksejs Truhans Aleksejs.Truhans@gmail.com
 */
public class Game {
	private final Player[] players;
	private Field field; 
	private final int columns;
	private final int rows;
	private static final int TO_WIN = 4;
	private static final Symbol[] PLAYER_SYMBOLS = { X, O };

	
	public Game(int columns, int rows, Player player0, Player player1) {
		this.field = new Field(columns, rows);
		this.columns = columns;
		this.rows = rows;
		players = new Player[] { player0, player1 } ;
		for (int i = 0; i < 2; i++) { 
			players[i].setSymbol(PLAYER_SYMBOLS[i]);
			players[i].setField(field.getView());
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

	boolean isWonLeftDiagonal(Symbol symbol) {
		for (int x = TO_WIN - 1; x < columns; x++) { 
			if (isWonInLeftDiagonalAt(x, 0, symbol)) 
				return true;
		}
		
		for (int y = 1; y < rows - (TO_WIN - 1); y++) { 
			if (isWonInLeftDiagonalAt(columns - 1, y, symbol)) 
				return true;			
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

	boolean isWonRightDiagonal(Symbol symbol) {
		for (int x = 0; x < columns - (TO_WIN - 1); x++) { 
			if (isWonInRightDiagonalAt(x, 0, symbol)) 
				return true;
		}
		
		for (int y = 1; y < rows - (TO_WIN - 1); y++) { 
			if (isWonInRightDiagonalAt(0, y, symbol)) 
				return true;
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
	
	void setField(Field field) { 
		this.field = field;
	}
}
