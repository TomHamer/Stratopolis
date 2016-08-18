package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by calum on 8/15/2016.
 */

/* Abstracts the board, represents it as an array of tiles which are individually
   manipulated by adding pieces. Allows checking for valid moves.
 */

public class BoardState {
    private Tile[][] board;
    private String state;
    private static final String URI_BASE = "gui/assets/";
    private static final int SQUARE_SIZE = 28;

    public BoardState() {
        board = new Tile[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                board[i][j] = new Tile();
            }
        }

        state = "";
    }

    public BoardState(String start) {
        board = new Tile[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                board[i][j] = new Tile();
            }
        }

        for (int i = 0; i <= start.length() - 4; i += 4) {
            this.PlaceTile(start.substring(i, i+4));
        }
    }

    // Determine if a given move is valid on the board
    public boolean IsValidMove (String move) {
        Pieces piece = new Pieces(move);
        Colour[] col = piece.colours;
        int[][] cor = piece.coords;
        boolean valid = true;

        for (int i = 0; i < 3; i++) {
            Colour c = board[cor[i][1]][cor[i][0]].Alignment();
            if (  !(c == col[i]   || col[i] == Colour.N ||
                    c == Colour.N || c      == Colour.W)) {
                return false;
            }
        }

        return (board[cor[0][1]][cor[0][0]].Height() ==
                board[cor[1][1]][cor[1][0]].Height() &&
                board[cor[1][1]][cor[1][0]].Height() ==
                board[cor[2][1]][cor[2][0]].Height());
    }

    // Perform a given move - keep track of played pieces with state
    public void PlaceTile (String move) {
        state = state + move;
        Pieces piece = new Pieces(move);
        Colour[] col = piece.colours;
        int[][] cor = piece.coords;

        for (int i = 0; i < 3; i++) {
            if (col[i] != null) {
                board[cor[i][1]][cor[i][0]].Stack(col[i]);
            }
        }
    }

    public void PutBoard () {
        for (Tile[] ts : board) {
            System.out.print("[");
            for (Tile t : ts) {
                System.out.print(t.Alignment() + ", ");
            }
            System.out.println();
        }
    }

    public Group GetBoardGroup (double squareSize) {
        Group display = new Group();

        Rectangle border = new Rectangle((26 * squareSize) + 20, (26 * squareSize) + 20);
        border.relocate(-10, -10);
        border.setStrokeWidth(3);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);
        display.getChildren().add(border);

        for (int x = 0; x < 26; x++) {
            for (int y = 0; y < 26; y++) {
                Group toAdd = board[y][x].TileFX(squareSize);
                if (toAdd == null) {continue;}
                toAdd.relocate(x * squareSize, y * squareSize);
                display.getChildren().add(toAdd);
            }
        }




        return display;
    }



    class Square extends ImageView {
        Square (String colour, double size) {
            System.out.println(URI_BASE + colour + ".png");
            setImage(new Image(BoardState.class.getResource(URI_BASE + colour + ".png").toString()));
            setFitHeight(size);
            setFitWidth(size);
        }
    }

    // Return the score for a given player. Can possibly optimise to score both players at once
    public int Score (Colour c) {
        return 0;
    }

    public String GetBoard() {
        return state;
    }
}