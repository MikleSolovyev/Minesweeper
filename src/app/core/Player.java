package minesweeper.app.core;

import java.util.Scanner;

public class Player {

    private int markedAll;
    private int markedCorrect;
    private int explored;
    private boolean lose;

    public Player() {
        this.markedAll = 0;
        this.markedCorrect = 0;
        this.explored = 0;
        this.lose = false;
    }

    public int getMarkedAll() {
        return markedAll;
    }

    public int getMarkedCorrect() {
        return markedCorrect;
    }

    public int getExplored() {
        return explored;
    }

    public boolean isLose() {
        return lose;
    }

    public void exploreCell(int x, int y, Field field) {
        if (field.getField()[y][x].isMarked()) {
            markCell(x, y, field);
        }

        if (field.getField()[y][x].getValueReal() == 'X') {
            lose = true;
        } else if (field.getField()[y][x].getValueReal() != '.') {
            field.getField()[y][x]
                    .setValueForPrint(field.getField()[y][x].getValueReal());
        } else {
            field.getField()[y][x].setValueForPrint('/');

            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + 1; j++) {
                    if (i >= 0 && j >= 0
                            && i < field.getFieldRows()
                            && j < field.getFieldCols()) {
                        if (field.getField()[i][j].getValueForPrint() == '*'
                                || field.getField()[i][j].getValueForPrint() == '.') {
                            exploreCell(j, i, field);
                        }
                    }
                }
            }
        }

        explored++;
    }

    public void markCell(int x, int y, Field field) {
        field.getField()[y][x].setMarked();

        if (field.getField()[y][x].isMarked()) {
            field.getField()[y][x].setValueForPrint('*');
        } else {
            field.getField()[y][x].setValueForPrint('.');
        }

        markedAll += (field.getField()[y][x].isMarked()) ? 1 : -1;

        if (field.getField()[y][x].getValueReal() == 'X') {
            markedCorrect += (field.getField()[y][x].isMarked()) ? 1 : -1;
        }
    }

    public String inputString() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
