package logic;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.*;


public class Animal {
    static Scanner sc = new Scanner(System.in);
    public String name;
    public int level;
    public int points;

    public Animal() {
    }

    public Animal(String name, int level, int points) {
        this.name = name;
        this.level = level;
        this.points = points;
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

    public int updatePoints(int points, int newPoints) {
        int finalPoints = points + newPoints;
        setPoints(finalPoints);
        return getPoints();
    }

    //*Print Pet
    public void printAnimal() {

        System.out.println(getName() + " current stats: ");
        System.out.println("Pet Level: " + getLevel());
        System.out.println("Points: " + getPoints());
    }
    //*PLAYING METHOD
    public static void mainMenu(Animal pet){
        System.out.println("***********************************************************************");
        System.out.println();
        System.out.println("\t\t\tMAIN MENU");
        System.out.println("Choose a game to level up " + pet.getName() + ": \n \t\tTicTacToe [1] \n \t\tHangman [2]\n \t\tRock Paper Scissors[3]\n \t\tQuit[4] ");
        System.out.print("\tInput: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                ticTacToe(pet);
                mainMenu(pet);
                break;
            case 2:
                hangMan(pet);
                mainMenu(pet);
                break;
            case 3:
                rockPaperScissors(pet);
                mainMenu(pet);
                break;
            default:
                break;
        }

    }
    //**PLAYING TTT
    public static void ticTacToe(Animal pet) {
        TicTacToe ttt = new TicTacToe();
        TicTacToe.main(null);
        int p1 = pet.updatePoints(pet.getPoints(), ttt.getTotalScore()); //adding previous points with new points
        int l1 = pet.levelUpAnimal(pet.getPoints());
        pet.printAnimal();
        mainMenu(pet);
    }
    //**PLAYING HANGMAN
    public static void hangMan(Animal pet) {
        newHangman hang = new newHangman();
        hang.main(null);
        int p2 = pet.updatePoints(pet.getPoints(), hang.getTotalPoints());
        System.out.println("Hangman Points: " + hang.getTotalPoints());
        System.out.println("Animal Points: " + pet.getPoints());
        pet.printAnimal();
        mainMenu(pet);
    }
    //**PLAYING RPS
    public static void rockPaperScissors(Animal pet) {
        RPS rps = new RPS();
        rps.main(null);
        int p3 = pet.updatePoints(pet.getPoints(), rps.getfinalScore()); //adding previous points with new points
        int l3 = pet.levelUpAnimal(pet.getPoints());
        //going back to game until it scores higher than 0
        if (pet.getPoints() < 0){
            rps.main(null);
        }
        pet.printAnimal();
        mainMenu(pet);
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
        System.out.println("READY TO START [y/n]: ");
        char start = sc.next().charAt(0);
        if (start == 'y'){
            mainMenu(animal);
        }else {}

        sc.close();

        System.out.println("🦎GOOD BYE🦎");
        animal.printAnimal();

    }
}