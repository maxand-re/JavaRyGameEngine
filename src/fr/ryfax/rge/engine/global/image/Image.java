package fr.ryfax.rge.engine.global.image;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Image {

    private BufferedImage bufferedImage;
    private BufferedImage original;

    private final boolean transparent;

    public Image(BufferedImage bufferedImage, boolean transparent) {
        this.bufferedImage = this.original = bufferedImage;
        this.transparent = transparent;
    }

    public void opacity(float value) { //TODO: Opti peut etre possible si on ne créer pas a chaque fois mais qu'on getGraphics seulement /!\
        BufferedImage out = ImageBuilder.createBlankImage(bufferedImage.getWidth(), bufferedImage.getHeight(), transparent).getBufferedImage(); // chaque create prends du temps

        Graphics2D g2d = (Graphics2D) out.getGraphics();

        Composite old = g2d.getComposite();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, value));
        g2d.drawImage(original, 0, 0, null);
        g2d.setComposite(old);

        bufferedImage = out;
    }

    public void crop(int top, int bottom, int left, int right) {
        BufferedImage out = ImageBuilder.createBlankImage(bufferedImage.getWidth() - left - right, bufferedImage.getHeight() - top - bottom, transparent).getBufferedImage();
        Graphics2D g2d = (Graphics2D) out.getGraphics();

        g2d.drawImage(bufferedImage.getSubimage(left, top, bufferedImage.getWidth() - right, bufferedImage.getHeight() - bottom), 0, 0, null);

        original = bufferedImage = out;
    }

    public void addShadow(int x, int y) {
        BufferedImage shadow = ImageBuilder.createBlankImage(bufferedImage.getWidth(), bufferedImage.getHeight(), transparent).getBufferedImage();
        Graphics2D g2d = (Graphics2D) shadow.getGraphics();
        RescaleOp op = new RescaleOp(new float[]{0, 0, 0, 1}, new float[4], null);

        g2d.drawImage(bufferedImage, op, x, y);
        g2d.drawImage(bufferedImage, 0, 0, null);

        original = bufferedImage = shadow;
    }

    public void changeColor(Color color) {
        BufferedImage out = ImageBuilder.createBlankImage(bufferedImage.getWidth(), bufferedImage.getHeight(), transparent).getBufferedImage();
        Graphics2D g2d = (Graphics2D) out.getGraphics();

        RescaleOp op;

        if(transparent)
            op = new RescaleOp(
                new float[]{color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, color.getAlpha()/255f},
                new float[4], null);
        else
            op = new RescaleOp(
                    new float[]{color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f},
                    new float[3], null);

        g2d.drawImage(bufferedImage, op, 0, 0);

        bufferedImage = out;
    }

    public void addBackgroundColor(Color color) {
        BufferedImage out = ImageBuilder.createBlankImage(bufferedImage.getWidth(), bufferedImage.getHeight(), transparent).getBufferedImage();
        Graphics2D g2d = (Graphics2D) out.getGraphics();

        g2d.setColor(color);
        g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g2d.drawImage(bufferedImage, 0, 0, null);

        bufferedImage = out;
    }

    public void resize(int width, int height) {
        BufferedImage resized = ImageBuilder.createBlankImage(width, height, transparent).getBufferedImage();
        Graphics2D g2d = (Graphics2D) resized.getGraphics();

        AffineTransform af = new AffineTransform();
        af.scale((double) width / original.getWidth(), (double) height / original.getHeight());

        g2d.setTransform(af);
        g2d.drawImage(original, 0, 0, null);

        original = bufferedImage = resized;
    }

    public BufferedImage getBufferedImage() { return bufferedImage; }
}
