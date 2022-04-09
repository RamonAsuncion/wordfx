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
        // TODO: Make a borderpane (since I can organize by left, right, center...etc) as root 
        //  and the buttons as containers

        // Initialize the title
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

        // Add items to the header.
        this.headerSection.setCenter(this.title);
        this.headerSection.setBottom(new Separator());
        this.headerSection.setLeft(this.questionMark);

        // Add multiple items to the right side of the header.
        this.rightHeaderSection.getChildren().addAll(this.histogram, this.setting);
        this.headerSection.setRight(this.rightHeaderSection);
    }

    private void createSettingButton() {
        setting = new Button();

        // TODO: Change the size
        setting.setPrefWidth(45);

        // TODO: Move the button to the top right. (not best way to do it?)
        setting.setTranslateX(0);
        setting.setTranslateY(0);

        // TODO: Create a menu that overlaps the whole game (StackPane)?
        setting.setOnAction(event -> {
            System.out.println("Settings - Button clicked!");
        });

        // TODO: Create an X to exit out of the menu

        // TODO: Create an option of 'Dark Mode' and 'Light Mode'

        setting.getStyleClass().add("setting-button");
    }

    /**
     * Create a histogram button to show the user their statistics.
     */
    private void createHistogramButton() {
        // Initialize a new button
        histogram = new Button();
        histogram.getStyleClass().add("histogram-button");

        // The width of button
        histogram.setPrefWidth(45);

        // Location of button
        setting.setTranslateX(0);
        setting.setTranslateY(0);
    }

    /**
     * Create a question mark button to show the user the help menu.
     */
    private void createQuestionMarkButton() {
        // Initialize a new button
        questionMark = new Button();
        questionMark.getStyleClass().add("question-mark-button");

        // The width of button
        questionMark.setPrefWidth(45);

        // Location of button
        setting.setTranslateX(0);
        setting.setTranslateY(0);
    }
}
