import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

// Finds and manages valid connections between movies (part of the model)
public class ConnectionValidator implements IConnectionValidator {

    private Map<String, Integer> usageCount;
    private final int MAX_USAGE_PER_PERSON = 3;

    // Constructor
    public ConnectionValidator() {
        this.usageCount = new HashMap<>();
    }

    // Checks if two movies have a valid connection
    public boolean isValidConnection(IMovie movie1, IMovie movie2) {
        List<String> shared = getSharedConnections(movie1, movie2);

        if (shared == null) {
            return false;
        }
        for (String person : shared) {
            if (getUsageCount(person) < MAX_USAGE_PER_PERSON) {
                return true;
            }
        }
        return false;
    }

    // Gets shared connections between two movies
    @Override
    public List<String> getSharedConnections(IMovie movie1, IMovie movie2) {
        Set<String> contributorsA = movie1.getAllContributors();
        Set<String> contributorsB = movie2.getAllContributors();
        List<String> shared = new ArrayList<>();

        for (String person : contributorsA) {
            if (contributorsB.contains(person)) {
                shared.add(person);
            }
        }
        return shared;
    }

    // Gets usage count for a connection
    @Override
    public int getUsageCount(String connection) {
        return usageCount.getOrDefault(connection, 0);
    }

    // Records connection in usage count
    @Override
    public void recordConnectionUse(List<String> connections) {
        for (String name: connections) {
            int count = usageCount.getOrDefault(name, 0);
            usageCount.put(name, count + 1);
        }
    }

    // Resets usage count
    @Override
    public void resetUsageCount() {
        usageCount.clear();
    }
}
