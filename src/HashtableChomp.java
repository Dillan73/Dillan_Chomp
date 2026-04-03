import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class HashtableChomp {
    int size;
    ArrayList<int[]> allBoards;
    private Hashtable<String, String> losingTable = new Hashtable<>();;
    long currentTime;
    public ArrayList<int[]> moves = new ArrayList<>();

    public static void main(String[] args) {
        HashtableChomp print3x3 = new HashtableChomp(10);
    }

    public HashtableChomp(int size){
        this.size = size;

        //make the 2d array of all the possible boards
            findBoards();

        //find all the best moves
            findBestMoves1DArray();
    }

    private void findBestMoves1DArray(){
        long timeTaken = System.currentTimeMillis() - currentTime;
        System.out.println("Find the boards took " + timeTaken + " ms. Finding the best move for each board...");

        for(int[] arr : allBoards){
            findMove(arr);
        }

        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private void findMove(int[] arr){
        String concat = "";
        String toString = Arrays.toString(arr);
        int[] forMoves = new int[size+2];
        System.arraycopy(arr, 0, forMoves, 0, size);

        for(int c = 0; c < size; c++){
            for(int r = 0; r < arr[c]; r++){
                int[] postMove = Arrays.copyOf(arr, size);
                for(int curr = c; curr < size; curr++){
                    postMove[curr] = Math.min(postMove[curr], r);
                }
                String move = Arrays.toString(postMove);
                if(losingTable.get(move) != null){
                    //put the board and move in move table as a pair
                    forMoves[size] = r;
                    forMoves[size+1] = c;
                    moves.add(forMoves);
                    return;
                }
            }
        }

        losingTable.put(concat, "losing");
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
        while(curr[0] <= size){
            int[] temp = Arrays.copyOf(curr, size);
            allBoards.add(temp);
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
