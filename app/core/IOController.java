package minesweeper.app.core;

import java.util.Scanner;

public class IOController {

    private static final String STARTMESSAGE =
            "How many mines do you want on the field? ";
    private static final String REQUESTMESSAGE =
            "Set/unset mines marks or claim a cell as free: ";
    private static final String WINMESSAGE =
            "Congratulations! You found all the mines!";
    private static final String LOSEMESSAGE =
            "You stepped on a mine and failed!";

    private int numberOfMines;

    private int x;
    private int y;
    private String command;

    public IOController() {
        this.numberOfMines = 0;
        this.x = 0;
        this.y = 0;
        this.command = "";
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCommand() {
        return command;
    }

    public boolean parseInput(String string, Field field) {
        Scanner sc = new Scanner(string);

        try {
            if (numberOfMines == 0) {
                numberOfMines = sc.nextInt();

                if (numberOfMines <= 0) {
                    System.out.println("Number of mines must be more than 0!");
                    return false;
                } else if (numberOfMines >= field.getFieldRows() * field.getFieldCols()) {
                    System.out.println("Number of mines must be less than number of cells!");
                    return false;
                }
            } else {
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
                command = sc.next();

                if (x < 0 || y < 0
                        || x >= field.getFieldCols()
                        || y >= field.getFieldRows()) {
                    System.out.println("Incorrect coordinates!");
                    return false;
                }

                if (field.getField()[y][x].getValueForPrint() != '.'
                        && field.getField()[y][x].getValueForPrint() != '*') {
                    System.out.println("This cell already explored!");
                    return false;
                }

                if (!"free".equals(command) && !"mine".equals(command)) {
                    System.out.println("Incorrect command!");
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex + " thrown!");
            return false;
        }

        return true;
    }

    public static void printStartMessage() {
        System.out.print(STARTMESSAGE);
    }

    public static void printRequestMessage() {
        System.out.print(REQUESTMESSAGE);
    }

    public static void printWinMessage() {
        System.out.println(WINMESSAGE);
    }

    public static void printLoseMessage() {
        System.out.println(LOSEMESSAGE);
    }
}
