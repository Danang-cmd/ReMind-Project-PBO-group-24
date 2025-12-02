package ui;

import javax.swing.*;
import database.ScoreDAO;
import model.Score;
import java.awt.*;
import java.util.List;

public class LeaderboardFrame extends JPanel {

    private final LoginFrame mainFrame;
    private final String username;

    public LeaderboardFrame(LoginFrame mainFrame, String username) {
        this.mainFrame = mainFrame;
        this.username = username;

        setLayout(new BorderLayout());
        setBackground(UIStyles.BACKGROUND);

        JLabel title = new JLabel("LEADERBOARD", SwingConstants.CENTER);
        title.setForeground(UIStyles.NEON_ACCENT);
        title.setFont(UIStyles.BOLD_XLARGE);
        title.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBackground(UIStyles.BACKGROUND.darker());
        area.setForeground(Color.WHITE);
        area.setFont(UIStyles.REGULAR_MEDIUM);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(UIStyles.neonBorder());
        add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        JButton back = UIStyles.createNeonButton("Back");
        back.addActionListener(e -> mainFrame.showMainMenuView(username));
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);

        List<Score> list = ScoreDAO.getLeaderboard();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s %-20s %-8s%n", "#", "Username", "Score"));
        sb.append("================================\n");
        int idx = 1;
        for (Score s : list) {
            sb.append(String.format("%-4d %-20s %-8d%n", idx++, s.username, s.score));
        }
        area.setText(sb.toString());
    }
}
