package main.tilemvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.WordleModel;

public class WordleMain extends Application {

    /** The view of our Wordle game */
    private WordleView wordleView;

    private WordleModel wordleModel;

    /** The virtual keyboard controller for handling events like typing */
    private VirtualKeyboardController keyboardController;

    /** Our Wordle scene where everything is displayed */
    private Scene scene;

    public static void main(String[] args) { launch(args); }

    @Override
    public void init() throws Exception {
        super.init();
        this.wordleView = new WordleView();
        this.wordleModel = new WordleModel();
    }

    @Override
    public void start(Stage primaryStage) {
        // Create new scene and use css resources from style.css
        scene = new Scene(this.wordleView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        keyboardController = new VirtualKeyboardController(this.wordleView, this.wordleModel, scene);

        // Add the scene graph to the stage
        primaryStage.setScene(scene);

        // Set the title for the main window
        primaryStage.setTitle("Wordle");

        // Display the scene
        primaryStage.show();
    }
}
