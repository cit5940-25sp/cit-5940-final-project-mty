import java.util.List;

// Displays current round status, player progress, recent plays, etc. (part of view)
public class GameView implements IGameView {
    private TerminalWithSuggestions terminal;
    private ConnectionValidator connectionValidator;
    private GameModel gameModel;

    // Constructor
    public GameView(TerminalWithSuggestions terminal, ConnectionValidator connectionValidator, GameModel gameModel) {
        this.terminal = terminal;
        this.connectionValidator = connectionValidator;
        this.gameModel = gameModel;
    }

    // Displays welcome message
    @Override
    public void showWelcomeMessage() {
        terminal.displayMessage("Welcome to the Movie Name Game!");
    }

    // Displays win conditions
    @Override
    public void showWinConditions(List<IPlayer> players) {
        for (IPlayer player : players) {
            terminal.displayMessage(player.getName() + " win condition: " + player.getWinConditionDescription());
        }
    }

    // Displays player for that round
    @Override
    public void showGameStart(IPlayer currentPlayer) {
        terminal.displayMessage("Round start: " + currentPlayer.getName());
    }

    // Displays prompt for movie
    @Override
    public void promptForMovie(IPlayer currentPlayer) {
        terminal.displayMessage(currentPlayer.getName() + ", enter a movie title.");
    }

    // Displays move success
    @Override
    public void showMoveSuccess(String movieTitle, IPlayer currentPlayer) {
        terminal.displayMessage(currentPlayer.getName() + " played " + movieTitle);
    }

    // Displays invalid move
    @Override
    public void showInvalidMove(String movieTitle) {
        terminal.displayMessage("Invalid movie title.");
    }

    // Displays player timeout
    @Override
    public void showTimeout(IPlayer currentPlayer) {
        terminal.displayMessage(currentPlayer.getName() + " ran out of time.");
    }

    // Displays game winner
    @Override
    public void showWinner(IPlayer winner) {
        terminal.displayMessage(winner.getName() + " is the winner!");

    }

    // Displays next player's turn
    @Override
    public void showNextTurn(IPlayer currentPlayer) {
        terminal.displayMessage("Next: " + currentPlayer.getName());
    }

    // Displays recent movie history, genres, and links between them
    @Override
    public void showMovieHistory(List<IMovie> recentMovies) {
        terminal.displayMessage("Last 5 movies:");

        for (int i = 0; i < recentMovies.size(); i++) {
            IMovie movie = recentMovies.get(i);
            terminal.displayMessage("- " + movie.getTitle() + " " + movie.getGenres());

            if (i < recentMovies.size() - 1) {
                IMovie next = recentMovies.get(i + 1);
                List<String> connections = connectionValidator.getSharedConnections(movie, next);
                terminal.displayMessage("  -> Links to next: " + connections);
            }
        }
    }

    // Displays player stats (individual score and progress towards win condition)
    @Override
    public void showPlayerStats(List<IPlayer> players, int roundCount) {
        terminal.displayMessage("Player stats after round " + roundCount);
        for (IPlayer player : players) {
            terminal.displayMessage(player.getName() + " | Score: " + player.getScore());

            terminal.displayMessage("  -> " + player.getWinConditionDescription());
            int progress = estimateProgress(player);
            terminal.displayMessage("  -> Progress: " + progress + " / 5" );
        }
    }

    // Estimates progress of player towards their win condition
    private int estimateProgress(IPlayer player) {
        IWinConditionStrategy strategy = player.getWinConditionStrategy();
        List<IMovie> played = player.getPlayedMovies();

        if (strategy instanceof ActorWinCondition) {
            ActorWinCondition actorWin = (ActorWinCondition) strategy;
            String target = actorWin.getSelectedActor();
            int count = 0;
            for (IMovie movie : played) {
                if (movie.getActors().contains(target)) {
                    count++;
                }
            }
            return count;
        } else if (strategy instanceof CrewMemWinCondition) {
            CrewMemWinCondition crewWin = (CrewMemWinCondition) strategy;
            String target = crewWin.getSelectedCrewMember();
            int count = 0;
            for (IMovie movie : played) {
                if (movie.getCrew().contains(target)) {
                    count++;
                }
            }
            return count;
        }

        return 0;
    }
}
