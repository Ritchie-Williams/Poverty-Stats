import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Adapter interface for data processing.
 * Unifies the filtering, sorting, and data retrieval methods into a single interface.
 */
public interface DataAdapter {
    List<Map<String, String>> getData(); // Retrieve the current dataset

    void filter(String key, String value); // Filter data by a specific key-value pair

    void sort(Comparator<Map<String, String>> comparator); // Sort data using a custom comparator
}
