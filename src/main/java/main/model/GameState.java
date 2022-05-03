package main.model;

/**
 * Simple enumeration to take care of the game state
 */
public enum GameState {
    NEW_GAME(true),
    GAME_IN_PROGRESS(true),
    GAME_PAUSED,
    GAME_WINNER,
    GAME_LOSER;

    final boolean playable;

    GameState() {
        this(false);
    }

    GameState(boolean playable) {
        this.playable = playable;
    }

    public boolean isPlayable() {
        return playable;
    }
}
