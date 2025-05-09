import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Stores information per movie (part of model)
public class Movie implements IMovie {
    private String title;
    private int year;
    private List<String> genres;
    private List<String> actors;
    private List<String> directors;
    private List<String> crew;
    private List<String> writers;
    private List<String> composers;
    private List<String> cinematographers;
    private Set<String> contributors;

    // Constructor
    public Movie(String title, int year, List<String> genres) {
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.actors = new ArrayList<>();
        this.directors = new ArrayList<>();
        this.crew = new ArrayList<>();
        this.writers = new ArrayList<>();
        this.composers = new ArrayList<>();
        this.cinematographers = new ArrayList<>();
        this.contributors = new HashSet<>();
    }

    // Gets movie title
    @Override
    public String getTitle() {
        return title;
    }

    // Gets movie release year
    @Override
    public int getYear() {
        return year;
    }

    // Gets movie genres
    @Override
    public List<String> getGenres() {
        return genres;
    }

    // Gets movie actors
    @Override
    public List<String> getActors() {
        return actors;
    }

    // Gets movie crew members
    @Override
    public List<String> getCrew() {
        return crew;
    }

    // Adds actor to list of actors and contributors
    @Override
    public void addActor(String name) {
        actors.add(name);
        contributors.add(name);
    }

    // Adds crew member to list of crew members and contributors
    @Override
    public void addCrew(String name) {
        crew.add(name);
        contributors.add(name);
    }

    // Adds director to list of directors and contributors
    @Override
    public void addDirector(String name) {
        directors.add(name);
        contributors.add(name);
    }

    // Adds writer to list of writers and contributors
    @Override
    public void addWriter(String name) {
        writers.add(name);
        contributors.add(name);
    }

    // Adds composer to list of composers and contributors
    @Override
    public void addComposer(String name) {
        composers.add(name);
        contributors.add(name);
    }

    // Adds cinematographer to list of cinematographers and contributors
    @Override
    public void addCinematographer(String name) {
        cinematographers.add(name);
        contributors.add(name);
    }

    // Gets list of directors
    @Override
    public List<String> getDirectors() {
        return directors;
    }

    // Gets list of writers
    @Override
    public List<String> getWriters() {
        return writers;
    }

    // Gets list of composers
    @Override
    public List<String> getComposers() {
        return composers;
    }

    // Gets list of cinematographers
    @Override
    public List<String> getCinematographers() {
        return cinematographers;
    }

    // Adds contributor to list of contributors
    @Override
    public void addContributor(String name) {
        contributors.add(name);
    }

    // Gets all contributors
    @Override
    public Set<String> getAllContributors() {
        return contributors;
    }
}
