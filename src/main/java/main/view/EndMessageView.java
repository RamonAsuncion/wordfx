/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/17/22
 * Time: 4:34 PM
 *
 * Project: csci205_final_project
 * Package: main.view
 * Class: EndMessageFinal
 *
 * Description:
 *
 * ****************************************
 */
package main.view;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.controller.WordleController;
import main.model.WordleModel;

public class EndMessageView {

    private final VBox endScreenHeader;
    /** The rectangle that shows up when you win */
    private Rectangle finalMessageRect;

    /** The label on the win screen */
    private Label finalMessageLabel;

    /** Stackpane for the win screen */
    private StackPane winStackPane;

    /** Border pane for the win screen */
    private BorderPane finalMessagePane;

    /** Label for the win screen */
    private Label groupNameLabel;

    /** The model of the game */
    private WordleModel wordleModel;

    /** The view of the game */
    private WordleView wordleView;

    /** Contains streak count if winner, secret word if loser */
    private Label winOrLoseInfo;

    private StackPane badWordStackPane;

    public EndMessageView(WordleModel wordleModel, WordleView wordleView) {
        // Initialize model and view
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;

        this.finalMessageRect = new Rectangle(300, 200);
        this.winStackPane = new StackPane();
        this.badWordStackPane = new StackPane();
        this.finalMessageLabel = new Label();
        this.finalMessagePane = new BorderPane();
        this.groupNameLabel = new Label("A game by Liv & Gang");
        this.endScreenHeader = new VBox();
    }

    /**
     * Adds two labels and a button to a rectangle, then adds to tiles stackpane
     * to stack on top of tiles
     *
     * @param winOrLose the string of the game ending result.
     */
    public void showEndScreen(String winOrLose, String streakOrSecretWord) {
        // Setting the styles
        this.finalMessageRect.setId("final-message-rect");
        this.groupNameLabel.setId("group-name-label");
        this.endScreenHeader.setId("end-screen-header");
        this.finalMessageLabel.setId("final-message-label");
        this.finalMessagePane.setId("final-message-pane");

        // Create message with the winner/loser result displayed.
        this.winOrLoseInfo = new Label(streakOrSecretWord);
        this.finalMessageLabel.setText(winOrLose);
        this.endScreenHeader.getChildren().addAll(this.finalMessageLabel, this.winOrLoseInfo);

        this.finalMessagePane.setAlignment(this.groupNameLabel, Pos.CENTER);

        // Placing sections of end message (header, play again button, group name)
        this.finalMessagePane.setTop(this.endScreenHeader);
        this.finalMessagePane.setCenter(this.wordleView.getPlayAgainBtn());
        this.finalMessagePane.setBottom(this.groupNameLabel);

        // Adding rectangle over tiles and text over rectangle
        this.winStackPane.getChildren().add(this.finalMessageRect);
        this.winStackPane.getChildren().add(this.finalMessagePane);
        this.wordleModel.getTileStackPane().getChildren().add(this.winStackPane);
        this.wordleView.getTileStack().getChildren().add(this.winStackPane);
        this.wordleView.getRoot().setCenter(this.wordleView.getTileStack());

        // Animate the screen with fading
        animateEndScreen();
    }

    /**
     * Makes the win screen fade in
     */
    public void animateEndScreen() {
        FadeTransition ft = new FadeTransition(Duration.millis(700), this.winStackPane);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
    }

    public void wordNotInListScreen() {
        Label wordNotInListMessage = new Label("Invalid word");
        wordNotInListMessage.setId("word-not-in-list-message");
        this.badWordStackPane.getChildren().add(wordNotInListMessage);
        this.wordleModel.getTileStackPane().getChildren().add(this.badWordStackPane);
        this.wordleView.getTileStack().getChildren().add(this.badWordStackPane);
        this.wordleView.getRoot().setCenter(this.wordleView.getTileStack());
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this.badWordStackPane);
        ft.setFromValue(3.0);
        ft.setToValue(0);
        ft.play();
    }

}
