import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class User {
    private String userId;
    private String pin;
    private List<Transaction> transactionHistory;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public void addTransaction(String type, double amount) {
        transactionHistory.add(new Transaction(type, amount));
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

class ATM {
    private User user;
    private JFrame frame;
    private JPanel panel;
    private JTextField userIdField;
    private JPasswordField pinField;
    private JTextArea outputArea;
    private JButton loginButton;

    public ATM() {
        this.user = null;
        this.frame = new JFrame("ATM");
        this.panel = new JPanel();
        this.userIdField = new JTextField(10);
        this.pinField = new JPasswordField(10);
        this.outputArea = new JTextArea(10, 30);
        this.loginButton = new JButton("Login");
    }

    public void start() {
        panel.setLayout(null); // Use null layout for absolute positioning
    
        JLabel userIdLabel = new JLabel("User ID: ");
        JLabel pinLabel = new JLabel("PIN: ");
        
        userIdLabel.setBounds(550, 200, 200, 40); // Set position and size for the user ID label
    userIdLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font and size for the user ID label

    userIdField.setBounds(660, 200, 200, 40); // Set position and size for the user ID text field
    userIdField.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font and size for the user ID text field

    pinLabel.setBounds(550, 250, 200, 40); // Set position and size for the PIN label
    pinLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font and size for the PIN label

    pinField.setBounds(660, 250, 200, 40); // Set position and size for the PIN text field
    pinField.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font and size for the PIN text field

    loginButton.setBounds(620, 330, 200, 40); // Set position and size for the login button
    loginButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size for the login button

        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(loginButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        
        frame.setSize(1650, 1080);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String pin = new String(pinField.getPassword());

                // Simulating user authentication
                if (authenticateUser(userId, pin)) {
                     user = new User(userId, pin); // Instantiate the user object
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid user ID or PIN. Exiting...");
                    System.exit(0);
                }
            }
        });
    }

    private boolean authenticateUser(String userId, String pin) {
        // Perform actual authentication logic here, e.g., checking against a database
        // For simplicity, let's assume a fixed user with ID "123" and PIN "456"
        return userId.equals("123") && pin.equals("456");
    }

    private void showMainMenu() {
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    panel.setLayout(null);

    // Load background image from local drive
    ImageIcon backgroundImage = new ImageIcon("C:/Users/rashm/Desktop/atm-banner.jpg");

    // Create a JLabel to display the background image
    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());

    // Set layout manager of the panel to null
    panel.setLayout(null);

    // Add the background label to the panel as the bottom layer
    panel.add(backgroundLabel);

    JButton transactionHistoryButton = new JButton("Transaction History");
    JButton withdrawButton = new JButton("Withdraw");
    JButton depositButton = new JButton("Deposit");
    JButton transferButton = new JButton("Transfer");
    JButton quitButton = new JButton("Quit");

    // Set position and size for buttons
    transactionHistoryButton.setBounds(650, 200, 220, 40);
    withdrawButton.setBounds(650, 250, 220, 40);
    depositButton.setBounds(650, 300, 220, 40);
    transferButton.setBounds(650, 350, 220, 40);
    quitButton.setBounds(650, 400, 220, 40);

    // Set font and size for buttons
    Font buttonFont = new Font("Arial", Font.PLAIN, 16);
    transactionHistoryButton.setFont(buttonFont);
    withdrawButton.setFont(buttonFont);
    depositButton.setFont(buttonFont);
    transferButton.setFont(buttonFont);
    quitButton.setFont(buttonFont);

    // Add buttons to the panel
    panel.add(transactionHistoryButton);
    panel.add(withdrawButton);
    panel.add(depositButton);
    panel.add(transferButton);
    panel.add(quitButton);

    // Add action listeners to buttons

    transactionHistoryButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showTransactionHistory();
        }
    });

    withdrawButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            performWithdrawal();
        }
    });

    depositButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            performDeposit();
        }
    });

    transferButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            performTransfer();
        }
    });

    quitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });

    frame.setSize(1650, 1080); // Set the frame size
}

//Transaction history


private void showTransactionHistory() {
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    List<Transaction> transactionHistory = user.getTransactionHistory();
    if (transactionHistory.isEmpty()) {
        JLabel noHistoryLabel = new JLabel("No transaction history found.");
        noHistoryLabel.setBounds(600, 250, 300, 40);
        noHistoryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(noHistoryLabel);
    } else {
        int labelY = 250;

        for (Transaction transaction : transactionHistory) {
            JLabel typeLabel = new JLabel(transaction.getType());
            typeLabel.setBounds(600, labelY, 200, 20);
            typeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(typeLabel);

             JLabel amountLabel = new JLabel(String.valueOf(transaction.getAmount())); // Convert double to String
            amountLabel.setBounds(800, labelY, 200, 20);
            amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(amountLabel);

            labelY += 30;
        }
    }

    JButton backButton = new JButton("Back");
    backButton.setBounds(620, 350, 200, 40);
    backButton.setFont(new Font("Arial", Font.PLAIN, 16));
    panel.add(backButton);

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMainMenu();
        }
    });
}




