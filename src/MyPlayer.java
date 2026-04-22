import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    int size;

    //my data structures
    int[] bestMoves;
    ArrayList<int[]> allBoards;
    public MyPlayer() {
        columns = new int[10];

        size = 10;

        MyChomp_FINAL chomper = new MyChomp_FINAL(size); //10
        bestMoves = chomper.bestMoves;
        allBoards = chomper.allBoards;

    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer start");

        gameBoard = pBoard;
        int column = 1;
        int row = 1;

        for(int c = 0; c < 10; c++){
            int chips = 0;
            for(int r = 0; r < 10; r++){
                if(gameBoard[r][c].isAlive){
                    chips++;
                }else{
                    break;
                }
            }
            columns[c] = chips;
        }
        long timeTracker = System.currentTimeMillis();
        for(int i = 0; i < allBoards.size(); i++){ //
            if(Arrays.equals(allBoards.get(i), columns)){ //if this is the actual board
                //we want to play the move found as the best move for this board:
                int move = bestMoves[i];

                //track the time it took to find the move. Then, return the move.
                long timeTaken = System.currentTimeMillis()-timeTracker;
                System.out.println("My player moved. It took " + timeTaken + " ms.");
                System.out.println("You there! Hurry up please!!! I'm getting bored...");
                return new Point(move/100, move%100); //play the move found as the "best move"
            }
        }

        System.out.println("My player has NOT moved, who the heck is this??");
        return new Point(0, 0);
    }

}
