/* ***************************************** * CSCI205 -Software Engineering and Design
 * Spring2022* Instructor: Prof. Brian King
 *
 * Name: Alvin Huynh
 * Section: 11 AM
 * Date: 4/18/22
 * Time: 11:13 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: SwitchButton
 *
 * Description:
 *
 * ****************************************
 */
package main.view;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SwitchButton extends Application {

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(300,300);

        Rectangle background = new Rectangle(300,300);
        background.setFill(Color.BLACK);

        ToggleSwtich darkModeSwitch = new ToggleSwtich();
        darkModeSwitch.setTranslateX(100);
        darkModeSwitch.setTranslateY(100);

        Text text = new Text();
        text.setFont(Font.font(18));
        text.setFill(Color.WHITE);
        text.setTranslateX(100);
        text.setTranslateY(200);
        text.textProperty().bind(Bindings.when(darkModeSwitch.switchOnProperty()).then("ON")
                .otherwise("OFF"));
        // add to the switch to the root
        root.getChildren().addAll(background, darkModeSwitch,text);
        return root;
    }

    private static class ToggleSwtich extends Parent {

        private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

        private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
        private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

        // get both animations to occur at the same time
        private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

        public BooleanProperty switchOnProperty(){
            return switchedOn;
        }

        public ToggleSwtich(){

            // create the background of button
            Rectangle rect = new Rectangle(100, 50);
            rect.setFill(Color.WHITE);
            rect.setStroke(Color.LIGHTGRAY);
            rect.setArcHeight(50);
            rect.setArcWidth(50);

            // Create the circle overlap
            Circle trigger = new Circle(25);
            trigger.setCenterX(25);
            trigger.setCenterY(25);
            trigger.setFill(Color.WHITE);
            trigger.setStroke(Color.LIGHTGRAY);

            translateAnimation.setNode(trigger);
            fillAnimation.setShape(rect);

            getChildren().addAll(rect,trigger);

            switchedOn.addListener((obs, oldState, newState) -> {
                boolean isOn = newState.booleanValue();
                // switch from left to right 50 is midpoint of rect
                translateAnimation.setToX(isOn ? 50 : 0);
                fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGRAY);
                fillAnimation.setToValue(isOn ? Color.LIGHTGRAY : Color.WHITE);

                animation.play();
            });

            setOnMouseClicked(event -> {
                switchedOn.set(!switchedOn.get());
            });
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
