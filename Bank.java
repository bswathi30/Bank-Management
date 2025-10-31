import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public String listAccounts() {
        if (accounts.isEmpty()) {
            return "No accounts in the bank.";
        }
        StringBuilder sb = new StringBuilder();
        for (Account acc : accounts) {
            sb.append("Account: ").append(acc.getAccountNumber())
                    .append(", Holder: ").append(acc.getAccountHolderName())
                    .append(", Balance: $").append(acc.getBalance()).append("\n");
        }
        return sb.toString();
    }
}