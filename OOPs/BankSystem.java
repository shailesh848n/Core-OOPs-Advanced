
import java.util.Scanner;

abstract class BankRules {
    // Abstract method: Har bank account ko ye rule follow karna hi hoga
    public abstract void displayAccountType();
}

class BankAccount extends BankRules {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Cash Deposited: ₹" + amount);
        }
    }

    public void deposit(double amount, String chequeNumber) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Cheque (" + chequeNumber + ") Cleared & Deposited: ₹" + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully Withdrew: ₹" + amount);
        } else {
            System.out.println("Transaction Failed: Insufficient balance!");
        }
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Category: General Bank Account");
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void checkBalance() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Holder: " + accountHolder);
        System.out.println("Acc No: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate = 0.04; 

    public SavingsAccount(String accountHolder, String accountNumber, double initialBalance) {
        super(accountHolder, accountNumber, initialBalance);
    }

   
    public void addInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        System.out.println("Interest of 4% added: ₹" + interest);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Category: Premium Savings Account");
    }
}

public class BankSystem {
    public static void main(String[] args) {
        SavingsAccount mySavings = new SavingsAccount("Shailesh", "SBI98765", 10000.0);

        mySavings.displayAccountType(); 
        mySavings.checkBalance();

        System.out.println("\n--- Performing Transactions ---");

        mySavings.deposit(2000.0);                
        mySavings.deposit(5000.0, "CHQ112233");    

        mySavings.addInterest(); 
        mySavings.checkBalance();
    }
}