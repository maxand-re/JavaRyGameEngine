package fr.ryfax.rge.engine.utils.drawing.font;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class FontRenderer {

    private int size = 16;
    private final Font font;

    public FontRenderer(Font font) {
        this.font = font;
    }

    public BufferedImage build(String str) {
        int width = str.length()*size;
        int height = size;

        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) out.getGraphics();

        int xRemove= 0;
        for(int s = 0; s < str.length(); s++) {
            char c = str.charAt(s);

            BufferedImage img = font.getChars().get(c).getBufferedImage();

            g2d.drawImage(img, s*16 - xRemove, 0, null);

            if(font.sizeChars.containsKey(c))
                xRemove += font.sizeChars.get(c) + font.defaultSpace;
            else
                xRemove += font.defaultSpace;
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
