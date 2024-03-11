# Parking Lot Management System Documentation

## Introduction
The Parking Lot Management System is a Java-based application designed to facilitate the management of a parking lot. It allows users to initialize the parking lot with multiple floors and spaces, add vehicles to the parking lot, park and remove vehicles, and calculate parking fees based on various parameters.

## Implementation Overview
The implementation of the Parking Lot Management System follows object-oriented principles and design patterns to ensure modularity, scalability, and maintainability. The system consists of several key components:

1. **ParkingLot**: Represents the parking lot and manages its operations.
2. **Floor**: Represents a floor in the parking lot and manages parking spaces.
3. **Vehicle**: Represents a vehicle with attributes such as vehicle type, registration number, and entry timestamp.
4. **ParkingSpace**: Represents a space for parking a vehicle on a floor.
5. **ParkingFeeCalculator**: An interface for calculating parking fees, with concrete implementations for different fee calculation strategies.
6. **HourlyParkingFeeCalculator**: Concrete implementation of `ParkingFeeCalculator` for calculating fees based on hourly rates.
7. **DailyParkingFeeCalculator**: Concrete implementation of `ParkingFeeCalculator` for calculating fees based on daily rates.

## Steps to Implement the System

### 1. Problem Understanding and Design
- Initially, I carefully analyzed the problem statement provided by the interviewer to understand the requirements and constraints of the Parking Lot Management System.
- I designed a class diagram outlining the main classes and their relationships to visualize the system's structure.

### 2. Implementation with Assistance
- With the problem statement in mind, I started implementing the core functionalities of the system, such as initializing the parking lot, adding floors, parking vehicles, and calculating parking fees.
- Throughout the implementation process, I sought assistance from an AI assistant to clarify doubts, discuss design decisions, and receive guidance on best practices.
- The AI assistant provided step-by-step guidance, helped identify potential issues, and suggested improvements to the codebase.

### 3. Testing and Validation
- After completing the implementation, I conducted thorough testing to validate the correctness and functionality of the system.

## Conclusion
The Parking Lot Management System is a robust and scalable solution for efficiently managing parking lots. By following object-oriented principles and leveraging assistance from an AI assistant, I successfully implemented the system while ensuring modularity, extensibility, and maintainability.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/kumarprem66/ParkingLot.git
```

2.Navigate to the project directory:
```bash
   cd car-parking-system
```

3. Build the project using Maven:
```bash
   mvn clean install
```
## Building and Running the Project

To build the project from the JAR file, follow these steps:

1. Download the latest release JAR file from the [Releases](https://github.com/kumarprem66/ParkingLot/releases/tag/v1.0.0) page.
2. Open a terminal or command prompt and navigate to the directory containing the JAR file.
3. Run the following command to execute the JAR file:

   ```sh
   java -jar Parking-1.0-SNAPSHOT.jar


## Dependencies
- Maven
- Lombok

## Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request for any improvements or additional features.
