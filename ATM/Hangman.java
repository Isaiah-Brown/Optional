package optional.ATM;

import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;


public class Hangman {

    char[] answer;
    char[] word;
    char[] guessed;
    int guesses = 8;

    public Hangman() throws Exception {
        
        File file = new File("optional/ATM/american-english");
        Scanner scan = new Scanner(file);
        int range = 0;
        while(scan.hasNextLine()) {
            scan.nextLine();
            range++;
        }
        range--;
        scan.close();

        Random rand = new Random();
        int idx = rand.nextInt(range);
        int count = 0;
        String possibleWord = "##";
        
        while(badWord(possibleWord)) {
            Scanner scan2 = new Scanner(file);
            idx = rand.nextInt(range);
            while(count < idx) {
                scan2.nextLine();
                count++;
            }
            possibleWord = scan2.nextLine();
            scan2.close();
            count = 0;
        }

        char[] answer = possibleWord.toCharArray(); 
        char[] word = new char[answer.length];
        for(int i = 0; i < answer.length; i++) {
            word[i] = '_';
        }
        char[] guessed = new char[0];

        this.answer = answer;
        this.word = word;
        this.guessed = guessed;
        
    }

    public boolean badWord(String s) {
        char[] array = s.toCharArray();
        boolean bad = false;
        for(int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c != 'a' && c != 'b' && c != 'c' && c != 'd' && c != 'e' && c != 'f' && c != 'g' && c != 'h' && c != 'i' && c != 'j' && c != 'k' && c != 'l' && c != 'm' && c != 'n' && c != 'o' && c != 'p' && c != 'q' && c != 'r' && c != 's' && c != 't' && c != 'u' && c != 'v' && c != 'w' && c != 'x' && c != 'y' && c != 'z') {
                bad = true;
            }
        }
        return bad;
    }



    public boolean guessLetter(char guess) {
        
        boolean goodGuess = false;
        for(int i = 0; i < guessed.length; i++) {
            if (guessed[i] == guess) {
                System.out.println("You already guessed that letter!");
                return true;
            }

        }

        for(int i = 0; i < answer.length; i++) {
            if (answer[i] == guess) {
                word[i] = guess;
                goodGuess = true;
            }

        }
        guessed = Arrays.copyOf(guessed, guessed.length + 1);
        guessed[guessed.length - 1] = guess;

        if (!goodGuess) {
            guesses -= 1;
        }

        return goodGuess;
    }

    public boolean repeat(char guess) {
        boolean repeated = false;
        for(int i = 0; i < guessed.length; i++) {
            if (guessed[i] == guess) {
                repeated = true;
            }
        }
        return repeated;
    }

    public void showWord() {
        System.out.println();
        System.out.println(word);
        System.out.println("Guess a letter:");
    }



    public static void main(String[] args) throws Exception {
        System.out.println("--- Let's Play Hangman! ---");
        Hangman deadman = new Hangman();
        System.out.println("Guess 'quit' to stop the game at any time.\nGuess 'letters' to see the letters you've already guessed.\nGuess 'solve' to attempt to solve the puzzle!\n\nI'm thinking of a " + Integer.toString(deadman.answer.length) + " letter word. Do you know what it is?");
        Scanner user = new Scanner(System.in);
        String answer = "a";

        deadman.showWord();
        String s = new String(deadman.answer);


        while(true) {
            answer = user.nextLine();
            if (answer.length() == 1) {
                char c = answer.charAt(0);
                boolean correct = deadman.guessLetter(c);
                if (!correct) {
                    if (deadman.guesses == 0) {
                        System.out.println("Game Over!");
                        System.out.println("The word was: "  + s);
                        user.close();
                        System.exit(0);
                    } else {
                        System.out.println("That letter is NOT in the word\n:( You have " + Integer.toString(deadman.guesses) + " guesses left. ");
                    }
                } else {
                    boolean solved = true;
                    for(int i = 0; i < deadman.word.length; i++) {
                        char curr = deadman.word[i];
                        if(curr == '_') {
                            solved = false;
                        } 
                    }
                    if (solved) {
                        System.out.println("You Win!");
                        System.out.println("The word was: "  + s);
                        System.exit(0);
                    }
                }
            } else if (answer.equals("letters")) {
                System.out.println("Here is a list of the letters you have already guessed:");
                String letters = "";
                for(int i = 0; i < deadman.guessed.length; i++) {
                    letters += deadman.guessed[i];
                    if (i != deadman.guessed.length - 1) {
                        letters += ", ";
                    }
                }
                System.out.println(letters);
            } else if (answer.equals("solve")) {
                System.out.println("Ok what is the word I'm thinking of?");
                answer = user.nextLine();
                if (answer.equals(s)) {
                    System.out.println("You Win!");
                } else {
                    System.out.println("That's wrong :/");
                }
                System.out.println("The word was: "  + s);
                user.close();
                System.exit(0);
            } else if(answer.equals("quit")) {
                System.out.println("Ok, the word was: " + s);
                user.close();
                System.exit(0); 
            } else {
                System.out.println("Command not recognized");
            }
            deadman.showWord();
        }
    }
}