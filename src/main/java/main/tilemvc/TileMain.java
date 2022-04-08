package main.tilemvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TileMain extends Application {

    private TileView theView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        this.theView = new TileView();

    }

    @Override
    public void start(Stage primaryStage) {
        // Create new scene and use css resources from style.css
        Scene scene = new Scene(theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        // Add the scene graph to the stage
        primaryStage.setScene(scene);

        // Set the title for the main window
        primaryStage.setTitle("Wordle");

        // Display the scene
        primaryStage.show();
    }
}
