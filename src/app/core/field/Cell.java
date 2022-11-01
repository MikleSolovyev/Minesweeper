package minesweeper.app.core.field;

public class Cell {
    private char valueReal;
    private char valueForPrint;
    private boolean marked;

    public Cell() {
        this.valueReal = '.';
        this.valueForPrint = '.';
        this.marked = false;
    }

    public char getValueReal() {
        return valueReal;
    }

    public void setValueReal(char valueReal) {
        this.valueReal = valueReal;
    }

    public char getValueForPrint() {
        return valueForPrint;
    }

    public void setValueForPrint(char valueForPrint) {
        this.valueForPrint = valueForPrint;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked() {
        this.marked = !this.marked;
    }
}
