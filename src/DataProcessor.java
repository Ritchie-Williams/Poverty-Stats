import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that processes data with filtering and sorting capabilities.
 * Implements the CSVProcessor interface.
 */
public class DataProcessor implements CSVProcessor {
    private final CSVProcessor csvProcessor;
    private Comparator<Map<String, String>> comparator;
    private String filterKey;
    private String filterValue;

    public DataProcessor(CSVProcessor csvProcessor) {
        this.csvProcessor = csvProcessor;
        this.comparator = null;
        this.filterKey = null;
        this.filterValue = null;
    }

    @Override
    public List<Map<String, String>> process() {
        List<Map<String, String>> data = csvProcessor.process();

        // Apply filtering
        if (filterKey != null && filterValue != null) {
            data = data.stream()
                    .filter(map -> map.get(filterKey).equalsIgnoreCase(filterValue))
                    .collect(Collectors.toList());
        }

        // Apply sorting
        if (comparator != null) {
            data.sort(comparator);
        }

        return data;
    }

    public void setComparator(Comparator<Map<String, String>> comparator) {
        this.comparator = comparator;
    }

    public void setFilter(String filterKey, String filterValue) {
        this.filterKey = filterKey;
        this.filterValue = filterValue;
    }
}
