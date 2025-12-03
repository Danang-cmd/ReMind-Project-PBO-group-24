package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIStyles {

    public static final Color BACKGROUND = new Color(18, 18, 20);
    public static final Color PANEL_BG = new Color(28, 28, 32);
    public static final Color CARD_BG = new Color(36, 36, 40);

    public static final Color NEON_ACCENT = new Color(57, 255, 200);
    public static final Color NEON_HOVER = new Color(120, 255, 220);
    public static final Color INPUT_BG = new Color(40, 40, 44);

    public static final Font REGULAR_MEDIUM = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font BOLD_MEDIUM = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font BOLD_LARGE = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font BOLD_XLARGE = new Font("Segoe UI", Font.BOLD, 34);

    public static LineBorder neonBorder() {
        return new LineBorder(NEON_ACCENT, 2, true);
    }

    public static LineBorder neonHoverBorder() {
        return new LineBorder(NEON_HOVER, 2, true);
    }

    public static LineBorder inputBorder() {
        return new LineBorder(new Color(90,90,90), 1, true);
    }

    public static LineBorder inputFocusedBorder() {
        return new LineBorder(NEON_ACCENT, 2, true);
    }

    public static JButton createNeonButton(String text) {

        JButton b = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);

                g2.setColor(new Color(0, 0, 0, 35));
                g2.fillRoundRect(2, 3, getWidth() - 4, getHeight() - 4, 22, 22);

                super.paintComponent(g2);
                g2.dispose();
            }
        };

        Color soft = new Color(150, 255, 230);
        Color softHover = new Color(180, 255, 240);

        b.setBackground(soft);
        b.setFont(BOLD_MEDIUM);
        b.setForeground(Color.BLACK);

        b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setOpaque(false);

        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        b.setHorizontalAlignment(SwingConstants.CENTER);
        b.setVerticalAlignment(SwingConstants.CENTER);

        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                b.setBackground(softHover);
                b.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                b.setBackground(soft);
                b.repaint();
            }
        });

        return b;
    }

    public static void styleInputField(JTextField field) {
        field.setFont(REGULAR_MEDIUM);
        field.setForeground(Color.WHITE);
        field.setBackground(INPUT_BG);
        field.setCaretColor(NEON_ACCENT);
        field.setBorder(inputBorder());
        field.setOpaque(true);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                field.setBorder(inputFocusedBorder());
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                field.setBorder(inputBorder());
            }
        });
    }
}
