import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Decorator that adds charting functionality.
 * Dynamically updates the chart based on processed data or user selections.
 */
public class ChartProcessor extends CSVDecorator {
    private JFrame frame;
    private ChartPanel chartPanel;

    public ChartProcessor(CSVProcessor csvProcessor) {
        super(csvProcessor);

        // Initialize the JFrame and ChartPanel
        frame = new JFrame("Poverty Chart Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        chartPanel = new ChartPanel(null);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * Updates and displays the chart with the given dataset.
     * The dataset can be created dynamically based on user selections or filtered/sorted data.
     */
    public void displayChart(List<Map<String, String>> data) {
        DefaultCategoryDataset dataset = createDataset(data);

        // Create a bar chart with proper parameters
        JFreeChart barChart = ChartFactory.createBarChart(
                "Poverty Statistics",           // Chart title
                "State",                        // X-axis label
                "Percentage",                   // Y-axis label
                dataset,                        // Data
                PlotOrientation.VERTICAL,       // Chart orientation
                true,                           // Include legend
                true,                           // Enable tooltips
                false                           // Disable URLs
        );

        // Update the chart panel
        chartPanel.setChart(barChart);
    }

    /**
     * Dynamically creates a dataset from the provided data.
     */
    private DefaultCategoryDataset createDataset(List<Map<String, String>> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, String> record : data) {
            String state = record.get("State");
            double childrenPoverty = parseSafe(record.get("Percent (Children Ages 0-17 in Poverty)"));
            double allPoverty = parseSafe(record.get("Percent (All People in Poverty)"));

            dataset.addValue(childrenPoverty, "Children", state);
            dataset.addValue(allPoverty, "All People", state);
        }

        return dataset;
    }

    /**
     * Safely parses a string to a double, returning 0 if the input is null or invalid.
     */
    private double parseSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
