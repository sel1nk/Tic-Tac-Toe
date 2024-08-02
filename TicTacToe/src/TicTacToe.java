import java.awt.*;
import  java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardWidth = 1500;
    int boardHeight = 1300;
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton [][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns = 0;



    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); //center the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 100));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);
        /*
        //for reply:
        JButton reply = new JButton("reply");
        reply.setBounds(100,100,100,100);
        reply.setLayout(null);
        //JPanel buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BorderLayout());
        //buttonPanel.add(reply);

        reply.setBounds(60, 400, 220, 30);

        // JPanel bounds
        //buttonPanel.setBounds(800, 800, 200, 100);

        // Adding to JFrame
        frame.add(reply, BorderLayout.EAST);
        reply.setBackground(Color.darkGray);
        reply.setForeground(Color.yellow);
        reply.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        reply.setFocusable(false);
                                        if (gameOver) {

                                            return;
                                        }


                                    }
                                });
        //----------------------------------------------

         */

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.yellow);
                tile.setFont((new Font("Arial", Font.BOLD, 200)));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (gameOver){
                            textLabel.setText("Game over");
                            return;
                        }

                        JButton tile = (JButton) actionEvent.getSource();

                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO: playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
    }
    public void checkWinner(){
        //horizontal
        checkHorizontal();
        //vertical
        checkVertical();
        //diagonal
        checkDiagonal();

        if(turns == 9){
            //checkWinner();
            if(gameOver){
                textLabel.setText("Tie!");
                for (int row = 0; row < 3; row++){
                    for(int col = 0; col < 3; col++){
                        setTie(board[row][col]);
                    }
                }
                gameOver = true;
            }else{

                gameOver = true;
                return;
            }

        }
    }
    public void checkHorizontal(){
        for(int r = 0; r < 3; r++){
            if(board[r][0].getText().equals("")){
                continue;
            }
            else if(board[r][0].getText().equals(board[r][1].getText()) &&
                    board[r][1].getText().equals(board[r][2].getText())){
                for (int i = 0; i < 3; i++){
                    setWinner(board[r][i]);
                }
                gameOver = true;
            }
        }
    }
    public void checkVertical(){
        for (int c = 0; c < 3; c++){
            if(board[0][c].getText().equals("")){
                continue;
            } else if (board[0][c].getText().equals(board[1][c].getText()) &&
                    board[1][c].getText().equals(board[2][c].getText())) {
                for (int i = 0; i < 3; i++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
            }
        }
    }
    public void checkDiagonal(){
        //diagonal left
        if(board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "")
        {
            for(int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
        }else{
            gameOver = false;
        }
        //diagonal right
        if(board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText() &&
                board[0][2].getText() != "")
        {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;

        }else{
            gameOver = false;
        }
    }
    public void setWinner(JButton tile){

        tile.setForeground(Color.darkGray);
        tile.setBackground(Color.green);
        textLabel.setText("Winner: " + currentPlayer);
    }
    public void setTie(JButton tile){
        tile.setBackground(Color.orange);
        tile.setForeground(Color.darkGray);
        textLabel.setText(("Tie!"));
    }
}
