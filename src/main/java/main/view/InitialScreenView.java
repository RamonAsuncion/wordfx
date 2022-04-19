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

import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.File;

public class InitialScreenView {

    private VBox root;

    private BorderPane bp;

    private Button threeLetterBtn, fourLetterBtn, fiveLetterBtn;
    private HBox topPane;

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

        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/images/new2.jpeg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        bp.setBackground(background);

        //Create three buttons
        threeLetterBtn = new Button("3 LETTER MODE\n(HARD)");
        fourLetterBtn = new Button("4 LETTER MODE\n(MEDIUM)");
        fiveLetterBtn = new Button("5 LETTER MODE\n(EASY)");

        // Style the three buttons
        threeLetterBtn.setId("three-letter-btn");
        fourLetterBtn.setId("four-letter-btn");
        fiveLetterBtn.setId("five-letter-btn");

        // Title
        Label question = new Label("WELCOME TO WORDFX\nCHOOSE A MODE:");
        topPane = new HBox(question);
        topPane.setAlignment(Pos.CENTER);
        question.setId("question");
        bp.setTop(topPane);

        // Placing our buttons
        root.getChildren().addAll(threeLetterBtn, fourLetterBtn, fiveLetterBtn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        bp.setCenter(root);

        // Placing name of the group
        Label groupName = new Label("A Game by Liv & Gang");
        groupName.setId("group-name");
        topPane = new HBox(groupName);
        topPane.setAlignment(Pos.CENTER);
        bp.setBottom(topPane);
    }

    //TODO might need to delete later
    private void createAndSetBackground(String filename, Button b) {
        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/images/" + filename).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        b.setBackground(background);
    }
}
