import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Adapter for the DataProcessor class.
 * Implements the DataAdapter interface to make DataProcessor compatible with the GUI.
 */
public class DataProcessorAdapter implements DataAdapter {
    private final DataProcessor dataProcessor;

    public DataProcessorAdapter(CSVProcessor csvProcessor) {
        this.dataProcessor = new DataProcessor(csvProcessor);
    }

    @Override
    public List<Map<String, String>> getData() {
        return dataProcessor.process();
    }

    @Override
    public void filter(String key, String value) {
        dataProcessor.setFilter(key, value);
    }

    @Override
    public void sort(Comparator<Map<String, String>> comparator) {
        dataProcessor.setComparator(comparator);
    }
}

