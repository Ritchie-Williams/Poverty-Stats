import org.apache.commons.csv.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of the CSVProcessor interface.
 * This class represents the core component in the Decorator Pattern.
 * It is responsible for reading the CSV file and returning the data as a list of maps.
 */
public class BasicCSVReader implements CSVProcessor {
    private final String filePath;

    public BasicCSVReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Map<String, String>> process() {
        List<Map<String, String>> data = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
            for (CSVRecord record : records) {
                data.add(record.toMap());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
