import static org.junit.Assert.*;
import org.junit.*;

public class TicTacToeModelTest {

    @Test
    public void testInitialStatus() {
        TicTacToeModel m = new TicTacToeModel();
        assertEquals(TicTacToeModel.Status.UNDECIDED, m.getStatus());
    }

    @Test
    public void testXWon() {
        TicTacToeModel m = new TicTacToeModel();
        m.play(0,0);
        m.play(2,2);
        m.play(0,1);
        m.play(1,2);
        m.play(0,2);
        assertEquals(TicTacToeModel.Status.X_WON, m.getStatus());
    }

    @Test
    public void testOWon() {
        TicTacToeModel m = new TicTacToeModel();
        m.play(0,0);
        m.play(2,2);
        m.play(0,1);
        m.play(1,2);
        m.play(1,1);
        m.play(0,2);
        assertEquals(TicTacToeModel.Status.O_WON, m.getStatus());
    }

    @Test
    public void testTie() {
        TicTacToeModel m = new TicTacToeModel();
        m.play(0,0);
        m.play(2,2);
        m.play(0,1);
        m.play(0,2);
        m.play(1,2);
        m.play(1,1);
        m.play(2,0);
        m.play(1,0);
        m.play(2,1);
        assertEquals(TicTacToeModel.Status.TIE, m.getStatus());
    }



}