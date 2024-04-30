package logic;

import java.util.Random;
import java.util.Scanner;

public class RPS
{
    private static int totalScore = 0;

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int finalScore = 0;

        int roundsToWin = 3;

        // game description/introduction
        System.out.println("Welcome to Rock, Paper, Scissors! (I'm sure you already know the rules).\nIt is best out of three, good luck!");

        // makes multiple rounds in the game
        while (true)
        {
            int playerWins = 0;
            int computerWins = 0;

            for (int round = 1; round <= roundsToWin; round++)
            {
                System.out.println("Round " + round + ":");
                String result = playRound(scanner);

                if (result.equals("win"))
                {
                    playerWins++;
                }

                else if (result.equals("lose"))
                {
                    computerWins++;
                }

                if (playerWins >= roundsToWin || computerWins >= roundsToWin)
                {
                    break;
                }
            }

            if (playerWins > computerWins)
            {
                System.out.println("Congratulations! You won the game!");
                finalScore++;
            }
            else if (computerWins > playerWins)
            {
                System.out.println("You lost the game.");
                finalScore--;
            }
            else
            {
                System.out.println("The game was a tie.");
            }

            // prints out score as the game is being played
            System.out.println("Current Score: " + finalScore);
            if (!playAgain(scanner))
            {
                break;
            }
        }
        // prints out final score
        System.out.println("Total Score: " + finalScore);
        //scanner.close();
    }
    public static String playRound(Scanner scanner)
    {
        // uses the length of the string array to randomly pick a play
        String[] rps = {"Rock", "Paper", "Scissors"};
        String computersMove = rps[new Random().nextInt(rps.length)];

        String playerMove = getPlayerMove(scanner);

        System.out.println("Computer played: " + computersMove);

        // if a tie
        if (playerMove.equals(computersMove))
        {
            System.out.println("The round was a tie!");
            return "tie";
        }

        // winning possibilities
        else if ((playerMove.equals("Rock") && computersMove.equals("Scissors")) || (playerMove.equals("Paper") && computersMove.equals("Rock")) ||(playerMove.equals("Scissors") && computersMove.equals("Paper")))
        {
            System.out.println("You won the round!");
            return "win";
        }
        else
        {
            System.out.println("You lost the round.");
            return "lose";
        }
    }

    // get final score
    public static int getfinalScore()
    {
        return totalScore;
    }

    // set final score
    public static void setfinalScore(int finalScore)
    {
        totalScore = finalScore;
    }

    // gets player move
    public static String getPlayerMove(Scanner scanner)
    {
        String playerMove;
        while (true) {
            System.out.println("Please enter your move: ");
            System.out.println("                                                             ");
            System.out.println("    Rock                  Paper                Scissors      ");
            System.out.println("    ______                _____                _______       ");
            System.out.println("----(_____)          ---- ____)____        ----  _____)___   ");
            System.out.println("     (______)              ________)            __________)  ");
            System.out.println("     (______)             ________)            __________)   ");
            System.out.println("---  (______)       ---   ________)       ---  (_____)       ");
            System.out.println("   --(____)             --________)          --(_____)       ");
            System.out.println("                                                             ");
            playerMove = scanner.nextLine();

            // checks players move is valid
            if (playerMove.equals("Rock") || playerMove.equals("Paper") || playerMove.equals("Scissors"))
            {
                break;
            }
            System.out.println(playerMove + " is not a valid move, please try again.");
        }
        //scanner.close();
        return playerMove;
    }

    // play again?
    public static boolean playAgain(Scanner scanner)
    {
        System.out.println("Play again? (Yes or No)");
        String playAgain = scanner.nextLine();
        return playAgain.equalsIgnoreCase("Yes") || playAgain.equalsIgnoreCase("Y");
    }
}

