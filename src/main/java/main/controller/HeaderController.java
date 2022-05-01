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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

/**
 * Simple class that handles events for the header
 */
public class HeaderController {

    /** The game's model */
    private final WordleModel wordleModel;

    /** The game's view */
    private final WordleView wordleView;

    /** The games header */
    private final Header header;

    /** Accounts for the property of the switch button */
    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    /** The background of the settings menu */
    private Rectangle settingsBackground;

    /**
     * Construct a header controller for header buttons.
     *
     * @param wordleView the games view.
     * @param wordleModel the games model.
     */
    public HeaderController(WordleView wordleView, WordleModel wordleModel) {
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;

        // Gets the header of the game.
        this.header = this.wordleModel.getHeader();

        // Initialize all the buttons
        this.initEventHandlers();
        this.ToggleSwitch();
    }

    /**
     * Initialize all the button and assign them to their appropriate functions.
     * e.g. Setting menu opening by gear icon.
     */
    private void initEventHandlers() {
        // The setting icon click for game options.
        this.header.getSettingButton().setOnMouseClicked(this::settingsMenu);

        // The question mark icon click for game help.
        this.header.getQuestionMarkButton().setOnMouseClicked(this::helpMenu);
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


    /**
     * Method that opens the settings menu
     */
    private void openSettingMenu() {
        StackPane stackPane = new StackPane(); // Add a node on top of a previous one.
        settingsBackground = new Rectangle(); // Background of the setting menu.
        BorderPane borderPane = new BorderPane(); // Align everything in a border.
        Pane pane = new Pane(); // Add all the nodes on the pane.
        VBox vBox = new VBox();

        vBox.setId("setting-vertical-box");
        // Make new labels for the setting menu.
        Label DarkThemeLabel = new Label("Dark Theme");

        // Add the items in a vertical alignment with border panes to align to elements.

        BorderPane vBoxBorderDT = new BorderPane();
        DarkThemeLabel.setId("setting-labels");
        vBox.getChildren().addAll(vBoxBorderDT, new Separator());
        vBoxBorderDT.setLeft(DarkThemeLabel);
        vBoxBorderDT.setRight(this.header.getDMSlider());

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
        if (!switchedOn.get()){
            settingsBackground.setFill(Color.WHITE);
        }
        else{
            this.wordleView.getRoot().setStyle("-fx-background-color: dimgrey");
            settingsBackground.setFill(Color.DIMGRAY);
        }

        settingsBackground.heightProperty().bind(this.wordleView.getRoot().heightProperty());
        settingsBackground.widthProperty().bind(this.wordleView.getRoot().widthProperty());

        // Add all the children to the setting menu.
        borderPane.setCenter(vBox);
        stackPane.getChildren().addAll(settingsBackground, borderPane);
        pane.getChildren().add(stackPane); // Needs a pane to add the stack pane?

        // Add overlay to the view.
        this.wordleView.getRoot().getChildren().add(pane);

        // Close the settings menu.
        exit.setOnMouseClicked(e -> {
            this.wordleView.getRoot().getChildren().remove(pane);
            this.wordleModel.setGameState(GameState.GAME_IN_PROGRESS);
        });
    }

    public void ToggleSwitch(){
        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState;
            // switch from left to right 50 is midpoint of rect
            this.header.getTranslateAnimation().setToX(isOn ? 25 : 0);
            this.header.getFillAnimation().setFromValue(isOn ? Color.WHITE : Color.LIGHTGRAY);
            this.header.getFillAnimation().setToValue(isOn ? Color.LIGHTGRAY : Color.WHITE);
            this.header.getAnimation().play();
        });

        this.header.getDMSlider().setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
            if (switchedOn.get() ){
            changeBackground("dark-mode");}
            else{
                changeBackground("light-mode");
            }

        });
    }

    private void changeBackground(String style) {
        if (style.equals("dark-mode")) {
            this.wordleView.getRoot().setStyle("-fx-background-color: dimgrey");
            this.settingsBackground.setFill(Color.DIMGRAY);
        }
        else {
            this.wordleView.getRoot().setStyle("-fx-background-color: white");
            this.settingsBackground.setFill(Color.WHITE);
        }
    }
}
