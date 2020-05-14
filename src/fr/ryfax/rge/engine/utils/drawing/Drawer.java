package fr.ryfax.rge.engine.utils.drawing;

import fr.ryfax.rge.engine.camera.Camera;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.global.scenes.SceneManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/*
 * Upgraded class of Graphics2D
 */
public class Drawer {

    /*
     * Todo: Refaire les position avec un affineTransform et translate
     */

    private Engine engine;
    private Camera camera;
    private Graphics2D g2d;

    public Drawer(Engine engine) {
        this.engine = engine;
        this.camera = SceneManager.getCurrentScene().getCamera();
        this.g2d = engine.getWindow().getCanvas().getGraphics();

        AffineTransform af = new AffineTransform();
        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }

    public void setColor(Color color) { g2d.setColor(color); }
    public void setLineWidth(int width) { g2d.setStroke(new BasicStroke(width)); }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public void line(double x, double y, double toX, double toY) {
        int newX = (int) (x - camera.getPosition().x);
        int newY = (int) (y - camera.getPosition().y);
        int newToX = (int) (camera.getPosition().x + toX);
        int newToY = (int) (camera.getPosition().y + toY);

        if(!isNeedToBeDraw(newX, newY, newToX, newToY)) return;
        g2d.drawLine(newX, newY, newToX, newToY);
    }

    public void lineNotRelative(double x, double y, double toX, double toY) {
        if(!isNeedToBeDraw(x, y, toX, toY)) return;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawLine((int) x, (int) y, (int) toX, (int) toY);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public void fillRect(double x, double y, double width, double height) {
        int newX = (int) (x - camera.getPosition().x);
        int newY = (int) (y - camera.getPosition().y);
        if(!isNeedToBeDraw(newX, newY, width, height)) return;
        g2d.fillRect((int) (x - camera.getPosition().x), (int) (y - camera.getPosition().y), (int) width, (int) height);
    }

    public void borderRect(double x, double y, double width, double height) {
        int newX = (int) (x - camera.getPosition().x);
        int newY = (int) (y - camera.getPosition().y);
        if(!isNeedToBeDraw(newX, newY, width, height)) return;
        g2d.drawRect((int) (x - camera.getPosition().x), (int) (y - camera.getPosition().y), (int) width, (int) height);
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     */
    public void fillRectNotRelative(int x, int y, int width, int height) {
        if(!isNeedToBeDraw(x, y, width, height)) return;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.fillRect(x, y, width, height);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }

    public void fillRectNotRelative(int x, int y, int width, int height, Color color) {
        if(!isNeedToBeDraw(x, y, width, height)) return;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }

    /*
     * Draw Image/BufferedImage at x, y
     * x, y relative to camera
     */
    public void image(Image img, double x, double y) {
        if(img == null) return;

        int newX = (int) (x - camera.getPosition().x);
        int newY = (int) (y - camera.getPosition().y);

        if(!isNeedToBeDraw(newX, newY, img.getBufferedImage().getWidth(), img.getBufferedImage().getHeight())) return;

        g2d.drawImage(img.getBufferedImage(),
                newX,
                newY, null);
    }

    public void image(BufferedImage img, double x, double y) {
        if(img == null) return;

        int newX = (int) (x - camera.getPosition().x);
        int newY = (int) (y - camera.getPosition().y);

        if(!isNeedToBeDraw(newX, newY, img.getWidth(), img.getHeight())) return;

        g2d.drawImage(img,
                (int) (x - camera.getPosition().x),
                (int) (y - camera.getPosition().y), null);
    }

    /*
     * Draw Image/BufferedImage at x, y
     */
    public void imageNotRelative(Image img, double x, double y) {
        if(img == null) return;
        if(img.getBufferedImage() == null) return;
        if(!isNeedToBeDraw(x, y, img.getBufferedImage().getWidth(), img.getBufferedImage().getHeight())) return;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawImage(img.getBufferedImage(), (int) x , (int) y, null);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }

    public void imageNotRelative(BufferedImage img, int x, int y) {
        if(img == null) return;
        if(!isNeedToBeDraw(x, y, img.getWidth(), img.getHeight())) return;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawImage(img, x , y, null);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }

    /*
     * Draw String at x, y and color.
     * x, y relative to camera.
     * Todo: Change to Text and not drawString()
     */
    @Deprecated
    public void text(String string, int x, int y) {
        g2d.drawString(string, (int) (x - camera.getPosition().x), (int) (y - camera.getPosition().y));
    }


    /*
     * Draw String at x, y and color.
     * Todo: Change to Text and not drawString()
     */
    @Deprecated
    public void textNotRelative(String string, int x, int y) {
        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawString(string, x, y);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
    }


    //TODO: add transform, and resetTransform after every draw

    /*
     * If the object to draw is outside the screen, return false
     */
    private boolean isNeedToBeDraw(double x, double y, double width, double height) {
        int screenW = engine.getWindow().getCanvas().getWidth();
        int screenH = engine.getWindow().getCanvas().getHeight();

        return (x < screenW && x + width > 0 && y < screenH && y + height > 0);
    }

    /*
     * Getters
     */
    public Graphics2D getGraphics2D() { return g2d; }
}
