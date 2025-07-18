# Selenium Test Suite - Project Falcon

This repository contains a comprehensive Selenium WebDriver test automation suite implemented in Java using Maven. The project follows best practices for maintainability, scalability, and readability, aligned with industry standards.

## Project Structure

- `src/main/java/com/projectfalcon/pages/`  
  Contains Page Object Model (POM) classes representing web pages and UI components. These encapsulate page interactions and elements for clean and reusable test code.

- `src/test/java/com/projectfalcon/tests/`  
  Contains TestNG/JUnit test classes covering functional test scenarios such as login, navigation, cart operations, and inventory management. Tests are designed with robustness and parallel execution in mind.

- `pom.xml`  
  Maven build file managing dependencies, plugins, and project lifecycle.

## Key Features

- **Page Object Model (POM):**  
  Modular, reusable page classes improve test readability and maintainability.

- **Robust Test Cases:**  
  Covers core user workflows and edge cases, including multi-threaded scenarios.

- **Cross-Browser Ready:**  
  Easily extendable to support multiple browsers via WebDriver configuration.

- **Maven Integration:**  
  Simplifies dependency management and build automation.

## Getting Started

### Prerequisites

- Java JDK 8 or higher  
- Maven 3.x  
- A modern browser (Chrome, Firefox, etc.)  
- IDE such as IntelliJ IDEA or Eclipse (optional but recommended)

### Running Tests

1. Clone the repository:  
   `git clone https://github.com/RVAcoder123/selenium-test-suite.git`

2. Navigate into the project directory:  
   `cd selenium-test-suite`

3. Run tests using Maven:  
   `mvn test`

Test reports and logs will be generated under the `target` directory.

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests for review.

## License

This project is licensed under the MIT License.

---

*Maintained by Gregg — QA Engineer & Automation Specialist*

