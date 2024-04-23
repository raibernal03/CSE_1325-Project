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
        System.out.println("Pet Name: " + getName());
        System.out.println("Pet Level: " + getLevel());
        System.out.println("Points: " + getPoints());
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Reptile Arcade");
        System.out.println("Well start by creating your pet ");
        Animal pet = new Animal();
        Scanner input = new Scanner(System.in);
        //Initiazion of Pet
        System.out.print("\tPet Name: ");
        pet.setName(input.nextLine());
        System.out.println("\tPet Level: "+ pet.getLevel());
        System.out.println("\tPoints: " + pet.getPoints());
        System.out.println();

        System.out.println("To level up your pet you'll have to gather points from the following games: \n \t\tTicTacToe [1] \n \t\tHangman [2]\n \t\tRock Paper Scissors[3]");
        System.out.print("\t\tGame#: ");
        int choice = input.nextInt();
        switch(choice){
            case 1:
                Main ttt = new Main();
                Main.main(null);
                int p1 = pet.updatePoints(pet.getPoints(), ttt.getfinalScore()); //adding previous points with new points
                System.out.println();
                int l1 = pet.levelUpAnimal(pet.getPoints());
                break;
            case 2:
                newHangman hang = new newHangman();
                hang.main(null);

                break;
            case 3:
                RPS rps = new RPS();
                RPS.main(null);
                int p3 = pet.updatePoints(pet.getPoints(), rps.getfinalScore()); //adding previous points with new points

                System.out.println();
                int l3 = pet.levelUpAnimal(pet.getPoints());
                break;
            default:
                break;
        }

    }
}

