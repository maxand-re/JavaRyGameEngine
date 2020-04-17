package fr.ryfax.rge.engine.utils.drawing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Font {


    private BufferedImage img;
    private HashMap<Character, BufferedImage> chars = new HashMap<>();

    public Font(BufferedImage img) {
        this.img = img;

        int c = 0;
        for(int y = 0; y < img.getHeight(); y += 16) {
            for(int x = 0; x < img.getWidth(); x += 16) {
                chars.put((char) c, img.getSubimage(x, y, 16, 16));
                c++;
            }
        }
    }

    public BufferedImage buildText(String text, Color fontColor, Color backColor) {
        BufferedImage out = new BufferedImage(text.length()*16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = out.createGraphics();

        g2d.setColor(backColor);
        g2d.fillRect(0, 0, text.length()*16, 16);

        for(int s = 0; s < text.length(); s++) {
            char c = text.charAt(s);
            g2d.drawImage(chars.get(c), s*16, 0, null);
        }



        g2d.dispose();

        return out;
    }


}
