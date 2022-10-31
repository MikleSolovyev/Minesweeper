package minesweeper.app.core;

import minesweeper.app.core.field.Cell;
import minesweeper.app.core.field.creator.*;

public class Field {

    private int fieldRows;
    private int fieldCols;
    private Cell[][] field;
    private int numberOfMines;

    public Field() {
        this.fieldRows = 9;
        this.fieldCols = 9;
        this.field = new Cell[fieldRows][fieldCols];
        this.numberOfMines = 0;

        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldCols; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getField() {
        return field;
    }

    public int getFieldRows() {
        return fieldRows;
    }

    public int getFieldCols() {
        return fieldCols;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    public void createMinesAndHints(int x, int y) {
        MinesCreator.createMines(this, x, y);
        HintsCreator.createHints(this);
    }

    public void printField(boolean lose) {
        System.out.println();

        System.out.print(" |");
        for (int i = 1; i <= fieldCols; i++) {
            System.out.print(i);
        }
        System.out.println("|");

        System.out.print("-|");
        for (int i = 0; i < fieldCols; i++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int i = 0; i < fieldRows; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < fieldCols; j++) {
                if (lose && field[i][j].getValueReal() == 'X') {
                    field[i][j].setValueForPrint(field[i][j].getValueReal());
                }

                System.out.print(field[i][j].getValueForPrint());
            }
            System.out.println("|");
        }

        System.out.print("-|");
        for (int i = 0; i < fieldCols; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }
}
