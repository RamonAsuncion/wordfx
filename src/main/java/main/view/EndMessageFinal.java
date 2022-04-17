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

public class EndMessageFinal {

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

    public EndMessageFinal(WordleModel wordleModel, WordleView wordleView) {
        // Initialize model and view
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;

        this.finalMessageRect = new Rectangle(300, 200);
        this.winStackPane = new StackPane();
        this.finalMessageLabel = new Label();
        this.finalMessagePane = new BorderPane();
        this.groupNameLabel = new Label();
    }

    /**
     * Adds two labels and a button to a rectangle, then adds to tiles stackpane
     * to stack on top of tiles
     *
     * @param winOrLose the string of the game ending result.
     */
    public void showEndScreen(String winOrLose, String streakOrSecretWord) {
        // Create rectangle
        this.finalMessageRect.setFill(Color.WHITE);
        this.finalMessageRect.setArcHeight(10.0d);
        this.finalMessageRect.setArcWidth(10.0d);
        this.finalMessageRect.setEffect(new DropShadow(10.0, Color.GREY));

        // Create label with the winning result displayed.
        VBox endScreenHeader = new VBox();
        Label importantInfo = new Label(streakOrSecretWord);
        this.finalMessageLabel.setText(winOrLose);
        this.finalMessageLabel.setId("finalMessageLabel");
        endScreenHeader.getChildren().addAll(this.finalMessageLabel, importantInfo);
        endScreenHeader.setAlignment(Pos.CENTER);

        // Create border pane
        this.finalMessagePane.setMaxSize(300, 200);
        this.finalMessagePane.setPadding(new Insets(18));
        this.finalMessagePane.setAlignment(this.finalMessageLabel, Pos.CENTER);
        this.finalMessagePane.setAlignment(this.groupNameLabel, Pos.CENTER);

        // Create label with names
        this.groupNameLabel.setId("nameLabel");
        this.groupNameLabel.setText("A game by Liv & Gang");

        // Add to border pane and stackpane
        this.finalMessagePane.setTop(endScreenHeader);
        this.finalMessagePane.setCenter(this.wordleView.getPlayAgainBtn());
        this.finalMessagePane.setBottom(this.groupNameLabel);
        this.winStackPane.getChildren().add(this.finalMessageRect);
        this.winStackPane.getChildren().add(this.finalMessagePane);
        this.wordleModel.getTileStackPane().getChildren().add(this.winStackPane);
        this.wordleView.getTileStack().getChildren().add(this.winStackPane);
        this.wordleView.getRoot().setCenter(this.wordleView.getTileStack());
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

}
