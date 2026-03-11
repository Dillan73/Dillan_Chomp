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
        MyNDChomp print3x3 = new MyNDChomp(3);
    }

    public MyNDChomp(int size){
        this.size = size;
        int[] baseCase = new int[size];
//        loseBoards.add(baseCase);
//        baseCase[0] = 1;
//        loseBoards.add(baseCase);

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
            //movesArray[index] = state;
            moves.add(state);
        }
        System.out.println("I'm done");
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

    private int[] optimalFindWinning(int[] curr){
//        System.out.println("Checking " + Arrays.toString(curr));
        int[] move = {-1, -1};
        for(int[] arr : loseBoards){
            boolean possible = true;
            boolean found = false; //if this is a possible move
//            System.out.println("looking at the possibility of " + Arrays.toString(arr));
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
//                        System.out.println("Val is " + val + " for the board " + Arrays.toString(curr) + " at h= " + h + ". Also, this is checking if the board " + Arrays.toString(arr) + " is possible." + "c=" + c + " and arr[c] = " + arr[c]);
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
//                        System.out.println("the selected move was (" + move[0] + "," + move[1] + ").");
                        return move;
                    }

                }
            }
            //no move to reach, move on to next board
        }
//        System.out.println("didn't find anything");
        return move;
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
}
