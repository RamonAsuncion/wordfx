package main.model;

import javafx.application.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleModelTest {

    private WordleModel wordleModel;

    @BeforeEach
    void setUp() {
        Application.launch(main.main.WordleMain.class);
        wordleModel = new WordleModel(5); // length won't matter for tests
    }

    @Test
    void incrementRow() {
        wordleModel.incrementRow();
        assertEquals(wordleModel.getRow(), 1);
    }

    @Test
    void incrementColumn() {
        // column starts at -1
        wordleModel.incrementColumn();
        assertEquals(wordleModel.getColumn(), 0);
    }

    @Test
    void decrementColumn() {
        // column starts at -1
        wordleModel.incrementColumn();
        wordleModel.incrementColumn();
        wordleModel.incrementColumn();
        wordleModel.decrementColumn();
        assertEquals(wordleModel.getColumn(), 1);
    }
}