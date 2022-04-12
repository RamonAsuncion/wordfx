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
package main.tilemvc;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import main.model.WordleModel;

import javax.swing.*;

public class VirtualKeyboardController {

    private WordleModel wordleModel;

    /** The view of our Wordle implementation */
    private WordleView wordleView;

    /** The scene, to take care of keyboard typing */
    private Scene scene;

    public VirtualKeyboardController(WordleView wordleView, WordleModel wordleModel, Scene scene) {
        this.wordleView = wordleView;
        this.wordleModel = wordleModel;
        this.scene = scene;
        initEventHandlers();
    }

    private void initEventHandlers() {
        // If virtual keyboard is clicked
        for (Button b : wordleView.getLetterList()) {
            b.setOnMouseClicked(event -> {
                Text t = new Text(b.getText().toUpperCase());
                if (b.getText().equals("")) { this.wordleView.deleteLetter(); }
                else { this.wordleView.typeLetter(t); }
            });
        }

        // If typed on physical keyboard
        scene.setOnKeyPressed(event -> {
                Text t = new Text(event.getText().toUpperCase());
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    this.wordleView.deleteLetter();
                }
                else {
                    this.wordleView.typeLetter(t);
                }
            }
        );
    }
}
