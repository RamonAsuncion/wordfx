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
package main.tilemvc;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;

/**
 * Header class to create the header section, which includes the "Wordle" header
 * and the long separator line splitting the section.
 */
public class Header {

    /** Wordle header section */
    private final BorderPane headerSection;

    /** Wordle header section (right side for multiple items) */
    private final HBox rightHeaderSection;

    /** The word "Wordle" */
    private final Label title;

    /** Histogram icon button */
    private Button histogram;

    /** Setting icon button */
    private Button setting;

    /** Question mark icon button */
    private Button questionMark;

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
        // FIXME: the title being not centered is driving me nuts.
        this.title = new Label("Wordle");
        this.title.setId("titleLabel");

        // Initialize header section
        this.headerSection = new BorderPane();
        this.headerSection.setId("header");

        // Initialize the right header section as a Horizontal Box.
        this.rightHeaderSection = new HBox();
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

        // Organize items in the header.
        this.headerSection.setLeft(this.questionMark);
        this.headerSection.setCenter(this.title);
        this.headerSection.setBottom(new Separator());

        // Add multiple items to the right side of the header.
        this.rightHeaderSection.getChildren().addAll(this.histogram, this.setting);
        this.headerSection.setRight(this.rightHeaderSection);
    }

    /**
     * Create a setting button to show the user the game options.
     */
    private void createSettingButton() {
        // Initialize a new button
        setting = new Button();

        // Add styling to the button
        setting.getStyleClass().add("setting-button");

        // The width of button
        setting.setPrefWidth(45);

        setting.setOnAction(event -> {
            System.out.println("Settings - Button clicked!");
        });

        // TODO: Create an X to exit out of the menu
        // TODO: Create an option of 'Dark Mode' and 'Light Mode'

    }

    /**
     * Create a histogram button to show the user their statistics.
     */
    private void createHistogramButton() {
        // Initialize a new button
        histogram = new Button();

        // Add styling to the button
        histogram.getStyleClass().add("histogram-button");

        // The width of button
        histogram.setPrefWidth(45);

        histogram.setOnAction(event -> {
            System.out.println("Histogram - Button clicked!");
        });
    }

    /**
     * Create a question mark button to show the user the help menu.
     */
    private void createQuestionMarkButton() {
        // Initialize a new button
        questionMark = new Button();

        // Add styling to the button
        questionMark.getStyleClass().add("question-mark-button");

        // The width of button
        questionMark.setPrefWidth(45);

        questionMark.setOnAction(event -> {
            System.out.println("Question Mark - Button clicked!");
        });
    }
}
