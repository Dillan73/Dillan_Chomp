import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class My2HTChomp_HT2 {
    int size; //i used this to be able to test more things (as a way to help debug and test ideas on simpler scenarios)
    ArrayList<int[]> allBoards; //all the possible size by size boards
    private Hashtable<String, String> losingTable = new Hashtable<>();; // the losing boards.
        // I used a hashtable because I know its more efficient at retrieval. I switched to strings from arrays cuz I looked thru the hashtable docs and saw it uses .equals, which doesn't work for arrays
    long currentTime; // used this to test how long specific parts/methods took
        //takes ~0.6 seconds to make at the start and doesn't noticeably lag when playing a move
    //public ArrayList<int[]> moves = new ArrayList<>(); // this stores the best move for a specific board

    public Hashtable<String, Integer> bestMoves = new Hashtable<>(); // new way of storing moves

    public static void main(String[] args) {
        My2HTChomp_HT2 doCoolThings = new My2HTChomp_HT2(10);
    }

    public My2HTChomp_HT2(int size){
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

        for(int[] arr : allBoards){
            findMove(arr); // goes thru each board in order it was made (this way its always already checked if a possible move is losing
        }

        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private void findMove(int[] arr){
        String concat = ""; //this is how i store the array to use in the hashtables
        concat = Arrays.toString(arr);
        long timeTester = System.currentTimeMillis();

        for(int c = 0; c < size; c++){ //the nested loop checks every chip (aka every possible move)
            for(int r = 0; r < arr[c]; r++){
                //for each move, it finds what the board would be if it was played
                int[] postMove = Arrays.copyOf(arr, size);
                for(int curr = c; curr < size; curr++){
                    postMove[curr] = Math.min(postMove[curr], r);
                } //makes the board if this move was played
                String move = Arrays.toString(postMove); //converts the board of the move as a string

                //if ive already identified the board from this move as a losing board, this is the move i want to play
                if(losingTable.get(move) != null){
                    Integer bestMove = 100*r+c;

                    bestMoves.put(concat, bestMove);
                    return; //since ive found a winning move, there's no reason to do anything more
                }
            }
        }

        //this part runs if none of the moves were losing boards --> this board is a losing board
            //i want to play a "random" move, but keep the board complex (remove as few chips as possible (1))

        ArrayList<Integer> awesomes = new ArrayList<>();
        ArrayList<Integer> acceptables = new ArrayList<>();
        for (int col = 0; col < size; col++) {
            if(arr[col] == 0){ //this deems a move that removes chips above it as unacceptable
                break; //bc this wont change in the future
            }
            if(col != size-1 && arr[col+1]==arr[col]){ //this deems a move that removes chips to the right as unacceptable
                continue; // this can change
            }
            Integer move = 100*(arr[col]-1)+col;

            if(arr[col] == 0 || col == 0){ //this move is acceptable
                acceptables.add(move);
            }else{ // but if it doesn't remove a row/col, its awesome
                awesomes.add(move);
            }
        }
        if(awesomes.isEmpty()){ //if there aren't any awesome chips, ill pick an acceptable one (always acceptable chips)
            awesomes = acceptables;
        }

//        System.out.println(concat + awesomes);
        int index = (int) (awesomes.size()*Math.random());
            //picks a random from 0 to awesomes.size-1

        bestMoves.put(concat, awesomes.get(index)); //puts that move as the move to play
//        System.out.println(System.currentTimeMillis() - timeTester);
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
