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
package main.view;

import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.tilemvc.Header;
import main.tilemvc.Tile;

import java.util.ArrayList;

public class WordleView {

    /** The 30 tiles representing all possible guesses */
    private Tile tiles;

    /** The virtual keyboard which user cna use to type */
    private VirtualKeyboardView vk;

    /** The "Wordle" header section */
    private Header header;

    /** The root node containing all three nodes above */
    private BorderPane root;

    /** List of all letters in the keyboard */
    private ArrayList<Button> letterList;

    private ArrayList<ArrayList<Label>> listOfGuesses;

    public ArrayList<ArrayList<Label>> getListOfGuesses() { return listOfGuesses; }

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
        // Initialize the three nodes + the main root
        this.header = new Header();
        this.tiles = new Tile();
        this.vk = new VirtualKeyboardView();
        this.root = new BorderPane();
        this.root.setId("background");
        this.listOfGuesses = new ArrayList<>();
        initSceneGraph();
    }

    /**
     * Initializes the scene graph containing header, tiles, and virtual keyboard
     */
    private void initSceneGraph() {
        // Creating scene components
        this.header.createHeader();
        this.tiles.createTilePane();
        this.vk.createVirtualKeyboard();

        // Fill our array after creating the virtual keyboard
        letterList = this.vk.getKeyboardKeys();
        this.listOfGuesses = this.tiles.getGuessList();

        // Set the scene accordingly
        this.root.setCenter(this.tiles.getTiles());
        this.root.setBottom(this.vk.getKeyboard());
        this.root.setTop(this.header.getHeaderSection());
    }

    public void flipTiles(ArrayList<Label> guess) {
        for (Label tile : guess) {
            RotateTransition rt = new RotateTransition(Duration.seconds(1.5), tile);
            rt.setAxis(Rotate.X_AXIS);
            rt.setFromAngle(0);
            tile.getStyleClass().add("exact");
            rt.setToAngle(360);
            rt.play();
        }
    }

    public void updateType(Text letter, int guess, int letterTile) {
        getListOfGuesses().get(guess).get(letterTile).textProperty().bind(letter.textProperty());
    }

    public void updateDelete(int guess, int letterTile) {
        getListOfGuesses().get(guess).get(letterTile).textProperty().bind(new Text(" ").textProperty());
    }
}
