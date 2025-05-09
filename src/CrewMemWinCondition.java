import java.util.List;

// Logic for crew member win condition (part of strategy pattern)
public class CrewMemWinCondition implements IWinConditionStrategy{
    private String selectedCrewMember;

    // Constructor
    public CrewMemWinCondition(String selectedCrewMember) {
        this.selectedCrewMember = selectedCrewMember;
    }

    // Checks if crew member win condition has been met
    @Override
    public boolean checkWin(List<IMovie> playedMovies) {
        int count = 0;
        for (IMovie movie : playedMovies) {
            if (movie.getCrew().contains(selectedCrewMember)) {
                    count++;
            }
        }
        return count >= 5;
    }

    // Gets English description of crew member win condition
    @Override
    public String getDescription() {
        return "Play 5 movies of crew member " + selectedCrewMember + ".";
    }

    // Returns selected crew member
    public String getSelectedCrewMember() {
            return selectedCrewMember;
    }
}

