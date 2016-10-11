package comp1110.ass2;

import java.io.*;
import java.util.ArrayList;
import comp1110.ass2.la4j.Matrix;
import comp1110.ass2.la4j.matrix.dense.Basic1DMatrix;

/**
 * Created by Tom on 25/09/2016.
 */
public class Trainer {

    private static final String URI_BASE = "gui/assets/";
    private static Matrix toInput = new Basic1DMatrix(27760,676); //this matrix is cancer
    private static Matrix toOutput = new Basic1DMatrix(27760,1);
    private static ArrayList<Double> inp = new ArrayList<>();
    private static ArrayList<Double> out = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Network is training - printing error, the matrix of alphas and the matrix of betas on every back-prop step");

        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("out/production/comp1140-ass2/comp1110/ass2/gui/assets/Samples"));
            String inputLine;
            int pointNumber = 0;
            //System.out.println(inputReader.readLine());
            while ((inputLine = inputReader.readLine()) != null) {
                process(inputLine, pointNumber);
                pointNumber++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //setup the output training data
        double[] target = new double[out.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = out.get(i);
        }
        toOutput = Matrix.from1DArray(27760,1, target);

        NN1HL nn = new NN1HL(8,676,1,0.0001);

        nn.train(toInput,toOutput,100);

    }
    //parses data from the
    private static void process(String line, int pointNumber) {
        //an even point number implies that we are looking at a line of boards, as opposed to a line of results
        if(pointNumber%2==0) {
            int columnIndex = pointNumber*20; // each line has 40 entries


            //] and "," implies that there is a break between two values marks the end of a line
            //every second line marks output as opposed to input
            char[] l = line.toCharArray();
            //iterate through all the characters in the line
            String analysed = "";
            for (int i = 0; i < l.length; i++) {
                //this if statement filters out the cases where we have a braket
                //filter out the null
                if (!(l[i] == '[' || l[i] == 'n' || l[i] == 'u' || l[i] == 'l' || l[i] == ' ')) {
                    //every comma represents a break
                    if (!(l[i] == ',' || l[i] == ']')) {
                        //this is a letter

                        analysed = analysed+l[i];

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
                                    toInput.set(columnIndex,rowIndex,-1.0);
                                    //add -1
                                } else if (tile.Alignment().equals(Colour.R)) {

                                    toInput.set(columnIndex,rowIndex,1.0);
                                    //add 1
                                } else {
                                    //add 0
                                    //toInput.set(rowIndex,columnIndex,0);
                                }
                                rowIndex++;
                            }
                        }
                        //System.out.println(columnIndex);

                        columnIndex++; //add one for each entry
                        analysed = "";//clear the variable to be analysed
                    }

                    //append to some variable and then parse in as a double
                }
            }


                   // = Matrix.from1DArray(27760,676,target).transpose();
        } else { //adds the ones and negative ones to the output vector

            char[] l = line.toCharArray();
            //iterate through all the characters in the line
            String analysed = "";
            int numChars = 0;
            for (int i = 0; i < l.length; i++) {
                //this if statement filters out the cases where we have a braket
                //filter out the null

                if (!(l[i] == '[' || l[i] == ' ')) {
                    //every comma represents a break
                    if (!(l[i] == ',' || l[i] == ']')) {
                        //this is a letter
                        analysed = analysed + l[i];
                        //System.out.println(l[i]);

                        //create a column vector
                        //add each element to the matrix
                    } else {
                        //the reason why this needs to be negative is that the data was actually collected wrogning
                        out.add(-Double.parseDouble(analysed));



                        analysed = "";


                    }


                }
            }



        }


        }




    }










