/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 *
 * Name: Alvin Huynh
 * Section: 11 AM
 * Date: 4/17/22
 * Time: 12:14 PM
 *
 * Project: csci205_final_project
 * Package: main.controller
 * Class: HeaderController
 *
 * Description:
 *
 * ****************************************
 */
package main.controller;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.model.GameState;
import main.model.WordleModel;
import main.view.Header;
import main.view.WordleView;

public class HeaderController {

    /** The game's model */
    private final WordleModel wordleModel;

    /** The game's view */
    private final WordleView wordleView;

    /** The games header */
    private final Header header;

    /**
     * Construct a header controller for header buttons.
     *
     * @param wordleView the games view.
     * @param wordleModel the games model.
     */
    public HeaderController(WordleView wordleView, WordleModel wordleModel) {
        // Instantiate a new wordle model and wordle view.
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;

        // Gets the header of the game.
        this.header = this.wordleModel.getHeader();

        // Initialize all the buttons
        this.initEventHandlers();
    }

    /**
     * Initialize all the button and assign them to their appropriate functions.
     * e.g. Setting menu opening by gear icon.
     */
    private void initEventHandlers() {
        // The setting icon click for game options.
        this.header.getSettingButton().setOnMouseClicked(this::settingsMenu);

        // The histogram icon click for game statistics.
        this.header.getHistogramButton().setOnMouseClicked(this::statisticsMenu);

        // The question mark icon click for game help.
        this.header.getQuestionMarkButton().setOnMouseClicked(this::helpMenu);

        this.header.getMenuThreeDashesButton().setOnMouseClicked(this::threeDashMenu);
    }

    /**
     * Open up the game option menu.
     *
     * @param event button click for setting menu.
     */
    private void settingsMenu(Event event) {
        // Set the game state to pause.
        this.wordleModel.setGameState(GameState.GAME_PAUSED);

        openSettingMenu();
    }

    /**
     * Open up the statistics menu.
     *
     * @param event button click for statistics menu.
     */
    private void statisticsMenu(Event event) {
        System.out.println("Histogram button being clicked.");
    }

    /**
     * Open up the help menu.
     *
     * @param event button click for help menu.
     */
    private void helpMenu(Event event) {
        System.out.println("Question mark button being clicked.");
    }

    /**
     * Open up the three dash menu.
     *
     * @param event button click for three dash menu.
     */
    private void threeDashMenu(Event event) {
        System.out.println("Three dash menu button being clicked");
    }


    private void openSettingMenu() {
        StackPane stackPane = new StackPane(); // Add a node on top of a previous one.
        Rectangle rectangle = new Rectangle(); // Background of the setting menu.
        BorderPane borderPane = new BorderPane(); // Align everything in a border.
        Pane pane = new Pane(); // Add all the nodes on the pane.
        VBox vBox = new VBox();

        vBox.setId("setting-vertical-box");
        // Make new labels for the setting menu.
        Label[] options = new Label[] {
                new Label("Timer Mode"),
                new Label("Dark Theme") };

        // Add the items in a vertical alignment with border panes to align to elements.
        for (Label option : options) {
            BorderPane vBoxBorder = new BorderPane();
            option.setId("setting-labels");
            vBox.getChildren().addAll(vBoxBorder, new Separator());
            vBoxBorder.setLeft(option);
            vBoxBorder.setRight(new Button("X")); // TODO: Change to the new button Alvin made.
        }

        // alignment.setBottom(new Label("Any revealed hints must be used in subsequent guesses"));

        // Add the "Setting" and the "X" in the header.
        BorderPane topHeader = new BorderPane();
        Button exit = new Button();
        topHeader.setId("setting-title");
        topHeader.setCenter(new Label("SETTINGS"));
        topHeader.setRight(exit);
        borderPane.setTop(topHeader);

        // Set the styling for the elements
        exit.getStyleClass().add("menu-setting-close-button");

        // Create a rectangle (as background) with a size of the whole screen.
        rectangle.setFill(Color.WHITE);
        rectangle.heightProperty().bind(this.wordleView.getRoot().heightProperty());
        rectangle.widthProperty().bind(this.wordleView.getRoot().widthProperty());

        // Add all the children to the setting menu.
        borderPane.setCenter(vBox);
        stackPane.getChildren().addAll(rectangle, borderPane);
        pane.getChildren().add(stackPane); // Needs a pane to add the stack pane?

        // Add overlay to the view.
        this.wordleView.getRoot().getChildren().add(pane);

        // Close the settings menu.
        exit.setOnMouseClicked(e -> {
            this.wordleView.getRoot().getChildren().remove(pane);
            this.wordleModel.setGameState(GameState.GAME_IN_PROGRESS);
        });
    }
}
