package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardControllerTest {

    GameBoardController gameBoardController;

    @BeforeEach
    void setUp() {
        gameBoardController = new GameBoardController();
    }

    @Test
    void allSpacesInitiallyEmpty() {
        assertTrue(gameBoardController.allSpacesEmpty());
    }

    @Test
    void allSpacesNotEmptyAfterFirstPlay() {
        gameBoardController.playSpace(1);
        assertFalse(gameBoardController.allSpacesEmpty());
    }

    @Test
    void allSpacesEmptyAfterInitialIllegalPlay() {
        gameBoardController.playSpace(9);
        assertTrue(gameBoardController.allSpacesEmpty());
    }

    @Test
    void currentPlayerSwitchesAfterTurn() {
        char firstPlayer = gameBoardController.getCurrentPlayer();
        gameBoardController.playSpace(1);
        char secondPlayer = gameBoardController.getCurrentPlayer();
        assertNotEquals(firstPlayer, secondPlayer);
    }

    @Test
    void currentPlayerDoesNotSwitchAfterBadAttempt() {
        gameBoardController.playSpace(3);
        char firstPlayer = gameBoardController.getCurrentPlayer();
        gameBoardController.playSpace(3);
        char secondPlayer = gameBoardController.getCurrentPlayer();
        assertEquals(firstPlayer, secondPlayer);
    }

    @Test
    void checkVictoryIsInitiallyFalse() {
        assertFalse(gameBoardController.checkVictory());
    }

    @Test
    void checkVictoryIsTrueAfterHorizontalMatch() {
        gameBoardController.playSpace(0);
        gameBoardController.playSpace(7);
        gameBoardController.playSpace(1);
        gameBoardController.playSpace(8);
        gameBoardController.playSpace(2);
        assertTrue(gameBoardController.checkVictory());
    }

    @Test
    void checkVictoryIsTrueAfterVerticalMatch() {
        gameBoardController.playSpace(1);
        gameBoardController.playSpace(6);
        gameBoardController.playSpace(4);
        gameBoardController.playSpace(8);
        gameBoardController.playSpace(7);
        assertTrue(gameBoardController.checkVictory());
    }

    @Test
    void checkVictoryIsTrueAfterDiagonalMatch() {
        gameBoardController.playSpace(0);
        gameBoardController.playSpace(1);
        gameBoardController.playSpace(4);
        gameBoardController.playSpace(3);
        gameBoardController.playSpace(8);
        assertTrue(gameBoardController.checkVictory());
    }

    @Test
    void cannotPlayAnotherSpaceAfterVictory() {
        gameBoardController.playSpace(0);
        gameBoardController.playSpace(1);
        gameBoardController.playSpace(4);
        gameBoardController.playSpace(3);
        gameBoardController.playSpace(8);
        gameBoardController.playSpace(7);
        assertTrue(gameBoardController.isEmpty(7));
    }

    @Test
    void boardIsResetAfterPostVictoryReset() {
        gameBoardController.playSpace(0);
        gameBoardController.playSpace(1);
        gameBoardController.playSpace(4);
        gameBoardController.playSpace(3);
        gameBoardController.playSpace(8);
        gameBoardController.reset();
        assertTrue(gameBoardController.allSpacesEmpty());
    }

    @Test
    void allSpacesPlayedReturnsFalseWhenEmptySpace() {
        assertFalse(gameBoardController.noEmptySpaces());
    }

    @Test
    void noEmptySpacesReturnsTrueWhenNoEmptySpaces() {
        gameBoardController = setUpDrawnBoard(gameBoardController);
        assertTrue(gameBoardController.noEmptySpaces());
    }

    @Test
    void checkForDrawIsFalseWhenGameStarts() {
        assertFalse(gameBoardController.checkForDraw());
    }

    @Test
    void checkForDrawIsTrueWhenAllSpacesPlayedWithoutVictory() {
        gameBoardController = setUpDrawnBoard(gameBoardController);
        assertTrue(gameBoardController.checkForDraw());
    }

    @Test
    void checkGameOverIsFalseInitially() {
        assertFalse(gameBoardController.checkGameOver());
    }

    @Test
    void checkGameOverIsTrueAfterVictory() {
        gameBoardController = setUpWonBoard(gameBoardController);
        assertTrue(gameBoardController.checkGameOver());
    }

    @Test
    void checkGameOverIsTrueAfterDraw() {
        gameBoardController = setUpDrawnBoard(gameBoardController);
        assertTrue(gameBoardController.checkGameOver());
    }

    @Test
    void playerShouldNotChangeAfterWinningPlay() {
        gameBoardController.playSpace(0);
        gameBoardController.playSpace(1);
        gameBoardController.playSpace(4);
        gameBoardController.playSpace(3);
        char lastPlayerToPlay = gameBoardController.getCurrentPlayer();
        gameBoardController.playSpace(8);
        char playerAfterLastPlay = gameBoardController.getCurrentPlayer();
        assertEquals(lastPlayerToPlay, playerAfterLastPlay);
    }


    private GameBoardController setUpWonBoard(GameBoardController gbc) {
        gbc.playSpace(0);
        gbc.playSpace(7);
        gbc.playSpace(1);
        gbc.playSpace(8);
        gbc.playSpace(2);
        return gbc;
    }

    private GameBoardController setUpDrawnBoard(GameBoardController gbc) {
        gbc.playSpace(0);
        gbc.playSpace(1);
        gbc.playSpace(2);
        gbc.playSpace(3);
        gbc.playSpace(5);
        gbc.playSpace(4);
        gbc.playSpace(6);
        gbc.playSpace(8);
        gbc.playSpace(7);
        return gbc;
    }


}