package comp1110.ass2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl;
import comp1110.ass2.gui.Board;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic1DMatrix;

import java.util.List;



/**
 * Created by Tom on 25/09/2016.
 */
public class Trainer {

    private static final String URI_BASE = "gui/assets/";
    private static Matrix toInput = new Basic1DMatrix(27760,676);
    private static Matrix toOutput = new Basic1DMatrix(694,1);
    private static ArrayList<Double> inp = new ArrayList<>();
    private static ArrayList<Double> out = new ArrayList<>();

    public static void main(String[] args) {

        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("out/production/comp1140-ass2/comp1110/ass2/gui/assets/Samples"));
            String inputLine;
            int pointNumber = 0;
            //System.out.println(inputReader.readLine());
            while ((inputLine = inputReader.readLine()) != null) {
                process(inputLine, pointNumber);
                pointNumber++;

            }
            System.out.println(toInput);



        } catch (IOException e) {
           e.printStackTrace();
        }
       // System.out.println(toInput);

        //System.out.println(toInput);
        //System.out.println(toOutput);

        //the cancer is the fact that the vector that is going in is 26*26 (676 long)

        NN1HL nn = new NN1HL(676,1,1);


        //System.out.println(normalisedInput);
        //System.out.println("the normalised output matrix is: ");
        //System.out.println(normalisedOutput);





        nn.train(toInput,toOutput,1000);
        //System.out.println(nn.alphas);
        //System.out.println(nn.betas);


    }

    private static void process(String line, int pointNumber) {
        //an even point number implies that we are looking at a line of boards, as opposed to a line of results
        if(pointNumber%2==0) {
            int columnIndex = pointNumber*40; // each line has 40 entries
            //] and "," implies that there is a break between two values marks the end of a line
            //every second line marks output as opposed to input
            char[] l = line.toCharArray();
            //iterate through all the characters in the line
            for (int i = 0; i < l.length; i++) {
                //this if statement filters out the cases where we have a braket
                //filter out the null
                String analysed = "";
                if (!(l[i] == ']' || l[i] == '[' || l[i] == 'n' || l[i] == 'u' || l[i] == 'l')) {
                    //every comma represents a break
                    if (!(l[i] == ',')) {
                        //this is a letter
                        analysed = analysed + l[i];
                        //create a column vector
                        //add each element to the matrix
                    } else {
                        BoardState b = new BoardState(analysed);
                        Tile[][] tList = b.getBoard();
                        int rowIndex = 0;
                        for (Tile[] c : tList) {
                            for (Tile tile : c) {
                                 // add one for each tile
                                if (tile.Alignment().equals(Colour.G)) {
                                    toInput.set(rowIndex,columnIndex,-1.0);
                                    //add -1
                                } else if (tile.Alignment().equals(Colour.R)) {

                                    toInput.set(rowIndex,columnIndex,1.0);
                                    //add 1
                                } else {
                                    //add 0
                                    //toInput.set(rowIndex,columnIndex,0);
                                }
                                rowIndex++;
                            }
                        }
                        columnIndex++; //add one for each entry
                        analysed = ""; //clear the variable to be analysed


                    }
                    //append to some variable and then parse in as a double
                }
            }
            double[] target = new double[inp.size()];
            for (int i = 0; i < target.length; i++) {
                target[i] = inp.get(i).doubleValue();  // java 1.4 style
                // or:
                target[i] = inp.get(i);                // java 1.5+ style (outboxing)
            }

            //toInput.set

                   // = Matrix.from1DArray(27760,676,target).transpose();
        } else { //adds the ones and negative ones to the output vector

            char[] l = line.toCharArray();
            //iterate through all the characters in the line
            for (int i = 0; i < l.length; i++) {
                //this if statement filters out the cases where we have a braket
                //filter out the null
                String analysed = "";
                if (!(l[i] == ']' || l[i] == '[')) {
                    //every comma represents a break
                    if (!(l[i] == ',')) {
                        //this is a letter
                        out.add((double) l[i]);
                        //create a column vector
                        //add each element to the matrix
                    }


                }
            }
            double[] target = new double[out.size()];
            for (int i = 0; i < target.length; i++) {
                target[i] = out.get(i);
            }

            toOutput = Matrix.from1DArray(27760,1, target);
        }



        }




    }
/*

        ArrayList<BoardState> initList = new ArrayList<>();
        initList.add(new BoardState("MMUA"));
        System.out.println(generateBoardStates(initList,1,true));

        //create a new medium player

    }
    private Matrix vectoriseBoard(BoardState board) {
        //this will be a 676 length binary vector. A red square will be replaced with a 1, and green replaced with -1. 0 will take the place of black squares and empty squares.
        //
        //The output value will be determined by the minimax function, and then normalised
        return null;
    }
    //once a list of moves has been created, initialise a matrix, put the values in and train the network


*/









