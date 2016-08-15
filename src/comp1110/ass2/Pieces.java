package comp1110.ass2;

/**
 * Created by calum on 8/15/2016.
 */

// Contains methods for converting strings representing pieces into data compatible with our abstraction of the board
public class Pieces {

    public Colour[] colours;
    public int[][] coords;

    public Pieces (String move) {
        colours = new Colour[3];
        coords = new int[3][2];

        // Fill the arrays with coordinates and colours representing the tiles that the move will affect
    }


}
