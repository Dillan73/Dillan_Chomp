import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class HashtableChomp_CURRENT {
    int size; //i made it work for nxn instead of 10x10, but only use 10x10
    ArrayList<int[]> allBoards; //this is where I store all the possible boards
    private Hashtable<String, String> losingTable = new Hashtable<>();; // the losing boards. Uses a hashtable for efficiency. Done as strings bc .equals doesnt work for arrays.
    long currentTime; // this is to help me see how long different parts of my project were taking
    //takes ~0.5 seconds to make and no noticeable lag to play moves
    public ArrayList<int[]> moves = new ArrayList<>(); // this stores the best move for a specific board

    public static void main(String[] args) {
        HashtableChomp_CURRENT doCoolThings = new HashtableChomp_CURRENT(10);
    }

    public HashtableChomp_CURRENT(int size){
        this.size = size;

        //find the possible boards and store in allBoards
            findBoards();

        //find all the best moves and store in moves
            findBestMoves();

        System.out.println(losingTable.size());
    }

    private void findBestMoves(){
        long timeTaken = System.currentTimeMillis() - currentTime;
        System.out.println("Find the boards took " + timeTaken + " ms. Finding the best move for each board...");

        for(int[] arr : allBoards){
            findMove(arr); // goes thru each board in order it was made
        }

        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private void findMove(int[] arr){
        String concat = "";
        concat = Arrays.toString(arr);
        int[] forMoves = new int[size+2]; //make the array that will be filled in w the board and best move
        System.arraycopy(arr, 0, forMoves, 0, size); //put the board in the forMoves array

        for(int c = 0; c < size; c++){ //goes thru each column
            for(int r = 0; r < arr[c]; r++){ // goes thru each possible move in the column
                int[] postMove = Arrays.copyOf(arr, size);//the board if this move was played
                for(int curr = c; curr < size; curr++){
                    postMove[curr] = Math.min(postMove[curr], r);
                } //makes the board if this move was played
                String move = Arrays.toString(postMove); //converts to a string

                //if this move brings to a losing board, we will store the move as the move to play
                if(losingTable.get(move) != null){
                    forMoves[size] = r;
                    forMoves[size+1] = c;
                    moves.add(forMoves);
                    return;
                }
            }
        }

        //if none of the moves were losing boards, add this board as a losing board
        losingTable.put(concat, "losing");

        //pick a move to play that keeps a lot of chips
        for(int col = size-1; col >= 0; col--){
            for(int row = arr[col]-1; row >= 0; row--){
                forMoves[size] = row;
                forMoves[size+1] = col;
                moves.add(forMoves);
                return;
            }
        }
    }

    private void findBoards(){
        System.out.println("Finding all the possible boards...");
        currentTime = System.currentTimeMillis();
        allBoards = new ArrayList<>();
        int[] curr = new int[size];
        curr[0] = 1;
        while(curr[0] <= size){ //if curr[0] > size, it would have more chips than possible
            int[] temp = Arrays.copyOf(curr, size);
            allBoards.add(temp); //add the current board

            //add a chip while making sure no column has more chips than a column to the left
            curr[size-1]+=1;
            for(int c = size-1; c > 0; c--){
                if(curr[c]>curr[c-1]){
                    curr[c-1]++;
                    for(int h = c; h < size; h++){
                        curr[h] = 0;
                    }
                }else{
                    break;
                }
            }
        }
    }
}
