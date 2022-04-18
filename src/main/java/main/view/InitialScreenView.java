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
import javafx.scene.layout.VBox;
import main.tilemvc.WordleMain;

public class InitialScreenView {

    private VBox root;

    private Button threeLetterBtn, fourLetterBtn, fiveLetterBtn;

    public Button getThreeLetterBtn() { return threeLetterBtn; }

    public Button getFourLetterBtn() { return fourLetterBtn; }

    public Button getFiveLetterBtn() { return fiveLetterBtn; }

    public VBox getRoot() { return root; }

    public InitialScreenView() {
        root = new VBox();
        initSceneGraph();
    }

    public void initSceneGraph() {
        threeLetterBtn = new Button("3 Letter Mode");
        fourLetterBtn = new Button("4 Letter Mode");
        fiveLetterBtn = new Button("5 Letter Mode");

        threeLetterBtn.setStyle("-fx-pref-width: 200; -fx-pref-height: 100; -fx-text-alignment: center; -fx-background-color: grey");
        fourLetterBtn.setStyle("-fx-pref-width: 200; -fx-pref-height: 100; -fx-text-alignment: center; -fx-background-color: grey");
        fiveLetterBtn.setStyle("-fx-pref-width: 200; -fx-pref-height: 100; -fx-text-alignment: center; -fx-background-color: grey");

        root.getChildren().addAll(threeLetterBtn, fourLetterBtn, fiveLetterBtn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
    }
}
