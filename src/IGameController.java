import java.io.IOException;
import java.util.List;

// Orchestrates the game loop: player input -> validation -> update state -> update view (part of controller)
public interface IGameController {

    // Initializes the game, players, and shows introductory messages
    public void initializeGame(List<IMovie> movieList);

    // Starts the game loop
    public void startGame() throws IOException;

    // Handles player input
    public void handlePlayerInput(String input);

    // Goes to next turn
    public void nextTurn();

    // Returns whether game is over
    public boolean isGameOver();

    // Ends the game
    public void endGame();

    // Handles player timeout
    public void handleTimeout();
}
