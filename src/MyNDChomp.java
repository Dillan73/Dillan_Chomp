import java.util.ArrayList;
import java.util.Arrays;

public class MyNDChomp {
    int size;
    int[][] allBoards;
    private ArrayList<int[]> loseBoards = new ArrayList<>();
    private ArrayList<int[]> winBoards = new ArrayList<>();
    public int[][] movesArray;
    public ArrayList<int[]> moves = new ArrayList<>();

    public static void main(String[] args) {
        MyNDChomp print3x3 = new MyNDChomp(10);
    }

    public MyNDChomp(int size){
        this.size = size;
        int[] baseCase = new int[size];
        loseBoards.add(baseCase);
        baseCase[0] = 1;
        loseBoards.add(baseCase);

        //make the 2d array of all the possible boards
        findBoards();

        //sout all the boards in Bradford notation
//        printAllBoards();

        //find all the best moves
        findBestMoves();

        //for each board, sout the move to play
//        printBestMoves();

    }

    private void printBestMoves(){
        System.out.println("Now: printing the moves arrays");
        for(int index = 0; index < moves.size(); index++){
            String StringVOfArr = Arrays.toString(moves.get(index));
            System.out.println(StringVOfArr);
        }
    }

    private void findBestMoves(){
        movesArray = new int[allBoards.length][size];
        System.out.println("Now: finding the best move for each board");
        for(int index = 0; index < allBoards.length; index++){
            int[] curr = allBoards[index];
            int[] move = findWinning(curr);
            if(move[0] == -1){
                move = pickLosing(curr);
                loseBoards.add(curr);
////                System.out.println(Arrays.toString(curr) + " is a losing?");
            }
            int[] state = new int[curr.length+2];
            for(int i = 0; i < curr.length; i++){
                state[i] = curr[i];
            }
            state[curr.length] = move[0];
            state[curr.length+1] = move[1];
            //movesArray[index] = state;
            moves.add(state);
        }
    }

    private int[] findWinning(int[] curr){
        for(int col = 0; col < curr.length; col++){
            for(int row = 0; row < curr[col]; row++){
                int[] temp = Arrays.copyOf(curr, size);
                for(int h = col; h < size; h++){
                    temp[h] = Math.min(temp[h], row);
                }
                if(contained(loseBoards, temp)){
//                    System.out.println(Arrays.toString(curr) + " is winning by playing (" + col + "," + row + ").");
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private int[] pickLosing(int[] curr) {
        for(int col = curr.length-1; col >= 0; col--){
            for(int row = curr[col]-1; row >= 0; row--){
                return new int[]{row, col};
            }
        }
//        System.out.println("Uhh wtf is going on here? Smth in pick losing (or before) went wrong.");
        return new int[]{0,0};
    }

    private void findBoards(){
        System.out.println("Now: finding all the possible boards");
        ArrayList<int[]> listBoards = new ArrayList<>();
        int[] curr = new int[size];
        curr[0] = 1;
        while(curr[0] <= size){
            int[] temp = Arrays.copyOf(curr, size);
            listBoards.add(temp);
            curr[size-1]+=1;
            for(int c = size-1; c > 0; c--){
                if(curr[c]>curr[c-1]){
                    curr[c-1]++;
                    for(int h = c; h < size; h++){
                        curr[h] = 0;
                    }
                }
            }
        }
        int index = 0;
        allBoards = new int[listBoards.size()][size];
        for(int[] arr : listBoards){
            allBoards[index] = arr;
            index++;
        }
    }

    boolean contained(ArrayList<int[]> existing, int[] potential){
        for(int[] arr : existing){
            boolean failed = false;
            if(Arrays.equals(arr, potential)){
                return true;
            }
        }
        return false;
    }

    String getBoard(int[] board) {
        String boardString = "(";
        for (int col = 0; col < board.length; col++) {
            boardString += board[col] + ",";
        }
        boardString = boardString.substring(0, boardString.length() - 1);
        boardString += ")";
        return boardString;
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
