import java.util.ArrayList;
import java.util.List;

// Tracks information on each player (scores + movies already played) (part of model)
public class Player implements IPlayer {
    private String name;
    private List<IMovie> playedMovies;
    private IWinConditionStrategy winConditionStrategy;
    private int score;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.playedMovies = new ArrayList<>();
        this.score = 0;
    }

    // Gets player name
    @Override
    public String getName() {
        return this.name;
    }

    // Gets list of played movies
    @Override
    public List<IMovie> getPlayedMovies() {
        return this.playedMovies;
    }

    // Adds movie to played movies
    @Override
    public void addPlayedMovie(IMovie movie) {
        this.playedMovies.add(movie);
    }

    // Checks if player has won by meeting win condition
    @Override
    public boolean hasWon() {
        return this.winConditionStrategy.checkWin(this.playedMovies);
    }

    // Gets win condition description
    @Override
    public String getWinConditionDescription() {
        return this.winConditionStrategy.getDescription();
    }

    // Gets win condition strategy
    @Override
    public IWinConditionStrategy getWinConditionStrategy() {
        return this.winConditionStrategy;
    }

    // Sets win condition strategy
    @Override
    public void setWinConditionStrategy(IWinConditionStrategy winConditionStrategy) {
        this.winConditionStrategy = winConditionStrategy;
    }

    // Gets player score
    @Override
    public int getScore() {
        return this.score;
    }

    // Sets player score
    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
