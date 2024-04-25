package logic;

import java.util.Random;
import java.util.Scanner;
public class RPS {
    public static int totalScore;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int finalScore = 0;

        while (true)
        {
            // uses the length of the string array to randomly pick a play
            String[] rps = {"Rock", "Paper", "Scissors"};
            String computersMove = rps[new Random().nextInt(rps.length)];

            String playerMove = getPlayerMove(scanner);

            System.out.println("Computer played: " + computersMove);
            // a tie
            if (playerMove.equals(computersMove))
            {
                System.out.println("The game was a tie!\nNo points given.");
            }
            // winning possiblities
            else if ((playerMove.equals("Rock") && computersMove.equals("Scissors")) ||(playerMove.equals("Paper") && computersMove.equals("Rock")) ||(playerMove.equals("Scissors") && computersMove.equals("Paper")))
            {
                finalScore++;
                System.out.println("You won!\n+1 point");
            } else
            {
                finalScore--;
                System.out.println("You lose.\n-1 point");
            }
            // prints out score
            System.out.println("Current Score: " + finalScore);
            if (!playAgain(scanner))
            {
                break;
            }
        }

        System.out.println("Total Score: " + finalScore);
        setfinalScore(finalScore);
        scanner.close();
    }

    // set final score
    public static void setfinalScore(int finalScore)
    {
        totalScore = finalScore;
    }

    // get final score
    public static int getfinalScore()
    {
        return totalScore;
    }

    // checks players move is valid
    public static String getPlayerMove(Scanner scanner) {
        String playerMove;
        while (true)
        {
            System.out.println("Please enter your move (Rock, Paper, or Scissors): ");
            playerMove = scanner.nextLine();
            if (playerMove.equals("Rock") || playerMove.equals("Paper") || playerMove.equals("Scissors"))
            {
                break;
            }
            System.out.println(playerMove + " is not a valid move, please try again.");
        }
        return playerMove;
    }

    // play again?
    public static boolean playAgain(Scanner scanner) {
        System.out.println("Play again? (Yes or No)");
        String playAgain = scanner.nextLine();
        return playAgain.equalsIgnoreCase("Yes") || playAgain.equalsIgnoreCase("Y");

    }
}

