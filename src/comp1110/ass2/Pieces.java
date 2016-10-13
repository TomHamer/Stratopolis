package comp1110.ass2;

/**
 * Created by calum on 8/15/2016.
 */


// Contains methods for converting strings representing pieces into data compatible with our abstraction of the board

// Pieces are given coordinates similarly to assignment 1

    // Written entirely by Tom Hamer

public class Pieces {


    public Colour[] colours;
    public int[][] coords;




    public Pieces (String move) {
        colours = new Colour[3];
        coords = new int[3][2];

        //set values for the origin of the pieces based on the numeric encoding of the letters

        int valX = Character.getNumericValue(move.charAt(0))-10;

        int valY = Character.getNumericValue(move.charAt(1))-10;


        // Fill the arrays with coordinates and colours representing the tiles that the move will affect
        int[] coordOrigin = {valX,valY};
        coords[0]= coordOrigin;

        if (!(move.charAt(2) == 'U')) {


            switch (move.charAt(2)) {
                case 'A':

                    colours[0] = Colour.R;
                    colours[1] = Colour.N;
                    colours[2] = Colour.N;


                    break;
                case 'B':

                    colours[0] = Colour.N;
                    colours[1] = Colour.N;
                    colours[2] = Colour.R;

                    break;
                case 'C':

                    colours[0] = Colour.N;
                    colours[1] = Colour.R;
                    colours[2] = Colour.N;

                    break;
                case 'D':

                    colours[0] = Colour.R;
                    colours[1] = Colour.N;
                    colours[2] = Colour.R;

                    break;
                case 'E':

                    colours[0] = Colour.N;
                    colours[1] = Colour.R;
                    colours[2] = Colour.R;

                    break;
                case 'F':

                    colours[0] = Colour.R;
                    colours[1] = Colour.R;
                    colours[2] = Colour.N;

                    break;
                case 'G':

                    colours[0] = Colour.R;
                    colours[1] = Colour.G;
                    colours[2] = Colour.R;

                    break;
                case 'H':

                    colours[0] = Colour.G;
                    colours[1] = Colour.R;
                    colours[2] = Colour.R;

                    break;
                case 'I':

                    colours[0] = Colour.R;
                    colours[1] = Colour.R;
                    colours[2] = Colour.G;

                    break;
                case 'J':

                    colours[0] = Colour.R;
                    colours[1] = Colour.R;
                    colours[2] = Colour.R;

                    break;
                case 'K':

                    colours[0] = Colour.G;
                    colours[1] = Colour.N;
                    colours[2] = Colour.N;

                    break;
                case 'L':

                    colours[0] = Colour.N;
                    colours[1] = Colour.N;
                    colours[2] = Colour.G;

                    break;
                case 'M':

                    colours[0] = Colour.N;
                    colours[1] = Colour.G;
                    colours[2] = Colour.N;

                    break;
                case 'N':

                    colours[0] = Colour.G;
                    colours[1] = Colour.N;
                    colours[2] = Colour.G;

                    break;
                case 'O':

                    colours[0] = Colour.N;
                    colours[1] = Colour.G;
                    colours[2] = Colour.G;

                    break;
                case 'P':

                    colours[0] = Colour.G;
                    colours[1] = Colour.G;
                    colours[2] = Colour.N;

                    break;
                case 'Q':

                    colours[0] = Colour.G;
                    colours[1] = Colour.R;
                    colours[2] = Colour.G;

                    break;
                case 'R':

                    colours[0] = Colour.R;
                    colours[1] = Colour.G;
                    colours[2] = Colour.G;

                    break;
                case 'S':

                    colours[0] = Colour.G;
                    colours[1] = Colour.G;
                    colours[2] = Colour.R;


                    break;
                case 'T':

                    colours[0] = Colour.G;
                    colours[1] = Colour.G;
                    colours[2] = Colour.G;

                    break;


            }
        } else {

            // do piece U

            colours[0] = Colour.R;
            colours[1] = Colour.G;


        }


        //calculate coordinates of which to place the piece

        switch (move.charAt(3)) {
            case 'A':
                if (move.charAt(2)=='U') {

                    int[] coord1A = {valX, valY + 1};
                    coords[1] = coord1A;

                } else {

                    int[] coord1A = {valX + 1, valY};
                    coords[1] = coord1A;


                    int[] coord2A = {valX, valY + 1};
                    coords[2] = coord2A;
                }

                break;

            case 'B':

                if (move.charAt(2)=='U') {

                    int[] coord1A = {valX+1, valY};
                    coords[1] = coord1A;

                } else {

                    int[] coord1B = {valX, valY + 1};
                    coords[1] = coord1B;


                    int[] coord2B = {valX - 1, valY};
                    coords[2] = coord2B;
                }

                break;

            case 'C':
                if (move.charAt(2)=='U') {

                    int[] coord1A = {valX, valY-1};
                    coords[1] = coord1A;

                } else {

                    int[] coord1C = {valX - 1, valY};
                    coords[1] = coord1C;


                    int[] coord2C = {valX, valY - 1};
                    coords[2] = coord2C;
                }

                break;
            case 'D':

                if (move.charAt(2)=='U') {

                    int[] coord1A = {valX - 1, valY};
                    coords[1] = coord1A;

                } else {

                    int[] coord1D = {valX, valY - 1};
                    coords[1] = coord1D;


                    int[] coord2D = {valX + 1, valY};
                    coords[2] = coord2D;
                }

                break;
        }










        }




}
