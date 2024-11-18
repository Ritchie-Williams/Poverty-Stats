import java.util.*;
import java.util.stream.Collectors;

/**
 * Decorator that adds sorting and filtering functionality.
 */
public class DataProcessor extends CSVDecorator {
    private Comparator<Map<String, String>> comparator;
    private String filterKey;
    private String filterValue;

    public DataProcessor(CSVProcessor csvProcessor) {
        super(csvProcessor);
        this.comparator = null; // Default: no sorting
        this.filterKey = null; // Default: no filtering
        this.filterValue = null; // Default: no filtering
    }

    public void setComparator(Comparator<Map<String, String>> comparator) {
        this.comparator = comparator;
    }

    public void setFilter(String filterKey, String filterValue) {
        this.filterKey = filterKey;
        this.filterValue = filterValue;
    }

    @Override
    public List<Map<String, String>> process() {
        List<Map<String, String>> data = super.process();

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
}
