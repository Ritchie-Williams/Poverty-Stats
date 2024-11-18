import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;


public class PovertyStatisticsGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private DataProcessor dataProcessor;
    private ChartProcessor chartProcessor;

    public PovertyStatisticsGUI(DataProcessor dataProcessor, ChartProcessor chartProcessor) {
        this.dataProcessor = dataProcessor;
        this.chartProcessor = chartProcessor;

        // Create and set up the main GUI frame
        frame = new JFrame("Poverty Statistics Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the table model and JTable
        tableModel = new DefaultTableModel(new String[]{"State", "Children Poverty (%)", "All Poverty (%)"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Populate the table with initial data
        updateTable(dataProcessor.process());

        // Controls panel for filtering and sorting
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(2, 4, 10, 10)); // Adjusted for more buttons

        // Filter by State
        JTextField filterField = new JTextField();
        JButton filterButton = new JButton("Filter by State");
        filterButton.addActionListener(e -> {
            String state = filterField.getText().trim();
            if (!state.isEmpty()) {
                dataProcessor.setFilter("State", state);
                List<Map<String, String>> filteredData = dataProcessor.process();
                updateTable(filteredData);
                chartProcessor.displayChart(filteredData);
            } else {
                // Reset to all data if filter field is empty
                List<Map<String, String>> allData = dataProcessor.process();
                updateTable(allData);
                chartProcessor.displayChart(allData);
            }
        });

        // Sorting Buttons for Children Poverty
        JButton sortChildrenAsc = new JButton("Sort by Children Poverty Asc");
        sortChildrenAsc.addActionListener(e -> {
            dataProcessor.setComparator((map1, map2) -> Double.compare(
                    parseSafe(map1.get("Percent (Children Ages 0-17 in Poverty)")),
                    parseSafe(map2.get("Percent (Children Ages 0-17 in Poverty)"))
            ));
            List<Map<String, String>> sortedData = dataProcessor.process();
            updateTable(sortedData);
            chartProcessor.displayChart(sortedData);
        });

        JButton sortChildrenDesc = new JButton("Sort by Children Poverty Desc");
        sortChildrenDesc.addActionListener(e -> {
            dataProcessor.setComparator((map1, map2) -> Double.compare(
                    parseSafe(map2.get("Percent (Children Ages 0-17 in Poverty)")),
                    parseSafe(map1.get("Percent (Children Ages 0-17 in Poverty)"))
            ));
            List<Map<String, String>> sortedData = dataProcessor.process();
            updateTable(sortedData);
            chartProcessor.displayChart(sortedData);
        });

        // Sorting Buttons for All Poverty
        JButton sortAllAsc = new JButton("Sort by All Poverty Asc");
        sortAllAsc.addActionListener(e -> {
            dataProcessor.setComparator((map1, map2) -> Double.compare(
                    parseSafe(map1.get("Percent (All People in Poverty)")),
                    parseSafe(map2.get("Percent (All People in Poverty)"))
            ));
            List<Map<String, String>> sortedData = dataProcessor.process();
            updateTable(sortedData);
            chartProcessor.displayChart(sortedData);
        });

        JButton sortAllDesc = new JButton("Sort by All Poverty Desc");
        sortAllDesc.addActionListener(e -> {
            dataProcessor.setComparator((map1, map2) -> Double.compare(
                    parseSafe(map2.get("Percent (All People in Poverty)")),
                    parseSafe(map1.get("Percent (All People in Poverty)"))
            ));
            List<Map<String, String>> sortedData = dataProcessor.process();
            updateTable(sortedData);
            chartProcessor.displayChart(sortedData);
        });

        // Add components to controls panel
        controlsPanel.add(new JLabel("State Filter:"));
        controlsPanel.add(filterField);
        controlsPanel.add(filterButton);
        controlsPanel.add(sortChildrenAsc);
        controlsPanel.add(sortChildrenDesc);
        controlsPanel.add(sortAllAsc);
        controlsPanel.add(sortAllDesc);

        // Add components to the frame
        frame.add(controlsPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set frame size and visibility
        frame.setSize(1000, 600); // Adjusted for more space
        frame.setVisible(true);
    }

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

