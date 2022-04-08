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

import javafx.scene.control.Button;

public class VirtualKeyboardController {
    private VirtualKeyboard theView;

    public VirtualKeyboardController(VirtualKeyboard theView) {
        this.theView = theView;
        initEventHandlers();
    }

    private void initEventHandlers() {
        for (Button b : this.theView.getLetters()) {
            b.setOnMouseClicked(event -> {
                System.out.println("hello");
            });
        }
    }
}
