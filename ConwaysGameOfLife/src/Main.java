public class Main {

    public static void main(String[] args) {
        int neighbours = 0;
        int count = 5;

        /* Set initial board state*/
        int[][] board = {
                { 0,0,0,0,0,0,0,0,0,0},
                { 0,0,1,0,0,0,0,0,0,0},
                { 0,0,1,0,0,0,0,0,0,0},
                { 0,0,1,0,0,0,0,0,0,0},
                { 0,0,0,0,0,0,0,0,0,0},
                { 0,0,0,0,0,0,0,0,0,0},
        };
        int[][] boardAux = new int [6][10];

        for (int i=0; i< board.length; i++){
            for(int j=0; j<board[i].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
            System.arraycopy(board[i], 0, boardAux[i],0, board[i].length);
        }

        while(count >0){

            System.out.println("----------");
            for (int x=0; x< board.length; x++){
                for(int y=0; y<board[x].length; y++){
                    neighbours=0;

                    /* Count neighbours */
                    int valuesX [] = {x-1, x, x+1};
                    int valuesY [] = {y-1, y, y+1};
                    for (int vx: valuesX){
                        for (int vy: valuesY) {
                            if(vx>-1 && vx<board.length && vy>-1 && vy<board[x].length) {
                                neighbours += board[vx][vy];
                            }
                        }
                    }
                    neighbours -= board[x][y];

                    /* Apply rules*/
                    if(neighbours>0 || board[x][y]==1){
                        if(neighbours<2 && board[x][y]==1) boardAux[x][y]=0;
                        else if(neighbours==3 && board[x][y]==0) boardAux[x][y]=1;
                        else if(neighbours>3 && board[x][y]==1) boardAux[x][y]=0;
                    }
                    System.out.print(boardAux[x][y]);
                }
                System.out.println();
            }

            for(int x=0; x<board.length; x++) {
                System.arraycopy(boardAux[x], 0, board[x],0, board[x].length);
            }

            count--;
        }
    }
}
