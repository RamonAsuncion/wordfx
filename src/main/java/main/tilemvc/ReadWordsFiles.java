/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/15/22
 * Time: 12:05 AM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: ReadWordsFiles
 *
 * Description:
 *
 * ****************************************
 */
package main.tilemvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ReadWordsFiles {

    private String secretWord;

    private ArrayList<String> wordSet = new ArrayList<>();

    public String createRandomWord(String wordFile) {
        File file = new File(wordFile);
        try {
            // Scan through file and create a set of all words
            Scanner scnr = new Scanner(file);
            while(scnr.hasNext()) {
                wordSet.add(scnr.next());
            }
            // Find a random word in the list at index randInt
            // and assign to secret word
            Random rand = new Random();
            int randInt = rand.nextInt((wordSet.size()));
            secretWord = wordSet.get(randInt);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return secretWord;
    }
}
