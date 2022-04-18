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
import main.view.EndMessageView;
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

    /** Secret word is added to usedwords.txt through this variable */
    private UsedWords usedWords;

    /** The model of the game */
    private WordleModel wordleModel;

    /** The view of the game */
    private WordleView wordleView;

    /** End message of the game */
    private EndMessageView endMessage;

    /**
     * Simple GuessEvaluator constructor to define the secret word, current guess,
     * and a guess analysis
     */
    public GuessEvaluator(WordleModel wordleModel, WordleView wordleView, String secretWord) {
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;
        this.secretWord = secretWord;
        this.endMessage = new EndMessageView(this.wordleModel, this.wordleView);

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
        this.guessAnalysis = new StringBuffer("-----");

        //first check for green letters (correct letter, correct position)
        for (int i = 0; i < currentGuess.length(); ++i) {
            this.wordleView.performFlip(this.wordleModel.getListOfGuesses().get(this.wordleModel.getRow()).get(i), i*500);
            if (this.secretWord.charAt(i) == currentGuess.charAt(i)) {
                this.guessAnalysis.setCharAt(i, '*');
                this.wordleView.changeTileColor("exact", i);
                this.wordleView.changeKeyboardLetterColor("exact", Character.toString(currentGuess.charAt(i)));
            }
            else if (!this.secretWord.contains(Character.toString(currentGuess.charAt(i)))) {
                this.wordleView.changeTileColor("wrong", i);
                this.wordleView.changeKeyboardLetterColor("wrong", Character.toString(currentGuess.charAt(i)));
            }
            else {
                this.guessAnalysis.setCharAt(i, '+');
                this.wordleView.changeTileColor("misplaced", i);
                this.wordleView.changeKeyboardLetterColor("misplaced", Character.toString(currentGuess.charAt(i)));
            }
        }
        System.out.println(this.guessAnalysis);
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
        this.endMessage.showEndScreen("You Lost!", message);
    }

    /**
     * In case user is a winner, we keep track of wins and print final message
     */
    private void winnerUser() {
        this.wordleModel.setGameState(GameState.GAME_WINNER);
        this.wordleModel.incrementCurrentWinStreak();
        String message = "Your streak: " + this.wordleModel.getCurrentWinStreak();
        this.endMessage.showEndScreen("You won!", message);
    }
}
