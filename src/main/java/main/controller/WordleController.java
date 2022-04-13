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
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import main.model.WordleModel;
import main.view.WordleView;

public class WordleController {

    /** The model of our Wordle implementation */
    private WordleModel wordleModel;

    /** The view of our Wordle implementation */
    private WordleView wordleView;

    /** The scene, to take care of keyboard typing */
    private Scene scene;

    /** Current row we are in, representing a given guess */
    private int guess;

    /** Current column we are in, representing a letter of a guess */
    private int letterTile;

    /** The state of our guess, initially unchecked */
    private GuessState guessState;

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
        this.guess = 0;
        this.letterTile = -1;
        this.guessState = GuessState.UNCHECKED;
        initEventHandlers();
    }

    /**
     * Initialize event handling such as typing on keyboard or
     * clicking on virtual keyboard
     */
    private void initEventHandlers() {
        // If virtual keyboard is clicked
        for (Button b : wordleView.getLetterList()) {
            b.setOnMouseClicked(event -> takeActionFromVirtualKeyboard(b));
        }

        // If typed on physical keyboard
        scene.setOnKeyPressed(this::takeActionFromKeyPressed);

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
                if (letterTile == 4) {
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
     * @param event - {@KeyEvent} represeting key pressed on physical keyboard
     */
    private void takeActionFromKeyPressed(KeyEvent event) {
        Text t = new Text(event.getText().toUpperCase());
        switch (event.getCode()) {
            case BACK_SPACE:
                if (letterTile >= 0) {
                    deleteFromTile();
                }
                break;
            case ENTER:
                if (letterTile == 4) {
                    this.wordleView.flipTiles(this.wordleView.getListOfGuesses().get(guess));
                    this.guessState = GuessState.CHECKED;
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
        this.wordleView.updateDelete(guess, letterTile);
        letterTile--;
    }

    /**
     * Adds the letter to the next open tile. If on last tile of a guess,
     * must check guess before jumping to typing in the next row.
     *
     * @param t - letter to be added
     */
    private void typeToTile(Text t) {
        if ((this.guessState == GuessState.UNCHECKED) && (letterTile < 4)) {
            letterTile++;
            this.wordleView.updateType(t, guess, letterTile);
        }
        else if (this.guessState == GuessState.CHECKED) {
            guess++;
            letterTile = 0;
            this.guessState = GuessState.UNCHECKED;
            this.wordleView.updateType(t, guess, letterTile);
        }
    }
}
