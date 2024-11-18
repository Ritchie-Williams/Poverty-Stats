public class PovertyDataApp {
    public static void main(String[] args) {
        // Path to the CSV file
        String filePath = "C:\\Users\\ritch\\IdeaProjects\\Poverty-Stats-Updated\\src\\poverty_data_2021.csv"; // Update this to the correct path

        // Initialize the processors
        CSVProcessor reader = new BasicCSVReader(filePath);
        DataProcessor dataProcessor = new DataProcessor(reader);
        ChartProcessor chartProcessor = new ChartProcessor(dataProcessor);

        // Display the GUI
        new PovertyStatisticsGUI(dataProcessor, chartProcessor);
    }
}


