package project;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class NoticeBoardFrame extends JFrame {
    private JTextArea noticeArea;
    private JTextField noticeField;
    private ArrayList<Notice> notices = new ArrayList<>();
    private String currentUser;

    public NoticeBoardFrame(String currentUser, HashMap<String, String> users) {
        this.currentUser = currentUser;
        setTitle("Notice Board");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue

        // Notice area to display selected notice
        noticeArea = new JTextArea();
        noticeArea.setEditable(false);
        noticeArea.setLineWrap(true);
        noticeArea.setWrapStyleWord(true);
        noticeArea.setBackground(Color.WHITE);
        noticeArea.setFont(new Font("Arial", Font.PLAIN, 14));
        noticeArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        // Input field for notice text
        noticeField = new JTextField(20);
        noticeField.setPreferredSize(new Dimension(300, 100)); // Make it bigger

        // Buttons
        JButton addButton = new JButton("Add Notice");
        JButton deleteButton = new JButton("Delete Notice");
        JButton viewButton = new JButton("View Notices");
        JButton backButton = new JButton("← Back");

        // Button styling
        JButton[] buttons = {addButton, deleteButton, viewButton, backButton};
        for (JButton button : buttons) {
            button.setBackground(new Color(100, 149, 237)); // CornflowerBlue
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));

        // Adding components to the panel
        panel.add(noticeArea, BorderLayout.CENTER);

        // Input panel for adding notices
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(240, 248, 255));
        inputPanel.add(noticeField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(viewButton);
        inputPanel.add(backButton);
        panel.add(inputPanel, BorderLayout.SOUTH);

        add(panel);

        // Adding action listeners
        addButton.addActionListener(e -> {
            String noticeText = noticeField.getText();
            if (!noticeText.isEmpty()) {
                notices.add(new Notice(noticeText, currentUser));
                noticeField.setText("");
                JOptionPane.showMessageDialog(this, "Notice added!");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a notice.");
            }
        });

        deleteButton.addActionListener(e -> {
            String noticeText = noticeField.getText();
            boolean found = false;
            for (Notice notice : notices) {
                if (notice.getText().equals(noticeText) && notice.getOwner().equals(currentUser)) {
                    notices.remove(notice);
                    found = true;
                    noticeArea.setText(""); // Clear notice area
                    JOptionPane.showMessageDialog(this, "Notice deleted!");
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Notice not found or you are not the owner.");
            }
        });

        viewButton.addActionListener(e -> {
            if (notices.isEmpty()) {
                noticeArea.setText("No notices available.");
            } else {
                StringBuilder allNotices = new StringBuilder();
                for (int i = 0; i < notices.size(); i++) {
                    allNotices.append(i + 1).append(": ").append(notices.get(i).getText()).append("\n");
                }
                noticeArea.setText(allNotices.toString());
                
                // Add mouse listener to show notice details on click
                noticeArea.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        try {
                            int index = noticeArea.getLineOfOffset(evt.getY());
                            if (index >= 0 && index < notices.size()) {
                                noticeArea.setText(notices.get(index).toString());
                            }
                        } catch (BadLocationException ble) {
                            ble.printStackTrace(); // Handle the exception
                        }
                    }
                });
            }
        });

        // Back navigation action
        backButton.addActionListener(e -> {
            new LoginFrame(); // Go back to login frame
            dispose();
        });

        setVisible(true);
    }
}
