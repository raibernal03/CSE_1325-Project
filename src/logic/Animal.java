package logic;

import java.util.Scanner;

public class Animal {
    public String name;
    public int level;
    public int points = 0;

    public Animal(){}

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

    public void setPoints(int newPoints) {
        this.points =  points;
    }

    //*Level Up Pet
    public void levelUpAnimal(int level, int points){
        //Level 0: points < 25
        if(points < 25){
            this.level = 0;
        }
        //Level 1: points < 50 && points > 25
        else if(points < 50 && points < 75){
            this.level = 1;
        }
        //Level 2: points < 75 && points > 50
        else if(points < 75 && points < 100){
            this.level = 2;
        }
        //Level 3: points < 100 && points > 75
        else if(points < 100 && points < 150){
            this.level = 3;
        }
    }

    //*Print Pet
    public void printAnimal(){
        System.out.println("Pet Name: " + name);
        System.out.println("Pet Level: " + level);
        System.out.println("Points: " + points);
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Reptile Arcade");
        System.out.println("Well start creating your pet ");
        Animal pet = new Animal();
        Scanner input = new Scanner(System.in);
        //Initiazion of Pet
        System.out.print("\tPet Name: ");
        pet.setName(input.nextLine());
        System.out.println("\tPet Level: "+ pet.getLevel());
        System.out.println("\tPoints: " + pet.getPoints());
        System.out.println();

        System.out.println("To level up your pet you'll have to gather point from the following games: \n \t\tTicTacToe [1] \n \t\tHangman [3]\n \t\tRock Paper Scissors[3]");

        int choice = input.nextInt();
        switch(choice){
            case 1:
                TicTacToe ttt = new TicTacToe();
                ttt.main(null);
                pet.setPoints(ttt.getTotalScore());
                // loop for neg points

                break;
            case 2:

                //Hangman hangman = new Hangman();
                //hangman.main(null);
                // loop for neg points

                break;
            case 3:
                Test rps = new Test();
                rps.main(null);
                //loops for neg point
                break;
            default:
                break;
        }
    }
}

