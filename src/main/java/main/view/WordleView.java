/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/8/22
 * Time: 8:24 AM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: WordleView
 *
 * Description:
 *
 * ****************************************
 */
package main.view;

import javafx.animation.*;
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
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.model.GameState;
import main.model.WordleModel;
import main.tilemvc.GuessEvaluator;
import main.tilemvc.UsedWords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class WordleView {

    /** The root node containing all three nodes above */
    private BorderPane root;

    /** The evaluator of our guess */
    private GuessEvaluator guessEval;

    /** The model of the game */
    private WordleModel wordleModel;

    /** Create button for darkmode */
    private Button darkMode;

    private boolean flippingDone;

    /** Stackpane to contain tiles and win screen */
    private StackPane tileStack;

    private Button playAgainBtn;

    public Button getPlayAgainBtn() { return playAgainBtn; }

    public StackPane getTileStack() { return tileStack; }

    public boolean isFlippingDone() { return flippingDone; }

    /** Create the getter for the darkmode button */
    public Button getDarkMode() {
        return darkMode;
    }

    /**
     * @return the root containing header, tiles, and keyboard, to create our scene
     */
    public BorderPane getRoot() { return root; }

    /**
     * Simple constructor to initialize the scene graph
     */
    public WordleView(WordleModel wordleModel) {
        this.wordleModel = wordleModel;
        // Get the secret word

        // Initialize the root for our display
        this.root = new BorderPane();
        this.root.setId("background");
        this.darkMode = new Button("DARK\n MODE");
        this.darkMode.getStyleClass().add("dark-mode-button");

        this.playAgainBtn = new Button();
        this.playAgainBtn.setText("Play again?");
        this.playAgainBtn.setPrefSize(100, 50);
        this.playAgainBtn.setId("playAgainBtn");
        initSceneGraph();
    }

    /**
     * Initializes the scene graph containing header, tiles, and virtual keyboard
     */
    private void initSceneGraph() {
        // Set the scene accordingly
        tileStack = new StackPane(this.wordleModel.getTiles().getTiles()); //this must be a stack for final message
        this.root.setCenter(tileStack);
        this.root.setBottom(this.wordleModel.getVk().getKeyboard());
        this.root.setTop(this.wordleModel.getHeader().getHeaderSection());
        System.out.println("Secret word " + this.wordleModel.getSecretWord());
    }

    /**
     * Will perform the action of flipping a tile on the screen for a
     * given duration
     *
     * @param tile - specific tile to be flipped
     */
    public boolean flipTiles(Label tile, int index) {
        flippingDone = false;
        if (index == 0) {
            RotateTransition rotation;
            rotation = new RotateTransition(Duration.seconds(1));
            rotation.setNode(tile);
            rotation.setAxis(Rotate.X_AXIS);
            rotation.setFromAngle(0);
            rotation.setToAngle(360);
            rotation.setCycleCount(1);
            rotation.play();
        }
        else {
            int delay = 0;
            if (index == 1) {
                delay = 500;
            }
            if (index == 2) {
                delay = 1000;
            }
            if (index == 3) {
                delay = 1500;
            }
            if (index == 4) {
                delay = 2000;
                flippingDone = true;
            }
            RotateTransition rotation;
            rotation = new RotateTransition(Duration.seconds(1));
            rotation.setNode(tile);
            rotation.setDelay(Duration.millis(delay));
            rotation.setAxis(Rotate.X_AXIS);
            rotation.setFromAngle(0);
            rotation.setToAngle(360);
            rotation.setCycleCount(1);
            rotation.play();
        }
        return flippingDone;
    }

    // TODO: If the answer is not in the word list shake.
    public void horizontalShakeTiles(ArrayList<Label> badTiles) {
    }

    /**
     * Takes care of binding what is being typed to the labels on the screen
     *
     * @param letter - Letter being typed
     * @param guess - Current guess user is typing on
     * @param letterTile - Next available tile
     */
    public void updateTyping(Text letter, int guess, int letterTile) {
        this.wordleModel.getListOfGuesses().get(guess).get(letterTile).textProperty()
                .bind(letter.textProperty());
    }

    /**
     * Deletes letter from the screen by binding a white space character to the label
     *
     * @param guess - Current guess user is typing on
     * @param letterTile - Next available tile
     */
    public void updateDelete(int guess, int letterTile) {
        this.wordleModel.getListOfGuesses().get(guess).get(letterTile).textProperty()
                .bind(new Text(" ").textProperty());
    }

    /**
     * Performs changes of color on the tile and on the virtual keyboard with css styling as well as flips tiles.
     * Green means letter on the guess is positioned the same way as on the secret word. Yellow means secret word
     * has such letter but misplaced. Grey simply means not in secret word. The flip is to reveal the colors.
     *
     * @param evaluation - String containing Dash - (becomes grey), Plus + (becomes yellow), and Asterisk * (becomes green)
     * @param guess - guess given by user (to be compared with secret word)
     */
    public void performScreenAnimation(String evaluation, String guess) {
        // Loop through our evaluation
        for (int i = 0; i < 5; i++) {
            // Correctly positioned letter
            if (evaluation.charAt(i) == ('*')) {
                flipTiles(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i), i);
                changeTileColor("exact", i);
                changeKeyboardLetterColor("exact", Character.toString(guess.charAt(i)));
            }
            // Misplaced letter
            if (evaluation.charAt(i) == ('+')) {
                flipTiles(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i), i);
                changeTileColor("misplaced", i);
                changeKeyboardLetterColor("misplaced", Character.toString(guess.charAt(i)));
            }
            // Wrong letter
            if (evaluation.charAt(i) == ('-')) {
                flipTiles(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i), i);
                changeTileColor("wrong", i);
                changeKeyboardLetterColor("wrong", Character.toString(guess.charAt(i)));
            }
        }
    }

    /**
     * Changes the color of the tiles containing the guessed letters (forming a guess word) on the screen
     *
     * @param style - css style: exact for green, misplaced for yellow, wrong for dark grey
     * @param index - index in which the letter is located on the guess
     */
    private void changeTileColor(String style, int index) {
        this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(index).getStyleClass().add(style);
    }

    /**
     * Changes the color on the virtual keyboard following the same logic as
     * the color changes in the tiles where letters are typed
     *
     * @param style - css style to style the color of the virtual key (exact, misplaced, or wrong)
     * @param letter - Current letter in guess
     */
    private void changeKeyboardLetterColor(String style, String letter) {
        // Obtain the index of current letter in guess and change its style
        int index = this.wordleModel.getLetterList().indexOf(letter);

        // Only possibility to change colors is from yellow to green, nothing else
        if (this.wordleModel.getKeysList().get(index).getStyleClass().toString().contains("misplaced") && (style.equals("exact"))) {
            // Remove the yellow styling
            this.wordleModel.getKeysList().get(index).getStyleClass().remove("misplaced");
            // Add the green
            this.wordleModel.getKeysList().get(index).getStyleClass().add("exact");
        }

        // Only edit the color of the keyboard key if it is not colored yet. Colored remains the same
        else if (!this.wordleModel.getKeysList().get(index).getStyleClass().contains("misplaced") &&
                !this.wordleModel.getKeysList().get(index).getStyleClass().contains("exact") &&
                !this.wordleModel.getKeysList().get(index).getStyleClass().contains("wrong")) {
            this.wordleModel.getKeysList().get(index).getStyleClass().add(style);
        }
    }
}
