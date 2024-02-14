package domain;

public class GameBoard {

    private char[] spaces;
    private char currentTurn;

    public GameBoard() {
        spaces = new char[9];
        for (int i = 0; i < 9; i++) {
            spaces[i] = ' ';
        }
        currentTurn = 'X';
    }

    public char[] getSpaces() {
        return spaces;
    }

    public char getSpace(int space) {
        return spaces[space];
    }

    public void setSpace(int space) {
        spaces[space] = currentTurn;
    }

    public char getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(char newPlayer) {
        currentTurn = newPlayer;
    }

}
