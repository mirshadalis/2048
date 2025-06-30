# 2048 Game in Java (Console Based)

## Description
This is a console-based implementation of the popular 2048 puzzle game written in Java. The game is played on a 4x4 grid where players combine numbered tiles by moving them up, down, left or right to achieve a tile with the value of 2048. The game features a simple text-based interface, score tracking and random tile generation.

## Features
- 4x4 game board with sliding tile mechanics
- Merge tiles with the same value to double their value
- Score tracking based on merged tile values
- Random generation of new tiles (2 or 4)
- Game over detection when no valid moves remain
- Input validation and user-friendly console interface
- Use WASD keys to move tiles and Q to quit

## How to Play
1. Run the `Game2048.java` file in a Java environment.
2. The game starts with two random tiles (2 or 4) on the 4x4 grid.
3. Use the following keys to move tiles:
   - **W**: Move tiles up
   - **A**: Move tiles left
   - **S**: Move tiles down
   - **D**: Move tiles right
   - **Q**: Quit the game
4. When two tiles with the same number collide, they merge into a single tile with double the value.
5. After each valid move, a new tile (2 or 4) appears in a random empty spot.
6. The game ends when no moves are possible or a tile reaches 2048.

## Installation
1. Ensure you have Java Development Kit (JDK) installed (version 8 or higher).
2. Clone this repository:
   ```bash
   git clone https://github.com/mirshadalis/2048-game-java.git
   ```
3. Navigate to the project directory:
   ```bash
   cd 2048-game-java
   ```
4. Compile and run the game:
   ```bash
   javac Game2048.java
   java Game2048
   ```

## Requirements
- Java Development Kit (JDK) 8 or higher
- A terminal or IDE to compile and run the Java code

## Code Structure
- **Game2048.java**: The main class containing the game logic, board management, and user interaction.
  - `Game2048()`: Constructor to initialize the game board and add initial tiles.
  - `main()`: Handles user input and game loop.
  - `printBoard()`: Displays the current game board and score.
  - `addNewTile()`: Adds a new tile (2 or 4) to a random empty cell.
  - `moveLeft()`, `moveRight()`, `moveUp()`, `moveDown()`: Handle tile movements and merging.
  - `rotateBoard()`: Rotates the board to simplify movement logic.
  - `isGameOver()`: Checks if the game is over.

## Example Gameplay
```
Score: 8
-------------------------
|   . |   . |   . |   . |
-------------------------
|   . |   . |   . |   . |
-------------------------
|   . |   2 |   2 |   . |
-------------------------
|   . |   . |   . |   . |
-------------------------
Move (WASD, Q=Quit): d
```
