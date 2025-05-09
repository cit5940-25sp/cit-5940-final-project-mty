import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Logic for actor win condition (part of strategy pattern)
public class ActorWinCondition implements IWinConditionStrategy {
    private String selectedActor;

    // Constructor
    public ActorWinCondition(String selectedActor) {
        this.selectedActor = selectedActor;
    }

    // Checks if actor win condition has been met
    @Override
    public boolean checkWin(List<IMovie> playedMovies) {
        Map<String, Integer>  actorCount = new HashMap<String, Integer>();
        for (IMovie movie : playedMovies) {
            List<String> actors = movie.getActors();
            for (String actor : actors) {
                if (!actorCount.containsKey(actor)) {
                    actorCount.put(actor, 1);
                } else {
                    actorCount.put(actor, actorCount.get(actor) + 1);
                }
                if (actorCount.get(actor) == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    // Gets English description of actor win condition
    @Override
    public String getDescription() {
        return "Play 5 movies of actor " + selectedActor + ".";
    }

    // Returns selected actor
    public String getSelectedActor() {
        return selectedActor;
    }
}
