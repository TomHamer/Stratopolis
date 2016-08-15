package comp1110.ass2;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tom on 15/08/2016.
 */

//runs a test on

public class PiecesTest {


    @Test
    public void coloursTest() throws Exception {
        String input = "DBKB";
        Pieces piece = new Pieces(input);
        Colour[] colours = new Colour[] {Colour.G,Colour.N,Colour.N};

        boolean success = piece.colours == colours;

        assertTrue("Test failed. Required colours were {G,N,N}, but pieces returned "+Arrays.toString(piece.colours), success );

    }
    @Test
    public void coordinatesTest() throws Exception {
        String input = "DBKB";
        Pieces piece = new Pieces(input);

        int[] coord1 = new int[] {4,2};
        int[] coord2 = new int[] {5,2};
        int[] coord3 = new int[] {4,3};
        int[][] coords = new int[][] {coord1,coord2,coord3};
        boolean success = piece.coords == coords;

        assertTrue("Test failed. Required coordinates were {{4,2},{5,2},{4,3}, but pieces returned "+Arrays.deepToString(piece.coords), success );

    }



}