package minesweeper.app.core.field.creator;

import minesweeper.app.core.Field;
import minesweeper.app.core.field.Cell;

public class HintsCreator {

    private static Cell[][] field;
    private static int fieldRows;
    private static int fieldCols;

    public static void createHints(Field f) {
        field = f.getField();
        fieldRows = f.getFieldRows();
        fieldCols = f.getFieldCols();

        for (int i = 0; i < fieldRows; i++) {
            for (int j = 0; j < fieldCols; j++) {
                if (field[i][j].getValueReal() == '.') {
                    createHintForCell(i, j);
                }
            }
        }
    }

    private static void createHintForCell(int y, int x) {
        int number = 0;

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i >= 0 && j >= 0 && i < fieldRows && j < fieldCols
                        && field[i][j].getValueReal() == 'X') {
                    number++;
                }
            }
        }

        field[y][x].setValueReal((number == 0) ? '.' : ((char) (number + '0')));
    }
}
