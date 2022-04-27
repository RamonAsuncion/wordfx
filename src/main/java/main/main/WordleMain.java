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

    /** The view of our WordFX game */
    private WordleView wordleView;

    /** The model of our WordFX game */
    private WordleModel wordleModel;

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
            createSecondScene();
            window.setScene(secondScene);
        });
        this.initialView.getFourLetterBtn().setOnMouseClicked(event -> {
            wordLength = 4;
            createSecondScene();
            window.setScene(secondScene);
        });
        this.initialView.getFiveLetterBtn().setOnMouseClicked(event -> {
            wordLength = 5;
            createSecondScene();
            window.setScene(secondScene);
        });
    }

    /**
     * Creates the second scene, with the model and view
     */
    private void createSecondScene() {
        this.wordleModel = new WordleModel(wordLength);
        this.wordleModel.setStreak(currentUserStreak);
        this.wordleView = new WordleView(this.wordleModel);

        // Create new scene and use css resources from style.css
        secondScene = new Scene(this.wordleView.getRoot(), 600, 850);
        secondScene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        //The virtual keyboard controller for handling events like typing
        WordleController keyboardController = new WordleController(this.wordleView, this.wordleModel, secondScene);

        //The controls for the header
        HeaderController headerController = new HeaderController(this.wordleView, this.wordleModel);
    }
}
