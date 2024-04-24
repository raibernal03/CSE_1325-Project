package logic;

import java.util.Scanner;
import java.lang.*;

public class Animal {
    static Scanner sc = new Scanner(System.in);

    public String name;

    public int level;

    public int points;

    public Animal() {
    }

    /*

    public Animal(String name, int level, int points) {
        this.name = name;
        this.level = level;
        this.points = points;
    }
    */

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

    //*Level Up Pet
    public int levelUpAnimal(int newPoints) {

        //Level 0: points < 25
        if (newPoints < 25) {
            setLevel(0);
        }
        //Level 1: points < 50 && points > 25
        else if (newPoints < 50 && newPoints < 75) {
            setLevel(1);
        }
        //Level 2: points < 75 && points > 50
        else if (newPoints < 75 && newPoints < 100) {
            setLevel(2);
        }
        //Level 3: points < 100 && points > 75
        else if (newPoints < 100 && newPoints < 150) {
            setLevel(3);
        }
        return getLevel();
    }

    //*Updating the points
    public int updatePoints(int points, int newPoints) {
        int finalPoints = points + newPoints;
        setPoints(finalPoints); //sets final points to the sum
        return getPoints();
    }

    //*Print Pet
    public void printAnimal() {
        System.out.println("\n\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E");
        System.out.println("\t\t"+getName() + " current stats: "); //prints pet name
        System.out.println("\t\tPet Level = " + getLevel()); //prints pet level
        System.out.println("\t\tPoints = " + getPoints()); // prints points
        System.out.println("\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\uD83E\uDD8E\n");
    }

    //*PLAYING METHOD
    public static void mainMenu(Animal pet) {
        System.out.println("***********************************************************************");
        System.out.println();
        System.out.println("\t\t\tMAIN MENU");
        System.out.println("Choose a game to level up " + pet.getName() + ": \n \t\tTicTacToe [1] \n \t\tHangman [2]\n \t\tRock Paper Scissors[3]\n \t\tQuit[4] ");

        System.out.print("\tInput: ");
        int choice = sc.nextInt();


        switch (choice) {
            case 1:
                ticTacToe(pet); //Calls Tic Tac Toe method
                mainMenu(pet); //Loops back to main menu
                break;
            case 2:
                hangMan(pet); //Calls Hangman method
                mainMenu(pet); //Loops back to main menu
                break;
            case 3:
                rockPaperScissors(pet); //Calls RPS method
                mainMenu(pet); //Loops back to the main menu
                break;
            default:
                break; // ends loops if user wants to exit
        }

    }

    //**PLAYING TTT
    public static void ticTacToe(Animal pet) {
        TicTacToe ttt = new TicTacToe(); //Creates new TTT class
        TicTacToe.main(null); //Calls TTT main method to start TT game
        int p1 = pet.updatePoints(pet.getPoints(), ttt.getTotalScore()); //Updating Pet points
        int l1 = pet.levelUpAnimal(pet.getPoints()); // Updating level based of on the new total of points
        pet.printAnimal(); // Prints new animal stats
//        mainMenu(pet);
    }

    //**PLAYING HANGMAN
    public static void hangMan(Animal pet) {
        Hangman hang = new Hangman(); // Creates new Hangman game Object
        hang.main(null); // Calls main function to start game
        int p2 = pet.updatePoints(pet.getPoints(), hang.getTotalPoints()); // updates points based of finale score
        System.out.println("Hangman Points: " + hang.getTotalPoints());
        System.out.println("Animal Points: " + pet.getPoints());
        pet.printAnimal(); // Prints new pet stats

//        mainMenu(pet);
    }

    //**PLAYING RPS
    public static void rockPaperScissors(Animal pet) {
        RPS rps = new RPS();// Creates new RPS game object
        rps.main(null); // Calls main function of RPS game
        //going back to game until it scores higher than 0
        if (pet.getPoints() < 0) {
            rps.main(null);
        }
        int p3 = pet.updatePoints(pet.getPoints(), rps.getfinalScore()); //Updates points base of final score
        int l3 = pet.levelUpAnimal(pet.getPoints());// Updates level based of points gained
        pet.printAnimal(); //Prints new Pet statistics
//        mainMenu(pet);
    }

    public static void main(String[] args) {

        System.out.println("Welcome to Reptile Arcade");
        System.out.println("Well start by creating your pet ");
        Animal animal = new Animal();
        //Initiazion of Pet
        System.out.print("\tPet Name: ");
        animal.setName(sc.next());
        System.out.println("\tPet Level: " + animal.getLevel());
        System.out.println("\tPoints: " + animal.getPoints());
        System.out.print("READY TO START [y/n]: ");
        char start = sc.next().charAt(0);
        if (start == 'y') {
            mainMenu(animal);
        }
//        mainMenu(animal);

        System.out.println("🦎GOOD BYE🦎");
        animal.printAnimal();
        sc.close();


    }
}