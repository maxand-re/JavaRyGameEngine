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
    }

    public void init() {
        createBufferStrategy(3);
        bs = getBufferStrategy();
    }

    public void ready() {
        g2d = (Graphics2D) bs.getDrawGraphics();
        g2d.setColor(engine.getParameters().getClearBufferColor());
        g2d.fillRect(0, 0, size.width, size.height);
    }

    public void finish() {
        g2d.dispose();
        bs.show();
    }


    /*
     * Getters
     */
    public int getWidth() { return size.width; }
    public int getHeight() { return size.height; }

    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) bs.getDrawGraphics();
    }
}
