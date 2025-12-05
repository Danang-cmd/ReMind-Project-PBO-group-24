package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import database.DBConnection;

public class LoginFrame extends JFrame {

    private String currentUser;

    public LoginFrame() {
        setTitle("ReMind");
        setSize(620, 740);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        showLoginView();

        setVisible(true);
    }

    public void showLoginView() {

        Image bg = new ImageIcon("assets/bg/login_bg.png").getImage();

        JPanel root = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        };
        root.setBackground(UIStyles.BACKGROUND);

        JPanel card = new JPanel();
        card.setBackground(UIStyles.PANEL_BG);
        card.setBorder(UIStyles.neonBorder());
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(420, 350));

        JLabel title = new JLabel("LOGIN");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(UIStyles.BOLD_LARGE);
        title.setForeground(UIStyles.NEON_ACCENT);
        title.setBorder(BorderFactory.createEmptyBorder(12, 0, 20, 0));
        card.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setForeground(UIStyles.NEON_ACCENT);
        userLabel.setFont(UIStyles.BOLD_MEDIUM);
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(userLabel);

        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        UIStyles.styleInputField(username);
        card.add(username);

        card.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(UIStyles.NEON_ACCENT);
        passLabel.setFont(UIStyles.BOLD_MEDIUM);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        UIStyles.styleInputField(password);
        card.add(password);

        card.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel btnRow = new JPanel();
        btnRow.setOpaque(false);
        JButton loginBtn = UIStyles.createNeonButton("Login");
        JButton regBtn = UIStyles.createNeonButton("Register");
        btnRow.add(loginBtn);
        btnRow.add(Box.createRigidArea(new Dimension(10, 0)));
        btnRow.add(regBtn);
        card.add(btnRow);

        loginBtn.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi username dan password.");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    currentUser = user;
                    showMainMenuView(user);
                } else {
                    JOptionPane.showMessageDialog(this, "Login gagal, cek kembali.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error.");
            }
        });

        regBtn.addActionListener(e -> showRegisterView());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0;

        root.add(card, gbc);
        setContentPane(root);
        revalidate();
        repaint();
    }

    public void showRegisterView() {
        setContentPane(new RegisterFrame(this));
        revalidate();
        repaint();
    }

    public void showMainMenuView(String username) {
        this.currentUser = username;
        setContentPane(new MainMenuFrame(this, username));
        revalidate();
        repaint();
    }

    public void showGameView(String username) {
        this.currentUser = username;
        setContentPane(new GameFrame(this, username));
        revalidate();
        repaint();
    }

    public void showLeaderboardView(String username) {
        this.currentUser = username;
        setContentPane(new LeaderboardFrame(this, username));
        revalidate();
        repaint();
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
