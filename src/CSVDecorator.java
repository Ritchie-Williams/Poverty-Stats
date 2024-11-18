import java.util.List;
import java.util.Map;

/**
 * Abstract base class representing the decorator in the Decorator Pattern.
 * Extends the CSVProcessor interface and adds functionality to the component
 * by delegating calls to the wrapped component.
 */
public abstract class CSVDecorator implements CSVProcessor {
    protected CSVProcessor csvProcessor;

    public CSVDecorator(CSVProcessor csvProcessor) {
        this.csvProcessor = csvProcessor;
    }

    @Override
    public List<Map<String, String>> process() {
        // Delegate the process call to the wrapped component
        return csvProcessor.process();
    }
}
