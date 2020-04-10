package fr.ryfax.rge.engine.utils.drawing;

import fr.ryfax.rge.engine.global.Engine;

import java.awt.*;

/*
 * Upgraded class of Graphics2D
 */
public class Drawer {

    private Engine engine;
    private Graphics2D g2d;

    public Drawer(Engine engine, Graphics2D g2d) {
        this.engine = engine;
        this.g2d = g2d;
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public void fillRect(int x, int y, int width, int height) {
        g2d.fillRect(x - (int) engine.getCamera().getPosition().x, (int) engine.getCamera().getPosition().y + y, width, height);
    }

    public void fillRect(int x, int y, int width, int height, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x - (int) engine.getCamera().getPosition().x, (int) engine.getCamera().getPosition().y + y, width, height);
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     */
    public void fillRectNotRelative(int x, int y, int width, int height) {
        g2d.fillRect(x, y, width, height);
    }

    public void fillRectNotRelative(int x, int y, int width, int height, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }

    /*
     * Draw String at x, y and color.
     * x, y relative to camera.
     * Todo: Change to BufferedImage and not drawString()
     */
    public void text(String string, int x, int y) {
        g2d.drawString(string, x - (int) engine.getCamera().getPosition().x, (int) engine.getCamera().getPosition().y + y);
    }

    public void text(String string, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.drawString(string, x - (int) engine.getCamera().getPosition().x, (int) engine.getCamera().getPosition().y + y);
    }

    /*
     * Draw String at x, y and color.
     * Todo: Change to BufferedImage and not drawString()
     */
    public void textNotRelative(String string, int x, int y) {
        g2d.drawString(string, x, y);
    }

    public void textNotRelative(String string, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.drawString(string, x, y);
    }


    /*
     * Getters
     */
    public Graphics2D getGraphics2D() { return g2d; }
}
