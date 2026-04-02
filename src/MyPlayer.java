import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    ArrayList<int[]> moves;
    int[] allBoards;
    int[] movesArray;
    int numBoards;

    public MyPlayer() {
        columns = new int[10];

//        MyNDChomp ChomperuskiBuski = new MyNDChomp(10); //10
//        moves = ChomperuskiBuski.moves;

        My10DChomp ChomperuskiBuski = new My10DChomp(10); //10
        allBoards = ChomperuskiBuski.allBoards;
        movesArray = ChomperuskiBuski.movesArray;
        numBoards = ChomperuskiBuski.numBoards;

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer start");

        gameBoard = pBoard;
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

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
//        for(int[] arr : moves){
//            int[] concat = Arrays.copyOf(arr, 10); //10
//            if(Arrays.equals(concat, columns)){
//                row = arr[10];
//                column = arr[11];
//                break;
//            }
//        }

        for(int i = 0; i < numBoards; i++){
            boolean found = true;
            for(int j = 0; j < 10; j++){
                if(allBoards[i*10+j] != columns[j]){
                    found = false;
                    break;
                }
            }
            if(found){
                return new Point(movesArray[2*i], movesArray[2*i+1]);
            }
        }

        Point myMove = new Point(row, column);
        System.out.println("My player has moved");
        return myMove;
    }

}
