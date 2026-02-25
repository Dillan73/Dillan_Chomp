public class MyChomp {
    int r0 = 0;
    int r1 = 0;
    int r2 = 0;
    int size;
    public static void main(String[] args) {
        MyChomp print3x3 = new MyChomp(3);
    }

    //print all the 3x3 boards w/o souting 19 times

    //have  3 values to show rows starting at 0
        //0<=r0<=r1<=r2<=3

    public MyChomp(int size){
        this.size = size;
        //sout all the boards
        String all3x3 = getAll3x3Boards();
        System.out.println(all3x3);
    }

    String getAll3x3Boards(){
        String allBoards = "This is printing all the possible boards in a 3x3 board in the form (# tiles left in the bottom row, # tiles left in the middle row, # tiles left in the top row): ";
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
}
