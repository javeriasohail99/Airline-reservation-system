import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginGUI extends JFrame {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, registerButton;

    public LoginGUI() {
        setTitle("Airline Reservation - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Welcome to Airline Reservation System", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setOpaque(true);
        header.setBackground(new Color(0, 123, 255));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(40, 167, 69));
        loginButton.setForeground(Color.WHITE);
        formPanel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 193, 7));
        registerButton.setForeground(Color.BLACK);
        formPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> new RegisterGUI());

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.equals("admin") && password.equals("admin")) {
            new MainMenuGUI(true, null);
            dispose();
            return;
        }

        ArrayList<Passenger> passengers = Database.loadPassengers();
        for (Passenger p : passengers) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                new MainMenuGUI(false, p);
                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Invalid username or password!");
    }
}
