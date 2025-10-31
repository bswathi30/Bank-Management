public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "Deposited: $" + amount + ". New balance: $" + balance;
        }
        return "Invalid deposit amount.";
    }

    public String withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return "Withdrew: $" + amount + ". New balance: $" + balance;
        }
        return "Invalid withdrawal: Insufficient funds or invalid amount.";
    }

    public String transfer(Account toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            return "Transferred: $" + amount + " to " + toAccount.getAccountNumber();
        }
        return "Transfer failed: Insufficient funds or invalid amount.";
    }
}