package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import java.util.Arrays;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;




/**
 * Created by Tom on 20/08/2016.
 */

//A deck consists of a list of tiles,

public class Deck {

    private char currentPieceOrientation;
    private char currentPieceType;
    private String toString;
    private char[] pieceArray;
    private int SIZE = 10;

    private static final String URI_BASE = "gui/assets/";

    private int homeX, homeY;
    private int draggedX, draggedY;

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
                rotate();
                double rotation = this.getRotate();
                this.setRotate((rotation+90) % 360);
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
               // snapToGrid

                if(true) { //if placement is fine



                drop((int) event.getSceneX(), (int) event.getSceneY(), (int) this.getRotate(), pieceType);

                    this.setRotate(0);

                    this.setImage(new Image(BoardState.class.getResource(URI_BASE + currentPieceType + ".png").toString()));
                    setLayoutX(homeX);
                    setLayoutY(homeY);

                } else {


                //currently just snaps back home
                setLayoutX(homeX);
                setLayoutY(homeY);


                }
                event.consume();



            });
        }
    }









    //create a javafx object

    public ImageView makeDeck(Colour alignment, int x, int y) {
        char[] deck;
        homeX = x;
        homeY = y;

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

        return deckFX(100, homeX, homeY);




    }

    public void getNextPiece() {

       //occurs when the next piece needs to be shown
        // set currentPiece


    }

    public void drop(int x, int y, int rotation, char piece) {

        pieceArray = Arrays.copyOfRange(pieceArray, 1, pieceArray.length);
        currentPieceType = pieceArray[0];

        System.out.println("Attempted to place " + currentPieceType + " in " + currentPieceOrientation
                             + " orientation " + " at " + "("+x+","+y+")");

        // do some conversion from screen coords to board coords

        //if isPlacementValid() {

        //makePlacement(textField.getText());

        //}



        //update board placement

        //place piece and get next piece

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
