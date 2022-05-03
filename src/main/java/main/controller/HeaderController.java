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
    }

    /**
     * Initialize all the button and assign them to their appropriate functions.
     * e.g. Setting menu opening by gear icon.
     */
    private void initEventHandlers() {
        // The setting icon click for game options.
        this.header.getDarkModeButton().setOnMouseClicked(this::settingsMenu);
    }

    /**
     * Open up the game option menu.
     *
     * @param event button click for setting menu.
     */
    private void settingsMenu(Event event) {
        // Set the game state to pause.
        this.wordleModel.setGameState(GameState.GAME_PAUSED);
    }

}
