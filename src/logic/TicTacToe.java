package logic;


import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    // Constants for the game
    private static final int BOARD_SIZE = 3;  // Size of the game board
    private static final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];  // Game board
    private static final char EMPTY = ' ';  // Character for empty spaces on the board
    private static final char PLAYER = 'X';  // Player's marker
    private static final char COMPUTER = 'O';  // Computer's marker
    private static final Scanner scanner = new Scanner(System.in);  // Scanner for reading user input
    private static final Random random = new Random();  // Random for computer moves

    // Static variable to store the total score
    private static int totalScore = 0;

    // Main method to start the game
    public static void main(String args[]) {
        int currentLevel = 1;  // Current level starting from 1
        int consecutiveWins = 0;  // Count of consecutive wins by the player

        // Game loop for different levels
        while (currentLevel <= 3) {
            System.out.println("Current Level: " + currentLevel);
            initializeBoard();  // Initialize the game board for new game

            while (true) {  // Inner loop for playing the game repeatedly until moving to next level
                printBoard();
                playerTurn();  // Handle player's turn
                printBoard();
                if (isGameFinished(PLAYER)) {  // Check if game finished after player's turn
                    consecutiveWins++;  // Increase consecutive win count
                    if (consecutiveWins == 5) {  // Check if player has won 5 times consecutively
                        currentLevel++;  // Advance to next level
                        consecutiveWins = 0;  // Reset consecutive win count
                        if (currentLevel > 3) {
                            System.out.println("All levels completed! Total Score: " + getTotalScore());
                            //scanner.close();
                            setTotalScore(totalScore);
                            return;  // End the game if all levels are completed
                        }
                        System.out.println("Advancing to next level!");
                    }
                    setTotalScore(getTotalScore() + getPointsForLevel(currentLevel));  // Update total score
                    System.out.println("Current Score: " + getTotalScore());
                    System.out.println("Consecutive Wins: " + consecutiveWins);
                    initializeBoard();  // Reset board to start new game or level
                    continue;  // Continue with a new game at current or next level
                }
                computerTurn(currentLevel);  // Handle computer's turn
                printBoard();
                if (isGameFinished(COMPUTER)) {  // Check if game finished after computer's turn
                    consecutiveWins = 0;  // Reset consecutive wins as computer might have won or draw
                    initializeBoard();  // Reset board to start new game
                    continue;  // Continue with a new game at the current level
                }
            }
        }
    }

    // Method to initialize the game board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;  // Set each spot to empty
            }
        }
    }

    // Handles player's turn to play
    private static void playerTurn() {
        int row, col;
        do {
            System.out.print("Enter row and column numbers (1-3): ");
            row = scanner.nextInt() - 1;  // Read row, adjust for zero-index
            col = scanner.nextInt() - 1;  // Read column, adjust for zero-index
        } while (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || board[row][col] != EMPTY);
        board[row][col] = PLAYER;  // Place player's mark
    }

    // Handles computer's turn to play based on difficulty level
    private static void computerTurn(int difficulty) {
        int row, col;
        boolean moveMade = false;
        while (!moveMade) {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
            if (board[row][col] == EMPTY) {  // Check if the chosen spot is empty
                board[row][col] = COMPUTER;  // Place computer's mark
                moveMade = true;
            }
        }
        System.out.println("Computer made a move:");
    }

    // Checks if the game is finished by either win or draw
    private static boolean isGameFinished(char player) {
        if (checkWin(player)) {  // Check for a win
            printBoard();
            System.out.println(player + " wins!");
            return true;  // Game finished, return true
        } else if (isBoardFull()) {  // Check if the board is full
            printBoard();
            System.out.println("It's a draw!");
            initializeBoard();  // Reset the board for a new game
            System.out.println("Starting a new game...");
            printBoard();
            return false;  // Continue game with a new board
        }
        return false;  // Game not finished, continue playing
    }

    // Checks for a win condition for the specified player
    private static boolean checkWin(char player) {
        // Check rows and columns for a win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player ||
                board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals for a win
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player ||
            board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Checks if the board is full
    private static boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;  // Found an empty spot, board is not full
                }
            }
        }
        return true;  // No empty spots, board is full
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
            case 1: return 1;  // Easy level points
            case 2: return 3;  // Medium level points
            case 3: return 5;  // Hard level points
            default: return 0;
        }
    }
}
