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

    /** Histogram icon button. */
    private Button histogram;

    /** Setting icon button. */
    private Button setting;

    /** Question mark icon button. */
    private Button questionMark;

    /** Menu three dash icon button. */
    private Button menuThreeDashes;

    /** Slider to turn on dark mode. */
    private Pane darkModeSlider;

    private Button exitSettingMenu;

    private Pane backgroundFrame;

    public Pane getBackgroundFrame() {
        return backgroundFrame;
    }

    public Button getExitSettingMenu() {
        return exitSettingMenu;
    }

    public TranslateTransition getTranslateAnimation() {
        return translateAnimation;
    }

    public FillTransition getFillAnimation() {
        return fillAnimation;
    }

    public ParallelTransition getAnimation() {
        return animation;
    }

    public Pane getDarkModeSlider() {
        return darkModeSlider;
    }

    private final TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private final FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

    /** The background of the settings menu */
    private Rectangle settingsBackground;

    public Rectangle getSettingsBackground() {
        return settingsBackground;
    }

    // get both animations to occur at the same time
    private final ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

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

        // Create all the sliders in the setting menu.
        this.createDarkModeSlider();
        this.createSettingMenu();


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
     * @return the button for the settings icon.
     */
    public Button getSettingButton() { return setting; }

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

    private void createDarkModeSlider() {
        darkModeSlider = new Pane();
        darkModeSlider.setPrefSize(60,10);

        Rectangle background = new Rectangle(150,150);
        background.setFill(Color.BLACK);

        // create the background of button
        Rectangle rect = new Rectangle(50, 25);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.LIGHTGRAY);
        rect.setArcHeight(25);
        rect.setArcWidth(25);

        // Create the circle overlap
        Circle trigger = new Circle(12.5);
        trigger.setCenterX(12.5);
        trigger.setCenterY(12.5);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(rect);

        darkModeSlider.getChildren().addAll(rect,trigger);
    }

    private void createSettingMenu() {
        // Add all the nodes on the pane.
        backgroundFrame = new Pane();
        StackPane stackPane = new StackPane(); // Add a node on top of a previous one.
        settingsBackground = new Rectangle(); // Background of the setting menu.
        BorderPane borderPane = new BorderPane(); // Align everything in a border.
        VBox vBox = new VBox();

        vBox.setId("setting-vertical-box");
        // Make new labels for the setting menu.
        Label DarkThemeLabel = new Label("Dark Theme");

        // Add the items in a vertical alignment with border panes to align to elements.
        BorderPane vBoxBorderDT = new BorderPane();
        DarkThemeLabel.setId("setting-labels");
        vBox.getChildren().addAll(vBoxBorderDT, new Separator());
        vBoxBorderDT.setLeft(DarkThemeLabel);
        vBoxBorderDT.setRight(darkModeSlider);

        // Add the "Setting" and the "X" in the header.
        BorderPane topHeader = new BorderPane();
        exitSettingMenu = new Button();
        topHeader.setId("setting-title");
        topHeader.setCenter(new Label("SETTINGS"));
        topHeader.setBottom(new Separator());
        topHeader.setRight(exitSettingMenu);
        borderPane.setTop(topHeader);

        // Add all the children to the setting menu.
        borderPane.setCenter(vBox);
        stackPane.getChildren().addAll(settingsBackground, borderPane);
        backgroundFrame.getChildren().add(stackPane);

        // Set the styling for the elements
        exitSettingMenu.getStyleClass().add("menu-setting-close-button");
    }
}
