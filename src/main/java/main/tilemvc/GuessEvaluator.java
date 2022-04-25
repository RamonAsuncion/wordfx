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

    private String currentGuess;

    private ArrayList<Character> correctArray;

    private ArrayList<Character> guessArray;

    private Map<Integer, ArrayList<String>> keyboardColors;

    /**
     * Simple GuessEvaluator constructor to define the secret word, current guess,
     * and a guess analysis
     */
    public GuessEvaluator(WordleModel wordleModel, WordleView wordleView, String secretWord) {
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;
        this.secretWord = secretWord;
        this.endMessage = new EndMessageView(this.wordleModel, this.wordleView);
        this.keyboardColors = new HashMap<>();

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
        //fw.write(this.secretWord);

    }

    /**
     * Returns an encoded string with -, +, and * characters indicating
     * what letters are promising
     *
     * @param currentGuess - user guess to be analyzed
     * @param wordLength - shows what mode user is playing
     * @return an encoded string with -, +, and * characters indicating
     * what letters are promising
     */

    public String analyzeGuess(String currentGuess, int wordLength) {
        this.guessAnalysis = new StringBuffer("-".repeat(wordLength));
        this.correctArray = new ArrayList<>();
        this.guessArray = new ArrayList<>();

        for (int i = 0; i < wordLength; i++) { this.correctArray.add(i, this.secretWord.charAt(i)); }
        for (int i = 0; i < wordLength; i++) { this.guessArray.add(i, currentGuess.charAt(i)); }

        for (int i = 0; i < wordLength; i++) {
            if (correctArray.get(i) == guessArray.get(i)) {
                this.guessAnalysis.setCharAt(i, '*');
                this.correctArray.set(i, '#');
                this.guessArray.set(i, '!');
            }
        }

        for (int i = 0; i < wordLength; i++) {
            if (correctArray.contains(guessArray.get(i))) {
                this.guessAnalysis.setCharAt(i, '+');
                this.correctArray.remove(guessArray.get(i));
            }
        }
        return this.guessAnalysis.toString();
    }

    public Map<Integer, ArrayList<String>> setKeyboardLetterColor(String style, String letter, int index) {
        // exact = *
        // misplaced = +
        // wrong = -

        keyboardColors.put(index, new ArrayList<String>());
        keyboardColors.get(index).add(style);
        keyboardColors.get(index).add(letter);

        return keyboardColors;
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
        String evaluation = analyzeGuess(guess, this.wordleModel.getWordLength());
        showAnalysis(evaluation, guess);

        // If the user gets the right word
        if (evaluation.equals("*".repeat(this.wordleModel.getWordLength()))) {
            winnerUser();
        }
        // If user runs out of guesses
        else if (this.wordleModel.getRow() >= 5) {
            loserUser();
        }
        this.wordleModel.incrementCurrentGuessNumber();
    }

    public void showAnalysis(String evaluation, String currentGuess) {
        String style;
        for (int i = 0; i < wordleModel.getWordLength(); i++) {
            switch (evaluation.charAt(i)) {
                case '*':
                    style = "exact";
                    break;
                case '+':
                    style = "misplaced";
                    break;
                default:
                    style = "wrong";
                    break;
            }
            this.wordleView.performFlip(this.wordleModel.getLetter(i), i, style, this.endMessage, keyboardColors);
            setKeyboardLetterColor(style, Character.toString(currentGuess.charAt(i)), i);
        }
    }

    /**
     * In case user is a loser, we tell them the secret word
     * and print final message
     */
    public void loserUser() {
        this.wordleModel.setGameState(GameState.GAME_LOSER);
        this.wordleModel.setStreak(0);
    }

    /**
     * In case user is a winner, we keep track of wins and print final message
     */
    public void winnerUser() {
        this.wordleModel.setGameState(GameState.GAME_WINNER);
        this.wordleModel.incrementCurrentWinStreak();
    }
}
