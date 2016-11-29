package comp1110.ass2.AI.NeuralNetworkFiles;

import java.io.*;
import java.util.ArrayList;

import comp1110.ass2.BoardState;
import comp1110.ass2.Colour;
import comp1110.ass2.Tile;
import comp1110.ass2.la4j.Matrix;
import comp1110.ass2.la4j.matrix.dense.Basic1DMatrix;

/**
 * Created by Tom on 25/09/2016.
 */

//trains a neural network based on the game data in samples.
//the data was generated by a function that matched two easy bots against eachother and assigned
//all the boards of the bot that won a 1 and all the boards of the bot that lost a -1

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

        //this choice of network architecture was derived from http://stats.stackexchange.com/questions/181/how-to-choose-the-number-of-hidden-layers-and-nodes-in-a-feedforward-neural-netw
        //where the formula was used:
        //number of hidden layer neurons = (no of samples)/(10*(dimension of input + dimension of output))
        //this calculated to approx 8 neurons
        NN1HL nn = new NN1HL(1,676,1,0.0001);

        nn.train(toInput,toOutput,100);

    }
    //parses data from the "samples.txt" file in gui. Each board is parsed in as a vector of -/+1 for red and green and 0 for anything else
    //stacking is ignored
    private static void process(String line, int pointNumber) {
        //an even point number implies that we are looking at a line of boards, as opposed to a line of results
        //this parses in the input matrix
        if(pointNumber%2==0) {
            int columnIndex = pointNumber*20; // each line has 40 entries


            //] and "," implies that there is a break between two values marks the end of a line
            //every second line marks output as opposed to input
            char[] l = line.toCharArray();
            //iterate through all the characters in the line
            String analysed = "";
            for (char aL : l) {
                //this if statement filters out the cases where we have a braket
                //filter out the null
                if (!(aL == '[' || aL == 'n' || aL == 'u' || aL == 'l' || aL == ' ')) {
                    //every comma represents a break
                    if (!(aL == ',' || aL == ']')) {
                        //this is a letter

                        analysed = analysed + aL;

                        //create a column vector
                        //add each element to the matrix
                    } else {
                        BoardState b = new BoardState(analysed);
                        Tile[][] tList = b.getTileArray();
                        int rowIndex = 0;
                        for (Tile[] c : tList) {
                            for (Tile tile : c) {
                                // add one for each tile
                                if (tile.Alignment().equals(Colour.G)) {
                                    toInput.set(columnIndex, rowIndex, -1.0);
                                    //add -1
                                } else if (tile.Alignment().equals(Colour.R)) {

                                    toInput.set(columnIndex, rowIndex, 1.0);
                                    //add 1
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

        } else { //parses in the output vector
            //adds the ones and negative ones to the output vector

            char[] l = line.toCharArray();
            //iterate through all the characters in the line
            String analysed = "";
            int numChars = 0;
            for (char aL : l) {
                //this if statement filters out the cases where we have a braket
                //filter out the null

                if (!(aL == '[' || aL == ' ')) {
                    //every comma represents a break
                    if (!(aL == ',' || aL == ']')) {
                        //this is a letter
                        analysed = analysed + aL;

                    } else {
                        //add the parsed double to the output matrix
                        out.add(-Double.parseDouble(analysed));


                        analysed = "";


                    }


                }
            }



        }


    }




}









