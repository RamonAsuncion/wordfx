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

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.GameState;
import main.model.WordleModel;
import main.tilemvc.WordleMain;
import main.view.WordleView;

import java.io.FileNotFoundException;

public class WordleController {

    /** The model of our Wordle implementation */
    private WordleModel wordleModel;

    /** The view of our Wordle implementation */
    private WordleView wordleView;

    /** The scene, to take care of keyboard typing */
    private Scene scene;

    /** The state of our row, initially unchecked */
    private GuessState guessState;

    private WordleMain wm;

    /**
     * Simple constructor for our Worldle game
     *
     * @param wordleView - The view of the game
     * @param wordleModel - The model of the game
     * @param scene - the scene to capture key events
     */
    public WordleController(WordleView wordleView, WordleModel wordleModel, Scene scene) {
        this.wordleView = wordleView;
        this.wordleModel = wordleModel;
        this.scene = scene;
        this.guessState = GuessState.UNCHECKED;
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
        this.scene.setOnKeyPressed(this::takeActionFromKeyPressed);

        this.wordleView.getWinButton().setOnMouseClicked(event -> startNewGame());
    }

    /**
     * Starts a new game if user would like to continue
     */
    private void startNewGame() {
        Stage stage = new Stage();
        wm = new WordleMain();
        try {
            wm.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        wm.setStreak(this.wordleModel.getCurrentWinStreak());
        wm.start(stage);
    }

    /**
     * Takes action from key pressed on virtual keyboard. Either type,
     * delete, or press enter.
     */
    private void takeActionFromVirtualKeyboard(Button b) {
        Text t = new Text(b.getText().toUpperCase());
        switch (b.getText()) {
            case "": //"" represents the delete key which is an icon
                deleteFromTile();
                break;
            case "ENTER":
                if (this.wordleModel.getColumn() == 4) {
                    this.guessState = GuessState.CHECKED;
                }
                break;
            default:
                typeToTile(t);
                break;
        }
    }

    /**
     * Takes action such as typing, deleting, or pressing enter
     * on physical keyboard
     *
     * @param event - {@KeyEvent} representing key pressed on physical keyboard
     */
    private void takeActionFromKeyPressed(KeyEvent event) {
        Text t = new Text(event.getText().toUpperCase());
        switch (event.getCode()) {
            case BACK_SPACE:
                if (this.wordleModel.getColumn() >= 0) {
                    deleteFromTile();
                }
                break;
            case ENTER:
                if (this.wordleModel.getColumn() == 4) {
                    // Flip the tiles, check guess, switch the guess state to checked, and jump to next guess
                    try {
                        this.wordleView.createEvaluator(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    this.guessState = GuessState.CHECKED;
                    this.wordleModel.incrementRow();
                }
                break;
            default:
                if (event.getCode().isLetterKey()) {
                    typeToTile(t);
                }
                break;
        }
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
        if ((this.guessState == GuessState.UNCHECKED) && (this.wordleModel.getColumn() < 4)) {
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
