package minesweeper;

public class cell {
    int row, col;
    boolean hidden = true;
    boolean isMined = false;
    boolean isBomb = false;
    String hint = "" + 0;

    public cell(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void Bomb() {
        this.isBomb = true;
        this.hint = "*";
        this.hidden = true;
    }

    public void updateHint(String count) {
        this.hint = count;
    }

    public String getValue() {
        if(hidden == true) {
            return "?";
        } else {
            return "" + hint;
        }
    }

    public String getHiddenValue() {
        return "" + hint;
    }

    public void mine() {
        this.hidden = false;
        if(hint.equals("*")){
            System.out.println("BOOM! at " + row + ", " + col);
            System.out.println("IT IS OVER IT IS OVER IT IS OVER");
            minesweeper.peek();
            System.out.println("IT IS OVER IT IS OVER IT IS OVER");
            System.exit(0);
        }
    }
}