import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    int size;

    //old:
    ArrayList<int[]> moves;
    //new:
    int[] bestMoves;
    ArrayList<int[]> allBoards;
    public MyPlayer() {
        columns = new int[10];

        size = 10;

//        MyUnifiedChomp_HT3 ChomperuskiBuski = new MyUnifiedChomp_HT3(size); //10
//        moves = ChomperuskiBuski.moves;

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

//        for(int[] arr : moves){
//            int[] concat = Arrays.copyOf(arr, 10); //look at the board part of the move array
//            if(Arrays.equals(concat, columns)){ //if this is the array for the current board:
//                return new Point(arr[10], arr[11]); //play the move found as the "best move"
//            }
//        }

        for(int i = 0; i < allBoards.size(); i++){
            if(Arrays.equals(allBoards.get(i), columns)){//if this is the array for the current board:
                int move = bestMoves[i];
                return new Point(move/100, move%100); //play the move found as the "best move"
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
