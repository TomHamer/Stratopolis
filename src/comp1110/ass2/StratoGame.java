package comp1110.ass2;

import java.util.*;


/**
 * This class provides the text interface for the Strato Game
 *
 * The game is based directly on Gigamic's Stratopolis game
 * (http://boardgamegeek.com/boardgame/125022/stratopolis)
 */
// Written jointly by Calum Snowdon and Tom Hamer
public class StratoGame {
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
    public static boolean isTilePlacementWellFormed(String tilePlacement) {
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
    public static boolean isPlacementWellFormed(String placement) {
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

    //generates a random move by generating all the moves and selecting a random one
    public static String generateMove(String placement, char piece, char opponentsPiece) {
        char[] redDeck = new char[] {'A','B','C','D','E','F','G','H','I','J'};
        boolean playerIsRed = false;
        for(char i:redDeck) {
            if(piece == i) {
                playerIsRed = true; // find if the player is red or green
                break;
            }
        }
        Random rand = new Random();
        ArrayList<String> mList = (new BoardState(placement)).generateAllPossibleMoves(playerIsRed,piece);
        int r = rand.nextInt(mList.size());
        return mList.get(r); // return the first randomly generated move

    }

}
