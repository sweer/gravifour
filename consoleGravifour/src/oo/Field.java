package oo;

import static oo.Symbol.EMPTY;

public class Field {
	public class View { 
		
		public int getColumns() { 
			return field.length;
		}
		
		public int getRows() { 
			return field[0].length;
		}

		public Symbol getCell(int column, int row) {
			return Field.this.getCell(column, row);
		}
		
	}
	
	private final Symbol[][] field;
	
	public View getView() { 
		return new View();
	}
	
	public Field(int columns, int rows) {
		field = new Symbol[columns][rows];
	}

	public Symbol getCell(int column, int row) {
		return field[column][row];
	}

	public boolean dropIntoColumn(int column, Symbol value) {
		if (field[column][0] != Symbol.EMPTY) {
			return false;
		}

		int y = field[column].length - 1;
		while (field[column][y] != Symbol.EMPTY) {
			y--;
		}
		
		field[column][y] = value;
		return true;
	}

	public void initialize() {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				field[x][y] = EMPTY;
			}
		}
	}

}
