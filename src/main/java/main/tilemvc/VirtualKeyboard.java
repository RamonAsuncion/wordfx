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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;

public class VirtualKeyboard {

    /** Virtual keyboard node to later be added to scene */
    private VBox keyboard;

    /** The {@HBox} to be added as every row of the virtual keyboard */
    private HBox topPane;

    /** List with all the letters contained in our virtual keyboard */
    private ArrayList<Button> letters;

    /**
     * @return all the letters contained in our keyboard (buttons)
     */
    public ArrayList<Button> getLetters() { return this.letters; }

    /**
     * @return the keyboard node
     */
    public VBox getKeyboard() { return keyboard; }

    /**
     * Simple constructor to initialize the virtual keyboard, and the
     * topPane that will be used to create each row of the keyboard
     */
    public VirtualKeyboard() {
        keyboard = new VBox();
        keyboard.setId("keyboard");

        topPane = new HBox();
        topPane.setId("topPane");

        this.letters = new ArrayList<>();
    }

    /**
     * Creates a letter key on the virtual keyboard
     *
     * @param letter keyboard letter key to be created
     * @return The letter key as a button
     */
    public Button createKey(Character letter) {
        Button key = new Button(letter.toString());
        this.letters.add(key);
        key.getStyleClass().add("keyboard-letter");
        key.getStyleClass().add("exact");
        return key;
    }

    /**
     * Creates the enter key on the virtual keyboard
     *
     * @param enter "ENTER" string to be put on the keyboard key
     * @return The enter key as a button
     */
    public Button createEnterKey(String enter) {
        Button enterKey = new Button(enter);
        enterKey.getStyleClass().add("keyboard-letter");
        enterKey.getStyleClass().add("enter");
        return enterKey;
    }

    /**
     * Creates the delete key on the virtual keyboard
     *
     * @return The delete key as a button
     */
    public Button createDeleteKey() {
        Button delKey = new Button();
        delKey.getStyleClass().add("keyboard-letter");
        delKey.getStyleClass().add("delete");
        return delKey;
    }

    /**
     * Creates a new top pane object with its css id to avoid
     * adding duplicates to root
     */
    public void newTopPane() {
        topPane = new HBox();
        topPane.setId("topPane");
    }

    /**
     * Creates the virtual keyboard by going through every single
     * key on the keyboard. Each key is a button.
     */
    public void createVirtualKeyboard() {
        // Each row of the keyboard
        ArrayList<Character> topKeyboard = new ArrayList<>(
                Arrays.asList('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P')
        );
        ArrayList<Character> midKeyboard = new ArrayList<>(
                Arrays.asList('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L')
        );
        ArrayList<Character> bottomKeyboard = new ArrayList<>(
                Arrays.asList('Z', 'X', 'C', 'V', 'B', 'N', 'M')
        );

        // Create top row of virtual keyboard
        for (int i = 0; i < topKeyboard.size(); i++) {
            topPane.getChildren().add(createKey(topKeyboard.get(i)));
        }
        keyboard.getChildren().add(topPane);

        // Create middle row of virtual keyboard
        newTopPane();
        for (int i = 0; i < midKeyboard.size(); i++) {
            topPane.getChildren().add(createKey(midKeyboard.get(i)));
        }
        keyboard.getChildren().add(topPane);

        // Create enter key, bottom row of virtual keyboard, and delete key
        // in this order.
        newTopPane();
        topPane.getChildren().add(createEnterKey("ENTER"));
        for (int i = 0; i < bottomKeyboard.size(); i++) {
            topPane.getChildren().add(createKey(bottomKeyboard.get(i)));
        }
        topPane.getChildren().add(createDeleteKey());
        keyboard.getChildren().add(topPane);
    }
}