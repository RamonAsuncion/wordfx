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

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Header class to create the header section, which includes the "Wordle" header
 * and the long separator line splitting the section.
 */
public class Header {

    /** Wordle header section */
    private final BorderPane headerSection;

    /** Wordle header section (right side for multiple items) */
    private final HBox rightHeaderSection;

    /** Wordle header section (left side for multiple items) */
    private final HBox leftHeaderSection;

    /** The word "Wordle" */
    private final Label title;

    /** Histogram icon button */
    private Button histogram;

    /** Setting icon button */
    private Button setting;

    /** Question mark icon button */
    private Button questionMark;

    /** Menu three dash icon button */
    private Button menuThreeDashes;

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
        this.title.setId("titleLabel");

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
        this.createHistogramButton();
        this.createQuestionMarkButton();
        this.createThreeDashButton();

        // Organize items in the header.
        this.headerSection.setCenter(this.title);
        this.headerSection.setBottom(new Separator());

        // Add multiple items to the right side of the header.
        this.leftHeaderSection.getChildren().addAll(this.menuThreeDashes, this.questionMark);
        this.headerSection.setLeft(this.leftHeaderSection);

        // Add multiple items to the right side of the header.
        this.rightHeaderSection.getChildren().addAll(this.histogram, this.setting);
        this.headerSection.setRight(this.rightHeaderSection);
    }

    /**
     * @return the button for the histogram icon.
     */
    public Button getHistogramButton() { return histogram; }

    /**
     * @return the button for the settings icon.
     */
    public Button getSettingButton() { return setting; }

    /**
     * @return the button for the question mark icon.
     */
    public Button getQuestionMarkButton() { return questionMark; }

    /**
     * @return the button for the menu three dashes.
     */
    public Button getMenuThreeDashesButton() { return menuThreeDashes; }

    /**
     * Create a setting button to show the user the game options.
     */
    private void createSettingButton() {
        // Initialize a new button and add styling.
        setting = new Button();
        setting.getStyleClass().add("setting-button");
    }

    /**
     * Create a histogram button to show the user their statistics.
     */
    private void createHistogramButton() {
        // Initialize a new button and add styling.
        histogram = new Button();
        histogram.getStyleClass().add("histogram-button");
    }

    /**
     * Create a question mark button to show the user the help menu.
     */
    private void createQuestionMarkButton() {
        // Initialize a new button and add styling.
        questionMark = new Button();
        questionMark.getStyleClass().add("question-mark-button");
    }

    /**
     * Create a three dash menu to show the user other modes
     */
    private void createThreeDashButton() {
        // Initialize a new button and add styling.
        menuThreeDashes = new Button();
        menuThreeDashes.getStyleClass().add("menu-three-button");
    }
}
