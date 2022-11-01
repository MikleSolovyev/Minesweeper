package minesweeper.app;

import minesweeper.app.core.*;

public class Game {

    private Player player;
    private IOController ioController;
    private Field field;
    private boolean endOfGame;

    public Game() {
        this.player = new Player();
        this.ioController = new IOController();
        this.field = new Field();
        this.endOfGame = false;
    }

    private void processPlayersMove() {
        do {
            IOController.printRequestMessage();
        } while (!ioController.parseInput(player.inputString(), field));

        if ("free".equals(ioController.getCommand())) {
            player.exploreCell(ioController.getX(), ioController.getY(), field);
        } else {
            player.markCell(ioController.getX(), ioController.getY(), field);
        }
    }

    private void checkEndOfGame() {
        if (player.isLose()) {
            IOController.printLoseMessage();
            endOfGame = true;
        } else if (player.getMarkedCorrect() == field.getNumberOfMines()
                && player.getMarkedAll() == player.getMarkedCorrect()
                || player.getExplored() + field.getNumberOfMines() == field.getFieldCols() * field.getFieldRows()
                && player.getMarkedAll() == 0) {
            IOController.printWinMessage();
            endOfGame = true;
        }
    }

    public void prepareForGame() {
        do {
            IOController.printStartMessage();
        } while (!ioController.parseInput(player.inputString(), field));

        field.setNumberOfMines(ioController.getNumberOfMines());

        while (!"free".equals(ioController.getCommand())) {
            field.printField(player.isLose());

            do {
                IOController.printRequestMessage();
            } while (!ioController.parseInput(player.inputString(), field));

            if ("mine".equals(ioController.getCommand())) {
                player.markCell(ioController.getX(), ioController.getY(), field);
            }
        }

        field.createMinesAndHints(ioController.getX(), ioController.getY());

        player.exploreCell(ioController.getX(), ioController.getY(), field);
        field.printField(player.isLose());
        checkEndOfGame();
    }

    public void run() {
        while (!endOfGame) {
            processPlayersMove();
            field.printField(player.isLose());
            checkEndOfGame();
        }
    }
}
