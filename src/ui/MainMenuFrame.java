package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends BaseFrame {

    private Image bg = new ImageIcon("assets/bg/menu_bg.png").getImage();

    public MainMenuFrame(LoginFrame mainFrame, String username) {
        super(mainFrame, username);
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

        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.setPreferredSize(new Dimension(350, 320));

        JLabel title = new JLabel("REMIND", SwingConstants.CENTER);
        title.setFont(UIStyles.BOLD_XLARGE);
        title.setForeground(UIStyles.NEON_ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(title);

        container.add(Box.createRigidArea(new Dimension(0, 25)));

        JButton play = UIStyles.createNeonButton("Play");
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(play);

        container.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton leaderboard = UIStyles.createNeonButton("Leaderboard");
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(leaderboard);

        container.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton logout = UIStyles.createNeonButton("Logout");
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(logout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(container, gbc);

        play.addActionListener(e -> mainFrame.showGameView(username));
        leaderboard.addActionListener(e -> mainFrame.showLeaderboardView(username));
        logout.addActionListener(e -> mainFrame.showLoginView());
    }
}
