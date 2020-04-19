package fr.ryfax.rge.engine.global;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*
 * Canvas with all properties, and methods to draw the game
 */
public class GameCanvas extends Canvas {

    private Dimension size;
    private Engine engine;

    private BufferStrategy bs;
    private Graphics2D g2d;

    public GameCanvas(Dimension size, Engine engine) {
        this.size = size;
        this.engine = engine;
        setBackground(Color.BLACK);
    }

    public void init() {
        setCursor(engine.getParameters().getCursor());

        createBufferStrategy(3);
        bs = getBufferStrategy();

        getGraphics();
    }

    public void ready() {
        g2d.setColor(engine.getParameters().getClearBufferColor());
        g2d.fillRect(0, 0, size.width, size.height);
    }

    public void finish() {
        bs.show();
    }


    /*
     * Getters
     */
    public int getWidth() { return size.width; }
    public int getHeight() { return size.height; }

    public Graphics2D getGraphics() {
        g2d = (Graphics2D) bs.getDrawGraphics();

        if(engine.getParameters().isAntiAliasing())
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return g2d;
    }
}
