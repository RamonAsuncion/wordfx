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

public class VirtualKeyboardController {

    /** The view of our Wordle implementation */
    private WordleView wordleView;

    /** The scene, to take care of keyboard typing */
    private Scene scene;

    public VirtualKeyboardController(WordleView wordleView, Scene scene) {
        this.wordleView = wordleView;
        this.scene = scene;
        initEventHandlers();
    }

    private void initEventHandlers() {
        // If virtual keyboard is clicked
        for (Button b : wordleView.getLetterList()) {
            b.setOnMouseClicked(event -> System.out.println(b.getText()));
        }

        // If typed on physical keyboard
        scene.setOnKeyPressed(event -> System.out.print(event.getCode()));
    }
}
