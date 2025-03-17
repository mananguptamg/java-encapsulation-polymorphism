import java.util.*;

abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    protected double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid or insufficient funds.");
        }
    }

    public abstract double calculateInterest();
}

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

// Subclass: SavingsAccount
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        return balance * (interestRate / 100);
    }
}

class CurrentAccount extends BankAccount implements Loanable {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double calculateInterest() {
        return 0; // Current accounts typically don't earn interest
    }

    @Override
    public void applyForLoan(double amount) {
        System.out.println("Loan applied for: " + amount);
    }

    @Override
    public boolean calculateLoanEligibility() {
        return balance + overdraftLimit > 1000; // Simple eligibility check
    }
}
public class BankingSystem {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();

        SavingsAccount savings = new SavingsAccount("123456", "Arun Kumar", 5000, 3.5);
        CurrentAccount current = new CurrentAccount("CA456", "Ajay Kumar", 2000, 500);

        accounts.add(savings);
        accounts.add(current);

        for (BankAccount account : accounts) {
            System.out.println("Account Holder: " + account.getHolderName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Interest: " + account.calculateInterest());
            if (account instanceof Loanable) {
                ((Loanable) account).applyForLoan(5000);
                System.out.println("Loan Eligible: " + ((Loanable) account).calculateLoanEligibility());
            }
            System.out.println();
        }
    }
}

//SampleOutput
//Account Holder: Arun Kumar
//Balance: 5000.0
//Interest: 175.00000000000003
//
//Account Holder: Ajay Kumar
//Balance: 2000.0
//Interest: 0.0
//Loan applied for: 5000.0
//Loan Eligible: true