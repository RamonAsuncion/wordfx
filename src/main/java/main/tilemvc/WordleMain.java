package main.tilemvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.WordleController;
import main.model.WordleModel;
import main.view.InitialScreenView;
import main.view.WordleView;

public class WordleMain extends Application {

    /** The view of our Wordle game */
    private WordleView wordleView;

    private WordleModel wordleModel;

    /** The virtual keyboard controller for handling events like typing */
    private WordleController keyboardController;

    /** Our Wordle scene where everything is displayed */
    private Scene firstScene, secondScene;

    private InitialScreenView initialView;

    private Stage window;

    private int wordLength;

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
        firstScene = new Scene(this.initialView.getBp(), 600, 850);

        firstScene.getStylesheets().add(
                getClass().getResource("initial-screen.css")
                        .toExternalForm());

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

        // Add the scene graph to the stage
        window.setScene(firstScene);

        // Set the title for the main window
        window.setTitle("Wordle");

        // Display the scene
        window.show();
    }

    private void createSecondScene() {
        this.wordleModel = new WordleModel(wordLength);
        this.wordleModel.setStreak(currentUserStreak);
        this.wordleView = new WordleView(this.wordleModel);

        // Create new scene and use css resources from style.css
        secondScene = new Scene(this.wordleView.getRoot(), 600, 850);
        secondScene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        keyboardController = new WordleController(this.wordleView, this.wordleModel, secondScene);
    }
}
