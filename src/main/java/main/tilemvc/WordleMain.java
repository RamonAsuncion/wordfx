package main.tilemvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WordleMain extends Application {

    /** The view of our Wordle game */
    private WordleView theView;

    private VirtualKeyboard keyboardView;

    private VirtualKeyboardController keyboardController;

    public static void main(String[] args) { launch(args); }

    @Override
    public void init() throws Exception {
        super.init();
        this.theView = new WordleView();
        this.keyboardView = new VirtualKeyboard();
    }

    @Override
    public void start(Stage primaryStage) {

        // Create new scene and use css resources from style.css
        Scene scene = new Scene(this.theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        this.keyboardController = new VirtualKeyboardController(this.keyboardView, this.theView);

        // Add the scene graph to the stage
        primaryStage.setScene(scene);

        // Set the title for the main window
        primaryStage.setTitle("Wordle");

        // Display the scene
        primaryStage.show();
    }
}
