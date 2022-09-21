import java.util.Scanner;

public class GameOfLife {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws InterruptedException {
        clear();

        // Choose board dimensions
        Scanner s = new Scanner(System.in);
        System.out.println("Informe o numero de linhas: ");
        int rows = s.nextInt();
        System.out.println("Informe o numero de colunas: ");
        int columns = s.nextInt();
        int[][] board;

        // Choose board visualization
        int visualizationOption = 0;
        while (visualizationOption!=1 && visualizationOption!=2 ){
            System.out.println("Informe como deseja visualizar a matriz:\n" +
                    "1 - Vizualicao em numeros (0 e 1).\n"+
                    "2 - Quadros coloridos.");
            visualizationOption = s.nextInt();
        }

        // Choose initial board values
        int initialStateOption = 0;
        while (initialStateOption!=1 && initialStateOption!=2 ){
            System.out.println("Informe como deseja inicializar a matriz:\n" +
                    "1 - Matriz aleatoria.\n"+
                    "2 - Escolher os valores.");
            initialStateOption = s.nextInt();
        }
        if(initialStateOption ==1){
            board = randomBoard(rows,columns);
        }
        else {
            board = setBoard(rows,columns);
        }

        while (true) {
            clear();
            printBoard(board, visualizationOption);
            sleep();
            board = nextGeneration(board);
        }
    }
    private static void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }

    private static void printBoard(int[][] board, int visualization) {
        if (visualization == 2){
            System.out.print(ANSI_WHITE_BACKGROUND);
            for (int i=0; i< board.length; i++){
                for(int j=0; j<board[i].length; j++){
                    if(board[i][j]==1){
                        System.out.print(ANSI_GREEN_BACKGROUND
                                + "   "
                                + ANSI_RESET
                                + " ");
                    } else {
                        System.out.print(ANSI_WHITE_BACKGROUND
                                + "   "
                                + ANSI_RESET
                                + " ");
                    }
                }
                System.out.println();
            }
        } else {
            for (int i = 0; i< board.length;i++){
                for (int j = 0; j < board[i].length; j++)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }
        }
    }
    private static void clear() {
        System.out.print("\033\143");
    }

    private static int[][] randomBoard(int rows, int columns){
        int[][] board = new int [rows][columns];
        for (int i = 0; i< board.length;i++)
            for (int j=0; j<board[i].length; j++)
                board[i][j] = Math.random() > 0.5? 1 : 0 ;
        return board;
    }

    private static  int [][] setBoard(int rows, int columns){
        int[][] initialBoard = new int[rows][columns];
        clear();
        System.out.println("Altere a matriz abaixo para definir os valores iniciais");
        printBoard(initialBoard, 1);
        System.out.println("---------");
        Scanner s = new Scanner(System.in);
        for (int i=0; i<rows;i++){
            String[] line = s.nextLine().split(" ");
            for (int j=0; j<line.length;j++)
                initialBoard[i][j] = Integer.parseInt(line[j]);
        }
        return initialBoard;
    }

    private static int[][] nextGeneration(int[][] board){
        int[][] futureGeneration = new int[board.length][board[0].length];
        int neighbours;

        for (int x=0; x< board.length; x++){
            for(int y=0; y<board[x].length; y++){
                neighbours = countNeighbours(board, x, y);
                futureGeneration[x][y] = cellNextGeneration(neighbours,board[x][y]);
            }
        }
        return futureGeneration;
    }

    private static int countNeighbours(int[][]board, int x, int y){
        int neighbours=0;
        for (int row = x-1; row<=x+1; row++){
            for (int column = y-1; column<=y+1; column++){
                if(row>-1 && row<board.length && column>-1 && column<board[x].length) {
                    neighbours += board[row][column];
                }
            }
        }
        neighbours -= board[x][y];
        return neighbours;
    }

    private static int cellNextGeneration(int neighbours, int cellState){
        if(neighbours>0 || cellState==1){
            if(neighbours<2 && cellState==1) return 0;
            else if(neighbours==3 && cellState==0) return 1;
            else if(neighbours>3 && cellState==1) return 0;
        }
        return cellState;
    }
}
