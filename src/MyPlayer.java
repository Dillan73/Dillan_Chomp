import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    ArrayList<int[]> moves;

    public MyPlayer() {
        columns = new int[10];

        HashtableChomp ChomperuskiBuski = new HashtableChomp(10); //10
        moves = ChomperuskiBuski.moves;

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
            int[] concat = Arrays.copyOf(arr, 10); //10
            if(Arrays.equals(concat, columns)){
                return new Point(arr[10], arr[11]);
            }
        }

        Point myMove = new Point(row, column);
        System.out.println("My player has moved");
        return myMove;
    }

}
