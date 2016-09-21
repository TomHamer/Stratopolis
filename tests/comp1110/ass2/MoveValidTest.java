package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by calum on 9/22/2016.
 */
public class MoveValidTest {
    @Test
    public void testBadBorders () {
        BoardState board = new BoardState("FBAA");
        assertFalse ("Piece hangs over top of board", board.IsValidMove("FAAC"));
        board = new BoardState("BFAA");
        assertFalse ("Piece hangs over left of board", board.IsValidMove("AFAB"));
        board = new BoardState("YFAB");
        assertFalse ("Piece hangs over right of board", board.IsValidMove("ZFAA"));
        board = new BoardState("FYAC");
        assertFalse ("Piece hangs over bottom of board", board.IsValidMove("FZAA"));
    }

    @Test
    public void testBadHeight () {
        BoardState board = new BoardState("AAAAAAAABBAA");
        assertFalse("Allows piece to be placed with overhang", board.IsValidMove("ABAD"));
    }

    @Test
    public void testBadColour () {
        BoardState board = new BoardState("AAAABBAA");
        assertFalse("Allows green to be placed over red", board.IsValidMove("ABOD"));
        board = new BoardState("AAKABBKA");
        assertFalse("Allows red to be placed over green", board.IsValidMove("ABED"));
    }

    @Test
    public void testBadStraddle () {
        BoardState board = new BoardState ("AAAA");
        assertFalse("Allows non-straddling placements", board.IsValidMove("AAAA"));
    }

    @Test
    public void testGoodPlacements () {
        BoardState board = new BoardState ("MMUA");

        assertTrue("Rejected valid move", board.IsValidMove("NLOB"));
        board.PlaceTile("NLOB");
        assertTrue("Rejected valid move", board.IsValidMove("LNBC"));
        board.PlaceTile("LNBC");
        assertTrue("Rejected valid move", board.IsValidMove("ONSC"));
        board.PlaceTile("ONSC");
        assertTrue("Rejected valid move", board.IsValidMove("KLDA"));
        board.PlaceTile("KLDA");
        assertTrue("Rejected valid move", board.IsValidMove("POTC"));
        board.PlaceTile("POTC");
        assertTrue("Rejected valid move", board.IsValidMove("MLEB"));
        board.PlaceTile("MLEB");
        assertTrue("Rejected valid move", board.IsValidMove("PLMB"));
        board.PlaceTile("PLMB");
        assertTrue("Rejected valid move", board.IsValidMove("KNJD"));
        board.PlaceTile("KNJD");
        assertTrue("Rejected valid move", board.IsValidMove("OLNB"));
        board.PlaceTile("OLNB");
        assertTrue("Rejected valid move", board.IsValidMove("MLDA"));
        board.PlaceTile("MLDA");
        assertTrue("Rejected valid move", board.IsValidMove("NPLD"));
        board.PlaceTile("NPLD");
        assertTrue("Rejected valid move", board.IsValidMove("NNBA"));
        board.PlaceTile("NNBA");
        assertTrue("Rejected valid move", board.IsValidMove("ONMC"));
        board.PlaceTile("ONMC");
        assertTrue("Rejected valid move", board.IsValidMove("LOFA"));
        board.PlaceTile("LOFA");
        assertTrue("Rejected valid move", board.IsValidMove("PQTC"));
    }
}
