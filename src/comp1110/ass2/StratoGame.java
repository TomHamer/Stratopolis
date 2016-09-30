package comp1110.ass2;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import comp1110.ass2.gui.Board;


/**
 * This class provides the text interface for the Strato Game
 *
 * The game is based directly on Gigamic's Stratopolis game
 * (http://boardgamegeek.com/boardgame/125022/stratopolis)
 */
public class StratoGame {
    private static BoardState b = new BoardState("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAKJLAPPABQKMCJNECRLRBLQGBNPKBLPHDPRPBJOFAMRRDOKHCMINCOTGAQITDTIIBRPKCKIDCRSOBTPCCSRQASGCAQKPBQUADPRLCQNJAIPSBGOIB");


    public static void main(String[] args) throws InterruptedException {
        //BoardState b = new BoardState("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBMLDANPLDNNBAONMCLOFAPQTC");
        //BoardState b = new BoardState("MMUANLOBLNBCONSCKLDAPOTCMLEBPLMBKNJDOLNBLOFAKJLAPPABQKMCJNECRLRBLQGBNPKBLPHDPRPBJOFAMRRDOKHCMINCOTGAQITDTIIBRPKCKIDCRSOBTPCCSRQASGCAQKPBQUADPRLCQNJAIPSBGOIB");
        long start = System.nanoTime();
        System.out.println(b.ConcurrentScore(true));
        long end = System.nanoTime();
        System.out.println("Concurrent time: " + (end - start));

        start = System.nanoTime();
        System.out.println(b.BoardScore(true));
        end = System.nanoTime();
        System.out.println("Sequential time: " + (end - start));
        //System.out.println("Green score is: " + b.BoardScore(true));
        //System.out.println("Red  score  is: " + b.BoardScore(false));
    }

    /**
     * Determine whether a tile placement is well-formed according to the following:
     * - it consists of exactly four characters
     * - the first character is in the range A .. Z
     * - the second character is in the range A .. Z
     * - the third character is in the range A .. U
     * - the fourth character is in the range A .. D
     *
     * @param tilePlacement A string describing a tile placement
     * @return True if the tile placement is well-formed
     */
    static boolean isTilePlacementWellFormed(String tilePlacement) {
        // FIXME Task 3: determine whether a tile placement is well-formed
        char x = tilePlacement.charAt(0);
        char y = tilePlacement.charAt(1);
        char p = tilePlacement.charAt(2);
        char o = tilePlacement.charAt(3);

        return (x >= 'A' && x <= 'Z' &&
                y >= 'A' && y <= 'Z' &&
                p >= 'A' && p <= 'U' &&
                o >= 'A' && o <= 'D');
    }

    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N four-character tile placements (where N = 1 .. 41)
     *  - each tile placement is well-formed
     *  - the first tile placement is 'MMUA'
     *  - the second tile placement (if any) is for a green tile
     *  - remaining tile placements alternate between red and green
     *  - no tile appears more than twice in the placement
     *
     * @param placement A string describing a placement of one or more tiles
     * @return True if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 4: determine whether a placement is well-formed
        if (placement == null) {return false;}
        if (placement.isEmpty()) {return false;}
        if (!placement.substring(0, 4).equals("MMUA")) {return false;}
        Set<Character> chars = new HashSet<>();
        Set<Character> chars2 = new HashSet<>();

        boolean green = true;

        for (int i = 4; i <= placement.length() - 4; i += 4) {
            if (!isTilePlacementWellFormed(placement.substring(i, i+4))) {return false;}
            if (green && (placement.charAt(i+2) <= 'J')) {return false;}
            if (!green && !(placement.charAt(i+2) <= 'J')) {return false;}
            if (chars.contains(placement.charAt(i+2))) {
                if (chars2.contains(placement.charAt(i+2))) {
                    return false;
                }
                chars2.add(placement.charAt(i+2));
            }
            green = !green;
            chars.add(placement.charAt(i+2));
        }
        return (placement.length() % 4 == 0);
    }

    /**
     * Determine whether a placement is valid.  To be valid, the placement must be well-formed
     * and each tile placement must follow the game's placement rules.

     * @param placement A placement string
     * @return True if the placement is valid
     */
    public static boolean isPlacementValid(String placement) {
        // FIXME Task 6: determine whether a placement is valid
        if (placement == null) {return false;}
        if (placement.isEmpty()) {return false;}

        BoardState board = new BoardState();

        if (!placement.substring(0, 4).equals("MMUA")) {return false;}
        else {board.PlaceTile("MMUA");}

        for (int i = 4; i <= placement.length() - 4; i += 4) {
            if (board.IsValidMove(placement.substring(i, i+4))) {
                board.PlaceTile(placement.substring(i, i+4));
            } else {
                return false;
            }
        }
        return isPlacementWellFormed(placement);
    }

    /**
     * Determine the score for a player given a placement, following the
     * scoring rules for the game.
     *
     * @param placement A placement string
     * @param green True if the score for the green player is requested,
     *              otherwise the score for the red player should be returned
     * @return the score for the requested player, given the placement
     */
    static int getScoreForPlacement(String placement, boolean green) {
        // FIXME Task 7: determine the score for a player given a placement
        return (new BoardState(placement)).BoardScore(green);

        //try {
        //    return (new BoardState(placement)).ConcurrentScore(green);
        //} catch(InterruptedException e) {
        //    e.printStackTrace();
        //}
        //return 0;
    }

    /**
     * Generate a valid move that follows from: the given placement, a piece to
     * play, and the piece the opponent will play next.
     *
     //* @param placement  A valid placement string indicating a game state
     //* @param piece  The piece you are to play ('A' to 'T')
     //* @param opponentsPiece The piece your opponent will be asked to play next ('A' to 'T' or 0 if last move).
     * @return A string indicating a valid tile placement that represents your move.
     */

    //generates all the possible moves, regardless of which player has what piece
    public static ArrayList<String> generateAllPossibleMoves(BoardState board, boolean redIsPlaying) {
        ArrayList<String> toReturn = new ArrayList<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char i : alphabet.toCharArray()) {
            for (char j : alphabet.toCharArray()) {
                for (char k : "ABCD".toCharArray()) {
                    if (redIsPlaying)
                        for (char p : "ABCDEFGHIJ".toCharArray()) {
                            String newMove = ""+i + j + p +k;
                            if (board.IsValidMove(newMove)) {
                                toReturn.add(newMove);
                            }
                        }
                    else {
                        for (char p : "KLMNOPQRST".toCharArray()) {
                            String newMove = "" + i + j + p + k;
                            if (board.IsValidMove(newMove)) {
                                toReturn.add(newMove);
                            }
                        }
                    }
                }
            }
        }
        return toReturn;
    }

    //generates all possible moves, given a piece that can be placed
    public static ArrayList<String> generateAllPossibleMoves(BoardState board, boolean redIsPlaying,char piece) {
        ArrayList<String> toReturn = new ArrayList<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char i : alphabet.toCharArray()) {
            for (char j : alphabet.toCharArray()) {
                for (char k : "ABCD".toCharArray()) {
                    String newMove = ""+i + j + piece + k;
                    if (board.IsValidMove(newMove)) {
                        toReturn.add(newMove);
                    }
                }
            }
        }


        return toReturn;


    }


    //simply using an easy player to generate a random move
    static String generateMove(String placement, char piece, char opponentsPiece) {
        //Board.EasyPlayer ep = new Board.EasyPlayer(true);
        return null;
    }
}
