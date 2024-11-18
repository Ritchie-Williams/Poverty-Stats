public class PovertyStatisticsApp {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\ritch\\IdeaProjects\\Poverty-Stats-Updated\\src\\poverty_data_2021.csv";

        // Create adapters
        CSVProcessor reader = new BasicCSVReader(filePath);
        DataAdapter dataAdapter = new DataProcessorAdapter(reader); // Adapts DataProcessor
        ChartAdapter chartAdapter = new ChartProcessorAdapter();   // Adapts ChartProcessor

        // Launch the GUI
        new PovertyStatisticsGUI(dataAdapter, chartAdapter);
    }
}
