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
import main.main.WordleMain;
import main.model.GameState;
import main.model.WordleModel;
import main.view.Header;
import main.view.TileView;
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

    private TileView tileView;

    public boolean isSwitchedOn() { return switchedOn.get(); }

    public BooleanProperty switchedOnProperty() { return switchedOn; }

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
        this.toggleSwitch();
    }

    /**
     * Initialize all the button and assign them to their appropriate functions.
     * e.g. Setting menu opening by gear icon.
     */
    private void initEventHandlers() {
        // The setting icon click for game options.
        this.header.getSettingButton().setOnMouseClicked(this::settingsMenu);
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
     * Method that opens the settings menu
     */
    private void openSettingMenu() {
        // Create a rectangle (as background) with a size of the whole screen.
//        if (!switchedOn.get()){
//            changeBackground("style.css");
//        }
//        else{
//            changeBackground("dark-mode.css");
//        }

        this.header.getSettingsBackground().heightProperty().bind(this.wordleView.getRoot().heightProperty());
        this.header.getSettingsBackground().widthProperty().bind(this.wordleView.getRoot().widthProperty());

        // Add overlay to the view.
        this.wordleView.getRoot().getChildren().add(this.header.getBackgroundFrame());

        // Close the settings menu.
        this.header.getExitSettingMenu().setOnMouseClicked(e -> {
            this.wordleView.getRoot().getChildren().remove(this.header.getBackgroundFrame());
            this.wordleModel.setGameState(GameState.GAME_IN_PROGRESS);
        });
    }

    public void toggleSwitch(){
        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState;
            // switch from left to right 50 is midpoint of rect
            this.header.getTranslateAnimation().setToX(isOn ? 25 : 0);
            this.header.getFillAnimation().setFromValue(isOn ? Color.WHITE : Color.LIGHTGRAY);
            this.header.getFillAnimation().setToValue(isOn ? Color.GREEN : Color.WHITE);
            this.header.getAnimation().play();
        });

        this.header.getDarkModeSlider().setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });
    }
}
