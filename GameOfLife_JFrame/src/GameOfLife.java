import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame{
    int rows = 50;
    int columns = 50;
    boolean board[][];
    JButton cells[][];

    public GameOfLife(){
        board = new boolean[rows][columns];
        cells = new JButton[rows][columns];
        setSize(600,600);

        setLayout(new GridLayout(rows,columns));

        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                board[i][j] = Math.random() < 0.3;
                JButton temp = new JButton();
                if(board[i][j]){
                    temp.setBackground(Color.GREEN);
                } else {
                    temp.setBackground(Color.WHITE);
                }
                add(temp);
                cells[i][j] = temp;
            }
        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean[][] futureGeneration = new boolean[rows][columns];
                for(int i = 0; i<rows; i++){
                    for(int j = 0; j<columns; j++){
                        int neighbours = countNeighbours(i,j);
                        if(board[i][j]){
                            if (neighbours<2)
                                futureGeneration[i][j] = false;
                            else if (neighbours==2 || neighbours==3)
                                futureGeneration[i][j] = true;
                            else if (neighbours>3)
                                futureGeneration[i][j] = false;
                        }
                        else{
                            if (neighbours == 3)
                                futureGeneration[i][j] = true;
                        }
                    }
                }

                board = futureGeneration;
                for (int i=0; i<rows; i++){
                    for (int j=0; j<columns; j++){
                        if(board[i][j]){
                            cells[i][j].setBackground(Color.GREEN);
                        } else {
                            cells[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            }
        });
        timer.start();

    }

    int countNeighbours(int x, int y){
        int count = 0;
        for(int i = x-1; i<=x+1; i++){
            for(int j = y-1; j<=y+1; j++){
                try{
                    if (board[i][j])
                        count++;
                } catch (Exception e){

                }
            }
        }
        if (board[x][y]) {
            count--;
        }
        return count;
    }

    public static void main(String[] args) {
        new GameOfLife();
    }


}