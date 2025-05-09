import java.util.*;

// Maintains game state, players, and movie history (part of model)
public class GameModel implements IGameModel {

    private List<IPlayer> players;
    private int currentPlayerIndex;
    private IMovie currentMovie;
    private List<IMovie> recentHistory;
    private Set<String> usedMovies;
    private IPlayer winner;
    private int roundCount;
    private ConnectionValidator connectionValidator;
    private IPlayer player1;
    private IPlayer player2;
    private Map<Integer, IMovie> movies;

    // Constructor
    public GameModel() {
        this.recentHistory = new ArrayList<>();
        this.usedMovies = new HashSet<>();
        this.roundCount = 0;
        this.connectionValidator = new ConnectionValidator();
    }

    // Initializes players, loads movies, and sets win conditions
    @Override
    public void initializePlayers() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        this.player1 = player1;
        this.player2 = player2;
        this.players = Arrays.asList(player1, player2);

        Set<String> allActors = new HashSet<>();
        Set<String> allCrew = new HashSet<>();

        Map<Integer, IMovie> movies = loadMovieData("tmdb_5000_movies.csv", "tmdb_5000_credits.csv");
        this.movies = movies;

        for (IMovie movie : movies.values()) {
            allActors.addAll(movie.getActors());
            allCrew.addAll(movie.getCrew());
        }

        List<String> actorList = new ArrayList<>(allActors);
        List<String> crewList = new ArrayList<>(allCrew);
        Random ran = new Random();


        boolean p1usingActor = ran.nextBoolean(); // choosing between win condition is actor or crew

        if (p1usingActor && !allActors.isEmpty()) {
            String selectedActor = actorList.get(ran.nextInt(actorList.size()));
            player1.setWinConditionStrategy(new ActorWinCondition(selectedActor));
        } else if (!allCrew.isEmpty()) {
            String selectedCrewMember = crewList.get(ran.nextInt(crewList.size()));
            player1.setWinConditionStrategy(new CrewMemWinCondition(selectedCrewMember));
        }

        boolean p2usingActor = ran.nextBoolean();
        if (p2usingActor && !allActors.isEmpty()) {
            String selectedActor = actorList.get(ran.nextInt(actorList.size()));
            player2.setWinConditionStrategy(new ActorWinCondition(selectedActor));
        } else if (!allCrew.isEmpty()) {
            String selectedCrew = crewList.get(ran.nextInt(crewList.size()));
            player2.setWinConditionStrategy(new CrewMemWinCondition(selectedCrew));
        }

        this.currentPlayerIndex = 0;
    }

    // Loads movie data from CSV files
    @Override
    public Map<Integer, IMovie> loadMovieData(String moviesCsvFile, String creditsCsvFile) {
        MovieIndex movieIndex = new MovieIndex();
        Map<Integer, IMovie> movies = movieIndex.loadMovies(moviesCsvFile); // loads the movies
        movieIndex.loadCast(creditsCsvFile, movies);
        return movies;
    }

    // Sets starting movie to currentMovie
    @Override
    public void setStartingMovie(IMovie movie) {
        this.currentMovie = movie;
    }

    // Gets list of players
    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    // Gets current player
    @Override
    public IPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    // Switches to next player in the game
    @Override
    public void switchToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        roundCount++;
    }

    // Gets current movie
    @Override
    public IMovie getCurrentMovie() {
        return currentMovie;
    }

    // Sets current movie
    @Override
    public void setCurrentMovie(IMovie currentMovie) {
        this.currentMovie = currentMovie;
    }

    // Checks if a move is valid
    @Override
    public boolean isValidMove(String movieTitle) {
        MovieIndex movieIndex = new MovieIndex();
        Map<Integer, IMovie> movies = movieIndex.loadMovies("tmdb_5000_movies.csv");
        movieIndex.loadCast("tmdb_5000_credits.csv", movies);
        IMovie candidate = movieIndex.getMovieByTitle(movieTitle);

        if (candidate == null) {
            return false;
        }
        if (usedMovies.contains(candidate.getTitle().toLowerCase())) {
            return false;
        }
        List<String> shared = getSharedConnections(currentMovie, candidate);
        return !shared.isEmpty();
    }

    // Makes move, updates movie history, and updates score
    @Override
    public void makeMove(String movieTitle) {
        MovieIndex movieIndex = new MovieIndex();
        Map<Integer, IMovie> loadedMovies = movieIndex.loadMovies("tmdb_5000_movies.csv");
        movieIndex.loadCast("tmdb_5000_credits.csv", loadedMovies);

        IMovie nextMovie = movieIndex.getMovieByTitle(movieTitle);
        if (nextMovie == null || usedMovies.contains(nextMovie.getTitle().toLowerCase())) {
            return;
        }

        List<String> sharedConnections = connectionValidator.getSharedConnections(currentMovie, nextMovie);
        connectionValidator.recordConnectionUse(sharedConnections);

        // Add the movie that was just connected FROM to the history
        if (currentMovie != null) {
            recentHistory.add(currentMovie);
            if (recentHistory.size() > 5) {
                recentHistory.remove(0);
            }
            usedMovies.add(currentMovie.getTitle().toLowerCase());
        }

        currentMovie = nextMovie;
        usedMovies.add(nextMovie.getTitle().toLowerCase());
        getCurrentPlayer().addPlayedMovie(nextMovie);

        int currentPlayerScore = getCurrentPlayer().getScore();
        currentPlayerScore++;
        getCurrentPlayer().setScore(currentPlayerScore);
    }

    // Checks whether player has won
    @Override
    public boolean checkWinCondition(IPlayer player) {
        boolean won = player.hasWon();
        if (won) {
            winner = player;
        }
        return won;
    }

    // Checks whether game is over
    @Override
    public boolean isGameOver() {
        return winner != null;
    }

    // Gets the winner
    @Override
    public IPlayer getWinner() {
        return winner;
    }

    // Gets recent history of played movies
    @Override
    public List<IMovie> getRecentHistory() {
        return new ArrayList<>(recentHistory);
    }

    // Gets round count
    @Override
    public int getRoundCount() {
        return roundCount;
    }

    // Gets shared connections
    private List<String> getSharedConnections(IMovie a, IMovie b) {
        Set<String> contributorsA = a.getAllContributors();
        Set<String> contributorsB = b.getAllContributors();
        List<String> shared = new ArrayList<>();

        for (String contributor : contributorsA) {
            if (contributorsB.contains(contributor)) {
                shared.add(contributor);
            }
        }
        return shared;
    }

    // Gets Player 1
    @Override
    public IPlayer getPlayer1() {
        return player1;
    }

    // Gets Player 2
    @Override
    public IPlayer getPlayer2() {
        return player2;
    }

    // Gets movies
    @Override
    public Map<Integer, IMovie> getMovies() {
        return movies;
    }

    // Converts a map to a list of movies
    @Override
    public List<IMovie> convertMapToListOfMovies(Map<Integer, IMovie> movies) {
        List<IMovie> movieList = new ArrayList<>();
        for (IMovie movie : movies.values()) {
            movieList.add(movie);
        }

        return movieList;
    }
}
