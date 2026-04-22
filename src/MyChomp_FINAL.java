import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class MyChomp_FINAL { //my current version

    int size; //i used this to be able to test more things (as a way to help debug and test ideas on simpler scenarios)
    ArrayList<int[]> allBoards; //all the possible size by size boards
    private Hashtable<String, String> losingTable = new Hashtable<>();; // the losing boards.
        // I used a hashtable because I know its more efficient at retrieval. I switched to strings from arrays cuz I looked thru the hashtable docs and saw it uses .equals, which doesn't work for arrays
    long currentTime; // used this to test how long specific parts/methods took
        //takes ~0.6 seconds to make at the start and doesn't noticeably lag when playing a move

    public int[] bestMoves; //this now stores the best moves as an int in an array
        //similar to 10d version, but has nothing hardcoded to do it

    public static void main(String[] args) {
        MyChomp_FINAL doCoolThings = new MyChomp_FINAL(10);
    }

    public MyChomp_FINAL(int size){
        this.size = size;

        //this creates allBoards with all the possible boards
            findBoards();

        //this creates moves (aka finds the best move for each board)
            findBestMoves();

        //System.out.println(losingTable.size());
    }

    private void findBestMoves(){
        long timeTaken = System.currentTimeMillis() - currentTime;
        System.out.println("Find the boards took " + timeTaken + " ms. Finding the best move for each board...");

        bestMoves = new int[allBoards.size()];

        for(int index = 0; index < allBoards.size(); index++){
            findMove(allBoards.get(index), index); // goes thru each board in order it was made (this way its always already checked if a possible move is losing
        }

        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private void findMove(int[] arr, int index){
        String concat = Arrays.toString(arr);

        int move = 0;

        for(int c = 0; c < size; c++){ //the nested loop checks every chip (aka every possible move)
            for(int r = 0; r < arr[c]; r++){
                //for each move, it finds what the board would be if it was played
                int[] postMove = Arrays.copyOf(arr, size);
                for(int curr = c; curr < size; curr++){
                    postMove[curr] = Math.min(postMove[curr], r);
                } //makes the board if this move was played
                String moveString = Arrays.toString(postMove); //converts the board of the move as a string

                //if ive already identified the board from this move as a losing board, this is the move i want to play
                if(losingTable.get(moveString) != null){
                    move = r*100+c;
                    bestMoves[index] = move;
                    return; //since ive found a winning move, theres no reason to do anything more
                }
            }
        }
        //this part runs if none of the moves were losing boards --> this board is a losing board

        //makes the board as a string
        losingTable.put(concat, "losing");

        //find a move to play that keeps a lot of chips

        for (int col = 0; col < size; col++) { //try to find a move that removes 1 chip and no edge chip (row/col)
            if(arr[col] <= 1){
                break; //bc this wont change in the future
            }
            if(col != size-1 && arr[col+1]==arr[col]){
                continue; // this can change
            }

            move = 100*(arr[col]-1)+col;
            if(bestMoves[index] == 0){
                bestMoves[index] = move;
            }else{
                if(Math.random()<0.35){
                    bestMoves[index] = move;
                }
            }

        }
        if(bestMoves[index] != 0){
            return;
        }

        for (int col = 0; col < size; col++) {
            if (arr[col] == 0) { //this deems a move that removes chips above it as unacceptable
                break; //bc this wont change in the future
            }
            if (col != size - 1 && arr[col + 1] == arr[col]) { //this deems a move that removes chips to the right as unacceptable
                continue; // this can change
            }

            move = 100*(arr[col]-1)+col;
            if (bestMoves[index] == 0) {
                bestMoves[index] = move;
            } else {
                if (Math.random() < 0.35) {
                    bestMoves[index] = move;
                }
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
