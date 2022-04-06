package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//TODO cleanup
public class HelloJava extends Application {
    
    private HBox topPane;
    private VBox root;
    private Rectangle rect;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the topPane node for our scene graph
        topPane = new HBox();
        topPane.setSpacing(10);
        topPane.setAlignment(Pos.CENTER);

        root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(50, 50, 50, 50));

        for (int i = 0; i < 6; ++i) {

            for (int j = 0; j < 5; ++j) {

                rect = new Rectangle(60, 60);
                rect.getStyleClass().add("input-box");
                //if (j == 0) { root.getChildren().add(rect); }
                topPane.getChildren().add(rect);

            }
            root.getChildren().add(topPane);
            topPane = new HBox();
            topPane.setSpacing(10);
            topPane.setAlignment(Pos.CENTER);
        }

        root.setAlignment(Pos.TOP_CENTER);
        scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
                getClass().getResource("style.css")
                        .toExternalForm());

        // Add the scene graph to the stage
        primaryStage.setScene(scene);

        // Set the title for the main window
        primaryStage.setTitle("Wordle Game");

        // Display the scene
        primaryStage.show();
    }

    /**
     * Initialize all controls and place them in the scene graph {@code topPane}
     *
     * @param topPane - is the topPane node container of the scene graph
     */
    private void initSceneGraph(VBox topPane) {

    }
}
