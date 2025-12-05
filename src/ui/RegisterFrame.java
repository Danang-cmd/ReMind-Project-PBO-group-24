package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import database.DBConnection;

public class RegisterFrame extends BaseFrame {

    private Image bg = new ImageIcon("assets/bg/login_bg.png").getImage();

    public RegisterFrame(LoginFrame mainFrame) {
        super(mainFrame, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    protected void initComponents() {

        setLayout(new GridBagLayout());
        setBackground(UIStyles.BACKGROUND);

        JPanel card = new JPanel();
        card.setBackground(UIStyles.PANEL_BG);
        card.setBorder(UIStyles.neonBorder());
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(420, 350));

        JLabel title = new JLabel("REGISTER");
        title.setFont(UIStyles.BOLD_LARGE);
        title.setForeground(UIStyles.NEON_ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(12,0,20,0));
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
        JButton register = UIStyles.createNeonButton("Register");
        JButton back = UIStyles.createNeonButton("Back");
        btnRow.add(register);
        btnRow.add(Box.createRigidArea(new Dimension(10,0)));
        btnRow.add(back);
        card.add(btnRow);

        register.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Isi username & password");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO users(username,password) VALUES(?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, pass);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(mainFrame, "Register Berhasil!");
                mainFrame.showLoginView();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Username sudah dipakai!");
            }
        });

        back.addActionListener(e -> mainFrame.showLoginView());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(card, gbc);
    }
}
