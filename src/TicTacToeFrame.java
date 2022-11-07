import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame implements TicTacToeView {

    private JButton[][] buttons;

    public TicTacToeFrame(){
        super("Tic Tac Toe!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        TicTacToeModel model = new TicTacToeModel();

        model.addTicTacToeView(this);


        buttons = new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];

        //TicTacToeController tttc = new TicTacToeController(model);

        for (int i = 0; i < TicTacToeModel.SIZE; i++) {
            for (int j = 0; j < TicTacToeModel.SIZE; j++) {
                JButton b = new JButton(" ");
               // b.setActionCommand(i + " " + j);
                buttons[i][j] = b;
                int x = i;
                int y = j;
                b.addActionListener(e -> model.play(x,y));
                this.add(b);
            }
        }
        this.setSize(300,300);
        this.setVisible(true);
    }

    @Override
    public void update(TicTacToeEvent e) {
        String label = e.isTurn()? "X": "O";
        buttons[e.getX()][e.getY()].setText(label);

        if(e.getStatus() == TicTacToeModel.Status.X_WON || e.getStatus() == TicTacToeModel.Status.O_WON){

            for(int i = 0; i < TicTacToeModel.SIZE; i++){
                for(int j = 0; j < TicTacToeModel.SIZE; j++){
                    buttons[i][j].setEnabled(false);
                }
            }
            JOptionPane.showMessageDialog(this, label + " WINS THE GAME!");
        }
        else if(e.getStatus() == TicTacToeModel.Status.TIE){

            for(int i = 0; i < TicTacToeModel.SIZE; i++){
                for(int j = 0; j < TicTacToeModel.SIZE; j++){
                    buttons[i][j].setEnabled(false);
                }
            }
            JOptionPane.showMessageDialog(this, " GAME IS TIED!");
        }
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}

