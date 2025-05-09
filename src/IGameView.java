import java.util.List;

// Displays current round status, player progress, recent plays, etc. (part of view)
public interface IGameView {

    // Displays welcome message
    public void showWelcomeMessage();

    // Displays win conditions
    public void showWinConditions(List<IPlayer> players);

    // Displays player for that round
    public void showGameStart(IPlayer currentPlayer);

    // Displays prompt for movie
    public void promptForMovie(IPlayer currentPlayer);

    // Displays move success
    public void showMoveSuccess(String movieTitle, IPlayer currentPlayer);

    // Displays invalid move
    public void showInvalidMove(String movieTitle);

    // Displays player timeout
    public void showTimeout(IPlayer currentPlayer);

    // Displays game winner
    public void showWinner(IPlayer winner);

    // Displays next player's turn
    public void showNextTurn(IPlayer currentPlayer);

    // Displays recent movie history, genres, and links between them
    public void showMovieHistory(List<IMovie> recentMovies);

    // Displays player stats (individual score and progress towards win condition)
    public void showPlayerStats(List<IPlayer> players, int roundCount);
}
