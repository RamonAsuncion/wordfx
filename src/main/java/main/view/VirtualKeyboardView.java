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
package main.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;

public class VirtualKeyboardView {

    /** Virtual keyboard node to later be added to scene */
    private VBox keyboard;

    /** The {@HBox} to be added as every row of the virtual keyboard */
    private HBox topPane;

    /** List with all the buttons representing virtual keyboard keys */
    private ArrayList<Button> keyboardKeys;

    /** List with all letters on the virtual keyboard */
    private ArrayList<Character> keyboardLetters;

    /**
     * @return The list with all letters on the virtual keyboard
     */
    public ArrayList<Character> getKeyboardLetters() { return keyboardLetters; }

    /**
     * @return the {@link ArrayList} with all the virtual keyboard keys
     */
    public ArrayList<Button> getKeyboardKeys() { return keyboardKeys; }

    /**
     * @return the keyboard node
     */
    public VBox getKeyboard() { return keyboard; }

    /**
     * Simple constructor to initialize the virtual keyboard, and the
     * topPane that will be used to create each row of the keyboard
     */
    public VirtualKeyboardView () {
        keyboard = new VBox();
        keyboard.setId("keyboard");

        topPane = new HBox();
        topPane.setId("topPane");

        keyboardKeys = new ArrayList<>();
        keyboardLetters = new ArrayList<>();
    }

    /**
     * Creates a letter key on the virtual keyboard
     *
     * @param letter keyboard letter key to be created
     * @return The letter key as a button
     */
    public Button createKey(Character letter) {
        Button key = new Button(letter.toString());
        keyboardKeys.add(key);
        keyboardLetters.add(letter);
        key.getStyleClass().add("keyboard-letter");
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
        //keyboardKeys.add(enterKey);
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
        Button delKey = new Button("");
        //keyboardKeys.add(delKey); //TODO fix misplacing colors
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
        for (Character value : topKeyboard) {
            topPane.getChildren().add(createKey(value));
        }
        keyboard.getChildren().add(topPane);

        // Create middle row of virtual keyboard
        newTopPane();
        for (Character character : midKeyboard) {
            topPane.getChildren().add(createKey(character));
        }
        keyboard.getChildren().add(topPane);

        // Create enter key, bottom row of virtual keyboard, and delete key
        // in this order.
        newTopPane();
        topPane.getChildren().add(createEnterKey("ENTER"));
        for (Character character : bottomKeyboard) {
            topPane.getChildren().add(createKey(character));
        }
        topPane.getChildren().add(createDeleteKey());
        keyboard.getChildren().add(topPane);
    }
}