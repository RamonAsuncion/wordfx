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
import main.tilemvc.Header;
import main.tilemvc.Tile;
import main.view.VirtualKeyboardView;

import java.util.ArrayList;

public class WordleModel {
    /** Current row that we are on */
    private int row;

    /** Current column that we are on */
    private int column;

    /** The 30 tiles representing all possible guesses */
    private Tile tiles;

    /** The virtual keyboard which user cna use to type */
    private VirtualKeyboardView vk;

    /** The "Wordle" header section */
    private Header header;

    /** List of all letters in the keyboard */
    private ArrayList<Character> letterList;

    /** List of all 6 guesses in the game */
    private ArrayList<ArrayList<Label>> listOfGuesses;

    /** The list with the buttons on the virtual keyboard */
    private ArrayList<Button> keysList;

    /**
     * @return List with all letters contained in last given guess
     */
    public ArrayList<Character> getLetterList() { return letterList; }

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
     * @return The VirtualKeyboard object that stores all keys on virtual keyboard
     */
    public VirtualKeyboardView getVk() { return vk; }

    /**
     * @return Header object that creates header section
     */
    public Header getHeader() { return header; }

    /** The current state of the game */
    private GameState gameState;

    public WordleModel() {
        // Three main components of interface
        this.header = new Header();
        this.tiles = new Tile();
        this.vk = new VirtualKeyboardView();

        // Keep track of where the next letter is typed or deleted
        this.row = 0;
        this.column = -1;

        // state of game starts our with NEW_GAME
        this.gameState = GameState.NEW_GAME;
        initInterface();
    }

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
     * @return row value incremented by 1
     */
    public int incrementRow() {
        return this.row++;
    }

    /**
     * @return column value incremented by 1
     */
    public int incrementColumn() {
        return this.column++;
    }

    /**
     * @return column value decremented by 1
     */
    public int decrementColumn() {
        return this.column--;
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
    public void setColumn(int col) {
        this.column = col;
    }


}
