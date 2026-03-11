import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    ArrayList<int[]> moves;

    public MyPlayer() {
        columns = new int[10];

        MyNDChomp ChomperuskiBuski = new MyNDChomp(10); //10
        moves = ChomperuskiBuski.moves;
        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

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
        for(int[] arr : moves){
            System.out.println("its doing this");
            int[] concat = Arrays.copyOf(arr, 10); //10
            if(Arrays.equals(concat, columns)){
                row = arr[10];
                System.out.println(row);
                column = arr[11];
                System.out.println(column);
                System.out.println("AEFAWEGASRGAWEFAWEGAWEFAWEFAWGEAEWSF   IT WORKED  ASDFASEAFAWE");
                break;
            }
        }

        Point myMove = new Point(row, column);
        return myMove;
    }

}
