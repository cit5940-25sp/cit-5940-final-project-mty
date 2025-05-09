import java.util.List;
import java.util.Map;

// Maintains game state, players, and movie history (part of model)
public interface IGameModel {

    // Initializes players, loads movies, and sets win conditions
    public void initializePlayers();

    // Loads movie data from CSV files
    public Map<Integer, IMovie> loadMovieData(String moviesCsvFile, String creditsCsvFile);

    // Sets starting movie to currentMovie
    public void setStartingMovie(IMovie movie);

    // Gets list of players
    public List<IPlayer> getPlayers();

    // Gets current player
    public IPlayer getCurrentPlayer();

    // Switches to next player in the game
    public void switchToNextPlayer();

    // Gets current movie
    public IMovie getCurrentMovie();

    // Sets current movie
    public void setCurrentMovie(IMovie currentMovie);

    // Checks if a move is valid
    public boolean isValidMove(String movieTitle);

    // Makes move, updates movie history, and updates score
    public void makeMove(String movieTitle);

    // Checks whether player has won
    public boolean checkWinCondition(IPlayer player);

    // Checks whether game is over
    public boolean isGameOver();

    // Gets the winner
    public IPlayer getWinner();

    // Gets recent history of played movies
    public List<IMovie> getRecentHistory();

    // Gets round count
    public int getRoundCount();

    // Gets Player 1
    public IPlayer getPlayer1();

    // Gets Player 2
    public IPlayer getPlayer2();

    // Gets movies
    public Map<Integer, IMovie> getMovies();

    // Converts a map to a list of movies
    public List<IMovie> convertMapToListOfMovies(Map<Integer, IMovie> movies);
}
