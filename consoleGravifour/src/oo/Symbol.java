package oo;

enum Symbol { 
	X('X'), O('O'), EMPTY('.');
	
	private char charr;
	
	private Symbol(char charr) { 
		this.charr = charr;
	}
	
	public char toChar() { 
		return charr; 
	}

	public static Symbol byChar(char c) {
		for (Symbol symbol : Symbol.values()) { 
			if (symbol.charr == c) 
				return symbol;
		}
		throw new IllegalArgumentException("No Symbol with char = '" + c + "'");
	}
}
