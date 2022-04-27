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
package main.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ReadWordsFiles {

    /** The set of all possible secret words */
    private ArrayList<String> secretWordSet = new ArrayList<>();

    /** The set of all possible guesses */
    private ArrayList<String> guessSet = new ArrayList<>();

    /**
     * Reads in the file and creates a set of words, then gets a random
     * word from that set to be the secret word
     *
     * @param wordFile - the 3, 4, or 5 letter word file
     * @return - the secret word
     */
    public String createRandomWord(String wordFile) {
        File file = new File(wordFile);
        // Scan through file and create a set of all words
        Scanner scnr = null;
        try {
            scnr = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scnr.hasNext()) {
            secretWordSet.add(scnr.next());
        }
        // Find a random word in the list at index randInt
        // and assign to secret word
        Random rand = new Random();
        int randInt = rand.nextInt((secretWordSet.size()));

        return secretWordSet.get(randInt);
    }

    public void createWordSet(String wordFile) {
        File file = new File(wordFile);
        // Scan through file and create a set of all words
        Scanner scnr = null;
        try {
            scnr = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scnr.hasNext()) {
            guessSet.add(scnr.next());
        }
    }

    /**
     * Checks that the user guess is a valid word from the set
     * @param guess - the user guess
     * @return - boolean, whether or not guess is in word set
     */
    public boolean isWordInSet(String guess) {
        if (guess.length() == 5) {
            return guessSet.contains(guess);
        }
        return secretWordSet.contains(guess);
    }
}
