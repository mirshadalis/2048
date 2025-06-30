import java.util.*;

public class Game2048 {
    // Constants for board size (4x4 grid) and target tile value (2048)
    private static final int SIZE = 4;
    private static final int TARGET = 2048;
    // 2D array representing the game board
    private int[][] board;
    // Tracks player's score based on merged tiles
    private int score;
    // Random number generator for tile placement and values
    private Random rand = new Random();

    // Constructor: Initializes an empty board and adds two starting tiles
    public Game2048() {
        board = new int[SIZE][SIZE];
        score = 0;
        addNewTile();
        addNewTile();
    }

    // Main method: Runs the game loop, handles user input and controls te game flow
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game2048 game = new Game2048();

        // Game loop: Continues until game is over or player quits
        while (true) {
            game.printBoard();
            if (game.isGameOver()) {
                System.out.println("Game Over!!! Final Score: " + game.score);
                break;
            }
            System.out.print("Move (WASD, Q=Quit): ");
            String input = scanner.nextLine().toUpperCase();

            // Allow player to quit the game
            if (input.equals("Q")) {
                System.out.println("Thanks for playing! Final Score: " + game.score);
                break;
            }

            // Process movement input (WASD) and update board with W = UP, A = LEFT, S = RIGHT, D = DOWN
            boolean moved = false;
            switch (input) {
                case "W": moved = game.moveUp(); break;
                case "A": moved = game.moveLeft(); break;
                case "S": moved = game.moveDown(); break;
                case "D": moved = game.moveRight(); break;
                default: System.out.println("Invalid input! Use W/A/S/D."); continue;
            }

            // Add a new tile only if a valid move was made
            if (moved) {
                game.addNewTile();
            } 
            else {
                System.out.println("No tiles moved. Try a different direction.");
            }
        }
        scanner.close();
    }

    // Displays the current game board and score in a formatted grid
    private void printBoard() {
        System.out.println("\nScore: " + score);
        System.out.println("-------------------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.printf("%4s |", ".");
                } 
                else {
                    System.out.printf("%4d |", board[i][j]);
                }
            }
            System.out.println();
            System.out.println("-------------------------");
        }
    }

    // Adds a new tile (2 or 4) to a random empty cell on the board
    private void addNewTile() {
        // Collect all empty cells
        List<int[]> empty = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) 
                    empty.add(new int[]{i, j});
            }
        }
        // Place a new tile if there are empty cells
        if (!empty.isEmpty()) {
            int[] pos = empty.get(rand.nextInt(empty.size()));
            // 90% chance for a 2 and 10% chance for a 4 (can be customized tho)
            board[pos[0]][pos[1]] = rand.nextDouble() < 0.9 ? 2 : 4;
        }
    }

    // Moves tiles left, merging identical tiles and updating score
    private boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            int[] row = board[i];
            int[] newRow = new int[SIZE];
            int pos = 0; // Tracks position in newRow for placing tiles

            // Process each tile in the row
            for (int j = 0; j < SIZE; j++) {
                if (row[j] != 0) {
                    // Place tile in next available position
                    if (newRow[pos] == 0) {
                        newRow[pos] = row[j];
                    } 
                    // Merge identical tiles, double value, and update score
                    else if (newRow[pos] == row[j]) {
                        newRow[pos] *= 2;
                        score += newRow[pos];
                        pos++;
                    } 
                    // Move to next position for non-matching tile
                    else {
                        pos++;
                        newRow[pos] = row[j];
                    }
                }
            }

            // Update board if row changed
            if (!Arrays.equals(row, newRow)) {
                board[i] = newRow;
                moved = true;
            }
        }
        return moved;
    }

    // Moves tiles right by rotating the board 180° then applying moveLeft and rotating back
    private boolean moveRight() {
        rotateBoard(180);
        boolean moved = moveLeft();
        rotateBoard(180);
        return moved;
    }

    // Moves tiles up by rotating the board 270° then applying moveLeft and rotating back
    private boolean moveUp() {
        rotateBoard(270);
        boolean moved = moveLeft();
        rotateBoard(90);
        return moved;
    }

    // Moves tiles down by rotating the board 90° then applying moveLeft and rotating back
    private boolean moveDown() {
        rotateBoard(90);
        boolean moved = moveLeft();
        rotateBoard(270);
        return moved;
    }

    // Rotates the board by the specified angle (in degrees) to simplify movement logic
    private void rotateBoard(int angle) {
        int times = angle / 90;
        for (int t = 0; t < times; t++) {
            // Create a new board for rotation
            int[][] newBoard = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    newBoard[j][SIZE - 1 - i] = board[i][j];
                }
            }
            board = newBoard;
        }
    }

    // Checks if the game is over (no valid moves or target tile reached)
    private boolean isGameOver() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                // Game continues if there are empty cells or target is reached
                if (board[i][j] == 0 || board[i][j] == TARGET) 
                    return false;
                // Game continues if adjacent tiles can merge
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) 
                    return false;
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) 
                    return false;
            }
        return true;
    }
}