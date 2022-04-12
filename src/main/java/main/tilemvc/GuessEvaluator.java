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
     * Returns the secret word
     *
     * @return the secret word
     */
    public String getSecretWord() { return this.secretWord; }

    /**
     * Returns the current guess
     *
     * @return the current guess
     */
    public String getCurrentGuess() { return this.currentGuess; }

    /**
     * Returns the analysis of the guess
     *
     * @return the analysis of the guess
     */
    public StringBuffer getGuessAnalysis() { return guessAnalysis; }

    /**
     * Simple GuessEvaluator constructor to define the secret word, current guess,
     * and a guess analysis
     *
     * @param secretWord - the word to be guessed
     * @param currentGuess - guess given by the user
     */
    public GuessEvaluator(String secretWord, String currentGuess) {
        this.secretWord = secretWord.toLowerCase();
        this.currentGuess = currentGuess.toLowerCase();
        this.guessAnalysis = new StringBuffer("-----");
        this.mapOfLetters = new HashMap<>();
    }

    /**
     * Returns an encoded string with -, +, and * characters indicating
     * what letters are promising
     *
     * @param guess guess given by user
     * @return an encoded string with -, +, and * characters indicating
     * what letters are promising
     */
    public String analyzeGuess(String guess) {

        //first check for green letters (correct letter, correct position)
        for (int i = 0; i < guess.length(); ++i) {
            if (this.secretWord.charAt(i) == guess.charAt(i)) { this.guessAnalysis.setCharAt(i, '*'); }
            else { mapOfLetters.put(this.secretWord.charAt(i), guess.charAt(i)); }
        }

        //then, check for yellow letters (correct letter, incorrect position)
        Set<Character> remainingSecretLetters = mapOfLetters.keySet();
        for (Map.Entry<Character,Character> entry : mapOfLetters.entrySet()) {
            if (remainingSecretLetters.contains(entry.getValue())) {
                this.guessAnalysis.setCharAt(guess.indexOf(entry.getValue()),'+');
            }
        }

        return this.guessAnalysis.toString();
    }

}
