import java.util.Arrays;

public class MyChomp {
    int size;
    int[][] allBoards;
    public static void main(String[] args) {
        MyChomp print3x3 = new MyChomp(3);
    }

    //print all the 3x3 boards w/o souting 19 times

    //have 3 values to show how many are left in each row (bottom is shown first)
        //0<=r0<=r1<=r2<=3 and r0<3.

    public MyChomp(int size){
        this.size = size;
        //make the 2d array of 19 boards
        makeAllBoards();

        //sout all the boards in Bradford notation
        printAllBoards();

        //for each board, sout the possible moves in Bradford notation
        printAllPossibleMoves();
    }

    void printAllPossibleMoves(){
        for(int index = 0; index < 19; index++){
            int c0 = allBoards[index][0];
            int c1 = allBoards[index][1];
            int c2 = allBoards[index][2];
            String thisBoardMoves = boardMovesString(c0, c1, c2);
            System.out.println(thisBoardMoves);
        }
    }

    String boardMovesString(int c0, int c1, int c2) {
        String boardMoves = "From the position " + getBoard(c0, c1, c2) + ", this possible boards are: ";

        boardMoves += getBoard(c0, c1, c2);
        boardMoves += ": ";
        String leftMoves = "";
        for(int i0 = 0; i0 < c0; i0++){
            if(i0 != 0){
                leftMoves += ", ";
            }
            leftMoves += getBoard(i0, Math.min(i0, c1), Math.min(i0, c2));
        }

        String middleMoves = "";
        for(int i1 = 0; i1 < c1; i1++){
            middleMoves += ", ";
            middleMoves += getBoard(c0, i1, Math.min(i1, c2));
        }

        String rightMoves = "";
        for(int i2 = 0; i2 < c2; i2++){
            rightMoves += ", ";
            rightMoves += getBoard(c0, c1, i2);
        }
        boardMoves += leftMoves + middleMoves + rightMoves + ".";

        return boardMoves;
    }

    void printAllBoards(){
        String boardsString = "All the possible 3x3 boards, written in the form (# tiles in left column, # tiles in middle column, # tiles in in right column) are: ";
        for(int row = 0; row < 19; row++) {
            int curr=row+1;
            String thisBoard = curr + ": ";
            int c0 = allBoards[row][0];
            int c1 = allBoards[row][1];
            int c2 = allBoards[row][2];
            thisBoard += getBoard(c0, c1, c2);
            boardsString = boardsString + thisBoard + " | ";
        }
        boardsString = boardsString.substring(0, boardsString.length()-3);
        System.out.println(boardsString);
    }

    int[][] getBoardPossibleMoves(int c0, int c1, int c2) {
        int[][] possibles = new int[c0 + c1 + c2][3];
        int index = 0;
        for (int i0 = c0; i0 >= 1; i0--) {
            possibles[index][0] = i0;
            possibles[index][1] = Math.min(i0, c1);
            possibles[index][2] = Math.min(i0, c2);
            index++;
        }
        for (int i1 = c1; i1 >= 0; i1--) {
            possibles[index][0] = c0;
            possibles[index][1] = i1;
            possibles[index][2] = Math.min(i1, c2);
            index++;
        }
        for (int i2 = c2; i2 >= 0; i2--) {
            possibles[index][0] = c0;
            possibles[index][1] = c1;
            possibles[index][2] = i2;
            index++;
        }
        return possibles;
    }

    void makeAllBoards(){
        allBoards = new int[19][3];
        int index = 0;
        for(int c0 = 1; c0 <= size; c0++){
            for(int c1 = 0; c1 <= c0; c1++){
                for(int c2 = 0; c2 <= c1; c2++){
                    allBoards[index][0] = c0;
                    allBoards[index][1] = c1;
                    allBoards[index][2] = c2;
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

    String DillanGetAll3x3Boards(){
        String allBoards = "This is printing all the possible boards in a 3x3 board in the form (# tiles gone in bottom row, # tiles gone in middle row, # tiles gon in top row): ";
        int curr = 1;
        for(int i0 = 0; i0 <= size-1; i0++){
            for(int i1 = i0; i1 <= size; i1++){
                for(int i2 = i1; i2 <= size; i2++){
                    String thisBoard = curr + ": (" + i0 + ", " + i1 + ", " + i2 + ")   ";
                    allBoards = allBoards + thisBoard;
                    curr++;
                }
            }
        }
        return allBoards;
    }

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
