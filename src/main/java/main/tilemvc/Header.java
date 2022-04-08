/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
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

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/**
 * Header class to create the header section, which includes the "Wordle" header
 * and the long separator line splitting the section.
 */
public class Header {

    /** Wordle header section */
    private VBox headerSection;

    /** The word "Wordle" */
    private Label title;

    /**
     * @return the headerSection including title and separator
     */
    public VBox getHeaderSection() { return headerSection; }

    /**
     * Simple constructor to initialize the title and header section, and their
     * respective css id's
     */
    public Header() {
        // Initialize the title
        this.title = new Label("Wordle");
        this.title.setId("titleLabel");

        // Initialize header section
        this.headerSection = new VBox();
        this.headerSection.setId("header");
    }

    /**
     * Creates the header section
     */
    public void createHeader() {
        this.headerSection.getChildren().addAll(this.title, new Separator());
    }
}
