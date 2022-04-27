/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/8/22
 * Time: 11:12 AM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: VirtualKeyboardController
 *
 * Description:
 *
 * ****************************************
 */
package main.controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.WordleModel;
import main.tilemvc.GuessEvaluator;
import main.tilemvc.WordleMain;
import main.view.EndMessageView;
import main.view.WordleView;
import java.io.IOException;

public class WordleController {

    /** The model of our Wordle implementation */
    private WordleModel wordleModel;

    /** The view of our Wordle implementation */
    private WordleView wordleView;

    /** The main class or our implementation */
    private WordleMain wm;

    /** The scene, to take care of keyboard typing */
    private Scene scene;

    /** The state of our row, initially unchecked */
    private GuessState guessState;

    /** Evaluates the guess based on the secret word */
    private GuessEvaluator evaluator;

    private EndMessageView endMessage;

    /**
     * Simple constructor for our Worldle game
     *
     * @param wordleView - The view of the game
     * @param wordleModel - The model of the game
     * @param scene - the scene to capture key events
     */
    public WordleController(WordleView wordleView, WordleModel wordleModel, Scene scene) {
        // Initialize view, model, and scene
        this.wordleView = wordleView;
        this.wordleModel = wordleModel;
        this.endMessage = new EndMessageView(this.wordleModel, this.wordleView);
        this.scene = scene;

        // Guess state starts out unchecked
        this.guessState = GuessState.UNCHECKED;

        // Initialize the guess evaluator and end message
        this.evaluator = new GuessEvaluator(this.wordleModel, this.wordleView, this.wordleModel.getSecretWord());

        initEventHandlers();
    }

    /**
     * Initialize event handling such as typing on keyboard or
     * clicking on virtual keyboard
     */
    private void initEventHandlers() {

        // If virtual keyboard is clicked
        for (Button b : this.wordleModel.getVk().getKeyboardKeys()) {
            b.setOnMouseClicked(event -> takeActionFromVirtualKeyboard(b));
        }

        // If typed on physical keyboard
        this.scene.setOnKeyPressed(event -> {
            try {
                takeActionFromKeyPressed(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.wordleView.getPlayAgainBtn().setOnMouseClicked(this::restartGame);

        // If this button is pressed, change to dark mode.
        this.wordleView.getDarkMode().setOnMouseClicked(event -> this.wordleView.getRoot().setId("theme1"));
    }

    /**
     * Starts a new game if user would like to continue
     *
     * @param event button handler
     */
    public void restartGame(Event event) {
        // Stops from implicitly exiting out the application before a
        // new stage is shown.
        Platform.setImplicitExit(false);

        wm = new WordleMain();

        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();

        // Close the scene a start a new one.
        stage.close();
        try {
            wm.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the streak of the game.
        wm.setStreak(this.wordleModel.getCurrentWinStreak());

        // Reapply last window position.
        stage.setX(stage.getX());
        stage.setY(stage.getY());

        // Reapply last window size.
        stage.setHeight(stage.getHeight());
        stage.setWidth(stage.getWidth());

        // Start the stage again.
        wm.start(stage);
    }

    /**
     * Takes action from key pressed on virtual keyboard. Either type,
     * delete, or press enter.
     */
    private void takeActionFromVirtualKeyboard(Button b) {
        // If the game is not playable return to the call stack.
        if (!this.wordleModel.getGameState().isPlayable()) {
            return;
        }

        Text t = new Text(b.getText().toUpperCase());
        switch (b.getText()) {
            // If user wants to delete last letter checked
            case "": //"" represents the delete key which is an icon
                if (this.wordleModel.getColumn() >= 0) {
                    deleteFromTile();
                }
                break;
            // If user wants to check guess
            case "ENTER":
                checkInput();
                break;

            // If user wants to simply type from virtual keyboard
            default:
                if (this.wordleView.isFlippingDone()) {
                    typeToTile(t);
                }
                break;
        }
    }

    /**
     * Takes action such as typing, deleting, or pressing enter
     * on physical keyboard
     *
     * @param event - {@KeyEvent} representing key pressed on physical keyboard
     */
    private void takeActionFromKeyPressed(KeyEvent event) throws IOException {
        // If the game is not playable return to the call stack.
        if (!this.wordleModel.getGameState().isPlayable()) {
            return;
        }

        Text t = new Text(event.getText().toUpperCase());
        switch (event.getCode()) {
            // If user wants to delete a letter
            case BACK_SPACE:
                if (this.wordleModel.getColumn() >= 0) {
                    deleteFromTile();
                }
                break;

            // If user wants to check a guess
            case ENTER:
                checkInput();
                break;

            // If user types on keyboard
            default:
                if (event.getCode().isLetterKey() && this.wordleView.isFlippingDone()) {
                    typeToTile(t);
                }
                break;
        }
    }

    /**
     * Checks for validity of input. Checks if input has enough letters
     * and if it is in the word list.
     */
    private void checkInput() {
        StringBuffer guess = getGuessFromTiles();
        // Ensure guess is valid by length and being in word list
        if (this.wordleModel.getColumn() == (this.wordleModel.getWordLength() - 1)) {
            if (this.wordleModel.getReader().isWordInSet(guess.toString().toLowerCase())) {
                // Evaluate guess, switch the guess state to checked, and jump to next guess
                this.evaluator.createEvaluator(guess.toString().toLowerCase());
                this.guessState = GuessState.CHECKED;
                this.wordleModel.incrementRow();
            }
            else {
                this.endMessage.invalidInputScreen("Invalid word");
                this.wordleView.horizontalShakeTiles();
            }
        }
        else {
            this.endMessage.invalidInputScreen("Not enough letters");
            this.wordleView.horizontalShakeTiles();
        }
    }

    /**
     * @return the guess from reading the labels aka tiles
     */
    private StringBuffer getGuessFromTiles() {
        StringBuffer s = new StringBuffer("");
        this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).forEach(t -> s.append(t.getText()));
        return s;
    }

    /**
     * Deletes the letter from the latest tile that contains the letter
     */
    private void deleteFromTile() {
        this.wordleView.updateDelete(this.wordleModel.getRow(), this.wordleModel.getColumn());
        this.wordleModel.decrementColumn();
    }

    /**
     * Adds the letter to the next open tile. If on last tile of a guess,
     * must check guess before jumping to typing in the next row.
     *
     * @param letter - letter to be added
     */
    private void typeToTile(Text letter) {
        if ((this.guessState == GuessState.UNCHECKED) && (this.wordleModel.getColumn() < (this.wordleModel.getWordLength() - 1))) {
            this.wordleModel.incrementColumn();
            this.wordleView.updateTyping(letter, this.wordleModel.getRow(), this.wordleModel.getColumn());
        }
        else if (this.guessState == GuessState.CHECKED) {
            this.wordleModel.setColumn(0);
            this.guessState = GuessState.UNCHECKED;
            this.wordleView.updateTyping(letter, this.wordleModel.getRow(), this.wordleModel.getColumn());
        }
    }
}
