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

    public int BoardScore (boolean green) {
        Colour col;
        if (green) {col = Colour.G;}
        else {col = Colour.R;}
        Union[][] sets = new Union[26][26];
        int maxLength = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[i][j].Alignment() == col) {
                    sets[i][j] = new Union();
                    maxLength = 1;
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[j][i].Alignment() == col) {
                    if (i < 25 && board[j][i+1].Alignment() == col) {
                        if (sets[j][i].head.length < sets[j][i+1].head.length) {
                            sets[j][i+1].Add(sets[j][i]);
                            sets[j][i] = sets[j][i+1];
                            maxLength = Math.max(sets[j][i].head.length, maxLength);
                        } else if (sets[j][i].head != sets[j][i+1].head) {
                            sets[j][i].Add(sets[j][i + 1]);
                            sets[j][i+1] = sets[j][i];
                            maxLength = Math.max(sets[j][i].head.length, maxLength);
                        }
                    }
                    if (j < 25 && board[j+1][i].Alignment() == col) {
                        if (sets[j][i].head.length < sets[j+1][i].head.length) {
                            sets[j+1][i].Add(sets[j][i]);
                            sets[j][i] = sets[j+1][i];
                            maxLength = Math.max(sets[j][i].head.length, maxLength);
                        } else if (sets[j][i].head != sets[j+1][i].head) {
                            sets[j][i].Add(sets[j+1][i]);
                            sets[j+1][i] = sets[j][i];
                            maxLength = Math.max(sets[j][i].head.length, maxLength);
                        }
                    }
                }
            }
        }
        return maxLength;
    }

    class Union {
        Union head;
        Union next;
        int length;

        Union () {
            head = this;
            next = null;
            length = 1;
        }

        void Add (Union u) {
            if (next == null) {
                u.SetHead(head);
                next = u;
                head.length += u.length;
            } else {
                next.Add(u);
            }
        }

        void SetHead (Union u) {
            head = u;
            if (next != null) {
                next.SetHead(u);
            }
        }
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
