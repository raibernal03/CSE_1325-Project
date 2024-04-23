package logic;


import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    // Constants for the game
    private static final int BOARD_SIZE = 3;
    private static final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static final char EMPTY = ' ';
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    // Static variable to store the total score
    private static int totalScore = 0;

    // Main method to start the game
    public static void main(String[] args) {
        int currentLevel = 1;
        int consecutiveWins = 0;

        // Game loop for different levels
        while (currentLevel <= 3) {
            System.out.println("Current Level: " + currentLevel);
            initializeBoard();
            while (true) {
                printBoard();
                playerTurn();
                printBoard();
                if (isGameFinished(PLAYER)) {
                    if (++consecutiveWins == 5) {
                        currentLevel++;
                        consecutiveWins = 0;
                        System.out.println("Advancing to next level!");
                        break;
                    }
                    setTotalScore(getTotalScore() + getPointsForLevel(currentLevel));
                    System.out.println("Current Score: " + getTotalScore());
                    System.out.println("Consecutive Wins: " + consecutiveWins);
                    break;
                }
                computerTurn(currentLevel);
                printBoard();
                if (isGameFinished(COMPUTER)) {
                    consecutiveWins = 0;
                    break;
                }
            }
        }

        System.out.println("All levels completed! Total Score: " + getTotalScore());
        
        scanner.close();
    }

    // Getter for total score
    public static int getTotalScore() {
        return totalScore;
    }

    // Setter for total score
    public static void setTotalScore(int score) {
        totalScore = score;
    }

    // Method to get points based on difficulty level
    private static int getPointsForLevel(int level) {
        switch (level) {
            case 1: return 1; // Easy
            case 2: return 3; // Medium
            case 3: return 5; // Hard
            default: return 0;
        }
    }

    // Initializes the game board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Handles player's turn to play
    private static void playerTurn() {
        int row, col;
        do {
            System.out.print("Enter row and column numbers (1-3): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || board[row][col] != EMPTY);
        board[row][col] = PLAYER;
    }

    // Handles computer's turn to play based on difficulty level
    private static void computerTurn(int difficulty) {
        int row, col;
        boolean moveMade = false;
        while (!moveMade) {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
            if (board[row][col] == EMPTY) {
                board[row][col] = COMPUTER;
                moveMade = true;
            }
        }
        System.out.println("Computer made a move:");
    }

    // Checks if the game is finished by either win or draw
    private static boolean isGameFinished(char player) {
        if (checkWin(player)) {
            printBoard();
            System.out.println(player + " wins!");
            return true;
        } else if (isBoardFull()) {
            printBoard();
            System.out.println("It's a draw!");
            return false;
        }
        return false;
    }

    // Checks for a win condition for the specified player
    private static boolean checkWin(char player) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2], player) ||
                    checkRowCol(board[0][i], board[1][i], board[2][i], player)) {
                return true;
            }
        }
        return checkRowCol(board[0][0], board[1][1], board[2][2], player) ||
                checkRowCol(board[0][2], board[1][1], board[2][0], player);
    }

    // Helper method for checkWin to compare characters in rows and columns
    private static boolean checkRowCol(char c1, char c2, char c3, char player) {
        return ((c1 == player) && (c1 == c2) && (c2 == c3));
    }

    // Checks if the board is full without any winners
    private static boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Displays the game board in the console
    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < BOARD_SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < BOARD_SIZE - 1) {
                System.out.println("-+-+-");
            }
        }
        System.out.println();  // Print an extra blank line for better separation
    }
}
