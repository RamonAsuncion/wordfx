package main.main;

import main.model.GameState;
import main.model.WordleModel;
import main.view.WordleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessEvaluatorTest {

    private GuessEvaluator evaluator3;
    private GuessEvaluator evaluator4;
    private GuessEvaluator evaluator5;
    private WordleModel wordleModel3;
    private WordleModel wordleModel4;
    private WordleModel wordleModel5;
    private WordleView wordleView3;
    private WordleView wordleView4;
    private WordleView wordleView5;


    @BeforeEach
    void setUp() {
        wordleModel3 = new WordleModel(3);
        wordleModel4 = new WordleModel(4);
        wordleModel5 = new WordleModel(5);
        wordleView3 = new WordleView(wordleModel3);
        wordleView4 = new WordleView(wordleModel3);
        wordleView5 = new WordleView(wordleModel3);
        evaluator3 = new GuessEvaluator(wordleModel3, wordleView3, "row");
        evaluator4 = new GuessEvaluator(wordleModel4, wordleView4, "time");
        evaluator5 = new GuessEvaluator(wordleModel5, wordleView5, "state");
    }

    @Test
    void feedback() {
        evaluator3.feedback("row");
        assertSame(wordleModel3.getGameState(), GameState.GAME_WINNER);
        assertSame(wordleModel3.getCurrentWinStreak(), 1);
    }
}