package isc;

public interface Game {
    /** Initialises the game state ready for a new game. */
    public void setUpGame();

    /** Plays a single turn, prompting the player for input and updating the score. */
    public void playTurn();

    /** Plays a full round, repeating turns until the game ends. */
    public void playRound();
}