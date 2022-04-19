/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/17/22
 * Time: 10:25 PM
 *
 * Project: csci205_final_project
 * Package: main.view
 * Class: InitialScreenView
 *
 * Description:
 *
 * ****************************************
 */
package main.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class InitialScreenView {

    private VBox root;

    private BorderPane bp;

    private Button threeLetterBtn, fourLetterBtn, fiveLetterBtn;

    public Button getThreeLetterBtn() { return threeLetterBtn; }

    public Button getFourLetterBtn() { return fourLetterBtn; }

    public Button getFiveLetterBtn() { return fiveLetterBtn; }

    public BorderPane getBp() { return bp; }

    public InitialScreenView() {
        root = new VBox();
        bp = new BorderPane();
        initSceneGraph();
    }

    public void initSceneGraph() {
        threeLetterBtn = new Button("3 LETTER MODE");
        fourLetterBtn = new Button("4 LETTER MODE");
        fiveLetterBtn = new Button("5 LETTER MODE");

        threeLetterBtn.setId("three-letter-btn");
        fourLetterBtn.setId("four-letter-btn");
        fiveLetterBtn.setId("five-letter-btn");

        Label question = new Label("What mode would\nyou like to play?");
        HBox topPane = new HBox(question);
        topPane.setAlignment(Pos.CENTER);
        question.setId("question");

        root.getChildren().addAll(threeLetterBtn, fourLetterBtn, fiveLetterBtn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        bp.setTop(topPane);
        bp.setCenter(root);
    }

    //TODO might need to delete later
    private void createAndSetBackground(String filename, Button b) {
        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/images/" + filename).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        b.setBackground(background);
    }
}
