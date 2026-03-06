import java.util.ArrayList;
import java.util.Arrays;

public class MyChomp {
    int size;
    int[][] allBoards;
    public static void main(String[] args) {
        MyChomp print3x3 = new MyChomp(3);
    }
    ArrayList<int[]> loseBoards = new ArrayList<>();
    ArrayList<int[]> winBoards = new ArrayList<>();

    //print all the 3x3 boards w/o souting 19 times

    //have 3 values to show how many are left in each row (bottom is shown first)
        //0<=r0<=r1<=r2<=3 and r0<3.

    public MyChomp(int size){
        this.size = size;
        int[] base = {1,0,0};
        loseBoards.add(base);

        //make the 2d array of 19 boards
        makeAllBoards();

        //sout all the boards in Bradford notation
        printAllBoards();

        //for each board, sout either losing board or the move to win
        printAllBestMoves();


        //for each board, sout the possible moves in Bradford notation
//        printAllPossibleMoves();
    }

    boolean contained(ArrayList<int[]> existing, int[] potential){
        for(int[] arr : existing){
            if(arr.length != potential.length){
                continue;
            }
            boolean failed = false;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] != potential[i]){
                    failed = true;
                    break;
                }
            }
            if(!failed){
                return true;
            }
        }
        return false;
    }

    void printAllBestMoves(){
        for(int index = 0; index < 19; index++){
            int c0 = allBoards[index][0];
            int c1 = allBoards[index][1];
            int c2 = allBoards[index][2];
            String result = i0possibleMoves(c0, c1, c2);
            if(!result.equals("nah")) {
                System.out.println(result);
                continue;
            }
            result = i1possibleMoves(c0, c1, c2);
            if(!result.equals("nah")) {
                System.out.println(result);
                continue;
            }
            result = i2possibleMoves(c0, c1, c2);
            if(!result.equals("nah")) {
                System.out.println(result);
                continue;
            }
            System.out.println("The board " + getBoard(c0, c1, c2) + " is a losing board. " + pickLosingMove(c0, c1, c2));
            int[] thisLosingBoard = {c0, c1, c2};
            loseBoards.add(thisLosingBoard);
        }
    }

    String pickLosingMove(int c0, int c1, int c2){
        String starter = "Might as well play (";
        int col = c0-1;
        if(c2 != 0) {
            col = c2 - 1;
            starter += "2, " + col + ").";
            return starter;
        }
        if(c1 != 0){
            col = c1-1;
            starter += "1, " + col + ").";
            return starter;
        }
        starter += "0, " + col + ").";
        return starter;
    }

    String i0possibleMoves(int c0, int c1, int c2) {
        for (int i0 = 1; i0 < c0; i0++) {
            int[] curr = {i0, Math.min(i0, c1), Math.min(i0, c2)};
            String result = "";
            if(contained(loseBoards, curr)){
                int[] state = {c0, c1, c2};
                winBoards.add(state);
                result = "The board " + getBoard(c0, c1, c2) + " is a winning board. A move to win is: " + "(0, " + i0 +")!";
            }else{
                if(!contained(winBoards, curr)){
                    System.out.println("smth wrong in i0 check");
                }
                result = "nah";
            }
            if (result.equals("nah")) {
                continue;
            } else {
                return result;
            }
        }
        return "nah";
    }

    String i1possibleMoves(int c0, int c1, int c2) {
        for (int i1 = 0; i1 < c1; i1++) {
            int[] curr = {c0, i1, Math.min(i1, c2)};
            String result = "";
            if(contained(loseBoards, curr)){
                int[] state = {c0, c1, c2};
                winBoards.add(state);
                result = "The board " + getBoard(c0, c1, c2) + " is a winning board. A move to win is: " + "(1, " + i1 +")!";
            }else{
                result = "nah";
            }
            if (result.equals("nah")) {
                continue;
            } else {
                return result;
            }
        }
        return "nah";
    }

    String i2possibleMoves(int c0, int c1, int c2) {
        for (int i2 = 0; i2 < c2; i2++) {
            int[] curr = {c0, c1, i2};
            String result = "";
            if(contained(loseBoards, curr)){
                int[] state = {c0, c1, c2};
                winBoards.add(state);
                result = "The board " + getBoard(c0, c1, c2) + " is a winning board. A move to win is: " + "(2, " + i2 +")!";
            }else{
                result = "nah";
            }
            if (result.equals("nah")) {
                continue;
            } else {
                return result;
            }
        }
        return "nah";
    }

    void printAllBoards(){
        String boardsString = "All the possible 3x3 boards, written in the form (# tiles in left column, # tiles in middle column, # tiles in in right column) are: ";
        for(int row = 0; row < 19; row++) {
            int curr=row+1;
            String thisBoard = curr + ": ";
//            int c0 = allBoards[row][0];
//            int c1 = allBoards[row][1];
//            int c2 = allBoards[row][2];
            thisBoard += getBoard(row);
            boardsString = boardsString + thisBoard + " | ";
        }
        boardsString = boardsString.substring(0, boardsString.length()-3);
        System.out.println(boardsString);
    }

    void makeAllBoards(){
        allBoards = new int[19][3];
        int index = 0;
        for(int c0 = 1; c0 <= size; c0++){
            for(int c1 = 0; c1 <= c0; c1++){
                for(int c2 = 0; c2 <= c1; c2++){
                    int[] curr = {c0, c1, c2};
                    allBoards[index] = curr;
                    index++;
                }
            }
        }
    }

    String getBoard(int index){
        int c0 = allBoards[index][0];
        int c1 = allBoards[index][1];
        int c2 = allBoards[index][2];
        return getBoard(c0, c1, c2);
    }

    String getBoard(int c0, int c1, int c2){
        return "(" + c0 + ", " + c1 + ", " + c2 + ")";
    }
                                        //How to do ts
    //recursively:
        //find positions where you have to put your opponent in a "winning state" -> "losing state"
        //find new positions that you can put your opponent in a "losing state" -> winning state

                            //Showing each specific step
    //start with the end position as a "losing state"
        //when (1,0) and (0,1) are empty
        //this is denoted with a crying emoji, since ur cooked
        //1 losing state

    //find all positions that you can put your opponent in the end position
        //tell the user what move accomplishes this. Call this a "winning state"
        //when (1,0) and (2,0) is empty or when (0,1) and (0,2) is empty
        //2 winning states

    //find positions in which you have to put your opponent in one of the winning positions
        //when there is (0,0), (0,1) and (1,0)
        //losing state, denoted again with a crying emoji
        //1 losing state (2)

    //find positions that can put your opponent in this losing state
        //when there is (0,0), (0,1), (1,0). Also, either

    //find positions where

}
