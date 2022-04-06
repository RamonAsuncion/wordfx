/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/6/22
 * Time: 12:46 PM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: TileView
 *
 * Description:
 *
 * ****************************************
 */
package main.tilemvc;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TileView {

    /** topPane is the {@link FlowPane} layout container for the top of the view */
    private HBox topPane;

    /** Root node for the scene graph */
    private VBox root;

    /** Individual tile to be added to our root */
    private Rectangle rect;

    /**
     * @return the root node for our scene graph
     */
    public VBox getRoot() { return root; }

    public TileView() {
        initSceneGraph();
    }

    private void initSceneGraph() {
        // Set up the topPane node for our scene graph
        topPane = new HBox();
        topPane.setSpacing(10);
        topPane.setAlignment(Pos.CENTER);

        // Set up the root for our scene graph
        root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(50, 50, 50, 50));

        // Loop through 6 rows and 5 columns
        for (int i = 0; i < 6; ++i) {

            for (int j = 0; j < 5; ++j) {

                // Create new tile and add to top pane
                rect = new Rectangle(60, 60);
                rect.getStyleClass().add("input-box");
                topPane.getChildren().add(rect);

            }
            root.getChildren().add(topPane);

            // Create new top pane, meaning new guess on a new horizontal box
            topPane = new HBox();
            topPane.setSpacing(10);
            topPane.setAlignment(Pos.CENTER);
        }

        root.setAlignment(Pos.TOP_CENTER);

    }
}
