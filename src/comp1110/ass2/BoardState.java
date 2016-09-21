package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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

    // Creates a BoardState and adds the given placement string to the board.
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

        // Checks that the colours it's been placed on are consistent with the rules.
        for (int i = 0; i < 3; i++) {
            if (cor[i][1] < 26 && cor[i][1] >= 0 && cor[i][0] < 26 && cor[i][0] >= 0) {
                Colour c = board[cor[i][1]][cor[i][0]].Alignment();
                if (!(c == col[i] || col[i] == Colour.N ||
                        c == Colour.N || c == Colour.W)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        // Checks that the heights of the tiles below are valid and that there is a piece adjacent,
        // and that if it's being stacked it is straddling at least 2 pieces.
        return (board[cor[0][1]][cor[0][0]].Height() == board[cor[1][1]][cor[1][0]].Height() &&
                board[cor[1][1]][cor[1][0]].Height() == board[cor[2][1]][cor[2][0]].Height()) && Adjacent(move) && Straddle(move);
    }

    // Determines whether a piece is adjacent to an already placed piece
    private boolean Adjacent (String move) {
        boolean adjacent = false;
        Pieces piece = new Pieces(move);
        int x = piece.coords[0][0];
        int y = piece.coords[0][1];

        // Each case checks the surrounding region of the piece. If any of them have non-zero
        // height, there is a tile there so the new piece is adjacent top another piece.
        switch (move.charAt(3)) {
            case 'A':
                if (y - 1 >= 0) {
                    adjacent = board[y-1][x].Height() != 0 || board[y-1][x+1].Height() != 0;
                }
                if (x - 1 >= 0) {
                    adjacent = adjacent || board[y][x-1].Height() != 0 || board[y+1][x-1].Height() != 0;
                }
                if (x + 2 < 26) {adjacent = adjacent || board[y][x+2].Height() != 0;}
                if (y + 2 < 26) {adjacent = adjacent || board[y+2][x].Height() != 0;}
                adjacent = adjacent || board[y+1][x+1].Height() != 0;
                break;

            case 'B':
                if (y - 1 >= 0) {
                    adjacent = board[y-1][x].Height() != 0 || board[y-1][x-1].Height() != 0;
                }
                if (x + 1 < 26) {
                    adjacent = adjacent || board[y][x+1].Height() != 0 || board[y+1][x+1].Height() != 0;
                }
                if (x - 2 >= 0) {adjacent = adjacent || board[y][x-2].Height() != 0;}
                if (y + 2 < 26) {adjacent = adjacent || board[y+2][x].Height() != 0;}
                adjacent = adjacent || board[y+1][x-1].Height() != 0;
                break;

            case 'C':
                if (y + 1 < 26) {
                    adjacent = board[y+1][x].Height() != 0 || board[y+1][x-1].Height() != 0;
                }
                if (x + 1 < 26) {
                    adjacent = adjacent || board[y][x+1].Height() != 0 || board[y-1][x+1].Height() != 0;
                }
                if (x - 2 >= 0) {adjacent = adjacent || board[y][x-2].Height() != 0;}
                if (y - 2 >= 0) {adjacent = adjacent || board[y-2][x].Height() != 0;}
                adjacent = adjacent || board[y-1][x-1].Height() != 0;
                break;

            case 'D':
                if (y + 1 < 26) {
                    adjacent = board[y+1][x].Height() != 0 || board[y+1][x+1].Height() != 0;
                }
                if (x - 1 >= 0) {
                    adjacent = adjacent || board[y][x-1].Height() != 0 || board[y-1][x-1].Height() != 0;
                }
                if (x + 2 < 26) {adjacent = adjacent || board[y][x+2].Height() != 0;}
                if (y - 2 >= 0) {adjacent = adjacent || board[y-2][x].Height() != 0;}
                adjacent = adjacent || board[y-1][x+1].Height() != 0;
                break;
        }

        return adjacent;
    }

    // Determines whether a piece straddles two already placed pieces
    private boolean Straddle (String move) {
        int[][] cor = new Pieces(move).coords;
        // This is the final test performed in the validation of a placement,
        // so we can assume that all the tiles are of the same height. If any of
        // them are 0, the piece isn't being stacked so we don't bother with the rest.
        if (board[cor[0][1]][cor[0][0]].Height() == 0) {
            return true;
        }

        // This loop finds the previous piece which takes up one of the same spaces
        // as the piece being placed. If that piece takes up the exact same space as
        // the piece being placed, the new piece isn't straddling so the placement is invalid.
        // If not, it is straddling.
        for (int i = state.length() - 4; i >= 0; i -= 4) {
            String lastMove = state.substring(i, i+4);
            int[][] cor2 = (new Pieces(lastMove)).coords;
            boolean overlap = false;
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (cor[x][0] == cor2[y][0] && cor[x][1] == cor2[y][1]) {
                        overlap = true;
                        break;
                    }
                }
                if (overlap) {break;}
            }
            if (overlap && lastMove.charAt(0) == move.charAt(0) &&
                           lastMove.charAt(1) == move.charAt(1) &&
                           lastMove.charAt(3) == move.charAt(3)) {
                return false;
            }
            if (overlap) {break;}
        }

        return true;
    }

    // Perform a given move - keep track of played pieces in the state string
    // NOTE: this method is unsafe, it doesn't check whether or not pieces
    // are valid before adding them. ALWAYS check with IsValidMove() before using.
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

    // Turns the board into a JavaFX group
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

    // Function to find the score for a certain player. Makes use of the
    // "Union" class, described below.
    public int BoardScore (boolean green) {
        Colour col;

        if (green) {col = Colour.G;}
        else {col = Colour.R;}

        Union[][] sets = new Union[26][26];
        int maxLength = 0;
        int height = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[i][j].Alignment() == col) {
                    sets[i][j] = new Union(board[i][j].Height());
                    maxLength = 1;
                }
            }
        }

        // "Links together" adjacent tiles of the same colour with a Union type.
        // Keeps track of the largest union created and returns the recorded value at the end.
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[j][i].Alignment() == col) {
                    if (i < 25 && board[j][i+1].Alignment() == col) {
                        if (sets[j][i].head != sets[j][i+1].head) {

                            sets[j][i].Add(sets[j][i+1]);
                            sets[j][i+1] = sets[j][i];

                            if (sets[j][i].head.length == maxLength) {
                                height = Math.max(sets[j][i].head.maxHeight, height);
                            } else if (maxLength < sets[j][i].head.length) {
                                height = sets[j][i].head.maxHeight;
                                maxLength = sets[j][i].head.length;
                            }

                        }
                    }

                    if (j < 25 && board[j+1][i].Alignment() == col) {
                        if (sets[j][i].head != sets[j+1][i].head) {

                            sets[j][i].Add(sets[j+1][i]);
                            sets[j+1][i] = sets[j][i];

                            if (sets[j][i].head.length == maxLength) {
                                height = Math.max(sets[j][i].head.maxHeight, height);
                            } else if (maxLength < sets[j][i].head.length) {
                                height = sets[j][i].head.maxHeight;
                                maxLength = sets[j][i].head.length;
                            }

                        }
                    }
                }
            }
        }
        return maxLength * height;
    }

    // Inspired by https://en.wikipedia.org/wiki/Disjoint-set_data_structure. A class
    // to assist with finding the score for a player. It's essentially a bunch of pointers.
    // It's a class which allows associations to be created with other members
    // of the class. A union is basically a linked list, u1 -> u2 -> u3 -> ... -> un,
    // except each instance carries a pointer to both the next element of the list and
    // a pointer to the start of the list. It also has a length field, which is only really
    // used by the head of the union, which keeps track of how many elements the union has.
    class Union {
        Union head; // The start of the list which the union is contained within
        Union next; // The next element in the list
        int length; // The number of elements in the list. NOTE: only kept valid for the head.
        int maxHeight;

        public Union (int height) {
            head = this;
            next = null;
            length = 1;
            maxHeight = height;
        }

        // Adds one union to the end of another one. Adding a union of length m to one of
        // length n is O(m + n).
        void Add (Union u) {
            if (next == null) {
                next = u.head;
                head.length += u.head.length;
                head.maxHeight = Math.max(head.maxHeight, u.head.maxHeight);
                u.head.SetHead(head); // Updates the head of all elements in the union being added. O(m).
            } else {
                next.Add(u); // Recursively finds the end of the list being added to. O(n).
            }
        }

        // Sets the head of each element in a union to a new value. O(n).
        void SetHead (Union u) {
            head = u;
            if (next != null) {
                next.SetHead(u);
            }
        }
    }

    public String GetBoard() {
        return state;
    }
}
