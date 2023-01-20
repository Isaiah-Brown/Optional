
package optional.ATM;

public class Bank {

    private double balance;

    public Bank() {
        balance = 0;
    }

    public void deposit(double amount) {
        if(amount >= 0) {
            balance += amount;
        } else {
            System.out.println("You cannot deposit a negative value");
        }
    }

    public void withdraw(double amount) {
        if(amount > balance) {
            System.out.println("insufficient funds");
        } else if (amount < 0) {
            System.out.println("You cannot withdraw a negative value");
        } else {
            balance -= amount;
        }
    }

    public void ToString() {
        double rounded = Math.round(balance * 100.00) / 100.00;    //https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        System.out.println(Double.toString(rounded));
    }
}