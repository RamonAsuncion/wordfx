/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos and Ramon Asuncion
 * Section: 02 - 11AM
 * Date: 4/8/22
 * Time: 8:13 AM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: Header
 *
 * Description:
 *
 * ****************************************
 */
package main.view;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Header class to create the header section, which includes the "WordFX" header
 * and the long separator line splitting the section.
 */
public class Header {

    /** WordFX header section */
    private final BorderPane headerSection;

    /** WordFX header section (right side for multiple items) */
    private final HBox rightHeaderSection;

    /** WordFX header section (left side for multiple items) */
    private final HBox leftHeaderSection;

    /** The word "WordFX". */
    private final Label title;

    /** Setting icon button. */
    private Button darkModeButton;

    /**
     * @return the headerSection including title and separator
     */
    public BorderPane getHeaderSection() { return headerSection; }

    /**
     * Simple constructor to initialize the title and header section, and their
     * respective css id's
     */
    public Header() {
        // Initialize the title
        this.title = new Label("WordFX");
        this.title.getStyleClass().add("titleLabel");

        // Initialize header section
        this.headerSection = new BorderPane();
        this.headerSection.setId("header");

        // Initialize the right header and left header section as a Horizontal Box.
        this.rightHeaderSection = new HBox();
        this.leftHeaderSection = new HBox();
    }

    /**
     * Creates the header section using a {@BorderPane} as
     * the container and {@HBox} to horizontally line the buttons.
     */
    public void createHeader() {
        // Create all the button in the header.
        this.createSettingButton();

        // Organize items in the header.
        this.headerSection.setBottom(new Separator());

        // Add multiple items to the right side of the header.
        //this.leftHeaderSection.getChildren().add(this.darkModeButton);
        this.headerSection.setRight(this.darkModeButton);
        this.headerSection.setCenter(this.title);
    }
    /**
     * @return the button for the settings icon.
     */
    public Button getDarkModeButton() { return darkModeButton; }

    /**
     * Create a setting button to show the user the game options.
     */
    private void createSettingButton() {
        // Initialize a new button and add styling.
        darkModeButton = new Button();
        darkModeButton.getStyleClass().add("setting-button");
    }
}
