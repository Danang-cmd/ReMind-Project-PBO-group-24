package ui;

import javax.swing.*;

public abstract class BaseFrame extends JPanel {

    protected final LoginFrame mainFrame;
    protected final String username;

    public BaseFrame(LoginFrame mainFrame, String username) {
        this.mainFrame = mainFrame;
        this.username = username;
        initComponents();
    }

    protected abstract void initComponents();
}