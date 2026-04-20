import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    ArrayList<int[]> moves;
    int size;

    public MyPlayer() {
        columns = new int[10];

        size = 10;

        HashtableChomp_CURRENT ChomperuskiBuski = new HashtableChomp_CURRENT(size); //10
        moves = ChomperuskiBuski.moves;

//        My10DChomp otherChomper = new My10DChomp();
//        allBoards = otherChomper.allBoards;
//        movesArray = otherChomper.movesArray;
//        numBoards = otherChomper.numBoards;

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer start");

        gameBoard = pBoard;
        int column = 1;
        int row = 1;

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */

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
//
//        for(int i = 0; i < numBoards; i++){
//            boolean found = true;
//            for(int j = 0; j < 10; j++){
//                if(allBoards[i*10+j] != columns[j]){
//                    found = false;
//                    break;
//                }
//            }
//            if(found){
//                return new Point(movesArray[2*i], movesArray[2*i+1]);
//            }
//        }

        Point myMove = new Point(row, column);
        System.out.println("My player has moved");
        return myMove;
    }

}
