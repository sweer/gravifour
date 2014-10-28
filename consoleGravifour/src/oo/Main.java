package oo;

public class Main {

	public static void main(String[] args) {
		Game game = new Game(7, 6, new HumanConsolePlayer(), new RandomComputerPlayer());
		game.run();
	}
}
