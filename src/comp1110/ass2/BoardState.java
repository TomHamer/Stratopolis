package comp1110.ass2;

/**
 * Created by calum on 8/15/2016.
 */

/* Abstracts the board, represents it as an array of tiles which are individually
   manipulated by adding pieces. Allows checking for valid moves.
 */

public class BoardState {
    private Tile[][] board;
    private String state;

    public BoardState() {
        board = new Tile[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                board[i][j] = new Tile();
            }
        }

        state = "";
    }

    // Determine if a given move is valid on the board
    public boolean IsValidMove (String move) {
        return true;
    }

    // Perform a given move - keep track of played pieces with state
    public void PlaceTile (String move) {

    }

    // Return the score for a given player. Can possibly optimise to score both players at once
    public int Score (Colour c) {
        return 0;
    }

    public String GetBoard() {
        return state;
    }
}
