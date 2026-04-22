import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    int size;

    //old:
    ArrayList<int[]> moves;
    //new:
    Hashtable<String, Integer> bestMoves;
    public MyPlayer() {
        columns = new int[10];

        size = 10;

        MyHashtableChompv2 ChomperuskiBuski = new MyHashtableChompv2(size); //10
        moves = ChomperuskiBuski.moves;

//        MyChomp_FINAL chomper = new MyChomp_FINAL(size); //10
//        bestMoves = chomper.bestMoves;

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

        for(int[] arr : moves){
            int[] concat = Arrays.copyOf(arr, 10); //look at the board part of the move array
            if(Arrays.equals(concat, columns)){ //if this is the array for the current board:
                return new Point(arr[10], arr[11]); //play the move found as the "best move"
            }
        }

//        String currStateString = Arrays.toString(columns);
//        Integer best = bestMoves.get(currStateString);
//        if(best == null){
//            System.out.println("Null with: " + currStateString);
//        }else{
//            System.out.println("My player has moved");
//            row = best % 100;
//            column = best/100;
//            return new Point(row, column);
//        }

        System.out.println("My player has moved");
        return new Point(0, 0);
    }

}
