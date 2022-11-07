import java.util.*;

public class TicTacToeModel {


    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

    private char[][] grid;
    private boolean turn;
    private Status status;
    private int emptySquares;

    private List<TicTacToeView> views;

    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;
        views = new ArrayList<>();

    }

    public void addTicTacToeView(TicTacToeView v){
        views.add(v);
    }

    private void changeTurn() {
        turn = !turn;
    }

    public Status getStatus() {return status;}

    private void updateStatus(int x, int y) {
        emptySquares--; //decrement after each play

        /*UNDECIDED GAME*/
        //For a possible win at least 5 plays should have occurred;
        if (emptySquares > 4){
            status = Status.UNDECIDED;
        }

        /*WIN GAME*/
        //Win by a vertical play (3 plays a column)
        if (grid[0][y] == grid[1][y] && grid[0][y] == grid[2][y]){
            status = turn? Status.X_WON:Status.O_WON;
        }

        //Win by a horizontal play (3 plays a row)
        if (grid[x][0] == grid[x][1] && grid[x][0] == grid[x][2]){
            status = turn? Status.X_WON:Status.O_WON;
        }

        //Win by a diagonal play (3 plays a diagonal)
        if(x == y) {
            if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
                status = turn ? Status.X_WON : Status.O_WON;
            }
        }

        //Win by a diagonal play (3 plays opposite diagonal)
        if(x == y) {
            if (grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
                status = turn ? Status.X_WON : Status.O_WON;
            }
        }

        /*TIE GAME*/
        if (emptySquares == 0){
            status = Status.TIE;
        }

        status = Status.UNDECIDED;
    }

    public boolean getTurn() {return turn;}

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O';
        updateStatus(x,y);

        for(TicTacToeView v: views){
            v.update(new TicTacToeEvent(this, x, y, turn, status));
        }
        changeTurn();
    }
}
