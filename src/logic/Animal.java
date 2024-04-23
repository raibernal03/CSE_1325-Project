package logic;

import java.util.Scanner;

public class Animal {
    public String name;
    public int level;
    public int points;

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

    public void setPoints(int points) {
        this.points = points;
    }

    //*Level Up Pet
    public int levelUpAnimal(int newPoints){

        //Level 0: points < 25
        if(newPoints < 25){
            setLevel(0);
        }
        //Level 1: points < 50 && points > 25
        else if(newPoints < 50 && newPoints < 75){
            setLevel(1);
        }
        //Level 2: points < 75 && points > 50
        else if(newPoints < 75 && newPoints < 100){
            setLevel(2);
        }
        //Level 3: points < 100 && points > 75
        else if(newPoints < 100 && newPoints < 150){
            setLevel(3);
        }
        return getLevel();
    }
    public int updatePoints(int points, int newPoints){
        int finalPoints = points + newPoints;
        setPoints(finalPoints);
        return getPoints();
    }
    //*Print Pet
    public void printAnimal(){

        System.out.println(getName() + " current stats: ");
        System.out.println("Pet Level: " + getLevel());
        System.out.println("Points: " + getPoints());
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Reptile Arcade");
        System.out.println("Well start by creating your pet ");
        Animal pet = new Animal();
        //Initiazion of Pet
        System.out.print("\tPet Name: ");
        pet.setName(input.nextLine());
        System.out.println("\tPet Level: "+ pet.getLevel());
        System.out.println("\tPoints: " + pet.getPoints());
        int playing = 0;
        Scanner input2 = new Scanner(System.in);
        int choice;
        do {
            System.out.println("***********************************************************************");
            System.out.println();
            System.out.println("\t\t\tMAIN MENU");
            System.out.println("Choose a game to level up " + pet.getName() + ": \n \t\tTicTacToe [1] \n \t\tHangman [2]\n \t\tRock Paper Scissors[3]\n \t\tQuit[4] ");
            System.out.print("\t\tGame#: ");
            choice = input2.nextInt();

            switch(choice){
                case 1:
                    Main ttt = new Main();
                    Main.main(null);
                    int p1 = pet.updatePoints(pet.getPoints(), ttt.getTotalScore()); //adding previous points with new points
                    System.out.println();
                    int l1 = pet.levelUpAnimal(pet.getPoints());
                    pet.printAnimal();
                    break;
                case 2:
                    newHangman hang = new newHangman();
                    hang.main(null);
                    int p2 = pet.updatePoints(pet.getPoints(), hang.getTotalPoints());
                    System.out.println("Hangman Points: "+ hang.getTotalPoints());
                    System.out.println("Animal Points: "+ pet.getPoints());
                    break;
                case 3:
                    RPS rps = new RPS();
                    RPS.main(null);
                    int p3 = pet.updatePoints(pet.getPoints(), rps.getfinalScore()); //adding previous points with new points
                    int l3 = pet.levelUpAnimal(pet.getPoints());
                    pet.printAnimal();
                    break;
//                case 4:
//                    playing = 1;
//                    input.close();
//                    input2.close();
//                        break;
                default:
                    playing = 1;
                    input.close();
                    input2.close();
                    break;
            }

        }while (playing == 0);

        System.out.println("GOOD BYE🦎🦎");
        pet.printAnimal();

    }
}

