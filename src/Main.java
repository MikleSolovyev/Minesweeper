package minesweeper;

import minesweeper.app.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.prepareForGame();
        game.run();
    }
}
