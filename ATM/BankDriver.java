package optional.ATM;

import java.util.Scanner;

public class BankDriver {

    public static void main(String[] args) {
        System.out.println("Welcome to the MAD bank!\nCurrent balance: $0.00\n");

        String question = "\nWhat would you like to do?\n(q)uit (d)eposit (w)ithdraw (c)heck balance";
        String deposit = "How much would you like to deposit?";
        String withdraw = "How much would you like to withdraw?";

        Bank bank = new Bank();
    
        Scanner user = new Scanner(System.in);
        String answer = "a";

        while(!answer.equals("q")) {
            System.out.println(question);
            answer = user.nextLine();
            if (answer.length() < 2) {
                if (answer.equals("d")) {
                    System.out.println(deposit);
                    try {
                        double amount = Double.valueOf(user.nextLine());
                        bank.deposit((amount));
                    } catch (Exception e) {
                        System.out.println("Commaned not recognized");
                    }
                   

                } else if (answer.equals("w")) {
                    System.out.println(withdraw);
                    try {
                        double amount = Double.valueOf(user.nextLine());
                        bank.withdraw(amount);
                    } catch (Exception e) {
                        System.out.println("Commaned not recognized");
                    }
                
                } else if (answer.equals("c")) {
                    bank.ToString();
                } else if (!answer.equals("q")) {
                    System.out.println("Commaned not recognized");
                }
            } else {
                System.out.println("Commaned not recognized");
            }
    
        }
        user.close();
        System.out.println("Bye Bye");
        System.exit(0);
    }
   
}
