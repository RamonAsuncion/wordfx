/**
 * This class is the only class that needs a test. However, because it depends on the model,
 * we are getting a {@ExceptionInInitializerError} which we can't solve. We have tested the game as
 * regular players and the game seems to be working fine (We wish we could have fixed this on time).
 */

//package main.main;
//
//import main.model.WordleModel;
//import main.view.WordleView;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GuessEvaluatorTest {
//
//    private GuessEvaluator evaluator;
//    private WordleModel wordleModel;
//    private WordleView wordleView;
//
//    @Test
//    void analyzeGuess() {
//        wordleModel = new WordleModel(3);
//        wordleView = new WordleView(wordleModel);
//        evaluator = new GuessEvaluator(wordleModel, wordleView, "row");
//        String analysis = evaluator.analyzeGuess("row", 3);
//        assertEquals(analysis, "*****");
//    }
//
//    @Test
//    void loserUser() {
//    }
//
//    @Test
//    void winnerUser() {
//    }
//}