import java.util.List;
import java.util.Map;

// Handles indexing and searching for movies (model)
public interface IMovieIndex {

    // Parses CSV file to get movie ID, title, year, and genre
    public Map<Integer, IMovie> loadMovies(String movieFile);

    // Associates cast and crew with the movies loaded (all under contributors)
    public void loadCast(String creditFile, Map<Integer, IMovie> movieMap);

    // Gets movie object by title
    public IMovie getMovieByTitle(String title);

    // Gets list of movies that are connected to input movie
    public List<IMovie> getConnectedMovies(IMovie movie);

    // Checks if index contains movie
    public boolean containsMovie(String title);

}
