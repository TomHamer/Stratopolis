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

    private Group deckFX(double size, int x, int y) {
        Group group = new Group();


        FXDraggablePiece FXDeck = new FXDraggablePiece(currentPieceType,size,x,y);

        group.getChildren().add(FXDeck);


        return  group;
    }




    private class FXDraggablePiece extends ImageView {

        double mouseX, mouseY;

        //doesnt deal with orientation yet


        FXDraggablePiece (char pieceType, double size, int x, int y) {
            System.out.println(URI_BASE + pieceType + ".png");
            setImage(new Image(BoardState.class.getResource(URI_BASE + pieceType + ".png").toString()));
            setFitHeight(size);
            setFitWidth(size);
            setOnScroll(event -> {// scroll to change orientation
                rotate();
                this.setRotate(180);
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

                if(true) {



                drop();

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

    public Group Deck(Colour alignment, int x, int y) {
        char[] deck;

        //generate a new deck
        if (alignment == Colour.R) {
            deck = new char[] {'A','B','C','D','E','F','G','H','I','J'};
        } {
            deck = new char[] {'K','L','M','N','O','P','Q','R','S','T'};
        }
        //shuffle the deck

        pieceArray = shuffle(deck);

        currentPieceOrientation = 'A';
        currentPieceType = pieceArray[0];

        return deckFX(100, x, y);




    }

    public void getNextPiece() {

       //occurs when the next piece needs to be shown
        // set currentPiece


    }

    public void drop() {

        pieceArray = Arrays.copyOfRange(pieceArray, 1, pieceArray.length);
        currentPieceType = pieceArray[0];
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
