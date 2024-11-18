import java.util.List;
import java.util.Map;

/**
 * Base interface representing the component in the Decorator Pattern.
 * Defines a method to process and return CSV data.
 */
public interface CSVProcessor {
    List<Map<String, String>> process();
}
