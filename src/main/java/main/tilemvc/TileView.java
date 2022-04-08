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


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public class TileView {

    /** topPane is the {@link FlowPane} layout container for the top of the view */
    private HBox topPane;

    /** Root node for the scene graph */
    private VBox root;

    /** Individual tile to be added to our root */
    private Rectangle rect;

    /** Play again button */
    private Button playAgainBtn;

    /** "Wordle" title */
    private Label title;

    /** List of topPanes */
    private ArrayList<Rectangle> rectList;

    /**  */
    private OnScreenKeyboard osk;


    /**
     * @return the root node for our scene graph
     */
    public VBox getRoot() { return root; }

    public TileView() {
        this.osk = new OnScreenKeyboard();
        initSceneGraph();
    }

    private void initSceneGraph() {
        // Set up the topPane node for our scene graph
        topPane = new HBox();
        topPane.setId("topPane");

        // Set up the root for our scene graph
        root = new VBox();
        root.setId("root");

        // TODO: Temporary for testing
        createSetting();

        title = new Label("WORDLE");
        title.setId("titleLabel");
        root.getChildren().addAll(title, new Separator());




        playAgainBtn = new Button("Play Again!");

        createTilePane();

        //root.getChildren().add(playAgainBtn);

        createVirtualKeyboard();
    }

    /**
     * Creates the virtual keyboard by going through every single
     * key on the keyboard. Each key is a button.
     */
    private void createVirtualKeyboard() {
        ArrayList<Character> topKeyboard = new ArrayList<>(
                Arrays.asList('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P')
        );
        ArrayList<Character> midKeyboard = new ArrayList<>(
                Arrays.asList('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L')
        );
        ArrayList<Character> bottomKeyboard = new ArrayList<>(
                Arrays.asList('Z', 'X', 'C', 'V', 'B', 'N', 'M')
        );

        topPane = new HBox();
        topPane.setId("topPane");
        for (Character character : topKeyboard) {
            topPane.getChildren().add(this.osk.createKey(character));
        }
        root.getChildren().add(topPane);

        topPane = new HBox();
        topPane.setId("topPane");
        for (Character value : midKeyboard) {
            topPane.getChildren().add(this.osk.createKey(value));
        }
        root.getChildren().add(topPane);

        topPane = new HBox();
        topPane.setId("topPane");
        topPane.getChildren().add(this.osk.createEnterKey("ENTER"));
        for (Character character : bottomKeyboard) {
            topPane.getChildren().add(this.osk.createKey(character));
        }
        topPane.getChildren().add(this.osk.createDeleteKey());
        root.getChildren().add(topPane);
    }

    /**
     * Creates the pane made up of the 30 tiles, meaning 6 guesses of 5-letter
     * words. Tiles (rectangles) are styled using css.
     */
    private void createTilePane() {
        // Loop through 6 rows and 5 columns
        for (int i = 0; i < 6; ++i) {

            for (int j = 0; j < 5; ++j) {

                // Create new tile and add to top pane
                rect = new Rectangle(57, 57);
                rect.setId("tile");
                topPane.getChildren().add(rect);

            }
            root.getChildren().add(topPane);

            // Create new top pane, meaning new guess on a new horizontal box
            topPane = new HBox();
            topPane.setId("topPane");
        }
    }

    private void createSetting() {
        // I'm an idiot I'm adding them to a vertical box that's why they don't align.


        // TODO: Creating a setting button
        Button setting = new Button();

        // TODO: Change the size
        setting.setPrefWidth(45);

        // TODO: Move the button to the top right. (not best way to do it?)


        // TODO: Create a menu that overlaps the whole game (StackPane)?
        setting.setOnAction(event -> {
            System.out.println("Button clicked!");
        });

        // TODO: Create an X to exit out of the menu

        // TODO: Create an option of 'Dark Mode' and 'Light Mode'

        setting.getStyleClass().add("setting-button");


        // TODO: This needs proper encapsulation (this is so I can see how full tool bar looks)
        Button histogram = new Button();
        Button questionMark = new Button();

        histogram.getStyleClass().add("histogram-button");
        questionMark.getStyleClass().add("question-mark-button");

        histogram.setPrefWidth(45);
        questionMark.setPrefWidth(45);

        root.getChildren().addAll(setting, histogram, questionMark);
    }
}
