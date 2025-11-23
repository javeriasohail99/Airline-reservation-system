import javax.swing.*;
import java.util.ArrayList;

public class RegisterGUI extends JFrame {
    JTextField nameField, usernameField;
    JPasswordField passwordField;
    JButton registerButton;

    public RegisterGUI() {
        setTitle("Passenger Registration");
        setSize(350, 250);
        setLocationRelativeTo(null); // center window
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 160, 25);
        add(nameField);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 60, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 60, 160, 25);
        add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 100, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 160, 25);
        add(passwordField);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(120, 150, 100, 25);
        add(registerButton);

        // Action
        registerButton.addActionListener(e -> registerPassenger());

        setVisible(true);
    }

    private void registerPassenger() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        // Load existing passengers
        ArrayList<Passenger> passengers = Database.loadPassengers();

        // Check for duplicate username
        for (Passenger p : passengers) {
            if (p.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
                return;
            }
        }

        // Add new passenger and save
        passengers.add(new Passenger(name, username, password));
        Database.savePassengers(passengers);

        JOptionPane.showMessageDialog(this, "Registration successful!");
        dispose(); // close registration window
    }
}

