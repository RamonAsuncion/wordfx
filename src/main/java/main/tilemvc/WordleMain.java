package main.tilemvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.WordleController;
import main.model.WordleModel;
import main.view.WordleView;

public class WordleMain extends Application {

    /** The view of our Wordle game */
    private WordleView wordleView;

    private WordleModel wordleModel;

    /** The virtual keyboard controller for handling events like typing */
    private WordleController keyboardController;

    /** Our Wordle scene where everything is displayed */
    private Scene scene;

    public static void main(String[] args) { launch(args); }

    @Override
    public void init() throws Exception {
        super.init();
        this.wordleModel = new WordleModel();
        this.wordleView = new WordleView(this.wordleModel);

    }

    /**
     * Sets the streak from previous wins by user
     *
     * @param streak - Amount of games won in a row by user
     */
    public void setStreak(int streak) {
        this.wordleModel.setStreak(streak);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage stage = primaryStage;
        // Create new scene and use css resources from style.css
        scene = new Scene(this.wordleView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        keyboardController = new WordleController(this.wordleView, this.wordleModel, scene);

        // Add the scene graph to the stage
        stage.setScene(scene);

        // Set the title for the main window
        stage.setTitle("Wordle");

        // Display the scene
        stage.show();
    }
}
