package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageLoader {

    public static ImageIcon loadImage(String path, int w, int h) {
        try {
            BufferedImage img = null;

            InputStream is = ImageLoader.class.getResourceAsStream(path.startsWith("/") ? path : "/" + path);
            if (is != null) {
                img = ImageIO.read(is);
                is.close();
            }

            if (img == null) {
                try {
                    img = ImageIO.read(new java.io.File(path));
                } catch (Exception exFile) {
                }
            }

            if (img == null) {
                ImageIcon icon = new ImageIcon(path);
                Image raw = icon.getImage();
                if (raw != null) {
                    Image scaled = raw.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaled);
                }
            } else {
                Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }

        } catch (Exception e) {
            System.out.println("Image load fail: " + path + " -> " + e.getMessage());
        }

        BufferedImage fallback = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(fallback);
    }
}