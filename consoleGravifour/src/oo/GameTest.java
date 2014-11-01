package oo;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void NoWinOnEmptyField() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				".......",
				".......",
				".......",
				".......",
				".......",
				"......."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertFalse(game.isWonLeftDiagonal(Symbol.X));
		assertFalse(game.isWonRightDiagonal(Symbol.X));
		assertFalse(game.isWonLeftDiagonal(Symbol.O));
		assertFalse(game.isWonRightDiagonal(Symbol.O));
	}

	@Test
	public void winLeftDiTopRight() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				"......X",
				".....X.",
				"....X..",
				"...X...",
				".......",
				"......."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonLeftDiagonal(Symbol.X));
	}

	@Test
	public void noWinLeftDi3and1() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				"......X",
				".....X.",
				"....X..",
				".......",
				"..X....",
				"......."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertFalse(game.isWonLeftDiagonal(Symbol.X));
	}

	@Test
	public void winLeftDiMiddle() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				".......",
				"....X..",
				"...X...",
				"..X....",
				".X.....",
				"......."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonLeftDiagonal(Symbol.X));
	}

	
	@Test
	public void winLeftDiTopLeft() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				"...X...",
				"..X....",
				".X.....",
				"X......",
				".......",
				"......."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonLeftDiagonal(Symbol.X));
	}
	
	@Test
	public void winLeftDiBottomRight() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				".......",
				".......",
				"......O",
				".....O.",
				"....O..",
				"...O..."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonLeftDiagonal(Symbol.O));
	}
	
	@Test
	public void winRightDiBottomLeft() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				".......",
				".......",
				"O......",
				".O.....",
				"..O....",
				"...O..."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonRightDiagonal(Symbol.O));
	}

	@Test
	public void winRightDiMiddle() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				".......",
				"..O....",
				"...O...",
				"....O..",
				".....O.",
				"......."
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonRightDiagonal(Symbol.O));
	}

	
	@Test
	public void winRightDiTopLeft() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				"O......",
				".O.....",
				"..O....",
				"...O...",
				".......",
				".......",
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonRightDiagonal(Symbol.O));
	}

	@Test
	public void noWinRightDi2and3() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				"O......",
				".O.....",
				".......",
				"...O...",
				"....O..",
				".....O.",
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertFalse(game.isWonRightDiagonal(Symbol.O));
	}

	@Test
	public void winRightDiTopRight() {
		Game game = new Game(7, 6, new HumanConsolePlayer(),
				new HumanConsolePlayer());
		// @formatter:off
		String[] fieldState = {
				"...X...",
				"....X..",
				".....X.",
				"......X",
				".......",
				".......",
		}; // @formatter:on
		FieldMock field = new FieldMock(fieldState);
		game.setField(field);
		assertTrue(game.isWonRightDiagonal(Symbol.X));
	}

}

class FieldMock extends Field {
	private final Symbol[][] field;

	public FieldMock(String[] initialData) {
		super(initialData[0].length(), initialData.length);
		int columns = initialData[0].length();
		int rows = initialData.length;
		field = new Symbol[columns][rows];
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				field[x][y] = Symbol.byChar(initialData[y].charAt(x));
			}
		}
	}

	public Symbol getCell(int column, int row) {
		return field[column][row];
	}
}