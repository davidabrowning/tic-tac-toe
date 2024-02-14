package logic;

import domain.GameBoard;

public class GameBoardController {

    private GameBoard gameBoard;

    public GameBoardController() {
        gameBoard = new GameBoard();
    }

    public boolean allSpacesEmpty() {
        for(int i = 0; i < 9; i++) {
            if (this.isEmpty(i) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean noEmptySpaces() {
        for (int i = 0; i < 9; i++) {
            if (isEmpty(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int space) {
        return gameBoard.getSpace(space) != 'X' && gameBoard.getSpace(space) != 'O';
    }

    public void playSpace(int space) {

        // Check if space is outside of game board
        if (space < 0 || space > 8) {
            return;
        }

        // Check if space is already claimed
        if (isEmpty(space) == false) {
            return;
        }

        // Check if game is already over
        if (checkGameOver() == true) {
            return;
        }

        gameBoard.setSpace(space);

        if (checkGameOver() == false) {
            swapTurns();
        }
    }

    public char getSpace(int space) {
        return gameBoard.getSpace(space);
    }

    public void swapTurns() {
        if (getCurrentPlayer() == 'X') {
            setCurrentPlayer('O');
        } else {
            setCurrentPlayer('X');
        }
    }

    public char getCurrentPlayer() {
        return gameBoard.getCurrentTurn();
    }

    public void setCurrentPlayer(char currentPlayer) {
        gameBoard.setCurrentTurn(currentPlayer);
    }

    public boolean checkGameOver() {
        return checkVictory() || noEmptySpaces();
    }

    public boolean checkForDraw() {
        return checkGameOver() && !checkVictory();
    }

    public boolean checkVictory() {
        return checkHorizontalVictory() || checkVerticalVictory() || checkDiagonalVictory();
    }

    public boolean checkHorizontalVictory() {
        if (isEmpty(0) == false && getSpace(0) == getSpace(1) && getSpace(1) == getSpace(2)) {
            return true;
        }
        if (isEmpty(3) == false && getSpace(3) == getSpace(4) && getSpace(4) == getSpace(5)) {
            return true;
        }
        if (isEmpty(6) == false && getSpace(6) == getSpace(7) && getSpace(7) == getSpace(8)) {
            return true;
        }
        return false;
    }

    public boolean checkVerticalVictory() {
        if (isEmpty(0) == false && getSpace(0) == getSpace(3) && getSpace(3) == getSpace(6)) {
            return true;
        }
        if (isEmpty(1) == false && getSpace(1) == getSpace(4) && getSpace(4) == getSpace(7)) {
            return true;
        }
        if (isEmpty(2) == false && getSpace(2) == getSpace(5) && getSpace(5) == getSpace(8)) {
            return true;
        }
        return false;
    }

    public boolean checkDiagonalVictory() {
        if (isEmpty(0) == false && getSpace(0) == getSpace(4) && getSpace(4) == getSpace(8)) {
            return true;
        }
        if (isEmpty(2) == false && getSpace(2) == getSpace(4) && getSpace(4) == getSpace(6)) {
            return true;
        }
        return false;
    }

    public void reset() {
        gameBoard = new GameBoard();
    }

}
