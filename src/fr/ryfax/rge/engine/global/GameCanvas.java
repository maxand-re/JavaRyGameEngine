package fr.ryfax.rge.engine.global;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*
 * Canvas with all properties, and methods to draw the game
 */
public class GameCanvas extends Canvas {

    private Dimension size;
    private final Engine engine;

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

    public void finish() {
        bs.show();
    }

    /*
     * Getters
     */
    public Dimension getSize() { return size; }

    public Graphics2D getGraphics() {
        try { refreshGraphics(); } catch (Exception ignore) {}
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
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        }else {
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        }
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
