import java.util.List;
import java.util.Set;

// Stores information per movie (part of model)
public interface IMovie {

    // Gets movie title
    public String getTitle();

    // Gets movie release year
    public int getYear();

    // Gets movie genres
    public List<String> getGenres();

    // Gets movie actors
    public List<String> getActors();

    // Gets movie crew members
    public List<String> getCrew();

    // Adds actor to list of actors and contributors
    public void addActor(String name);

    // Adds crew member to list of crew members and contributors
    public void addCrew(String name);

    // Adds director to list of directors and contributors
    public void addDirector(String name);

    // Adds writer to list of writers and contributors
    public void addWriter(String name);

    // Adds composer to list of composers and contributors
    public void addComposer(String name);

    // Adds cinematographer to list of cinematographers and contributors
    public void addCinematographer(String name);

    // Gets list of directors
    public List<String> getDirectors();

    // Gets list of writers
    public List<String> getWriters();

    // Gets list of composers
    public List<String> getComposers();

    // Gets list of cinematographers
    public List<String> getCinematographers();

    // Adds contributor to list of contributors
    public void addContributor(String name);

    // Gets all contributors
    public Set<String> getAllContributors();
}
