```
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String owner;
    private double balance;
    private List<String> transactions;

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
        transactions.add("Withdrawn: " + amount);
    }

    public void transfer(BankAccount toAccount, double amount) {
        this.withdraw(amount);
        toAccount.deposit(amount);
        transactions.add("Transferred: " + amount + " to " + toAccount.getAccountNumber());
    }
}
```