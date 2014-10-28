package oo;

import static oo.Symbol.EMPTY;

import java.util.Random;

public class RandomComputerPlayer extends Player {
	private Random random = new Random();

	@Override
	public int getMove() {
		int move;
		
		do { 
			move = random.nextInt(getField().getColumns()); 
		} while (getField().getCell(move, 0) != EMPTY);
		
		return move; 
	}

	@Override
	public void announceWinner(Symbol symbol) {
		
	}

}
