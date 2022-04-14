/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/8/22
 * Time: 8:24 AM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: WordleView
 *
 * Description:
 *
 * ****************************************
 */
package main.view;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import main.model.WordleModel;
import main.tilemvc.GuessEvaluator;
import main.tilemvc.Header;
import main.tilemvc.Tile;
import java.util.ArrayList;

public class WordleView {

    /** The 30 tiles representing all possible guesses */
    private Tile tiles;

    /** The virtual keyboard which user cna use to type */
    private VirtualKeyboardView vk;

    /** The "Wordle" header section */
    private Header header;

    /** The root node containing all three nodes above */
    private BorderPane root;

    /** List of all letters in the keyboard */
    private ArrayList<Character> letterList;

    /** List of all 6 guesses in the game */
    private ArrayList<ArrayList<Label>> listOfGuesses;

    /** The list with the buttons on the virtual keyboard */
    private ArrayList<Button> keysList;

    /** The evaluator of our guess */
    private GuessEvaluator guessEval;

    /** The model of the game */
    private WordleModel wordleModel;

    /** The rectangle that shows up when you win */
    private Rectangle winRect;

    /** The label on the win screen */
    private Label winLabel;

    /** Stackpane for the win screen */
    private StackPane winStackPane;

    /** Border pane for the win screen */
    private BorderPane winBorderPane;

    /** Button for the win screen */
    private Button winButton;

    /** Label for the win screen */
    private Label nameLabel;



    /**
     * @return List with all buttons on the virtual keyboard
     */
    public ArrayList<Button> getKeysList() { return keysList; }

    /**
     * @return List with all possible guesses (will be blank labels)
     */
    public ArrayList<ArrayList<Label>> getListOfGuesses() { return listOfGuesses; }

    /**
     * @return the root containing header, tiles, and keyboard, to create our scene
     */
    public BorderPane getRoot() { return root; }

    /**
     * Simple constructor to initialize the scene graph
     */
    public WordleView(WordleModel wordleModel) {
        // Initialize the three nodes + the main root
        this.header = new Header();
        this.tiles = new Tile();
        this.vk = new VirtualKeyboardView();
        this.wordleModel = wordleModel;
        this.root = new BorderPane();
        this.root.setId("background");
        this.listOfGuesses = new ArrayList<>();
        this.winRect = new Rectangle(300, 200);
        this.winStackPane = new StackPane();
        this.winLabel = new Label();
        this.winBorderPane = new BorderPane();
        this.winButton = new Button();
        this.nameLabel = new Label();

        initSceneGraph();
    }

    /**
     * Initializes the scene graph containing header, tiles, and virtual keyboard
     */
    private void initSceneGraph() {
        // Creating scene components
        this.header.createHeader();
        this.vk.createVirtualKeyboard();

        // Fill our array after creating the virtual keyboard
        this.letterList = this.vk.getKeyboardLetters();
        this.keysList = this.vk.getKeyboardKeys();
        this.listOfGuesses = this.tiles.getGuessList();

        // Set the scene accordingly
        this.root.setCenter(this.tiles.getTileStackPane());
        this.root.setBottom(this.vk.getKeyboard());
        this.root.setTop(this.header.getHeaderSection());
    }

    /**
     * Creates an evaluator for a given guess. The evaluator will take care of
     * finding if a letter is in the correct position, misplaced, or not even
     * in the word.
     *
     * @param guess - Given guess by user
     */
    public void createEvaluator(ArrayList<Label> guess) {
        StringBuffer s = new StringBuffer("");
        for (Label tile : guess) {
            s.append(tile.getText());
        }
        this.guessEval = new GuessEvaluator("HELLO", s.toString());
        String evaluation = this.guessEval.analyzeGuess(s.toString());
        System.out.println("GUESS " + s);
        changeTileColor(evaluation, s.toString());
    }

    /**
     * Will perform the action of flipping a tile on the screen for a
     * given duration
     *
     * @param tile - specific tile to be flipped
     */
    public void flipTiles(Label tile) {
        RotateTransition rotation;
        rotation = new RotateTransition(Duration.seconds(1));
        rotation.setNode(tile);
        rotation.setAxis(Rotate.X_AXIS);
        rotation.setFromAngle(0);
        rotation.setToAngle(360);
        rotation.setCycleCount(1);
        rotation.play();
    }

