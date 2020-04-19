package fr.ryfax.rge.engine.utils.drawing.font;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class FontRenderer {

    private Color fontColor = new Color(0x000000), backColor = new Color(0x00000000, true);
    private int size = 16;
    private final Font font;

    public FontRenderer(Font font) {
        this.font = font;
    }

    public BufferedImage build(String str) {
        int width = str.length()*size;
        int height = size;

        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = out.createGraphics();


        RescaleOp op = new RescaleOp(
                new float[]{fontColor.getRed()/255f, fontColor.getGreen()/255f, fontColor.getBlue()/255f, fontColor.getAlpha()/255f},
                new float[4],
                null);

        g2d.setColor(backColor);

        AffineTransform af = g2d.getTransform();
        af.scale(size / 16d, size / 16d);
        g2d.setTransform(af);

        int sizeRem = 0;
        for(int s = 0; s < str.length(); s++) {
            char c = str.charAt(s);

            int toRemove = 0;

            if(font.getSizeChars().containsKey(c))
                toRemove += font.getSizeChars().get(c);

            toRemove += 5;

            g2d.fillRect(s*16 - sizeRem, 0, 16-toRemove, 16);
            g2d.drawImage(font.getChars().get(c), op, s*16 - sizeRem, 0);

            sizeRem += toRemove;
        }

        g2d.dispose();

        return out;
    }


    /*
     * Setters
     */
    public void setFontColor(Color color) { fontColor = color; }
    public void setBackgroundColor(Color color) { backColor = color; }
    public void setSize(int size) { this.size = size; }

    /*
     *
     */

    public int getSize() { return size; }
}
