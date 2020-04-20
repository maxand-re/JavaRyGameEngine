package fr.ryfax.rge.engine.utils.drawing;

import fr.ryfax.rge.engine.elements.camera.Camera;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Upgraded class of Graphics2D
 */
public class Drawer {

    private Engine engine;
    private Camera camera;
    private Graphics2D g2d;

    public Drawer(Engine engine) {
        this.engine = engine;
        this.camera = engine.getSceneManager().getCurrentScene().getCamera();
        this.g2d = engine.getWindow().getCanvas().getGraphics();
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }

    public void setLineWidth(int width) {
        g2d.setStroke(new BasicStroke(width));
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public void line(double x, double y, double toX, double toY) {
        g2d.drawLine((int) (x - camera.getPosition().x), (int) (camera.getPosition().y + y), (int) (camera.getPosition().x + toX), (int) (camera.getPosition().y + toY));
    }

    public void lineNotRelative(double x, double y, double toX, double toY) {
        g2d.drawLine((int) x, (int) y, (int) toX, (int) toY);
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public void fillRect(double x, double y, double width, double height) {
        g2d.fillRect((int) (x - camera.getPosition().x), (int) (camera.getPosition().y + y), (int) width, (int) height);
    }

    public void borderRect(double x, double y, double width, double height) {
        g2d.drawRect((int) (x - camera.getPosition().x), (int) (camera.getPosition().y + y), (int) width, (int) height);
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
     * Draw String with a Font at x, y
     * x, y relative to camera
     */
    public void image(Image image, int x, int y) {
        g2d.drawImage(image.getBufferedImage(),
                x - (int) camera.getPosition().x,
                (int) camera.getPosition().y + y, null);
    }

    public void image(Image image, double x, double y) {
        g2d.drawImage(image.getBufferedImage(),
                (int) x - (int) camera.getPosition().x,
                (int) camera.getPosition().y + (int) y, null);
    }

    public void image(BufferedImage img, int x, int y) {
        g2d.drawImage(img,
                x - (int) camera.getPosition().x,
                (int) camera.getPosition().y + y, null);
    }

    public void imageNotRelative(BufferedImage img, int x, int y) {
        g2d.drawImage(img, x , y, null);
    }

    /*
     * Draw String at x, y and color.
     * x, y relative to camera.
     * Todo: Change to Text and not drawString()
     */
    @Deprecated
    public void text(String string, int x, int y) {
        g2d.drawString(string, x - (int) camera.getPosition().x, (int) camera.getPosition().y + y);
    }


    /*
     * Draw String at x, y and color.
     * Todo: Change to Text and not drawString()
     */
    @Deprecated
    public void textNotRelative(String string, int x, int y) {
        g2d.drawString(string, x, y);
    }


    //TODO: add transform, and resetTransform after every draw

    /*
     * Getters
     */
    public Graphics2D getGraphics2D() { return g2d; }
}