    /**
     * Adds two labels and a button to a rectangle, then adds to tiles stackpane
     * to stack on top of tiles
     */
    public void showWinScreen() {
         //Create rectangle
        this.winRect.setFill(Color.WHITE);
        this.winRect.setArcHeight(10.0d);
        this.winRect.setArcWidth(10.0d);
        this.winRect.setEffect(new DropShadow(10.0, Color.GREY));

        // Create "You won" label
        this.winLabel.setText("You won!");
        this.winButton.setText("Play again?");
        this.winButton.setPrefSize(100, 50);
        this.winButton.setId("winButton");
        this.winLabel.setId("winLabel");

        // Create border pane
        this.winBorderPane.setMaxSize(300, 200);
        this.winBorderPane.setPadding(new Insets(10));
        this.winBorderPane.setAlignment(this.winLabel, Pos.CENTER);
        this.winBorderPane.setAlignment(this.nameLabel, Pos.CENTER);

        // Create label with names
        this.nameLabel.setId("nameLabel");
        this.nameLabel.setText("A game by: Liv, Ramon, Pedro, and Alvin");

        // Add to border pane and stackpane
        this.winBorderPane.setTop(this.winLabel);
        this.winBorderPane.setCenter(this.winButton);
        this.winBorderPane.setBottom(this.nameLabel);
        this.winStackPane.getChildren().add(this.winRect);
        this.winStackPane.getChildren().add(this.winBorderPane);
        this.tiles.getTileStackPane().getChildren().add(this.winStackPane);

        animateWinScreen();
    }

    /**
     * Makes the win screen fade in
     */
    public void animateWinScreen() {
        ObjectBinding<Node> frontNode = Bindings.valueAt(this.winStackPane.getChildren(),
                Bindings.size(this.winStackPane.getChildren()).subtract(1));

        FadeTransition ft = new FadeTransition(Duration.millis(700), this.winStackPane);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
    }

    /**
     * Takes care of binding what is being typed to the labels on the screen
     *
     * @param letter - Letter being typed
     * @param guess - Current guess user is typing on
     * @param letterTile - Next available tile
     */
    public void updateTyping(Text letter, int guess, int letterTile) {
        getListOfGuesses().get(guess).get(letterTile).textProperty().bind(letter.textProperty());
    }

    /**
     * Deletes letter from the screen by binding a white space character to the label
     *
     * @param guess - Current guess user is typing on
     * @param letterTile - Next available tile
     */
    public void updateDelete(int guess, int letterTile) {
        getListOfGuesses().get(guess).get(letterTile).textProperty().bind(new Text(" ").textProperty());
    }

    /**
     * Changes the color on the tile on the screen with css styling. Green means letter on the guess is
     * positioned the same way as on the secret word. Yellow means secret word has such letter but misplaced.
     * Grey simply means not in secret word.
     *
     * @param evaluation - String containing Dash - (becomes grey), Plus + (becomes yellow), and Asterisk * (becomes green)
     * @param guess - guess given by user (to be compared with secret word)
     */
    public void changeTileColor(String evaluation, String guess) {
        // Loop through our evaluation
        for (int i = 0; i < 5; i++) {
            // Correctly positioned letter
            if (evaluation.charAt(i) == ('*')) {
                flipTiles(this.listOfGuesses.get(this.wordleModel.getRow()).get(i));
                this.listOfGuesses.get(this.wordleModel.getRow()).get(i).getStyleClass().add("exact");
                changeKeyboardLetterColor("exact", guess.charAt(i));
            }
            // Misplaced letter
            if (evaluation.charAt(i) == ('+')) {
                flipTiles(this.listOfGuesses.get(this.wordleModel.getRow()).get(i));
                this.listOfGuesses.get(this.wordleModel.getRow()).get(i).getStyleClass().add("misplaced");
                changeKeyboardLetterColor("misplaced", guess.charAt(i));
            }
            // Wrong letter
            if (evaluation.charAt(i) == ('-')) {
                flipTiles(this.listOfGuesses.get(this.wordleModel.getRow()).get(i));
                this.listOfGuesses.get(this.wordleModel.getRow()).get(i).getStyleClass().add("wrong");
                changeKeyboardLetterColor("wrong", guess.charAt(i));
            }
        }

        if (evaluation.equals("*****")) {
            showWinScreen();
        }
    }

    /**
     * Changes the color on the virtual keyboard following the same logic as
     * the color changes in the tiles where letters are typed
     *
     * @param style - css style to style the color of the virtual key (exact, misplaced, or wrong)
     * @param letter - Current letter in guess
     */
    private void changeKeyboardLetterColor(String style, char letter) {
        // Obtain the index of current letter in guess and change its style
        int index = this.letterList.indexOf(letter);

        // Only possibility to change colors is from yellow to green, nothing else
        if (this.keysList.get(index).getStyleClass().toString().contains("misplaced") && (style.equals("exact"))) {
            this.keysList.get(index).getStyleClass().remove("misplaced");
            this.keysList.get(index).getStyleClass().add("exact");
        }

        // If key already has a color
        else if (this.keysList.get(index).getStyle().contains("misplaced") ||
                this.keysList.get(index).getStyle().contains("exact") ||
                this.keysList.get(index).getStyle().contains("wrong")) {
            // Don't do anything if letter already has a color
        }
        else {
            this.keysList.get(index).getStyleClass().add(style);
        }
    }
}
