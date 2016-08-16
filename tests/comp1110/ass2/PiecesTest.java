package comp1110.ass2;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tom on 15/08/2016.
 */

//runs a test on the pieces class, checking whether the class returns the correct pieces and colours

public class PiecesTest {


    @Test
    public void coloursTest() throws Exception {
        String[] inputs = {"DBKB","HCAD","EFDC"};

        Colour[][] colours = new Colour[][] {{Colour.G,Colour.N,Colour.N},{Colour.R,Colour.N,Colour.N},{Colour.R,Colour.N,Colour.R}};

        for (int j=0; j<3; j++){
            colourTestAgainst(inputs[j],colours[j]);
        }




    }
    @Test
    public void coordinatesTest() throws Exception {
        String[] inputs = {"DBKB","HCAD","EFDC"};

        int[][][] testCoords = new int[][][] {{{3,1}, {4,1},{3,0}},{{7,2},{6,2},{7,3}},{{4,5},{4,4},{3,5}}};

        for (int j=0; j<3; j++){
            coordTestAgainst(inputs[j],testCoords[j]);
        }



    }

    private void colourTestAgainst(String move, Colour[] colours) {
        Pieces piece = new Pieces(move);

            if (!Arrays.equals(colours, piece.colours)) {
                assertTrue("Test failed. Required colours for" + move + " were" + Arrays.toString(colours)+", but pieces returned "+Arrays.toString(piece.colours), false );
            }
        }


    private void coordTestAgainst(String move, int[][] coords) {
        Pieces piece = new Pieces(move);

        for (int i = 0; i < 3; i++) {
            if (!Arrays.equals(coords[i], piece.coords[i])) {
                assertTrue("Test failed. Required coordinates for " + move + " were" + Arrays.deepToString(coords) + ", but pieces returned "+Arrays.deepToString(piece.coords), false );
            }
        }


    }



}