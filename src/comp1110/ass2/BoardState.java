package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * Created by calum on 8/15/2016.
 */

/* Abstracts the board, represents it as an array of tiles which are individually
   manipulated by adding pieces. Allows checking for valid moves.
   Written in its entirety by Calum Snowdon, except for the final function which efficiently finds possible moves
   given a boardState, which was written by Tom Hamer.
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

    public Tile[][] getTileArray() {return board;}

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

        Rectangle fill = new Rectangle(26 * squareSize + 17, 26 * squareSize + 17, Color.GRAY);
        fill.relocate(-8.5, -8.5);
        display.getChildren().add(fill);

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

    // Adds a piece to a board group without generating a new group. There is
    // room for optimisation, currently it stacks tile objects on top of each other
    // so there are objects in the background which are never seen again
    public void UpdateBoardGroup(Group boardGroup, double squareSize, String placement) {
        PlaceTile(placement);
        Pieces piece = new Pieces(placement);

        for (int i = 0; i < 3; i++) {
            int x = piece.coords[i][0];
            int y = piece.coords[i][1];

            Group toAdd = board[y][x].TileFX(squareSize);
            toAdd.relocate(x * squareSize, y * squareSize);
            boardGroup.getChildren().add(toAdd);
        }
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
                    height = 1;
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
                        }
                    }

                    if (j < 25 && board[j+1][i].Alignment() == col) {
                        if (sets[j][i].head != sets[j+1][i].head) {
                            sets[j][i].Add(sets[j+1][i]);
                            sets[j+1][i] = sets[j][i];
                        }
                    }

                    if (sets[j][i].head.length == maxLength) {
                        height = Math.max(sets[j][i].head.maxHeight, height);
                    } else if (maxLength < sets[j][i].head.length) {
                        height = sets[j][i].head.maxHeight;
                        maxLength = sets[j][i].head.length;
                    }
                }
            }
        }
        return maxLength * height;
    }

    // True if green is winning, false if red
    public boolean BreakTie() {
        Union[][] sets = new Union[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[j][i].Alignment() != Colour.N && board[j][i].Alignment() != Colour.W) {
                    sets[j][i] = new Union(board[j][i].Height(), board[j][i].Alignment());
                }
            }
        }

        // "Links together" adjacent tiles of the same colour with a Union type.
        // Keeps track of the largest union created and returns the recorded value at the end.
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[j][i].Alignment() != Colour.N && board[j][i].Alignment() != Colour.W) {
                    if (i < 25 && board[j][i+1].Alignment() == board[j][i].Alignment()) {
                        if (sets[j][i].head != sets[j][i+1].head) {
                            sets[j][i].Add(sets[j][i+1]);
                            sets[j][i+1] = sets[j][i];
                        }
                    }

                    if (j < 25 && board[j+1][i].Alignment() == board[j][i].Alignment()) {
                        if (sets[j][i].head != sets[j+1][i].head) {
                            sets[j][i].Add(sets[j+1][i]);
                            sets[j+1][i] = sets[j][i];
                        }
                    }
                }
            }
        }

        Set<Union> regionsG = new HashSet<>();
        Set<Union> regionsR = new HashSet<>();

        boolean broken = false;

        for (Union[] us : sets) {
            for (Union u : us) {
                if (u != null) {
                    if (u.colour == Colour.G) {
                        regionsG.add(u.head);
                    } else {
                        regionsR.add(u.head);
                    }
                }
            }
        }

        while (true) {
            if (regionsG.isEmpty() && regionsR.isEmpty()) {
                Random rand = new Random();
                return rand.nextBoolean();
            } else if (regionsR.isEmpty()) {
                return true;
            } else if (regionsG.isEmpty()) {
                return false;
            }

            Union maxG = new Union(0);
            for (Union uG : regionsG) {
                if (uG.length > maxG.length || (uG.length == maxG.length && uG.maxHeight > maxG.maxHeight)) {
                    maxG = uG;
                }
            }

            Union maxR = new Union(0);
            for (Union uR : regionsR) {
                if (uR.length > maxR.length || (uR.length == maxR.length && uR.maxHeight > maxR.maxHeight)) {
                    maxR = uR;
                }
            }

            if (maxG.length * maxG.maxHeight == maxR.length * maxR.maxHeight) {
                regionsG.remove(maxG);
                regionsR.remove(maxR);
            } else {
                return (maxG.length * maxG.maxHeight > maxR.length * maxR.maxHeight);
            }
        }
    }

    private class Cores {
        private int availableCores = 4;

        boolean Available () {return availableCores > 0;}

        void CheckOut () {availableCores--;}
        void Return () {availableCores++;}
        boolean Done () {return availableCores == Runtime.getRuntime().availableProcessors();}
    }

    private class Score {
        private int size = 0;
        private int height = 0;

        void Set(int inSize, int inHeight) {
            if (inSize > size) {
                size = inSize;
                height = inHeight;
            } else if (inSize == size && inHeight > height) {
                height = inHeight;
            }
        }

        int Return () {return size * height;}
    }

    // A parallelised version of scoring. Is slower than the sequential version
    // and is mildly dodgy code.
    public int ConcurrentScore (boolean green) throws InterruptedException {
        Colour col;

        Cores cores = new Cores();
        Score score = new Score();

        if (green) {col = Colour.G;}
        else {col = Colour.R;}

        Union[][] sets = new Union[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (board[i][j].Alignment() == col) {
                    sets[i][j] = new Union(board[i][j].Height());
                }
            }
        }

        class PartialScorer implements Runnable {
            private int start, end;
            private int maxLength = 0;
            private int height = 0;


            private PartialScorer (int rangeStart, int rangeEnd) {
                start = rangeStart;
                end   = rangeEnd;
            }

            @Override
            public void run() {
                for (int i = start; i < end; i++) {
                    if (cores.Available()) {
                        synchronized (cores) {
                            if (cores.Available() && end - start > 5) {
                                cores.CheckOut();

                                Thread scorer = new Thread(new PartialScorer((start + end) / 2, end));
                                end = (start + end) / 2;
                                scorer.start();
                            }
                        }
                    }

                    int j = i / 26;
                    int k = i % 26;

                    synchronized (board[j][k]) {
                        if (board[j][k].Alignment() == col) {
                            if (k != 25 && board[j][k+1].Alignment() == col) {
                                synchronized (board[j][k+1]) {
                                    if (sets[j][k].head != sets[j][k+1].head) {

                                        sets[j][k].Add(sets[j][k+1]);
                                        sets[j][k+1] = sets[j][k];

                                        if (sets[j][k].head.length == maxLength) {
                                            height = Math.max(sets[j][k].head.maxHeight, height);
                                        } else if (maxLength < sets[j][k].head.length) {
                                            height = sets[j][k].head.maxHeight;
                                            maxLength = sets[(j)][k].head.length;
                                        }
                                    }
                                }
                            }

                            if (j < 25 && board[j + 1][k].Alignment() == col) {
                                synchronized (board[j + 1][k]) {
                                    if (sets[j][k].head != sets[j + 1][k].head) {

                                        sets[j][k].Add(sets[j + 1][k]);
                                        sets[j + 1][k] = sets[j][k];

                                        if (sets[j][k].head.length == maxLength) {
                                            height = Math.max(sets[j][k].head.maxHeight, height);
                                        } else if (maxLength < sets[j][k].head.length) {
                                            height = sets[j][k].head.maxHeight;
                                            maxLength = sets[(j)][k].head.length;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                synchronized (score) {
                    score.Set(maxLength, height);
                }

                synchronized (cores) {
                    cores.Return();
                    cores.notify();
                }

            }
        }

        cores.CheckOut();
        new Thread(new PartialScorer(0, 675)).start();


        synchronized (cores) {
            while (!cores.Done()) {
                try {
                    cores.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return score.Return();
    }



    // Inspired by https://en.wikipedia.org/wiki/Disjoint-set_data_structure. A class
    // to assist with finding the score for a player. It's essentially a bunch of pointers.
    // It's a class which allows associations to be created with other members
    // of the class. A union is basically a linked list, u1 -> u2 -> u3 -> ... -> un,
    // except each instance carries a pointer to both the next element of the list and
    // a pointer to the start of the list. It also has a length field, which is only really
    // used by the head of the union, which keeps track of how many elements the union has.
    private class Union {
        Union head; // The start of the list which the union is contained within
        Union next; // The next element in the list
        int length; // The number of elements in the list. NOTE: only kept valid for the head.
        int maxHeight; // Largest height in the set. Only valid at the head.
        Colour colour; // Used to differentiate between sets for each player, used in the tie breaking algorithm

        public Union (int height) {
            head = this;
            next = null;
            length = 1;
            maxHeight = height;
            colour = null;
        }

        public Union (int height, Colour col) {
            head = this;
            next = null;
            length = 1;
            maxHeight = height;
            colour = col;
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

    public String GetBoard()
    {
        String toReturn = "";
        // For some reason "null" was showing up in the string, we couldn't
        // figure out the problem so we just filtered out the null
        for(char i: state.toCharArray()) {
            if(!(i=='n'||i=='l'||i=='u')) {
                toReturn=toReturn + i;
            }
        }
        return toReturn;
    }

    //Written by Tom Hamer
    //Efficient move search algorithm
    //generates all the possible moves for a given boardstate and deckpiece
    //we take each tile on the board and add the coordinates recieved by looking in every direction, hence minimising the
    //search space we have to look through when analysing the orientation and whether the piece is valid
    //NB: Moves where the origin of the piece was two tiles away from the tile were left out because that is generally
    //a bad move, and also helps us decrease the search space.
    public ArrayList<String> generateAllPossibleMoves(boolean redIsPlaying, char piece) {
        HashSet<String> hs = new HashSet<>(); // a hashset for all the moves to guard against duplicates
        String bString = this.GetBoard();
        HashSet<char[]> coords = new HashSet<>(); //using a hashset for the coordinates to remove duplicates
           for(int i = 0; i<bString.length();i+=4) {
               Pieces p = new Pieces(bString.substring(i, i + 4));
               for (int[] c : p.coords) {
                   //retrieve a list of x and y coords that bound the shape by going one unit in each direction
                   coords.add(new char[]{(char) (c[0] + 66), (char) (c[1] + 66)});
                   coords.add(new char[]{(char) (c[0] + 65), (char) (c[1] + 66)});
                   coords.add(new char[]{(char) (c[0] + 64), (char) (c[1] + 66)});
                   coords.add(new char[]{(char) (c[0] + 66), (char) (c[1] + 65)});
                   coords.add(new char[]{(char) (c[0] + 64), (char) (c[1] + 65)});
                   coords.add(new char[]{(char) (c[0] + 65), (char) (c[1] + 65)});
                   coords.add(new char[]{(char) (c[0] + 66), (char) (c[1] + 64)});
                   coords.add(new char[]{(char) (c[0] + 65), (char) (c[1] + 64)});
                   coords.add(new char[]{(char) (c[0] + 64), (char) (c[1] + 64)});
               }
           }


           //code to get the (flattened) list of all tiles. Colour here is irrelevant
           //the variable board gives a list of tiles
                for(char[] cor:coords) {
                    for(char o:"ABCD".toCharArray()) {
                        //create the full piece from the coodinates along with a trial orientation.
                        //if it is valid add it to the hashset
                        String pieceToTry = ""+ cor[0]+cor[1]+piece+o;
                        if(this.IsValidMove(pieceToTry)) {
                        hs.add(pieceToTry);
                        }

                    }
                }

        //convert to arraylist
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(hs);
        return toReturn;
    }
}
