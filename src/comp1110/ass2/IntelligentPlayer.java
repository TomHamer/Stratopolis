package comp1110.ass2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import comp1110.ass2.la4j.Matrix;
import comp1110.ass2.la4j.Vector;
import comp1110.ass2.la4j.matrix.dense.Basic1DMatrix;
import comp1110.ass2.la4j.vector.dense.BasicVector;

/**
 * Created by Tom on 9/10/2016.
 */

    //a player trained with a one-hidden-layer neural network to tell whether boards are good or bad
    //only plays red!!
public class IntelligentPlayer {


    private Matrix alphas;
    private Matrix betas;
    private Matrix biases;
    private int M; //dimension of the hidden layer
    private int K; //dimension of the output layer
    private int P; //dimension of the input layer

    //this AI requires the user to pass in the architecture of the neural network, so that the player can feed
    //the correct values forward
    public IntelligentPlayer(NN1HL n) {
        //set M,K and P
        this.K = n.getK();
        this.P = n.getP();
        this.M = n.getM();

        //parse in the network from the file
        this.alphas = new Basic1DMatrix(P,M);
        this.betas = new Basic1DMatrix(M,K);
        this.biases = new Basic1DMatrix(Math.max(M,K),2);

        //parse alphas
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("out/production/comp1140-ass2/comp1110/ass2/gui/assets/alphas.txt"));
            String inputLine;
            int pointNumber = 0;
            while ((inputLine = inputReader.readLine()) != null) {
                processAlphas(inputLine, pointNumber);
                pointNumber++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //parse betas
        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("out/production/comp1140-ass2/comp1110/ass2/gui/assets/betas.txt"));
            String inputLine;
            int pointNumber = 0;
            while ((inputLine = inputReader.readLine()) != null) {

                processBetas(inputLine, pointNumber);
                pointNumber++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }




    }


        public String getBestMove(BoardState board, char deckPiece) {
            //generates all possible boards that could come from moves that are available to the AI
            ArrayList<BoardState> possibleBoards = generateNextBoards(board,deckPiece);
            BoardState bestBoard = possibleBoards.get(0);
            int moveNumber = 0;
            //looks through the boards, evaluating each with a static evaluation function
            for(int i = 0; i<possibleBoards.size();i++) {
                if(evaluateBoard(bestBoard)<evaluateBoard(possibleBoards.get(i))) {
                    bestBoard = possibleBoards.get(i);
                    moveNumber = i;
                }
            }

            //finally, takes the index of the best move that is found
            return board.generateAllPossibleMoves(true,deckPiece).get(moveNumber);
        }

        //generates an arraylist of boards
        private ArrayList<BoardState> generateNextBoards(BoardState board, char deckPiece) {
            ArrayList<BoardState> toReturn = new ArrayList<>();
            //always reds turn
            ArrayList<String> movesList = board.generateAllPossibleMoves(true,deckPiece);
            //maps out all the possible moves
            for(int i = 0; i<movesList.size();i++) {
                    BoardState tBoard = new BoardState(board.GetBoard()); // initialise a new board
                    tBoard.PlaceTile(movesList.get(i));
                    toReturn.add(tBoard);
            }
            return toReturn;
        }

    //essentially the same as the feedforward algorithm from NN1HL
    //vectorises the board and then feeds it through the alphas and betas to get a result
    private double evaluateBoard(BoardState board) {
        //vectorise the board
        BasicVector bvec = new BasicVector(676);
        Tile[][] tList = board.getBoard();
        int rowIndex = 0;
        for (Tile[] c : tList) {
            for (Tile tile : c) {
                // add one for each tile
                if (tile.Alignment().equals(Colour.G)) {
                    bvec.set(rowIndex,-1.0);
                    //add -1
                } else if (tile.Alignment().equals(Colour.R)) {

                    bvec.set(rowIndex,1.0);
                    //add 1
                } else {
                    //add 0
                    //toInput.set(rowIndex,columnIndex,0);
                }
                rowIndex++;
            }
        }


        Vector Z = new BasicVector(M);

        for(int m = 0; m<M;m++) {
            double z;
            //System.out.println(x);
            z = sigmoid(biases.get(m,0)+alphas.getColumn(m).innerProduct(bvec)); //
            Z.set(m,z);
            //System.out.println("the value of Z is "+Z);
        }

        Vector T = new BasicVector(K);

        for(int k = 0; k<K;k++) {
            double t;
            t = biases.get(k,1)+betas.getColumn(k).innerProduct(Z);
            T.set(k,t);
        }
        //using the identity function here, but this code may become useful
        Vector Y = new BasicVector(K);
        for(int k=0;k<K;k++) {
            Y.set(k,T.get(k));
        }
        return -Y.get(0);

    }

    private void processAlphas(String line, int pointNumber) {
        String toParse = "";
        boolean added = true;
        int doubleCount = 0;

        for(int i = 0; i<line.toCharArray().length;i++) {
            if(!(line.toCharArray()[i] == ' ')){
                toParse = toParse + line.toCharArray()[i];
            } else {
                alphas.set(pointNumber, doubleCount, Double.parseDouble(toParse));
                        doubleCount++;
                        toParse = "";
                    }
                }
            }



    //we know that there will be only one column of betas as this is a regression task
    private void processBetas(String line, int pointNumber) {
        betas.set(pointNumber,0,Double.parseDouble(line));
    }


    private double sigmoid(double x) {
        return 1/(1 + Math.exp(-x));
    }




}

