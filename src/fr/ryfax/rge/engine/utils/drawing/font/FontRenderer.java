package fr.ryfax.rge.engine.utils.drawing.font;

import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.image.ImageBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FontRenderer {

    private int size = 16;
    private final Font font;

    public FontRenderer(Font font) {
        this.font = font;
    }

    public Image build(String str) {
        int width = str.length()*size;
        int height = size;

        int[] xRemoves = new int[str.length()+1];

        int xRemove = 0;
        for(int s = 0; s < str.length(); s++) {
            char c = str.charAt(s);
            if(font.sizeChars.containsKey(c)) xRemove += font.sizeChars.get(c) + font.defaultSpace;
            else xRemove += font.defaultSpace;
            xRemoves[s+1] = xRemove;
        }

        Image out = ImageBuilder.createBlankImage(width - xRemove, height, true);
        Graphics2D g2d = (Graphics2D) out.getBufferedImage().getGraphics();

        for(int s = 0; s < str.length(); s++) {
            char c = str.charAt(s);
            BufferedImage img = font.getChars().get(c).getBufferedImage();
            g2d.drawImage(img, s*16 - xRemoves[s], 0, null);
        }

        g2d.dispose();

        return out;
    }


    /*
     * Setters
     */
    public void setSize(int size) { this.size = size; }

    /*
     *
     */

    public int getSize() { return size; }
}
