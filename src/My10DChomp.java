import java.util.ArrayList;
import java.util.Arrays;

public class My10DChomp {
    int size;
    int[] allBoards;
    private ArrayList<int[]> loseBoards = new ArrayList<>();
    public int[] movesArray;
    long currentTime;
    int numBoards = 184755;

    public static void main(String[] args) {
        My10DChomp print3x3 = new My10DChomp(10);
    }

    public My10DChomp(int size){
        this.size = size;
        int[] baseCase = new int[size];

        //make the 2d array of all the possible boards
        findBoards();

        //find all the best moves
            findBestMoves1DArray();

    }

    private void findBestMoves1DArray(){
        long timeTaken = System.currentTimeMillis() - currentTime;
        System.out.println("Find the boards took " + timeTaken + " ms. Finding the best move for each board...");
        movesArray = new int[numBoards*2];
        int spot = 0;
        for(int index = 0; index < numBoards; index++){
            findMove(index);
            spot+=2;
        }
        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private void findMove(int index){
//        int[] move = {-1, -1};
        for(int[] arr : loseBoards){
            boolean found; //if this is a possible move
            for(int c = 0; c < size; c++){ //is arr a possible board?
                if(arr[c]>allBoards[index*10+c]){ //it isn't bc can't add tiles
                    break;
                }
                if(arr[c] == allBoards[index*10+c]) {
                    continue;
                }
                if(arr[c]<allBoards[index*10+c]){
                    found = true;
                    for(int h = c; h < size; h++){
                        int val = Math.min(arr[c], allBoards[index*10+h]); // value at curr[h] if clicking in col c
                        if(val != arr[h]) { //this click was necessary, but doesn't lead to correct --> board not possible
                            found = false;
//                            System.out.println("Saw that this board isn't feasible");
                            break;
                        }
                    }
                    if(!found){
                        break;
                    }
                    movesArray[index*2] = arr[c];
                    movesArray[index*2+1] = c;
                    return;
                }
            }
        }
        int[] curr = Arrays.copyOfRange(allBoards, index*10, index*10+10);
        loseBoards.add(curr);
        for(int col = curr.length-1; col >= 0; col--){
            for(int row = curr[col]-1; row >= 0; row--){
                movesArray[index*2] = row;
                movesArray[index*2+1] = col;
                return;
            }
        }
    }

    private void findBoards(){
        System.out.println("Finding all the possible boards...");
        currentTime = System.currentTimeMillis();
        allBoards = new int[numBoards*size];
        int index = 0;
        int[] curr = new int[size];
        curr[0] = 1;
        while(curr[0] <= size){
            System.arraycopy(curr, 0, allBoards, index, size);
            index+=10;
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
