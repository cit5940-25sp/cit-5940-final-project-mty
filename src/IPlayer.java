import java.util.List;

// Tracks information on each player (scores + movies already played) (part of model)
public interface IPlayer {

    // Gets player name
    public String getName();

    // Gets list of played movies
    public List<IMovie> getPlayedMovies();

    // Adds movie to played movies
    public void addPlayedMovie(IMovie movie);

    // Checks if player has won by meeting win condition
    public boolean hasWon();

    // Gets win condition description
    public String getWinConditionDescription();

    // Gets win condition strategy
    public IWinConditionStrategy getWinConditionStrategy();

    // Sets win condition strategy
    public void setWinConditionStrategy(IWinConditionStrategy winConditionStrategy);

    // Gets player score
    public int getScore();

    // Sets player score
    public void setScore(int score);
}
