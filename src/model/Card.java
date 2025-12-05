package model;

import javax.swing.JButton;
import util.Clickable;

public class Card implements Clickable {

    public int id;
    public JButton button;
    public boolean matched;
    public int position;

    private Runnable clickAction;

    public Card(int id, JButton btn, int position) {
        this.id = id;
        this.button = btn;
        this.position = position;
        this.matched = false;
    }

    public void setClickAction(Runnable action) {
        this.clickAction = action;
        button.addActionListener(e -> onClick());
    }

    @Override
    public void onClick() {
        if (clickAction != null && isEnabled()) {
            clickAction.run();
        }
    }

    @Override
    public boolean isEnabled() {
        return !matched;
    }
}
