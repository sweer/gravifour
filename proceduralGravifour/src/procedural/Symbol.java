package procedural;

enum Symbol { 
	X('X'), O('O'), EMPTY('.');
	
	private char charr;
	
	private Symbol(char charr) { 
		this.charr = charr;
	}
	
	public char toChar() { 
		return charr; 
	}
}
