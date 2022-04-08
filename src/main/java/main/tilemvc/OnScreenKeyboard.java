/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/6/22
 * Time: 11:35 PM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: OnScreenKeyboard
 *
 * Description:
 *
 * ****************************************
 */
package main.tilemvc;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class OnScreenKeyboard {


    public Button createKey(Character letter) {
        Button key = new Button(letter.toString());
        key.getStyleClass().add("keyboard-letter");
        return key;
    }

    public Button createEnterKey(String enter) {
        Button enterKey = new Button(enter);
        enterKey.getStyleClass().add("keyboard-letter");
        enterKey.getStyleClass().add("enter");
        return enterKey;
    }

    public Button createDeleteKey() {
        Button delKey = new Button();
        delKey.getStyleClass().add("keyboard-letter");
        delKey.getStyleClass().add("delete");
        return delKey;
    }
}
