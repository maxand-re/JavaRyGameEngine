package fr.ryfax.rge.engine.global.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageBuilder {

    private Image image;

    public ImageBuilder(String path) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(cl.getResourceAsStream(path)));
            BufferedImage compatImg = config.createCompatibleImage(img.getWidth(), img.getHeight(), Transparency.TRANSLUCENT);

            Graphics g = compatImg.getGraphics();
            g.drawImage(img, 0, 0, null);

            image = new Image(compatImg, true);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public ImageBuilder(BufferedImage image) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        BufferedImage compatImg = config.createCompatibleImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);

        Graphics g = compatImg.getGraphics();
        g.drawImage(image, 0, 0, null);

        this.image = new Image(compatImg, true);
    }

    public static Image createBlankImage(int width, int height, boolean transparent) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();

        if(transparent)
            return new Image(config.createCompatibleImage(width, height, Transparency.TRANSLUCENT), true);
        else
            return new Image(config.createCompatibleImage(width, height, Transparency.OPAQUE), false);

    }

    public Image build() {
        return image;
    }

}
