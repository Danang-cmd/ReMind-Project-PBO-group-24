package ui;

import javax.swing.*;
import javax.swing.table.*;
import database.ScoreDAO;
import model.Score;

import java.awt.*;
import java.util.List;

public class LeaderboardFrame extends JPanel {
    private Image bg = new ImageIcon("assets/bg/leaderboard_bg.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }

    private final LoginFrame mainFrame;
    private final String username;

    public LeaderboardFrame(LoginFrame mainFrame, String username) {

        this.mainFrame = mainFrame;
        this.username = username;

        setLayout(new GridBagLayout());
        setBackground(UIStyles.BACKGROUND);

        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(450, 500));
        card.setBorder(UIStyles.neonBorder());
        card.setBackground(UIStyles.PANEL_BG);

        JLabel title = new JLabel("LEADERBOARD", SwingConstants.CENTER);
        title.setForeground(UIStyles.NEON_ACCENT);
        title.setFont(UIStyles.BOLD_XLARGE);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        card.add(title, BorderLayout.NORTH);

        String[] columns = {"No.", "Username", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        List<Score> list = ScoreDAO.getLeaderboard();
        int rank = 1;
        for (Score s : list) {
            model.addRow(new Object[]{rank++, s.username, s.score});
        }

        JTable table = new JTable(model);
        table.setFont(UIStyles.REGULAR_MEDIUM);
        table.setRowHeight(32);
        table.setForeground(Color.WHITE);
        table.setBackground(UIStyles.CARD_BG);
        table.setGridColor(UIStyles.NEON_ACCENT);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        TableColumn rankCol = table.getColumnModel().getColumn(0);
        rankCol.setPreferredWidth(40);
        rankCol.setMaxWidth(50);
        rankCol.setMinWidth(40);

        JTableHeader header = table.getTableHeader();
        header.setFont(UIStyles.BOLD_MEDIUM);
        header.setBackground(UIStyles.NEON_ACCENT);
        header.setForeground(Color.BLACK);
        ((DefaultTableCellRenderer) header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setBackground(UIStyles.CARD_BG);

        card.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);

        JButton back = UIStyles.createNeonButton("Back");
        back.addActionListener(e -> mainFrame.showMainMenuView(username));
        bottom.add(back);

        card.add(bottom, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(card, gbc);
    }
}
