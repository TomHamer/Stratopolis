package comp1110.ass2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.util.Arrays;
import comp1110.ass2.gui.Board;

/**
 * Created by Tom on 20/08/2016.
 */

//A deck consists of a list of tiles,

public class Deck {

    private char currentPieceOrientation;
    private final int boardCoordX = (Board.getBoardWidth() - Board.getSquareSize()* 26) / 2 - 10;
    private final int boardCoordY = (Board.getBoardHeight() - Board.getSquareSize() * 26 - 50) / 2 - 10;
    private char currentPieceType;
    private String toString;
    private char[] pieceArray;
    private Board board;

    private static final String URI_BASE = "gui/assets/";

    private int homeX, homeY;
    private final int SIZE_OF_DECK = 46; // this is not the number of pieces in the deck, it is the physical size of the icon


    private ImageView deckFX(double size, int x, int y) { return new FXDraggablePiece(currentPieceType,size,x,y); }

    private class FXDraggablePiece extends ImageView {

        double mouseX, mouseY;

        //doesnt deal with orientation yet


        FXDraggablePiece (char pieceType, double size, int x, int y) {
            System.out.println(URI_BASE + pieceType + ".png");
            setImage(new Image(BoardState.class.getResource(URI_BASE + pieceType + ".png").toString()));
            setLayoutX(homeX);
            setLayoutY(homeY);
            setFitHeight(size);
            setFitWidth(size);
            setOnScroll(event -> {// scroll to change orientation

                double rotation = this.getRotate();
                this.setRotate((rotation % 360) + 90);
                if (getRotate() == 0) {
                    currentPieceOrientation = 'A';
                } else if (getRotate() == 90) {
                    currentPieceOrientation = 'B';
                } else if (getRotate() == 180) {
                    currentPieceOrientation = 'C';
                } else if (getRotate() == 270) {
                    currentPieceOrientation = 'D';
                }
                event.consume();
            });
            setOnMousePressed(event -> {

                // mouse press indicates begin of drag
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

            });
            setOnMouseDragged(event -> {      // mouse is being dragged
                //toFront();
                double movementX = event.getSceneX() - mouseX;
                double movementY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);

                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                event.consume();
            });
            setOnMouseReleased(event -> {     // drag is complete
                int xDrop = (int) this.getLayoutX();
                int yDrop = (int) this.getLayoutY();
                int squareX = (int) (xDrop-boardCoordX)/23;
                int squareY = (int) (yDrop-boardCoordY)/23;


               // snapToGrid
                char yLetter = 'A';
                char xLetter = 'A';


                System.out.println("Attempted to place " + currentPieceType + " in " + currentPieceOrientation
                        + " orientation " + " at " + "("+xDrop+","+yDrop+")");



                if (squareX<26 && 0<= squareX && 0<=squareY && squareY<26) {
                    switch (currentPieceOrientation) {

                        case 'A':
                            break; //nothing to be done

                        case 'B':
                            squareX++;
                            break;
                        case 'C':
                            squareX++;
                            squareY++;
                            break;
                        case 'D':
                            squareY++;
                            break;
                    }

                    xLetter = (char) ('A' + squareX);
                    yLetter = (char) ('A' + squareY);


                    String newPiece = "" + xLetter + yLetter + currentPieceType + currentPieceOrientation;


                    if (board.getBoardState().IsValidMove(newPiece)) {


                        board.addPlacement(newPiece);
                        pieceArray = Arrays.copyOfRange(pieceArray, 1, pieceArray.length);
                        currentPieceType = pieceArray[0];

                        this.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));
                        System.out.println("successfully placed a piece!");


                    }
                }


                    this.setRotate(0);
                    currentPieceOrientation = 'A';


                    setLayoutX(homeX);
                    setLayoutY(homeY);




                event.consume();



            });
        }
    }









    //create a javafx object

    public ImageView makeDeck(Board theBoard, Colour alignment, int x, int y) {
        char[] deck;
        homeX = x;
        homeY = y;
        board = theBoard;

        //generate a new deck
        if (alignment == Colour.R) {
            deck = new char[] {'A','B','C','D','E','F','G','H','I','J'};
        } else  {
            deck = new char[] {'K','L','M','N','O','P','Q','R','S','T'};
        }
        //shuffle the deck

        pieceArray = shuffle(deck);

        currentPieceOrientation = 'A';
        currentPieceType = pieceArray[0];

        return deckFX(SIZE_OF_DECK, homeX, homeY);

    }

    public void getNextPiece() {

       //occurs when the next piece needs to be shown
        // set currentPiece


    }


    public void rotate() {
        switch(currentPieceOrientation){
            case 'A':
                currentPieceOrientation ='B';
                break;
            case 'B':
                currentPieceOrientation ='C';
                break;
            case 'C':
                currentPieceOrientation ='D';
                break;
            case 'D':
                currentPieceOrientation ='A';
                break;
        }


    }

    private char[] shuffle(char[] list) {

        Random random = new Random();

        for(int i = 0; i<list.length; i++) {
            int rand = random.nextInt(list.length);

            char elem1 =  list[i];
            char elem2 =  list[rand];
            list[rand] = elem1;
            list[i] = elem2;


        }


        return list;
    }



}
