import java.util.*;

public class Game2048 {
    private static final int SIZE = 4;
    private static final int TARGET = 2048;
    private int[][] board;
    private int score;
    private Random rand = new Random();

    public Game2048() {
        board = new int[SIZE][SIZE];
        score = 0;
        addNewTile();
        addNewTile();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game2048 game = new Game2048();

        while (true) {
            game.printBoard();
            if (game.isGameOver()) {
                System.out.println("Game Over!!! Final Score: " + game.score);
                break;
            }
            System.out.print("Move (WASD, Q=Quit): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                System.out.println("Thanks for playing! Final Score: " + game.score);
                break;
            }

            boolean moved = false;
            switch (input) {
                case "W": moved = game.moveUp(); break;
                case "A": moved = game.moveLeft(); break;
                case "S": moved = game.moveDown(); break;
                case "D": moved = game.moveRight(); break;
                default: System.out.println("Invalid input! Use W/A/S/D."); continue;
            }

            if (moved) {
                game.addNewTile();
            } else {
                System.out.println("No tiles moved. Try a different direction.");
            }
        }
        scanner.close();
    }

    
    private void printBoard() {
        System.out.println("\nScore: " + score);
        System.out.println("-------------------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.printf("%4s |", ".");
                } else {
                    System.out.printf("%4d |", board[i][j]);
                }
            }
            System.out.println();
            System.out.println("-------------------------");
        }
    }

    private void addNewTile() {
        List<int[]> empty = new ArrayList<>();
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == 0) empty.add(new int[]{i, j});

        if (!empty.isEmpty()) {
            int[] pos = empty.get(rand.nextInt(empty.size()));
            board[pos[0]][pos[1]] = rand.nextDouble() < 0.9 ? 2 : 4;
        }
    }

    private boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
            int[] row = board[i];
            int[] newRow = new int[SIZE];
            int pos = 0;

            for (int j = 0; j < SIZE; j++) {
                if (row[j] != 0) {
                    if (newRow[pos] == 0) {
                        newRow[pos] = row[j];
                    } 
                    else if (newRow[pos] == row[j]) {
                        newRow[pos] *= 2;
                        score += newRow[pos];
                        pos++;
                    } 
                    else {
                        pos++;
                        newRow[pos] = row[j];
                    }
                }
            }

            if (!Arrays.equals(row, newRow)) {
                board[i] = newRow;
                moved = true;
            }
        }
        return moved;
    }

    private boolean moveRight() {
        rotateBoard(180);
        boolean moved = moveLeft();
        rotateBoard(180);
        return moved;
    }

    private boolean moveUp() {
        rotateBoard(270);
        boolean moved = moveLeft();
        rotateBoard(90);
        return moved;
    }

    private boolean moveDown() {
        rotateBoard(90);
        boolean moved = moveLeft();
        rotateBoard(270);
        return moved;
    }

    private void rotateBoard(int angle) {
        int times = angle / 90;
        for (int t = 0; t < times; t++) {
            int[][] newBoard = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++)
                for (int j = 0; j < SIZE; j++)
                    newBoard[j][SIZE - 1 - i] = board[i][j];
            board = newBoard;
        }
    }

    private boolean isGameOver() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0 || board[i][j] == TARGET) return false;
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) return false;
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) return false;
            }
        return true;
    }
}
