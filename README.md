# Complex Number Stack Calculator

## Overview
A Java-based desktop application implementing a **stack-oriented calculator** for complex numbers (*a + bj*).  
Developed as part of a Software Engineering course, this project focuses on **clean object-oriented design**, **robust error handling** and **test-driven development**.

The calculator follows a **Reverse Polish Notation (RPN)** approach, enabling efficient and flexible mathematical operations.

---

## Core Features

### Complex Number Arithmetic
- Addition, subtraction
- Multiplication, division
- Sign inversion
- Square root computation

---

### Stack-Based Architecture (RPN)
- Custom stack implementation for managing operands
- Advanced stack operations:
  - `CLEAR` – remove all elements
  - `DROP` – remove top element
  - `DUP` – duplicate top element
  - `SWAP` – swap top two elements
  - `OVER` – copy second element to top

---

### Variable Management
- Store and retrieve values using alphanumeric variables
- Backed by a `HashMap<Character, ComplexNumber>`
- Supported operations:
  - Save (`>a`)
  - Load (`<a`)
  - Add (`+a`)
  - Subtract (`-a`)

---

### Graphical User Interface (JavaFX)
- Built with **JavaFX (FXML + SceneBuilder)**
- Real-time stack visualization using `TableView`
- Dynamic UI behavior:
  - Buttons enabled/disabled based on stack state
  - Prevents invalid operations (e.g., division with insufficient operands)

---

## Input Format
The calculator accepts complex numbers in the following formats:

- `a+bJ` → e.g., `3+2J`
- `a-bJ` → e.g., `5-4J`
- `J`, `-J`
- Real-only values → e.g., `7`

---

## Stack Behavior (RPN Example)

Operations follow **Reverse Polish Notation**:

Input:
- 3+2J ⏎
- 1+1J ⏎
- \+

Result:
- 4+3J


---

## Tech Stack & Engineering Practices

- **Language:** Java  
- **UI Framework:** JavaFX (FXML / SceneBuilder)  
- **Testing:** JUnit 5  
- **Architecture:** Object-Oriented Programming (OOP)

### Key Design Principles
- Separation of concerns (Controller / Model / Data structures)
- Immutable-style operations for complex numbers
- Defensive programming with custom exceptions

---

## Code Structure Highlights

- **`ComplexNumber.java`**  
  Encapsulates all mathematical logic for complex numbers, including arithmetic operations and formatting.

- **`StackManagement.java`**  
  Custom abstraction over stack behavior, integrated with JavaFX via `ObservableList`.

- **`CalculatorController.java`**  
  Handles UI events and acts as the bridge between the interface and application logic (MVC Controller).

- **`*Test.java`**  
  Comprehensive unit tests validating:
  - Mathematical accuracy (with floating-point tolerance)
  - Stack consistency and edge cases

---

## Error Handling
The application ensures stability through custom runtime exceptions:

- `EmptyStackException`
- `InvalidOperationException`
- `NotEnoughNumbersException`

Example: Division by zero in complex numbers is safely handled.

---

## How to Run
1. Clone the repository:
`git clone <your-repo-url>`

2. Open the project in an IDE (IntelliJ / Eclipse)

3. Configure JavaFX (if required)

4. Run:
`Calculator.java`