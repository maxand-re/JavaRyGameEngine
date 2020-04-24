package fr.ryfax.rge.engine.utils.drawing.font;

import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.image.ImageBuilder;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class FontRenderer {

    private final int defaultSize = 16;
    private int size = defaultSize;
    private final Font font;

    public FontRenderer(Font font) {
        this.font = font;
    }

    public Image build(String str) {
        int[] xRemoves = new int[str.length()+1];

        int xRemove = 0;
        for(int s = 0; s < str.length(); s++) {
            char c = str.charAt(s);
            if(font.sizeChars.containsKey(c)) xRemove += font.sizeChars.get(c) + font.defaultSpace;
            else xRemove += font.defaultSpace;
            xRemoves[s+1] = xRemove;
        }

        int width = str.length()*size - xRemove*(size/defaultSize);
        int height = size;

        Image out = ImageBuilder.createBlankImage(width, height, true);
        Graphics2D g2d = (Graphics2D) out.getBufferedImage().getGraphics();

        AffineTransform transform = new AffineTransform();
        transform.scale((double) size / defaultSize, (double) size / defaultSize);
        g2d.setTransform(transform);

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
