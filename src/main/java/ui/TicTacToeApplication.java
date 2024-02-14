package ui;

import logic.GameBoardController;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class TicTacToeApplication extends Application {

    private GameBoardController gameBoardController;
    private final static int gameBoardSpaceHeight = 200;
    private final static int gameBoardSpaceWidth = 200;


    @Override
    public void start(Stage stage) throws Exception {

        gameBoardController = new GameBoardController();
        List<Button> gameBoardSpaceButtons = new ArrayList<>();
        Font mediumFont = new Font(40);
        Font largeFont = new Font(60);

        // Configure next turn tracker
        Label nextTurnLabel = new Label("Next turn: " + gameBoardController.getCurrentPlayer());
        nextTurnLabel.setFont(mediumFont);

        // Configure playing area
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 9; i++) {
            int spaceButtonNum = i;
            Button spaceButton = new Button(String.valueOf(gameBoardController.getSpace(i)));
            spaceButton.setPrefWidth(gameBoardSpaceWidth);
            spaceButton.setPrefHeight(gameBoardSpaceHeight);
            spaceButton.setFont(largeFont);
            spaceButton.setOnAction(event -> {
                System.out.println("You clicked on button " + spaceButtonNum);
                gameBoardController.playSpace(spaceButtonNum);
                spaceButton.setText(String.valueOf(gameBoardController.getSpace(spaceButtonNum)));
                if (gameBoardController.checkVictory()) {
                    nextTurnLabel.setText("Game over. " + gameBoardController.getCurrentPlayer() + " wins!");
                } else {
                    nextTurnLabel.setText("Next turn: " + gameBoardController.getCurrentPlayer());
                }
            });
            gameBoardSpaceButtons.add(spaceButton);
            int col = i % 3;
            int row = i / 3;
            gridPane.add(spaceButton, col, row);
        }

        // Configure the reset button
        Button resetButton = new Button("Reset game board");
        resetButton.setFont(mediumFont);
        resetButton.setMaxWidth(gameBoardSpaceWidth * 3);
        resetButton.setOnAction(event -> {
            gameBoardController.reset();
            nextTurnLabel.setText("Next turn: " + gameBoardController.getCurrentPlayer());
            for (Button button : gameBoardSpaceButtons) {
                button.setText(" ");
            }
        });

        // Set column widths and row heights
        for (int i = 0; i < 3; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(gameBoardSpaceWidth));
            gridPane.getRowConstraints().add(new RowConstraints(gameBoardSpaceHeight));
        }

        // Add elements to the scene
        VBox vbox = new VBox();
        vbox.getChildren().add(nextTurnLabel);
        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(resetButton);
        Scene scene = new Scene(vbox);

        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        System.out.println("Starting application.");
        Application.launch(TicTacToeApplication.class);
        System.out.println("Closing application.");
    }

}
