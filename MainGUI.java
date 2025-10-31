import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private Bank bank = new Bank();
    private JTextField accNumField, nameField, balanceField, toAccField;
    private JTextArea outputArea;

    public MainGUI() {
        setTitle("Bank Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel: Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        inputPanel.add(new JLabel("Account Number:"));
        accNumField = new JTextField();
        inputPanel.add(accNumField);

        inputPanel.add(new JLabel("Holder Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Initial/Amount:"));
        balanceField = new JTextField();
        inputPanel.add(balanceField);

        inputPanel.add(new JLabel("To Account (for transfer):"));
        toAccField = new JTextField();
        inputPanel.add(toAccField);

        add(inputPanel, BorderLayout.NORTH);

        // Center: Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Bottom: Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 220)); // Beige background
        JButton addBtn = new JButton("Add Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton checkBalBtn = new JButton("Check Balance");
        JButton listBtn = new JButton("List Accounts");

        buttonPanel.add(addBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(checkBalBtn);
        buttonPanel.add(listBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add sample accounts
        bank.addAccount(new Account("ACC001", "Alice", 1000));
        bank.addAccount(new Account("ACC002", "Bob", 500));

        // Button Actions
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String accNum = accNumField.getText();
                    String name = nameField.getText();
                    double initBal = Double.parseDouble(balanceField.getText());
                    bank.addAccount(new Account(accNum, name, initBal));
                    outputArea.append("Account added: " + accNum + "\n");
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid amount. Please enter a number.\n");
                }
            }
        });

        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Account acc = bank.findAccount(accNumField.getText());
                    if (acc != null) {
                        double amount = Double.parseDouble(balanceField.getText());
                        outputArea.append(acc.deposit(amount) + "\n");
                    } else {
                        outputArea.append("Account not found.\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid amount. Please enter a number.\n");
                }
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Account acc = bank.findAccount(accNumField.getText());
                    if (acc != null) {
                        double amount = Double.parseDouble(balanceField.getText());
                        outputArea.append(acc.withdraw(amount) + "\n");
                    } else {
                        outputArea.append("Account not found.\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid amount. Please enter a number.\n");
                }
            }
        });

        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Account fromAcc = bank.findAccount(accNumField.getText());
                    Account toAcc = bank.findAccount(toAccField.getText());
                    if (fromAcc != null && toAcc != null) {
                        double amount = Double.parseDouble(balanceField.getText());
                        outputArea.append(fromAcc.transfer(toAcc, amount) + "\n");
                    } else {
                        outputArea.append("One or both accounts not found.\n");
                    }
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid amount. Please enter a number.\n");
                }
            }
        });

        checkBalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account acc = bank.findAccount(accNumField.getText());
                if (acc != null) {
                    outputArea.append("Balance: $" + acc.getBalance() + "\n");
                } else {
                    outputArea.append("Account not found.\n");
                }
            }
        });

        listBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.append(bank.listAccounts() + "\n");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI();
            }
        });
    }
}