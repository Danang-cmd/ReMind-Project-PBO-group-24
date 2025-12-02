package ui;

import javax.swing.*;
import database.ScoreDAO;
import model.Score;
import java.util.List;

public class LeaderboardFrame extends JFrame {

    public LeaderboardFrame() {
        setTitle("Leaderboard");
        setSize(350, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 20, 300, 320);
        add(scroll);

        List<Score> list = ScoreDAO.getLeaderboard();

        area.append("TOP 10 SCORES\n");
        area.append("=====================\n");

        for (Score s : list) {
            area.append(s.username + " - " + s.score + "\n");
        }

        setVisible(true);
    }
}
