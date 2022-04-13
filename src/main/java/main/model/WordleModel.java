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

import main.tilemvc.Tile;
import main.view.WordleView;

public class WordleModel {
    /** Current row that we are on */
    private int row;

    /** Current column that we are on */
    private int column;

    private Tile tiles;

    public WordleModel() {
        this.tiles = new Tile();
        this.row = 0;
        this.column = -1;
    }

    public int incrementRow() {
        return this.row++;
    }

    public int incrementColumn() {
        return this.column++;
    }

    public int decrementColumn() {
        return this.column--;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int col) {
        this.column = col;
    }

}
