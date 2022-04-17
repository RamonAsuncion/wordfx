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

import main.model.GameState;
import main.model.WordleModel;
import main.view.EndMessageFinal;
import main.view.WordleView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This is a simple class to evaluate guesses by users
 */
public class GuessEvaluator {

    /** Word to be guessed. "word of the day" in Wordle context */
    private String secretWord;

    /** This is the analysis of the given guess, including correct and incorrect letters */
    private StringBuffer guessAnalysis;

    /** This keeps track of the letters in both the secret word and guessed word */
    private Map<Character, Character> mapOfLetters;

    private UsedWords usedWords;

    private WordleModel wordleModel;

    private WordleView wordleView;

    private EndMessageFinal endMessage;

    /**
     * Simple GuessEvaluator constructor to define the secret word, current guess,
     * and a guess analysis
     */
    public GuessEvaluator(WordleModel wordleModel, WordleView wordleView, String secretWord) {
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;
        this.secretWord = secretWord;
        this.guessAnalysis = new StringBuffer("-----");
        this.endMessage = new EndMessageFinal(this.wordleModel, this.wordleView);

        // If used words.txt does not exist, create it
        try {
            checkForUsedWordsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If used words.txt does not exist, create it. Always add the secret word
     * to usedwords.txt
     */
    private void checkForUsedWordsFile() throws IOException {
        File usedFile = new File("src/usedwords.txt");
        FileWriter fw = new FileWriter(usedFile);
        if (!usedFile.exists()) {
            try {
                this.usedWords.createFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fw.write(this.secretWord);

    }

    /**
     * Returns an encoded string with -, +, and * characters indicating
     * what letters are promising
     *
     * @return an encoded string with -, +, and * characters indicating
     * what letters are promising
     */
    public String analyzeGuess(String currentGuess) {
        // Initially empty
        this.mapOfLetters = new TreeMap<>();

        //first check for green letters (correct letter, correct position)
        for (int i = 0; i < currentGuess.length(); ++i) {
            if (this.secretWord.charAt(i) == currentGuess.charAt(i)) { this.guessAnalysis.setCharAt(i, '*'); }
            else { mapOfLetters.put(this.secretWord.charAt(i), currentGuess.charAt(i)); }
        }

        //then, check for yellow letters (correct letter, incorrect position)
        Set<Character> remainingSecretLetters = mapOfLetters.keySet();
        for (Map.Entry<Character,Character> entry : mapOfLetters.entrySet()) {
            if (remainingSecretLetters.contains(entry.getValue())) {
                this.guessAnalysis.setCharAt(currentGuess.indexOf(entry.getValue()),'+');
            }
        }
        return this.guessAnalysis.toString();
    }

    /**
     * Creates an evaluator for a given guess. The evaluator will take care of
     * finding if a letter is in the correct position, misplaced, or not even
     * in the word.
     *
     * @param guess - Given guess by user
     */
    public void createEvaluator(String guess) {
        // Obtain result from analyzing guess
        String evaluation = analyzeGuess(guess);

        // Perform screen animation such as flipping tiles
        this.wordleView.performScreenAnimation(evaluation, guess);

        // If the user gets the right word.
        if (evaluation.equals("*****")) {
            winnerUser();
        }
        // If user runs out of guesses.
        else if (this.wordleModel.getRow() >= 5) {
            loserUser();
        }
        this.wordleModel.incrementCurrentGuessNumber();
    }

    /**
     * In case user is a loser, we tell them the secret word
     * and print final message
     */
    private void loserUser() {
        this.wordleModel.setGameState(GameState.GAME_LOSER);
        this.wordleModel.setStreak(0);
        String message = "Secret word was " + this.wordleModel.getSecretWord().toUpperCase();
        if (this.wordleView.isFlippingDone()) {
            this.endMessage.showEndScreen("You Lost!", message);
        }
    }

    /**
     * In case user is a winner, we keep track of wins and print final message
     */
    private void winnerUser() {
        this.wordleModel.setGameState(GameState.GAME_WINNER);
        this.wordleModel.incrementCurrentWinStreak();
        String message = "Your streak: " + this.wordleModel.getCurrentWinStreak();
        if (this.wordleView.isFlippingDone()) {
            this.endMessage.showEndScreen("You won!", message);
        }
    }
}
