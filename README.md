
# AStarAlgorithm - README

## Project Title

AStarAlgorithm - Java Implementation of the A* Algorithm for Puzzle Solving

## Description

The **AStarAlgorithm** project implements the A* search algorithm in Java to solve sliding puzzles. The algorithm finds the optimal path to the goal state by exploring possible moves and choosing the best one based on the Manhattan distance heuristic. This project demonstrates efficient pathfinding for puzzles, providing insight into search algorithms and heuristic-based problem solving.

## Getting Started

### Prerequisites

To run this project, ensure you have the following installed:
- **Java Development Kit (JDK)** (Version 8 or higher)
- **Java IDE or Terminal** (Eclipse, IntelliJ, or any terminal for compiling and running Java code)

### Installing

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd AStarAlgorithm
   ```

3. Compile the Java files:
   ```bash
   javac AStarAlgorithm.java
   ```

### Executing the Program

To run the program, use the following command in your terminal or IDE:
```bash
java AStarAlgorithm
```

The program will:
- Initialize a sliding puzzle.
- Apply the A* algorithm to find the optimal solution.
- Output the sequence of moves needed to reach the goal state.

## Features

- **A* Search Algorithm**: Uses the Manhattan distance heuristic to find the shortest path in sliding puzzles.
- **Puzzle Solver**: Solves 3x3 and 4x4 sliding puzzles by exploring possible moves and backtracking if necessary.
- **Move Tracking**: Displays the series of moves required to reach the goal state from the initial configuration.

## Built With

- **Java** - The programming language used for this project.

## Contributing

To contribute to this project:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Open a Pull Request.

## Authors

- **Parth Gupta** - *Initial work* - [GitHub Profile](https://github.com/parthgupta)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- The project is inspired by exploring efficient search algorithms, such as A*, in solving complex puzzles.