private void performWithdrawal() {
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    JLabel amountLabel = new JLabel("Enter amount to withdraw: ");
    JTextField amountField = new JTextField(10);
    JButton withdrawButton = new JButton("Withdraw");
    JButton backButton = new JButton("Back");

    amountLabel.setBounds(620, 150, 200, 40);
    amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));

    amountField.setBounds(620, 210, 200, 40);
    amountField.setFont(new Font("Arial", Font.PLAIN, 16));

    withdrawButton.setBounds(620, 270, 200, 40);
    withdrawButton.setFont(new Font("Arial", Font.BOLD, 16));

    backButton.setBounds(620, 330, 200, 40);
    backButton.setFont(new Font("Arial", Font.BOLD, 16));

    panel.add(amountLabel);
    panel.add(amountField);
    panel.add(withdrawButton);
    panel.add(backButton);

    withdrawButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());

            // Perform withdrawal logic here, e.g., updating account balance
            // For simplicity, let's just add a withdrawal transaction to the user's transaction history
            user.addTransaction("Withdrawal", amount);
            JOptionPane.showMessageDialog(frame, "Withdrawal of " + amount + " successful.");
            showMainMenu();
        }
    });

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMainMenu();
        }
    });

    frame.setSize(1650, 1080);
}



   private void performDeposit() {
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    JLabel amountLabel = new JLabel("Enter amount to deposit: ");
    JTextField amountField = new JTextField(10);
    JButton depositButton = new JButton("Deposit");
    JButton backButton = new JButton("Back");

    amountLabel.setBounds(620, 150, 200, 40);
    amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));

    amountField.setBounds(620, 210, 200, 40);
    amountField.setFont(new Font("Arial", Font.PLAIN, 16));

    depositButton.setBounds(620, 270, 200, 40);
    depositButton.setFont(new Font("Arial", Font.BOLD, 16));

    backButton.setBounds(620, 330, 200, 40);
    backButton.setFont(new Font("Arial", Font.BOLD, 16));

    panel.add(amountLabel);
    panel.add(amountField);
    panel.add(depositButton);
    panel.add(backButton);

    depositButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());

            // Perform deposit logic here, e.g., updating account balance
            // For simplicity, let's just add a deposit transaction to the user's transaction history
            user.addTransaction("Deposit", amount);
            JOptionPane.showMessageDialog(frame, "Deposit of " + amount + " successful.");
            amountField.setText(""); // Clear the amount field after deposit

            // You can choose to show a success message and stay on the deposit page,
            // or return to the main menu after a successful deposit.
            // To return to the main menu, call the showMainMenu() method.
            showMainMenu();
        }
    });

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMainMenu();
        }
    });

    frame.setSize(1650, 1080);
}


    

   
private void performTransfer() {
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    JLabel amountLabel = new JLabel("Enter amount to transfer: ");
    JTextField amountField = new JTextField(10);
    JLabel recipientLabel = new JLabel("Enter recipient's user ID: ");
    JTextField recipientField = new JTextField(10);
    JButton transferButton = new JButton("Transfer");
    JButton backButton = new JButton("Back");

    amountLabel.setBounds(620, 200, 200, 40);
    amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));

    amountField.setBounds(800, 200, 200, 40);
    amountField.setFont(new Font("Arial", Font.PLAIN, 16));

    recipientLabel.setBounds(620, 270, 200, 40);
    recipientLabel.setFont(new Font("Arial", Font.PLAIN, 16));

    recipientField.setBounds(800, 270, 200, 40);
    recipientField.setFont(new Font("Arial", Font.PLAIN, 16));

    transferButton.setBounds(700, 340, 200, 40);
    transferButton.setFont(new Font("Arial", Font.BOLD, 16));

    backButton.setBounds(700, 400, 200, 40);
    backButton.setFont(new Font("Arial", Font.BOLD, 16));

    panel.add(amountLabel);
    panel.add(amountField);
    panel.add(recipientLabel);
    panel.add(recipientField);
    panel.add(transferButton);
    panel.add(backButton);

    transferButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double amount = Double.parseDouble(amountField.getText());
            String recipientUserId = recipientField.getText();

            // Perform transfer logic here, e.g., updating account balances of both users
            // For simplicity, let's just add a transfer transaction to the user's transaction history
            user.addTransaction("Transfer to " + recipientUserId, amount);
            JOptionPane.showMessageDialog(frame, "Transfer of " + amount + " to " + recipientUserId + " successful.");
            showMainMenu();
        }
    });

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMainMenu();
        }
    });

    frame.setSize(1650, 1080);
}


}

public class oasisAtm {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ATM atm = new ATM();
                atm.start();
            }
        });
    }
}