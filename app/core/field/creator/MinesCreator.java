package minesweeper.app.core.field.creator;

import minesweeper.app.core.Field;
import minesweeper.app.core.field.Cell;

import java.util.Random;

public class MinesCreator {

    private static Cell[][] field;
    private static int fieldRows;
    private static int fieldCols;
    private static int numberOfMines;
    private static int xFirst;
    private static int yFirst;

    public static void createMines(Field f, int x, int y) {
        field = f.getField();
        fieldRows = f.getFieldRows();
        fieldCols = f.getFieldCols();
        numberOfMines = f.getNumberOfMines();
        xFirst = x;
        yFirst = y;

        initializeMines();
        randomizeMines();
    }

    private static void initializeMines() {
        int minesInitialised = 0;

        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldCols; j++) {
                if (minesInitialised == numberOfMines) {
                    return;
                }

                if (i != yFirst || j != xFirst) {
                    field[i][j].setValueReal('X');
                    minesInitialised++;
                }
            }
        }
    }

    private static void randomizeMines() {
        Random rand = new Random();

        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldCols; j++) {
                if (i != yFirst || j != xFirst) {
                    int index = rand.nextInt(fieldRows * fieldCols - 1);
                    if (index >= yFirst * fieldCols + xFirst) {
                        index++;
                    }

                    char temp = field[i][j].getValueReal();
                    field[i][j]
                            .setValueReal(field[index / fieldCols][index % fieldCols]
                                    .getValueReal());
                    field[index / fieldCols][index % fieldCols].setValueReal(temp);
                }
            }
        }
    }
}
