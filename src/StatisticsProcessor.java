import java.util.List;
import java.util.Map;

/**
 * Decorator that calculates and displays descriptive statistics.
 */
public class StatisticsProcessor extends CSVDecorator {

    public StatisticsProcessor(CSVProcessor csvProcessor) {
        super(csvProcessor);
    }

    /**
     * Calculates and displays the average poverty rates for children and all people.
     */
    public void displayStatistics() {
        List<Map<String, String>> data = super.process();

        // Calculate average poverty for children
        double avgChildrenPoverty = data.stream()
                .mapToDouble(record -> parseSafe(record.get("Percent (Children Ages 0-17 in Poverty)")))
                .average()
                .orElse(0);

        // Calculate average poverty for all people
        double avgAllPoverty = data.stream()
                .mapToDouble(record -> parseSafe(record.get("Percent (All People in Poverty)")))
                .average()
                .orElse(0);

        // Display the statistics
        System.out.printf("Average Poverty for Children: %.2f%%\n", avgChildrenPoverty);
        System.out.printf("Average Poverty for All People: %.2f%%\n", avgAllPoverty);
    }

    /**
     * Safely parses a string to a double, returning 0 if the input is null or invalid.
     */
    private double parseSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0; // Default value for missing or empty entries
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0; // Default value for invalid numbers
        }
    }
}
