package minesweeper;
/*
mvn archetype:generate -DgroupId=minesweeper -DartifactId=miniproj -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
git add . (add ALL content of cart to github)
git commit -m "While Loop"                  (add comment while committing)
git push origin main                        (push to main branch)

mvn compile exec:java -Dexec.mainClass="minesweeper.minesweeper"

javac src/main/java/minesweeper/minesweeper.java

vans_@LAPTOP-AS886SBL MINGW64 ~/VISA NUS-ISS VTTP/testing/miniproj/src/main/java (main)
$ javac minesweeper/minesweeper.java


*/

import java.io.Console;
import java.util.Random;
import minesweeper.*;

public class minesweeper {
    public static String[][] game = null;
    public static cell cells[][] = new cell[10][10];

    public static void main(String[] args){
        //Start a 10X10 gameboard
        game = new String[10][10];

        Console cons = System.console();
        String input = "";
        reset();

        while(true) {
            Random r1 = new Random();
            Random r2 = new Random();

            System.out.println("Please enter input: ");
            System.out.println("USAGE: q (QUIT) / reset (RESET BOARD) / try X,Y (MINE) / difficulty X (DO THIS AFTER EVERY RESET) / peek (REVEAL BOARD)");
            input = cons.readLine();
            String[] entry;

            if(input.contains("q"))
                break;
            else if(input.contains("reset")){
                reset();
                updateBoard();
                showGameBoard();
            }
            else if(input.contains ("try")) {
                entry = input.substring(3).split(",");
                for (int i = 0; i < entry.length; i++)
                    entry[i] = entry[i].trim();
                cells[Integer.parseInt(entry[0])][Integer.parseInt(entry[1])].mine();
                updateBoard();
                showGameBoard();
            } 
            else if(input.contains("bomb")){
                entry = input.substring(4).split(",");
                for (int i = 0; i < entry.length; i++)
                    entry[i] = entry[i].trim();
                cells[Integer.parseInt(entry[0])][Integer.parseInt(entry[1])].Bomb();
            }
            else if(input.contains("peek")){
                updateBoard();
                peek();
            }
            else if(input.contains("difficulty")){
                int level = Integer.parseInt(input.substring(11).trim());
                for (int i = 0; i < level; i++)
                    cells[r1.nextInt(10)][r2.nextInt(10)].Bomb();
            }  

        }

    }

    public static void showGameBoard() {
        //Show gameboard
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        System.out.println("   -- -- -- -- -- -- -- -- -- --");
        int countRows = 0;
        for(int i = 0; i < game.length; i++) {
            System.out.print(countRows + " ");
            for(int j = 0; j < game.length; j++){
                //System.out.print("|" + game[i][j] + "|"); //DEPRECATED
                //cells[i][j].hidden = false;
                System.out.print("|" + cells[i][j].getValue() + "|");
            }
            countRows++;
            System.out.println();
        }
    }

    public static void updateBoard() {
        int count = 0;
        for(int i = 0; i < game.length; i++) {
            for(int j = 0; j < game[0].length; j++){
                    for(int k = -1; k < 2; k++) {   
                        for(int l = -1; l < 2; l++){
                            if(i + k > -1 && i + k <= (game.length - 1)
                            && j + l > -1 && j + l <= (game.length - 1)
                            ){
                                if(cells[i+k][j+l].getHiddenValue().equals("*") && !cells[i][j].getHiddenValue().equals("*")){
                                    //System.out.println("Cells counted: "+ (i+k) + ", " + (j+l));
                                    count++;
                                }
                        }
                    }
            }
            if(!cells[i][j].getHiddenValue().equals("*")){
                game[i][j] = "" + count; //DEPRECATED
                cells[i][j].updateHint(count + "");
            }
            //System.out.println("COUNT: " + count);
            count = 0;
            }
        }
    }

    public static void peek(){
        //Show gameboard
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        System.out.println("   -- -- -- -- -- -- -- -- -- --");
        int countRows = 0;
        for(int i = 0; i < game.length; i++) {
            System.out.print(countRows + " ");
            for(int j = 0; j < game.length; j++){
                System.out.print("|" + cells[i][j].getHiddenValue() + "|");
            }
            countRows++;
            System.out.println();
        }
    }

    public static void reset(){
        //Instantiate gameboard with 0s
        for(int i = 0; i < game.length; i++) {
            for(int j = 0; j < game[0].length; j++){
                game[i][j] = "?"; //DEPRECATED
                cells[i][j] = new cell(i, j);
            }
        }
    }

    public void bomb(int row, int col){
        cells[row][col].Bomb();
    }

}
