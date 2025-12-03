package model;

import javax.swing.JButton;

public class Card {
    public int id;            
    public JButton button;
    public boolean matched;   
    public int position;      

    public Card(int id, JButton btn, int position) {
        this.id = id;
        this.button = btn;
        this.matched = false;
        this.position = position;
    }
}
