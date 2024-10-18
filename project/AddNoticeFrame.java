package project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddNoticeFrame extends JFrame {
    private JTextField noticeField;
    private ArrayList<Notice> notices;
    private String currentUser;

    public AddNoticeFrame(String currentUser, ArrayList<Notice> notices) {
        this.currentUser = currentUser;
        this.notices = notices;

        setTitle("Add Notice");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue

        noticeField = new JTextField();
        noticeField.setPreferredSize(new Dimension(300, 100)); // Bigger input field

        JButton submitButton = new JButton("Submit Notice");
        JButton backButton = new JButton("← Back");

        // Button styling
        styleButton(submitButton);
        styleButton(backButton);

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new Color(240, 248, 255));
        panel.add(new JLabel("Enter Notice:"));
        panel.add(noticeField);
        panel.add(submitButton);
        panel.add(backButton);

        add(panel);

        // Action listeners
        submitButton.addActionListener(e -> {
            String noticeText = noticeField.getText();
            if (!noticeText.isEmpty()) {
                notices.add(new Notice(noticeText, currentUser)); // Add notice
                JOptionPane.showMessageDialog(this, "Notice added!");
                noticeField.setText(""); // Clear field after submission
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a notice.");
            }
        });

        backButton.addActionListener(e -> {
            new NoticeBoardFrame(currentUser, new HashMap<>()); // Return to the notice board
            dispose(); // Close this frame
        });

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(100, 149, 237)); // CornflowerBlue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
