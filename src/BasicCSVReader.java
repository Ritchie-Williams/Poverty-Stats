import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for reading CSV files.
 * Implements the CSVProcessor interface to retrieve data as a list of maps.
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
