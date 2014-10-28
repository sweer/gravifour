package oo;

public abstract class Player {
	private Symbol symbol;
	private Field.View field;

	void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	Symbol getSymbol() {
		return symbol;
	}
	
	Field.View getField() {
		return field;
	}

	void setField(Field.View field) {
		this.field = field;
	}

	public abstract int getMove();

	public abstract void announceWinner(Symbol symbol);
		

}
