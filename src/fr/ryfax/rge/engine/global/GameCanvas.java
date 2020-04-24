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

        refreshGraphics();
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
        try { refreshGraphics(); }
        catch (Exception e) { engine.getLogger().warn("Graphics not ready..."); }
        return g2d;
    }

    private void refreshGraphics() {
        g2d = (Graphics2D) bs.getDrawGraphics();

        if(engine.getParameters().isAntiAliasing()) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if(engine.getParameters().isQualityRendering()) {
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        }else {
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        }

        /*
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);*/
    }

    /*
     * Setters
     */

    public void setSize(Dimension size) {
        this.size = size;
        super.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        super.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        super.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
}
