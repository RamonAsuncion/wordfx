package main.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.HeaderController;
import main.controller.WordleController;
import main.model.WordleModel;
import main.view.InitialScreenView;
import main.view.WordleView;

public class WordleMain extends Application {

    /** Initial scene greeting user */
    private Scene firstScene;

    /** Second scene displaying the actual game */
    private Scene secondScene;

    /** This object takes care of the view of our initial scene */
    private InitialScreenView initialView;

    /** Stage object to be shown */
    private Stage window;

    /** Length of words chosen by user */
    private int wordLength;

    /** User streak from previous games */
    private int currentUserStreak;
    private WordleModel wordleModel;
    private WordleView wordleView;

    public Scene getSecondScene() { return secondScene; }

    public static void main(String[] args) { launch(args); }

    @Override
    public void init() throws Exception {
        super.init();
        this.initialView = new InitialScreenView();
        wordLength = 0;
    }

    /**
     * Sets the streak from previous wins by user
     *
     * @param streak - Amount of games won in a row by user
     */
    public void setStreak(int streak) {
        this.currentUserStreak = streak;
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        firstScene = new Scene(this.initialView.getBorderPane(), 600, 850);

        firstScene.getStylesheets().add(
                getClass().getResource("initial-screen.css")
                        .toExternalForm());

        initEventHandlersForInitialScreen();

        // Add the scene graph to the stage
        window.setScene(firstScene);

        // Set the title for the main window
        window.setTitle("WordFX");

        // Display the scene
        window.show();
    }

    /**
     * Takes care of handling the clicks on the initial screen, where
     * user will click on the desired mode
     */
    private void initEventHandlersForInitialScreen() {
        this.initialView.getThreeLetterBtn().setOnMouseClicked(event -> {
            wordLength = 3;
            createSecondScene("style.css");
            window.setScene(secondScene);
        });
        this.initialView.getFourLetterBtn().setOnMouseClicked(event -> {
            wordLength = 4;
            createSecondScene("style.css");
            window.setScene(secondScene);
        });
        this.initialView.getFiveLetterBtn().setOnMouseClicked(event -> {
            wordLength = 5;
            createSecondScene("style.css");
            window.setScene(secondScene);
        });

    }

    /**
     * Creates the second scene, with the model and view
     */
    public void createSecondScene(String style) {
        wordleModel = new WordleModel(wordLength);
        wordleModel.setStreak(currentUserStreak);
        wordleView = new WordleView(wordleModel);

        // Create new scene and use css resources from style.css
        secondScene = new Scene(wordleView.getRoot(), 600, 850);
        secondScene.getStylesheets().add(
                getClass().getResource(style)
                        .toExternalForm());

        //The virtual keyboard controller for handling events like typing
        WordleController keyboardController = new WordleController(wordleView, wordleModel, secondScene);

        //The controls for the header
        HeaderController headerController = new HeaderController(wordleView, wordleModel);

        this.wordleModel.getHeader().getDarkModeSlider().setOnMouseClicked(e -> {
            if (!headerController.isSwitchedOn()) {
                switchToDarkMode("dark-mode.css");
                headerController.switchedOnProperty().set(true);
            }
            else {
                switchToLightMode("style.css");
                headerController.switchedOnProperty().set(false);
            }
        });
    }

    public void switchToDarkMode(String style) {
        secondScene.getStylesheets().remove(
                getClass().getResource("style.css")
                        .toExternalForm());
        secondScene.getStylesheets().add(
                getClass().getResource(style)
                        .toExternalForm());
    }

    public void switchToLightMode(String style) {
        secondScene.getStylesheets().remove(
                getClass().getResource("dark-mode.css")
                        .toExternalForm());
        secondScene.getStylesheets().add(
                getClass().getResource(style)
                        .toExternalForm());
    }
}
