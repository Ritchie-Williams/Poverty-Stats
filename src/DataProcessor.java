import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for processing data with filtering and sorting capabilities.
 * Implements the CSVProcessor interface.
 */
public class DataProcessor implements CSVProcessor {
    private final CSVProcessor csvProcessor;
    private Comparator<Map<String, String>> comparator;
    private String filterKey;
    private String filterValue;

    public DataProcessor(CSVProcessor csvProcessor) {
        this.csvProcessor = csvProcessor;
    }

    @Override
    public List<Map<String, String>> process() {
        List<Map<String, String>> data = csvProcessor.process();

        // Apply filtering only if a filter is set
        if (filterKey != null && filterValue != null) {
            data = data.stream()
                    .filter(map -> map.get(filterKey).equalsIgnoreCase(filterValue))
                    .collect(Collectors.toList());
        }

        // Apply sorting if a comparator is set
        if (comparator != null) {
            data.sort(comparator);
        }

        return data;
    }

    public void setFilter(String filterKey, String filterValue) {
        this.filterKey = filterKey;
        this.filterValue = filterValue;
    }

    public void setComparator(Comparator<Map<String, String>> comparator) {
        this.comparator = comparator;
    }
}

