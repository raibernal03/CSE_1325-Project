package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class newHangman {

    public int totalPoints;

    //class variables
    String word; // the word the user needs to guess
    int level;  // sets the level of the game ( basically how long the word is)
    int lives; // they get 7 lives/ changces to guess
    String gWrd; // the word that the user has guessed so far. Will be the same as Word at the end of the game (if the user won) 
    String gAlphabet; // the list of letters that have been guessed 
    int points; // the points the user gets to level up their virtual pet


    // constuctor, to set inital values
    newHangman(){
        points = 0;
        lives = 7;
        gWrd = "_____"; 
        gAlphabet = " ";
    }

    public static void main(String[] args) {
        newHangman newMan = new newHangman();
        newMan.firstGame();
    } //end of main

    // basically the new Main
    public void firstGame(){
        // setting up the hangman game
        Scanner scan = new Scanner(System.in);
        //newHangman newMan = new newHangman();

        // prints out a quick description of the game and rules
        System.out.println("\n\n\t\tWecome to hangman!\n");
        System.out.println("A game where you guess a word by entering letter or guessing the whole word.\n"
        + "The words are Reptile themed (and they do include some fictional reptiles)\n"
        + "There are three levels with increasing word length and increasing point return. \n"
        + "HOWEVER if you lose the game the points you would have won are now subtracted instead of added");
        
        System.out.println("\nLevel 1) word length: 4-5 letters, 3 points" +
        "\nLevel 2) word length: 6-7 letters, 4 points\nLevel 3) word length: 8-9 letters, 5 points\n");

        // setting up game
        menu(this, scan);
        
        try {
            getWord(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setGWord(this);

        // starting the game   
        gameMenu(this, scan);          

        scan.close();
        System.out.println("in firstGame: the total points you earned is: " + this.points);
    }

    /* asks user if they want to quit or what level they want to play at */
    public static void menu(newHangman newMan, Scanner scan){

        // quits or sets the game level
        System.out.println("\t\tmenu option: \n0) quit game \n1) Play Game (level 1)");
        System.out.println("2) Play Game (level 2)\n3) Play Game (level 3)\nEnter the number of the option you want:");

        int choice = scan.nextInt();
        switch (choice) {
            case 0:
                System.out.println("the total points you earned is: "+newMan.points+"\nExiting...");
                System.exit(0);                
                break;
        
            case 1:
                newMan.level = choice;
                break;
            
            case 2:
                newMan.level = choice;
                break;

            case 3:
                newMan.level = choice;
                break;

            default:
                System.out.print("What you entered was not an option.");
                System.out.println(" As punishment, you'll play on the hardest setting"); 
                newMan.level = 3;
                break;
        }
   
    } // end of menu
    
    // gets the Word the user will guess from a file and saves it in the Hangman var 
    public static void getWord(newHangman newMan) throws IOException{
        /* generates the hangman word that the user guesses
         * 1) choose the correst file for the user's level
         * 2) saves all the words from the file in an arrayList wordList
         * 3) shuffels the arrayList so it's in a different order
         * 4) takes the first element and saves it as the hangman word 
         */

        BufferedReader reader;
        String fileName;
        ArrayList<String> wordList = new ArrayList<String>();
        
        //1) choose the correst file needed for the level
        switch(newMan.level){
            case 1:
                fileName = "src/logic/4or5LtrWrd.txt";
                break;
            case 2:
                fileName = "src/logic/6or7LtrWrd.txt";
                break;
            case 3:
                fileName = "src/logic/8or9LtrWrd.txt";
                break;
            default: //level 1
                System.out.println("ERROR:: No level found. Defaulting to level 1");
                newMan.level = 1;
                fileName = "4or5LtrWrd.txt";
                break;
        }

        //2)  reads in the file into the array list
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line; 
            while((line =  reader.readLine()) != null){
                wordList.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
        
            e.printStackTrace();
        } 

        // 3) randomizes the list 
        Collections.shuffle(wordList);

        //4) save a word in hanngman word
        newMan.word = wordList.get(0).toLowerCase();
        System.out.println("the chosen word is: " + newMan.word);
    
    } // end of getWord


    // sets the guessed word to all blanks
    public static void setGWord(newHangman newMan){
        
        newMan.gWrd = "";
        for(int i =0; i < newMan.word.length(); i++){
            newMan.gWrd = newMan.gWrd + "_"; 

        }
    }

/*-------------------------------- starting the game -------------------------- */

    // asks user if they want to quit, guess a letter or the whole word
    public static void gameMenu(newHangman newMan, Scanner scan){

        // checks if you're out of lives, and subtracts points
        if(newMan.lives == 0){
            System.err.println("Wahh wahh, you're out of lives. The word was: " + newMan.word + ". Better luck next time");
            newMan.subtractPoints();
            System.out.println("You now have " + newMan.points + " points.");
            newMan.reset();
            newMan.replay(scan);
        }
        // printing status
        System.out.println("\n\n\t\tCurrent Status \nWord length: " + newMan.word.length());
        System.out.println("Guessed word: " + newMan.gWrd +"\nletters you've guessed: " + newMan.gAlphabet);
        System.out.println("lives: " + newMan.lives + "\npoints: " + newMan.points);

        // menu options
        System.out.println("\n\t\t Game menu options: \n0) quit game \n1) guess a letter");
        System.out.println("2) guess the whole word \nEnter the number of the option you want:");

        int choice = 0;
        choice = scan.nextInt();        

        switch (choice) {
            case 0: // quit
                System.out.println("the total points you earned is: "+newMan.points+"\nExiting...");
                System.exit(0);                
                break;

            case 1: // guess letter
                guessLetter(newMan, scan);                
                break;
        
            case 2: // guess whole word
                guessWholeWord(newMan, scan);
                break;

            default:
                System.out.println("well what you entered was not an option. minus one life");
                newMan.removeLife(newMan);
                gameMenu(newMan, scan);
                break;
        }

    } // end of game menu

    // checks if the letter entered is in the word
    public static void guessLetter(newHangman newMan, Scanner scan ) {
        // vars for colored terminal output
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        
        // 1) get letter from user
        System.out.println("Enter a letter:");
        char newLetter = scan.next().charAt(0);

        // 2) check if the letter is in the gAlphabet
        if(newMan.gAlphabet.indexOf(newLetter) != -1){
            System.out.println(RED + "You've already entered the letter " + newLetter + RESET);
            System.out.println("here are all the letters you've looked for: " + newMan.gAlphabet);
            guessLetter(newMan, scan);
        }
        else{
            // adding letter to gAlphabet
            newMan.gAlphabet = newMan.gAlphabet + newLetter + " " ;
        }

        // 3) checks if letter is in the word
        // if the letter is not in the word
        if(newMan.word.indexOf(newLetter) == -1){  
            System.out.println(RED + "sorry the letter " + newLetter + " was not in the word" + RESET);
            newMan.removeLife(newMan);
            gameMenu(newMan, scan);
        }
        else{ //letter is in the word
            System.out.println(GREEN + "Yay the letter " + newLetter + " in the word!" + RESET);

            // updates gWrd
            char[] tempGWrd = newMan.gWrd.toCharArray();
            for(int i =0; i < newMan.word.length(); i++ ){

                if (newMan.word.charAt(i) == newLetter) {      
                    tempGWrd[i] = newLetter; 
                }
            }

            // checks if the guessed word is the word
            String newGWrd = new String(tempGWrd);
            newMan.gWrd = newGWrd;
            if( newGWrd.equals(newMan.word)){
               newMan.winGame(scan);
            }
            else{
                System.out.println("the updated word is: " + newMan.gWrd);
                gameMenu(newMan, scan);
            }

        }
    }//end of guessLetter

    // allows user to guess the whole word
    public static void guessWholeWord(newHangman newMan, Scanner scan){
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";

        System.out.println("Enter the full word: ");
        String guess = scan.next().toLowerCase();
        
        // the word was right
        if(guess.equals(newMan.word)){
            newMan.winGame(scan);
        }
        else{ // the word was wrong
            System.out.println(RED + " " + guess + " was not the word, sorry.");
            newMan.removeLife(newMan);
            System.out.println("you have " + newMan.lives + " lives left." + RESET);
            gameMenu(newMan, scan);
        }
    } // end of guessWholeWord

    public void removeLife(newHangman newMan){
        newMan.lives = (newMan.lives - 1);
    }

    // resets the hangman for the new game
    public void reset(){
        level = 0;
        lives = 7;
        gWrd = "_____"; 
        gAlphabet = " ";
        word = null;
    }

    public void replay(Scanner scan){
        menu(this, scan);
        
        try {
            getWord(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setGWord(this);

        // starting the game   
        gameMenu(this, scan);
    }
    
    // adds points, prints out winning message, and resets game
    public void winGame(Scanner scan){
        //for colored output
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m"; 


        this.addPoints();

        System.out.println("        \\|/");
        System.out.println(CYAN + "You got it! the word was " + this.word);
        System.out.println("You got " + this.points + " points" + RESET);
        System.out.println("        /|\\");
        this.reset();
        this.replay(scan);
    }

    public void addPoints(){
        this.points = (this.level + 2) + this.points;
        totalPoints = this.points;
    }

    public void subtractPoints(){
        this.points = (-1 * (this.level + 2)) + this.points;
        totalPoints = this.points;
    }



 

}
