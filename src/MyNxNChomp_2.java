import java.util.ArrayList;
import java.util.Arrays;

public class MyNxNChomp_2 { // old version that brought 3x3 to NxN
    int size;
    int[][] allBoards;
    private ArrayList<int[]> loseBoards = new ArrayList<>();
    private ArrayList<int[]> winBoards = new ArrayList<>();
    public int[] movesArray;
    public ArrayList<int[]> moves = new ArrayList<>();
    long currentTime;

    public static void main(String[] args) {
        MyNxNChomp_2 print3x3 = new MyNxNChomp_2(10);
    }

    public MyNxNChomp_2(int size){
        this.size = size;
        int[] baseCase = new int[size];

        //make the 2d array of all the possible boards
        findBoards();

        //sout all the boards in Bradford notation
//        printAllBoards();

        //find all the best moves
        findBestMoves();
//        findBestMoves1DArray();

        //for each board, sout the move to play
//        printBestMoves();

        System.out.println(loseBoards.size());

    }

    private void printBestMoves(){
        System.out.println("Now: printing the moves arrays" + System.currentTimeMillis());
        currentTime = System.currentTimeMillis();
        for(int index = 0; index < moves.size(); index++){
            String StringVOfArr = Arrays.toString(moves.get(index));
            System.out.println(StringVOfArr);
        }
    }

    private void findBestMoves(){
        long timeTaken = System.currentTimeMillis() - currentTime;
        System.out.println("Find the boards took " + timeTaken + " ms. Finding the best move for each board...");
        for(int index = 0; index < allBoards.length; index++){
            int[] curr = allBoards[index];
            int[] move = optimalFindWinning(curr);
//            int[] move = findWinning(curr);
            if(move[0] == -1){
                move = pickLosing(curr);
                loseBoards.add(curr);
//                System.out.println(Arrays.toString(curr) + " is a losing?");
            }
            int[] state = new int[curr.length+2];
            for(int i = 0; i < curr.length; i++){
                state[i] = curr[i];
            }
            state[curr.length] = move[0];
            state[curr.length+1] = move[1];
            moves.add(state);
        }
        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private void findBestMoves1DArray(){
        movesArray = new int[allBoards.length*(size+2)];
        int spot = 0;
        long timeTaken = System.currentTimeMillis() - currentTime;
        System.out.println("Find the boards took " + timeTaken + " ms. Finding the best move for each board...");
        for(int index = 0; index < allBoards.length; index++){
            int[] curr = allBoards[index];
            int[] move = optimalFindWinning(curr);
//            int[] move = findWinning(curr);
            if(move[0] == -1){
                move = pickLosing(curr);
                loseBoards.add(curr);
//                System.out.println(Arrays.toString(curr) + " is a losing?");
            }
            System.arraycopy(curr, 0, movesArray, spot, size);
            System.arraycopy(move, 0, movesArray, spot+size, 2);
            spot+=size+2;
        }
        timeTaken = System.currentTimeMillis()-currentTime;
        System.out.println("I'm done. My process took " + timeTaken + " ms.");
    }

    private int[] optimalFindWinning(int[] curr){
        int[] move = {-1, -1};
        for(int[] arr : loseBoards){
            boolean possible = true;
            boolean found = false; //if this is a possible move
            for(int c = 0; c < size; c++){ //is arr a possible board?
                if(arr[c]>curr[c]){ //it isn't bc can't add tiles
                    possible = false;
                    break;
                }
                if(arr[c] == curr[c]) {
                    continue;
                }
                if(arr[c]<curr[c]){
                    found = true;
                    for(int h = c; h < size; h++){
                        int val = Math.min(arr[c], curr[h]); // value at curr[h] if clicking in col c
                        if(val != arr[h]) { //this click was necessary, but doesn't lead to correct --> board not possible
                            found = false;
//                            System.out.println("Saw that this board isn't feasible");
                            break;
                        }
                    }
                    if(!found){
                        break;
                    }
                    if(found) {
                        move[0] = arr[c];
                        move[1] = c;
                        return move;
                    }

                }
            }
        }
        return move;
    }

    private int[] pickLosing(int[] curr) {
        for(int col = curr.length-1; col >= 0; col--){
            for(int row = curr[col]-1; row >= 0; row--){
                return new int[]{row, col};
            }
        }
        return new int[]{0,0};
    }

    private void findBoards(){
        System.out.println("Finding all the possible boards...");
        currentTime = System.currentTimeMillis();
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
                }else{
                    break;
                }
            }
        }
        System.out.println(System.currentTimeMillis()-currentTime);
        int index = 0;
        allBoards = new int[listBoards.size()][size];
        System.out.println(listBoards.size());
        for(int[] arr : listBoards){
            allBoards[index] = arr;
            index++;
        }
        System.out.println(System.currentTimeMillis()-currentTime);
    }

    boolean contained(ArrayList<int[]> existing, int[] potential){
        for(int[] arr : existing){
            if(Arrays.equals(arr, potential)){
                return true;
            }
        }
        return false;
    }
}
