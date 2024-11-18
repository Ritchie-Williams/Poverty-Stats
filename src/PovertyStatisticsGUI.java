import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Main GUI class for the Poverty Statistics Viewer.
 * Interacts with DataAdapter and ChartAdapter to display and manipulate data.
 */
public class PovertyStatisticsGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private DataAdapter dataAdapter;
    private ChartAdapter chartAdapter;

    public PovertyStatisticsGUI(DataAdapter dataAdapter, ChartAdapter chartAdapter) {
        this.dataAdapter = dataAdapter;
        this.chartAdapter = chartAdapter;

        // Set up the main GUI frame
        frame = new JFrame("Poverty Statistics Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the table and its model
        tableModel = new DefaultTableModel(new String[]{"State", "Children Poverty (%)", "All Poverty (%)"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Populate the table with initial data
        updateTable(dataAdapter.getData());
        chartAdapter.displayChart(dataAdapter.getData()); // Show initial data in the chart

        // Controls panel for filtering and sorting
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(2, 4, 10, 10));

        // Filter functionality
        JTextField filterField = new JTextField();
        JButton filterButton = new JButton("Filter by State");
        filterButton.addActionListener(e -> {
            String state = filterField.getText().trim();
            if (!state.isEmpty()) {
                // Filter by the given state
                dataAdapter.filter("State", state);
                List<Map<String, String>> filteredData = dataAdapter.getData();
                updateTable(filteredData);
                chartAdapter.displayChart(filteredData);
            }
        });

        // Reset filter functionality
        JButton resetFilterButton = new JButton("Reset Filter");
        resetFilterButton.addActionListener(e -> {
            // Clear any existing filters
            dataAdapter.filter(null, null); // Reset filter
            List<Map<String, String>> fullData = dataAdapter.getData();
            updateTable(fullData); // Update table with the full dataset
            chartAdapter.displayChart(fullData); // Update chart with the full dataset
            filterField.setText(""); // Clear the filter field
        });

        // Sorting Buttons for Children Poverty
        JButton sortChildrenAsc = new JButton("Sort by Children Poverty Asc");
        sortChildrenAsc.addActionListener(e -> {
            dataAdapter.sort(Comparator.comparing(map -> parseSafe(map.get("Percent (Children Ages 0-17 in Poverty)"))));
            List<Map<String, String>> sortedData = dataAdapter.getData();
            updateTable(sortedData);
            chartAdapter.displayChart(sortedData);
        });

        JButton sortChildrenDesc = new JButton("Sort by Children Poverty Desc");
        sortChildrenDesc.addActionListener(e -> {
            dataAdapter.sort((map1, map2) -> Double.compare(
                    parseSafe(map2.get("Percent (Children Ages 0-17 in Poverty)")),
                    parseSafe(map1.get("Percent (Children Ages 0-17 in Poverty)"))
            ));
            List<Map<String, String>> sortedData = dataAdapter.getData();
            updateTable(sortedData);
            chartAdapter.displayChart(sortedData);
        });

        // Sorting Buttons for All Poverty
        JButton sortAllAsc = new JButton("Sort by All Poverty Asc");
        sortAllAsc.addActionListener(e -> {
            dataAdapter.sort(Comparator.comparing(map -> parseSafe(map.get("Percent (All People in Poverty)"))));
            List<Map<String, String>> sortedData = dataAdapter.getData();
            updateTable(sortedData);
            chartAdapter.displayChart(sortedData);
        });

        JButton sortAllDesc = new JButton("Sort by All Poverty Desc");
        sortAllDesc.addActionListener(e -> {
            dataAdapter.sort((map1, map2) -> Double.compare(
                    parseSafe(map2.get("Percent (All People in Poverty)")),
                    parseSafe(map1.get("Percent (All People in Poverty)"))
            ));
            List<Map<String, String>> sortedData = dataAdapter.getData();
            updateTable(sortedData);
            chartAdapter.displayChart(sortedData);
        });

        // Add components to the controls panel
        controlsPanel.add(new JLabel("State Filter:"));
        controlsPanel.add(filterField);
        controlsPanel.add(filterButton);
        controlsPanel.add(resetFilterButton);
        controlsPanel.add(sortChildrenAsc);
        controlsPanel.add(sortChildrenDesc);
        controlsPanel.add(sortAllAsc);
        controlsPanel.add(sortAllDesc);

        // Add components to the frame
        frame.add(controlsPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set frame size and make it visible
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    /**
     * Updates the table with the given data.
     */
    private void updateTable(List<Map<String, String>> data) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Map<String, String> record : data) {
            tableModel.addRow(new Object[]{
                    record.get("State"),
                    record.get("Percent (Children Ages 0-17 in Poverty)"),
                    record.get("Percent (All People in Poverty)")
            });
        }
    }

    /**
     * Safely parses a string into a double.
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
