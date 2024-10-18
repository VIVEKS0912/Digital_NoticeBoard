package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class LoginFrame extends JFrame {
    private HashMap<String, String> users = new HashMap<>();
    private String currentUser;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123"; // Simple password for demo purposes

    public LoginFrame() {
        setTitle("User Login");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton createUserButton = new JButton("Create User");
        JButton adminLoginButton = new JButton("Admin Login");

        // Login action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentUser = username;
                new NoticeBoardFrame(currentUser, users);
                dispose(); // Close login frame
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });

        // Admin login action
        adminLoginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                new AdminUserManagementFrame(users);
                dispose(); // Close login frame
            } else {
                JOptionPane.showMessageDialog(this, "Invalid admin credentials.");
            }
        });

        // User creation action
        createUserButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                users.put(username, password);
                JOptionPane.showMessageDialog(this, "User created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a username and password.");
            }
        });

        // Layout setup
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createUserButton);
        panel.add(adminLoginButton);

        add(panel);
        setVisible(true);
    }
}

