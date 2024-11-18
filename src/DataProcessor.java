import java.util.*;
import java.util.stream.Collectors;

/**
 * Decorator that adds sorting and filtering functionality.
 */
import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor extends CSVDecorator {
    private Comparator<Map<String, String>> comparator;
    private String filterKey;
    private String filterValue;

    public DataProcessor(CSVProcessor csvProcessor) {
        super(csvProcessor);
        this.comparator = null;
        this.filterKey = null;
        this.filterValue = null;
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

            // Reset the filter
            filterKey = null;
            filterValue = null;
        }

        // Apply sorting
        if (comparator != null) {
            data.sort(comparator);
        }

        return data;
    }
}

