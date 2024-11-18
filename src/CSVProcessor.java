import java.util.List;
import java.util.Map;

/**
 * Interface for processing CSV data.
 * Classes implementing this interface should provide a process() method to retrieve data.
 */
public interface CSVProcessor {
    List<Map<String, String>> process();
}
