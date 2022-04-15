/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2022
 * Instructor: Prof. Brian King
 *
 * Name: Pedro Carneiro Passos
 * Section: 02 - 11AM
 * Date: 4/12/22
 * Time: 4:32 PM
 *
 * Project: csci205_final_project
 * Package: main.tilemvc
 * Class: GuessEvaluator
 *
 * Description:
 *
 * ****************************************
 */
package main.tilemvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This is a simple class to evaluate guesses by users
 */
public class GuessEvaluator {

    /** Word to be guessed. "word of the day" in Wordle context */
    private String secretWord;

    /** Current guess given by the user */
    private String currentGuess;

    /** This is the analysis of the given guess, including correct and incorrect letters */
    private StringBuffer guessAnalysis;

    /** This keeps track of the letters in both the secret word and guessed word */
    private Map<Character, Character> mapOfLetters;

    /**
     * Simple GuessEvaluator constructor to define the secret word, current guess,
     * and a guess analysis
     */
    public GuessEvaluator(String secretWord, String currentGuess) {
        this.secretWord = secretWord;
        this.currentGuess = currentGuess.toLowerCase();
        this.guessAnalysis = new StringBuffer("-----");
        this.mapOfLetters = new TreeMap<>();
    }

    /**
     * Returns an encoded string with -, +, and * characters indicating
     * what letters are promising
     *
     * @param guess guess given by user
     * @return an encoded string with -, +, and * characters indicating
     * what letters are promising
     */
    public String analyzeGuess() {
        //first check for green letters (correct letter, correct position)
        for (int i = 0; i < this.currentGuess.length(); ++i) {
            if (this.secretWord.charAt(i) == this.currentGuess.charAt(i)) { this.guessAnalysis.setCharAt(i, '*'); }
            else { mapOfLetters.put(this.secretWord.charAt(i), this.currentGuess.charAt(i)); }
        }

        //then, check for yellow letters (correct letter, incorrect position)
        Set<Character> remainingSecretLetters = mapOfLetters.keySet();
        for (Map.Entry<Character,Character> entry : mapOfLetters.entrySet()) {
            if (remainingSecretLetters.contains(entry.getValue())) {
                this.guessAnalysis.setCharAt(this.currentGuess.indexOf(entry.getValue()),'+');
            }
        }
        return this.guessAnalysis.toString();
    }
}
