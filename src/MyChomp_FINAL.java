import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class MyChomp_FINAL { //my chomp solver thingy

    int size; //this allows me to change the size it does this for (which helped me to test/debug)
    ArrayList<int[]> allBoards; //where i store all the possible boards
    Hashtable<String, String> losingTable = new Hashtable<>();; // where i store the losing boards.
        // Used a hashtable bc ik its more efficient at retrieval, which is what this is mainly used for
        // Used Strings bc arrays don't work with .equals (why? why java? why?)

    long currentTime; // used this to test how long my program/ sections of it took
        //Varies a decent bit for some reason, but rn its doing it at ~0.35 seconds

    public int[] bestMoves; //this now stores the best moves as an int in an array
        //similar to 10d version, but has nothing hardcoded to do it

    boolean optimize = false;

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
        System.out.println("Finished in " + timeTaken + " ms.");
        System.out.println("Now determining the best move for each board...");
        //above this is time keeping related

        bestMoves = new int[allBoards.size()]; //this initialized my bestMoves array. Its given allBoards.size so there's an index (move) for each board

        for(int index = 0; index < allBoards.size(); index++){ //goes thru each board
            findMove(allBoards.get(index), index); // finds the best move and puts in it bestMoves at the index
        }

        //below this is js shenanigans to print the time it took
        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("My process completed in a total of... *Drumroll please* ...");
        double timeInSeconds = ((double) timeTaken);
        timeInSeconds = (double) Math.round(timeInSeconds);
        timeInSeconds /= 1000;
        System.out.println("Just " + timeInSeconds + " seconds!");
    }

    private void findMove(int[] arr, int index){
        String concat = Arrays.toString(arr); //used for the hashtable

        int move = 0; //this is what gets put into bestMoves (once its given the right value)

        for(int c = 0; c < size; c++){ //the nested loop loops thru every chip (which corresponds to every move)
            for(int r = 0; r < arr[c]; r++){
                //this makes a string of what the board would be if the move was played
                int[] postMove = Arrays.copyOf(arr, size);
                for(int curr = c; curr < size; curr++){
                    postMove[curr] = Math.min(postMove[curr], r);
                }
                String moveString = Arrays.toString(postMove);

                //if the board made by this move has been identified as a losing board, i pick it as the move to play
                if(losingTable.get(moveString) != null){
                    move = r*100+c;
                    bestMoves[index] = move;
                    return; //no need to continue
                }
            }
        }

        //this runs if no move led to a losing board, meaning this board is a losing board

        losingTable.put(concat, "losing"); //adds the board to the "losing boards" hashtable

        //now, i pick a move that keeps the board "complicated"

        //this loop tries to find a move that removes 1 chip and doesn't finish clearing a row or column
        for (int col = 1; col < size; col++) { //can skip 0 bc that clears a row
            if(arr[col] <= 1){ // if its 0, i can't play, and if its 1 it clears a column
                break;
                //we can break bc the # of chips can't increase in a column further right
            }
            if(col != size-1 && arr[col]==arr[col+1]){ // avoids clearing this and more chips to the right
                continue; // only a continue bc this can change
            }


            move = 100*(arr[col]-1)+col;
            if(bestMoves[index] == 0){
                bestMoves[index] = move; //sets this move as the move to play
            }
            if(optimize){ //if i want to optimize the speed, i can js be done here
                return;
            }

            if(Math.random()<0.35){
                bestMoves[index] = move;
            }
            //if im willing to sac a few ms, the above will make it pick one of the "equally" bad moves, instead of the same one:
                //i think playing very similar moves whenever its losing tells the player they can win
                //i feel like that motivation and learning should be avoided
                    //as would be expected from any code, we're taking a long-term psychological angle at this :)
        }
        if(bestMoves[index] != 0){ //if ive picked a move under those constraints to play, im done
            return;
        }

        //otherwise i loosen the constraints to pick a move that removes a chip, but also clears a row/col:
            //there must be a "top-right" chip that fulfills this, so no need to loosen from here

        for (int col = 0; col < size; col++) { //now doesn't skip the left most column
            if (arr[col] == 0) { //now allows a move on the bottom row.
                //the rest of this loop is the same as the other loop
                break;
            }
            if (col != size - 1 && arr[col + 1] == arr[col]) {
                continue;
            }

            move = 100*(arr[col]-1)+col;
            if(bestMoves[index] == 0){
                bestMoves[index] = move; //sets this move as the move to play
            }
            if(optimize){ //if i want to optimize the speed, i can js be done here
                return;
            }

            if(Math.random()<0.35){
                bestMoves[index] = move;
            }
        }
    }

    private void findBoards(){
        System.out.println("Finding all the possible boards...");
        currentTime = System.currentTimeMillis();
        allBoards = new ArrayList<>();
        //above is setup+time-keeping stuff

        //this makes the array version of the board with 1 chip
        int[] curr = new int[size];
        curr[0] = 1;

        //after this i progressively add chips to the right side of the board.

        while(curr[0] <= size){
            int[] temp = Arrays.copyOf(curr, size);
            allBoards.add(temp); //add the current board

            //adds a chip to the right most column
            curr[size-1]+=1;

            //if that made it have more than a column to the left, the left column is incremented
                //this continues until reaching the first column (cuz it has no column to the left)
            for(int c = size-1; c > 0; c--){
                if(curr[c]>curr[c-1]){
                    curr[c-1]++;
                    for(int h = c; h < size; h++){
                        curr[h] = 0;
                    }
                }else{
                    break; //once smth doesn't need to increment, nothing else will
                }
            }
        }
    }
}
