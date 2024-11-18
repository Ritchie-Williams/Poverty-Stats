This updated version of the Poverty Statistics Viewer application implements the Adapter Design Pattern to streamline interaction between the graphical user interface (GUI), data processing, and charting functionalities. By using the Adapter Pattern, the program achieves seamless compatibility between components with differing interfaces, ensuring flexibility and scalability.

Classes Implementing the Adapter Pattern:

Adapter Interfaces:
  DataAdapter
    Standardizes the interface for data handling operations such as filtering, sorting, and retrieving data.
    Abstracts the complexities of data processing from the GUI.


ChartAdapter
  Standardizes the interface for charting operations.
  Allows the GUI to interact with the charting library without being tied to its implementation.
  
  Adapter Implementations:
    DataProcessorAdapter
Adapts the DataProcessor class to the DataAdapter interface.
Provides a unified interface for filtering, sorting, and retrieving data.
Ensures that the GUI interacts with the dataset in a consistent way.


ChartProcessorAdapter
Adapts the ChartProcessor class to the ChartAdapter interface.
Provides a unified interface for generating and displaying charts.
Decouples the charting logic from the GUI, making it easier to switch charting libraries if needed.

