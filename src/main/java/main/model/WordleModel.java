/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/11/22
 * Time: 11:13 PM
 *
 * Project: csci205_final_project
 * Package: main.model
 * Class: WordleModel
 *
 * Description:
 *
 * ****************************************
 */
package main.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import main.tilemvc.ReadWordsFiles;
import main.view.Header;
import main.view.Tile;
import main.view.VirtualKeyboardView;

import java.util.ArrayList;

public class WordleModel {
    /** Current row that we are on */
    private int row;

    /** Current column that we are on */
    private int column;

    /** Current guess number */
    private int currentGuessNumber;

    /** The 30 tiles representing all possible guesses */
    private Tile tiles;

    /** The virtual keyboard which user cna use to type */
    private VirtualKeyboardView vk;

    /** The "Wordle" header section */
    private Header header;

    /** List of all letters in the keyboard */
    private ArrayList<String> letterList;

    /** List of all 6 guesses in the game */
    private ArrayList<ArrayList<Label>> listOfGuesses;

    /** The list with the buttons on the virtual keyboard */
    private ArrayList<Button> keysList;

    /** Keeps track of how many games user has won */
    private int currentWinStreak;

    private ReadWordsFiles reader;

    private String secretWord;

    private int wordLength;

    public void setWordLength(int wordLength) { this.wordLength = wordLength; }

    public int getWordLength() { return wordLength; }

    public String getSecretWord() { return secretWord; }

    public ReadWordsFiles getReader() { return reader; }

    /**
     * @return the current win streak of the player
     */
    public int getCurrentWinStreak() { return currentWinStreak; }

    public void setStreak(int currentWinStreak) { this.currentWinStreak = currentWinStreak; }

    /**
     * @return List with all letters contained in last given guess
     */
    public ArrayList<String> getLetterList() { return letterList; }

    /**
     * @return List with all possible guesses (will be blank labels)
     */
    public ArrayList<ArrayList<Label>> getListOfGuesses() { return listOfGuesses; }

    /**
     * @return List with all buttons on the virtual keyboard
     */
    public ArrayList<Button> getKeysList() { return keysList; }

    /**
     * @return all 30 tiles
     */
    public Tile getTiles() { return tiles; }

    /**
     * @return the stackpane in the tile class
     */
    public StackPane getTileStackPane() { return this.tiles.getTileStackPane(); }

    /**
     * @return The VirtualKeyboard object that stores all keys on virtual keyboard
     */
    public VirtualKeyboardView getVk() { return vk; }

    /**
     * @return Header object that creates header section
     */
    public Header getHeader() { return header; }

    /** The current state of the game */
    private GameState gameState;

    public void setGameState(GameState gameState) { this.gameState = gameState; }

    public GameState getGameState() { return gameState;}

    public WordleModel(int wordLength) {
        this.wordLength = wordLength;

        // Three main components of interface
        this.header = new Header();
        this.tiles = new Tile(wordLength);
        this.vk = new VirtualKeyboardView();

        // Keep track of where the next letter is typed or deleted
        this.row = 0;
        this.column = -1;

        // state of game starts our with NEW_GAME
        this.gameState = GameState.NEW_GAME;
        this.currentWinStreak = 0;
        this.reader = new ReadWordsFiles();

        readFileOfWords(wordLength);
        initInterface();
    }

    /**
     * Reads file of words according to word length user has chosen
     *
     * @param wordLength - word length user has chosen (3, 4, or 5)
     */
    private void readFileOfWords(int wordLength) {
        switch (wordLength) {
            case 3:
                this.secretWord = this.reader.createRandomWord("3words.txt");
                break;
            case 4:
                this.secretWord = this.reader.createRandomWord("4words.txt");
                break;
            default:
                this.secretWord = this.reader.createRandomWord("5words.txt");
                break;

        }
    }

    /**
     * Initializes the interface with the header, tiles, and virtual keyboard
     */
    private void initInterface() {
        // Creating scene components
        this.header.createHeader();
        this.vk.createVirtualKeyboard();

        // Fill our array after creating the virtual keyboard
        this.letterList = this.vk.getKeyboardLetters();
        this.keysList = this.vk.getKeyboardKeys();
        this.listOfGuesses = this.tiles.getGuessList();
    }

    /**
     */
    public void incrementRow() {
        this.row++;
    }

    /**
     */
    public void incrementColumn() {
        this.column++;
    }

    /**
     */
    public void decrementColumn() {
        this.column--;
    }

    /**
     * @return row value
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return column value
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets new column
     * @param col - new value for column
     */
    public void setColumn(int col) { this.column = col; }

    /**
     * Increments the win streak by one
     */
    public void incrementCurrentWinStreak() { this.currentWinStreak++; }

    /**
     * Increments the current guess
     */
    public void incrementCurrentGuessNumber() { this.currentGuessNumber++; }


}
