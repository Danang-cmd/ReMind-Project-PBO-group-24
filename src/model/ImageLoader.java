package util;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {

    public static ImageIcon loadImage(String path, int w, int h) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}