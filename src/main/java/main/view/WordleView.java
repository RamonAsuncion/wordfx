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

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.model.GameState;
import main.model.WordleModel;
import main.tilemvc.GuessEvaluator;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordleView {

    /** The root node containing all three nodes above */
    private BorderPane root;

    /** The evaluator of our guess */
    private GuessEvaluator guessEval;

    /** The model of the game */
    private WordleModel wordleModel;

    /** The rectangle that shows up when you win */
    private Rectangle winRect;

    /** The label on the win screen */
    private Label winLabel;

    /** Stackpane for the win screen */
    private StackPane winStackPane;

    /** Border pane for the win screen */
    private BorderPane winBorderPane;

    /** Button for the win screen */
    private Button winButton;

    /** Label for the win screen */
    private Label nameLabel;

    /** Stackpane to contain tiles and win screen */
    private StackPane tileStack;

    /** The secret word */
    private String secretWord;

    public Button getWinButton() { return winButton; }

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
        this.winRect = new Rectangle(300, 200);
        this.winStackPane = new StackPane();
        this.winLabel = new Label();
        this.winBorderPane = new BorderPane();
        this.winButton = new Button();
        this.nameLabel = new Label();

        initSceneGraph();
        initSecretWord();
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
    }

    private void initSecretWord() {
        this.secretWord = this.guessEval.createRandomWord();
    }

    /**
     * Creates an evaluator for a given guess. The evaluator will take care of
     * finding if a letter is in the correct position, misplaced, or not even
     * in the word.
     *
     * @param guess - Given guess by user
     */
    public void createEvaluator(ArrayList<Label> guess) throws FileNotFoundException {
        StringBuffer s = new StringBuffer("");
        for (Label tile : guess) {
            s.append(tile.getText());
        }
        this.guessEval = new GuessEvaluator(secretWord, s.toString());
        String evaluation = this.guessEval.analyzeGuess(s.toString());
        performScreenAnimation(evaluation, s.toString());
        if (evaluation.equals("*****")) {
            this.wordleModel.setGameState(GameState.GAME_WINNER);
            this.wordleModel.incrementCurrentWinStreak();
            showWinScreen();
        }
    }

    /**
     * Will perform the action of flipping a tile on the screen for a
     * given duration
     *
     * @param tile - specific tile to be flipped
     */
    public void flipTiles(Label tile) {
        RotateTransition rotation;
        rotation = new RotateTransition(Duration.seconds(1));
        rotation.setNode(tile);
        rotation.setAxis(Rotate.X_AXIS);
        rotation.setFromAngle(0);
        rotation.setToAngle(360);
        rotation.setCycleCount(1);
        rotation.play();
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
     * Performs changes of color on the tile and on the virtual keyboard with css styling as well as filps tiles.
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
                flipTiles(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i));
                changeTileColor("exact", i);
                changeKeyboardLetterColor("exact", guess.charAt(i));
            }
            // Misplaced letter
            if (evaluation.charAt(i) == ('+')) {
                flipTiles(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i));
                changeTileColor("misplaced", i);
                changeKeyboardLetterColor("misplaced", guess.charAt(i));
            }
            // Wrong letter
            if (evaluation.charAt(i) == ('-')) {
                flipTiles(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i));
                changeTileColor("wrong", i);
                changeKeyboardLetterColor("wrong", guess.charAt(i));
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
    private void changeKeyboardLetterColor(String style, char letter) {
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

    /**
     * Adds two labels and a button to a rectangle, then adds to tiles stackpane
     * to stack on top of tiles
     */
    public void showWinScreen() {
        // Create rectangle
        this.winRect.setFill(Color.WHITE);
        this.winRect.setArcHeight(10.0d);
        this.winRect.setArcWidth(10.0d);
        this.winRect.setEffect(new DropShadow(10.0, Color.GREY));

        // Create "You won" label
        this.winLabel.setText("You won!");
        this.winLabel.setId("winLabel");

        // Create play again button
        this.winButton.setText("Play again?");
        System.out.println("STREAK: " + this.wordleModel.getCurrentWinStreak());
        this.winButton.setPrefSize(100, 50);
        this.winButton.setId("winButton");

        // Create border pane
        this.winBorderPane.setMaxSize(300, 200);
        this.winBorderPane.setPadding(new Insets(18));
        this.winBorderPane.setAlignment(this.winLabel, Pos.CENTER);
        this.winBorderPane.setAlignment(this.nameLabel, Pos.CENTER);

        // Create label with names
        this.nameLabel.setId("nameLabel");
        this.nameLabel.setText("A game by Liv & Gang");

        // Add to border pane and stackpane
        this.winBorderPane.setTop(this.winLabel);
        this.winBorderPane.setCenter(this.winButton);
        this.winBorderPane.setBottom(this.nameLabel);
        this.winStackPane.getChildren().add(this.winRect);
        this.winStackPane.getChildren().add(this.winBorderPane);
        this.wordleModel.getTileStackPane().getChildren().add(this.winStackPane);
        tileStack.getChildren().add(this.winStackPane);
        this.root.setCenter(tileStack);

        animateWinScreen();
    }

    /**
     * Makes the win screen fade in
     */
    public void animateWinScreen() {
        FadeTransition ft = new FadeTransition(Duration.millis(700), this.winStackPane);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
    }
}
