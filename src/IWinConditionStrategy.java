import java.util.List;

// Interface for win condition strategy (part of strategy pattern)
public interface IWinConditionStrategy {

    // Checks if win condition has been met
    public boolean checkWin(List<IMovie> playedMovies);

    // Returns English description of win condition
    public String getDescription();

}
