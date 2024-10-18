package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AdminUserManagementFrame extends JFrame {
    private HashMap<String, String> users;

    public AdminUserManagementFrame(HashMap<String, String> users) {
        this.users = users;
        setTitle("Admin User Management");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JTextField usernameField = new JTextField(15);
        JButton deleteUserButton = new JButton("Delete User");
        JButton backButton = new JButton("← Back");

        // Deleting user action
        deleteUserButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (users.containsKey(username)) {
                users.remove(username);
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        });

        // Back navigation action
        backButton.addActionListener(e -> {
            new LoginFrame(); // Go back to login frame
            dispose();
        });

        // Layout setup
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(new JLabel("Username to delete:"));
        panel.add(usernameField);
        panel.add(deleteUserButton);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }
}
