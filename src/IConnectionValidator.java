import java.util.List;

// Finds and manages valid connections between movies (part of the model)
public interface IConnectionValidator {

    // Checks if two movies have a valid connection
    public boolean isValidConnection(IMovie movie1, IMovie movie2);

    // Gets shared connections between two movies
    public List<String> getSharedConnections(IMovie movie1, IMovie movie2);

    // Gets usage count for a connection
    public int getUsageCount(String connection);

    // Records connection in usage count
    public void recordConnectionUse(List<String> connections);

    // Resets usage count
    public void resetUsageCount();
}
