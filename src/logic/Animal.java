package logic;

import java.util.Scanner;

public class Animal {
    //static Scanner sc = new Scanner(System.in);

    public String name;
    public int level;
    public int points;

    public Animal() {
    }

    //getters and setters
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

    //levels up the pet based of the points the pet/user currently has
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
    //sums the old points and the new points adn assigns it to the overall points

    public int updatePoints(int points, int newPoints) {
        int finalPoints = points + newPoints;
        setPoints(finalPoints);
        return getPoints();
    }

    //prints animal stats(points and level)
    public static void printAnimal(Animal pet) {
        System.out.println("\n\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E");
        System.out.println("\t\t" + pet.getName() + " current stats: ");
        System.out.println("\t\tPet Level = " + pet.getLevel());
        System.out.println("\t\tPoints = " + pet.getPoints());
        System.out.println("\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\n");
    }

    public static void ticTacToe(Animal pet) {
        TicTacToe ttt = new TicTacToe();
        ttt.main(null);
        int p1 = pet.updatePoints(pet.getPoints(), ttt.getTotalScore()); //gets score/points
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

    public static void clearScanner(Scanner scanner) {
        while (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\t🦎🦎Welcome to Reptile Arcade🦎🦎");
        System.out.println("Let's start by creating your pet ");
        Animal animal = new Animal();

        System.out.print("\tPet Name: ");
        if (in.hasNextLine()) {
            animal.setName(in.nextLine());
        } else {
            System.out.println("No input provided. Please enter a pet name.");
            System.exit(1); // Terminate the program if no input is provided
        }
        //animal.setName(sc.nextLine());
        System.out.println("\tPet Level: " + animal.getLevel());
        animal.setLevel(0); // You may set the initial level here.
        System.out.println("\tPoints: " + animal.getPoints());
        animal.setPoints(0); // You may set the initial points here.
        boolean ready = true;
        while (ready) {
            System.out.println("\n🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎🦎");
            System.out.println("MAIN MENU");
            System.out.println("Choose a game to level up " + animal.getName() + ":");
            System.out.println("1. Tic Tac Toe");
            System.out.println("2. Hangman");
            System.out.println("3. Rock Paper Scissors");
            System.out.println("4. Print " + animal.getName() + " stats");
            System.out.println("5. Quit");
            int choice;
            if(in.hasNextLine()) {
                choice = Integer.parseInt(in.nextLine());
            }
            else{
                choice = 4;
            }
            // Check if there's an integer available to read
            if (choice == 1) {
                ticTacToe(animal);
            } else if (choice == 2) {
                hangMan(animal);
            } else if (choice == 3) {
                rockPaperScissors(animal);
            } else if (choice == 4) {
                printAnimal(animal);
            } else if (choice == 5) {
                ready = false;
                //in.close();
                printAnimal(animal);
                System.out.println("🦎GOOD BYE🦎");
                System.exit(0);
            }
            //clearScanner(in);
        }
    }

}