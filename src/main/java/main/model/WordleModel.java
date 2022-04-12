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

import main.view.WordleView;

public class WordleModel {
    /** Current row that we are on */
    private int row;

    /** Current column that we are on */
    private int column;

    private WordleView wv;

    public WordleModel() {
        wv = new WordleView();
        row = 0;
        column = 0;
    }


}
