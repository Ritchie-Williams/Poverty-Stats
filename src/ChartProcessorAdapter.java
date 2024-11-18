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
 * Adapter for the ChartProcessor class.
 * Implements the ChartAdapter interface, adapting the charting functionality to work with the GUI.
 */
public class ChartProcessorAdapter implements ChartAdapter {
    private JFrame frame;
    private ChartPanel chartPanel;

    /**
     * Initializes the chart display window.
     * This adapts the JFreeChart library to a unified interface.
     */
    public ChartProcessorAdapter() {
        frame = new JFrame("Poverty Chart Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        chartPanel = new ChartPanel(null);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * Displays a bar chart using the provided data.
     * Adapts the chart creation process to the ChartAdapter interface.
     */
    @Override
    public void displayChart(List<Map<String, String>> data) {
        DefaultCategoryDataset dataset = createDataset(data);

        JFreeChart barChart = ChartFactory.createBarChart(
                "Poverty Statistics",
                "State",
                "Percentage",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chartPanel.setChart(barChart);
    }

    /**
     * Creates a dataset for the chart from the provided data.
     * Adapts raw data into a format that JFreeChart can use.
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
