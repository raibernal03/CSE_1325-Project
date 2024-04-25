package logic;

import java.util.Scanner;

public class Animal {
    //static Scanner sc = new Scanner(System.in);

    public String name;
    public int level;
    public int points;

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int levelUpAnimal(int newPoints) {
        if (newPoints < 25) {
            return 0;
        } else if (newPoints < 50) {
            return 1;
        } else if (newPoints < 75) {
            return 2;
        } else if (newPoints < 100) {
            return 3;
        }
        return getLevel();
    }

    public int updatePoints(int points, int newPoints) {
        int finalPoints = points + newPoints;
        setPoints(finalPoints);
        return getPoints();
    }

    public static void printAnimal(Animal pet) {
        System.out.println("\n\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E");
        System.out.println("\t\t" + pet.getName() + " current stats: ");
        System.out.println("\t\tPet Level = " + pet.getLevel());
        System.out.println("\t\tPoints = " + pet.getPoints());
        System.out.println("\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\n");
    }

    public static void mainMenu(Animal pet) {

        System.out.println("\n🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎");
        System.out.println("\n\t\t\tMAIN MENU");
        System.out.println("Choose a game to level up " + pet.getName() + ":");
        System.out.println("\tA. Tic Tac Toe");
        System.out.println("\tB. Hangman");
        System.out.println("\tC. Rock Paper Scissors");
        System.out.println("\tD. Print "+ pet.getName()+ " stats" );
        System.out.println("\tE. Quit");
        Scanner in = new Scanner(System.in);
        //System.out.print("\tInput: ");
        String choice = in.nextLine();
        switch (choice) {
            case "A":
                ticTacToe(pet);
                mainMenu(pet);
                break;
            case "B":
                hangMan(pet);
                mainMenu(pet);
                break;
            case "C":
                rockPaperScissors(pet);
                mainMenu(pet);
                break;
            case "D":
                printAnimal(pet);
                mainMenu(pet);
            case "E":
                System.out.println("Goodbye!");
                in.close();
                break;
            default:
                System.out.println("Invalid choice. Please choose again.\n\n");
                mainMenu(pet);
                break;
        }

    }

    public static void ticTacToe(Animal pet) {
        TicTacToe ttt = new TicTacToe();
        ttt.main(null);
        int p1 = pet.updatePoints(pet.getPoints(), ttt.getTotalScore());
        int l1 = pet.levelUpAnimal(p1);
        //pet.printAnimal();
    }

    public static void hangMan(Animal pet) {
        Hangman hang = new Hangman();
        hang.main(null);
        int p2 = pet.updatePoints(pet.getPoints(), hang.getTotalPoints());
        //pet.printAnimal();
    }

    public static void rockPaperScissors(Animal pet) {
        RPS rps = new RPS();
        rps.main(null);
        int p3 = pet.updatePoints(pet.getPoints(), rps.getfinalScore());
        int l3 = pet.levelUpAnimal(p3);
        //pet.printAnimal();
    }

    /*public static char choice() {
        Scanner in = new Scanner(System.in);
        String input = "";
        while (input.isEmpty()) {
            System.out.print("\tInput: ");
            if (in.hasNextLine()) {
                input = in.nextLine().trim();
            } else {
                // If there is no next line available, wait for user input
                try {
                    Thread.sleep(100); // Sleep for a short period to avoid busy waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid choice.");
            }
        }
        return input.toUpperCase().charAt(0);
    }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\t\t🦎🦎Welcome to Reptile Arcade🦎🦎");
        System.out.println("\t\t\t\tLet's start by creating your pet ");
        Animal animal = new Animal();

        System.out.print("\t\t\t\t\tPet Name: ");
        animal.setName(sc.nextLine());
        System.out.println("\tPet Level: " + animal.getLevel());
        animal.setLevel(0); // You may set the initial level here.
        System.out.println("\tPoints: " + animal.getPoints());
        animal.setPoints(0); // You may set the initial points here.
        mainMenu(animal);

        System.out.println("🦎 GOOD BYE 🦎");
        animal.printAnimal(animal);
        sc.close(); // Closing the Scanner here after it's done being used.
    }
}
