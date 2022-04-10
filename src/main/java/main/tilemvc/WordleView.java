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
package main.tilemvc;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;

public class WordleView {

    /** The 30 tiles representing all possible guesses */
    private Tile tiles;

    /** The virtual keyboard which user cna use to type */
    private VirtualKeyboard vk;

    /** The "Wordle" header section */
    private Header header;

    /** The root node containing all three nodes above */
    private BorderPane root;

    /** List of all letters in the keyboard */
    private ArrayList<Button> letterList;

    /**
     * @return the {@link ArrayList} with all the letters contained in the virtual keyboard
     */
    public ArrayList<Button> getLetterList() { return letterList; }

    /**
     * @return the root containing header, tiles, and keyboard, to create our scene
     */
    public BorderPane getRoot() { return root; }

    /**
     * Simple constructor to initialize the scene graph
     */
    public WordleView() {
        initSceneGraph();
    }

    /**
     * Initializes the scene graph containing header, tiles, and virtual keyboard
     */
    private void initSceneGraph() {
        // Initialize the three nodes + the main root
        this.header = new Header();
        this.tiles = new Tile();
        this.vk = new VirtualKeyboard();
        this.root = new BorderPane();
        this.root.setId("background");

        // Creating scene components
        this.header.createHeader();
        this.tiles.createTilePane();
        this.vk.createVirtualKeyboard();

        // Fill our array after creating the virtual keyboard
        letterList = this.vk.getLetters();

        // Set the scene accordingly
        this.root.setCenter(this.tiles.getTiles());
        this.root.setBottom(this.vk.getKeyboard());
        this.root.setTop(this.header.getHeaderSection());
    }
}
