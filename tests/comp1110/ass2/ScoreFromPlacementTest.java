package comp1110.ass2;

import org.junit.Test;

import static comp1110.ass2.TestUtility.PLACEMENTS;
import static comp1110.ass2.TestUtility.SCORES;
import static org.junit.Assert.assertTrue;

/**
 * Task 7
 * 
 * Determine the score for a player given a placement, following the
 * scoring rules for the game.
 */
public class ScoreFromPlacementTest {

    @Test(timeout=10000)
    public void testScore() {
        int score = -1;
        int ref = -1;
        for (int i = 0; i < PLACEMENTS.length; i++) {
            score = StratoGame.getScoreForPlacement(PLACEMENTS[i], true);
            ref = SCORES[i*2];
            assertTrue("Incorrect score for '"+PLACEMENTS[i]+"', expected "+ref+", but got "+score, score == ref);
            score = StratoGame.getScoreForPlacement(PLACEMENTS[i], false);
            ref = SCORES[1+(i*2)];
            assertTrue("Incorrect score for '"+PLACEMENTS[i]+"', expected "+ref+", but got "+score, score == ref);
        }
    }
}
