This update proivdes better better functionality to my PovertyStats Appplication through the Decorator Pattern. 

The program uses the Decorator Design Pattern to dynamically extend the functionality of a base data processor. Each decorator adds a specific capability, such as filtering, sorting, calculating statistics, or generating charts.

Classes Implementing the Decorator Pattern:

Base Component:
CSVProcessor: Defines the core functionality for processing CSV data.

Concrete Component:
BasicCSVReader: Reads CSV data into a list of maps.

Decorator:
CSVDecorator: Abstract class that wraps a CSVProcessor and allows adding additional behaviors.

Concrete Decorators:
DataProcessor: Adds filtering and sorting capabilities.
StatisticsProcessor: Adds functionality to calculate and display aggregated statistics.
ChartProcessor: Adds functionality to generate and display dynamic bar charts.

Decorator Advantages:
Modularity: Each feature (filtering, sorting, statistics, charting) is implemented independently.
Reusability: New decorators can be added without altering existing code.
Scalability: The program can easily accommodate additional processing steps.
