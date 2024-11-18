import java.util.List;
import java.util.Map;

/**
 * Adapter interface for chart generation.
 * Unifies the chart creation process, making it interchangeable.
 */
public interface ChartAdapter {
    void displayChart(List<Map<String, String>> data); // Display a chart for the given data
}
