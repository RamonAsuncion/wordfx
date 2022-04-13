/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/6/22
 * Time: 12:46 PM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: TileView
 *
 * Description:
 *
 * ****************************************
 */
package main.tilemvc;


import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**
 * Tile class that creates the 30 tiles where the guesses will be typed on
 */
public class Tile {

    /** Root node for the scene graph */
    private VBox tiles;

    /** Row of tiles representing each guess */
    private HBox topPane;

    /** Individual tile to be added to our tiles */
    private Label rect;

    /** List of labels where letters will be placed */
    private ArrayList<Label> letterList;

    /** List of all guesses, where each guess is a list of labels (letters) */
    private ArrayList<ArrayList<Label>> guessList;

    /**
     * @return the {@ArrayList} of a the guesses, containing each list of letters
     * contained in guesses
     */
    public ArrayList<ArrayList<Label>> getGuessList() {
        return guessList;
    }

    /**
     * @return the 30 tiles representing all the guesses
     */
    public VBox getTiles() { return tiles; }

    /**
     * Simple constructor for the Tile class. Initializes the tiles and
     * topPane, where the topPane represents each row of tiles.
     */
    public Tile() {
        // Set up the tiles for our scene graph
        tiles = new VBox();
        tiles.getStyleClass().add("tile");

        // Set up the topPane node for our scene graph
        topPane = new HBox();
        topPane.setId("topPane");

        // Initialize the two arraylists to store all the guesses and
        // letters respectively
        guessList = new ArrayList<ArrayList<Label>>();
        letterList = new ArrayList<>();

        createTilePane();
    }

    /**
     * Creates the pane made up of the 30 tiles, meaning 6 guesses of 5-letter
     * words. Tiles (rectangles) are styled using css.
     */
    private void createTilePane() {
        // Loop through 6 rows (guesses) and 5 columns (length of word)
        //TODO change 5 to whatever length customer wants
        for (int i = 0; i < 6; ++i) {

            for (int j = 0; j < 5; ++j) {

                // Create new tile and add to top pane
                rect = new Label();
                rect.getStyleClass().add("tile");
                rect.setStyle("-fx-border-color: darkgray;");
                rect.setPrefSize(57, 57);
                topPane.getChildren().add(rect);

                // Add label to list of letters
                letterList.add(rect);
            }
            tiles.getChildren().add(topPane);

            // Add all 5 letters to guess list as a guess
            guessList.add(letterList);
            // Make sure to create new letterList (new guess)
            letterList = new ArrayList<>();

            // Create new top pane, meaning new guess on a new horizontal box
            topPane = new HBox();
            topPane.setId("topPane");
        }
    }
}
