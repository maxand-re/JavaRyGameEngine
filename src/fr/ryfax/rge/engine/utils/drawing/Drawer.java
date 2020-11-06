package fr.ryfax.rge.engine.utils.drawing;

import fr.ryfax.rge.engine.camera.Camera;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/*
 * Upgraded class of Graphics2D
 */
public class Drawer {

    private final Engine engine;
    private final Camera camera;
    private final Graphics2D g2d;
    private final AffineTransform baseTransform;

    public Drawer(Engine engine) {
        this.engine = engine;
        this.camera = SceneManager.getCurrentScene().getCamera();
        this.g2d = engine.getWindow().getCanvas().getGraphics();

        baseTransform = new AffineTransform();
        baseTransform.rotate(
                -camera.getRotation().getRadians(),
                camera.getRotation().offset.x,
                camera.getRotation().offset.y);

        baseTransform.translate(engine.getWindow().getFrame().getWidth() / 2.0, engine.getWindow().getFrame().getHeight() / 2.0);
        baseTransform.scale(camera.getZoom(), camera.getZoom());


        // Clear Buffer
        g2d.setColor(engine.getParameters().getClearBufferColor());
        g2d.fillRect(0, 0, engine.getWindow().getFrame().getWidth(), engine.getWindow().getFrame().getHeight());

        g2d.setTransform(baseTransform);
    }

    public Drawer setColor(Color color) { g2d.setColor(color); return this; }
    public Drawer setLineWidth(int width) { g2d.setStroke(new BasicStroke(width)); return this; }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public Drawer line(Vector2D from, Vector2D to) {
        Vector2D newFrom = new Vector2D(
                from.x - camera.getPosition().x,
                from.y - camera.getPosition().y);
        Vector2D newTo = new Vector2D(
                camera.getPosition().x + to.x,
                camera.getPosition().y + to.y);

        if(isUselessToDraw(new Vector2D(newFrom.x, newFrom.y), new Dimension((int) newTo.x, (int) newTo.y))) return null;
        g2d.drawLine((int) newFrom.x, (int) newFrom.y, (int) newTo.x, (int) newTo.y);
        return this;
    }

    public Drawer lineNotRelative(Vector2D from, Vector2D to) {
        if(isUselessToDrawNotRelative(from, new Dimension((int) to.x, (int) to.y))) return null;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawLine((int) from.x, (int) from.y, (int) to.x, (int) to.y);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
        return this;
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     * x, y relative to camera.
     */
    public Drawer fillRect(Vector2D pos, Dimension size) {
        int newX = (int) (pos.x - camera.getPosition().x);
        int newY = (int) (pos.y - camera.getPosition().y);
        if(isUselessToDraw(new Vector2D(newX, newY), size)) return null;
        g2d.fillRect((int) (pos.x - camera.getPosition().x), (int) (pos.y - camera.getPosition().y), (int) size.getWidth(), (int) size.getHeight());
        return this;
    }

    public Drawer borderRect(Vector2D pos, Dimension size) {
        int newX = (int) (pos.x - camera.getPosition().x);
        int newY = (int) (pos.y - camera.getPosition().y);
        if(isUselessToDraw(new Vector2D(newX, newY), size)) return null;
        g2d.drawRect((int) (pos.x - camera.getPosition().x), (int) (pos.y - camera.getPosition().y), (int) size.getWidth(), (int) size.getHeight());
        return this;
    }

    /*
     * Draw fill rectangle at x, y with width, height and color.
     */
    public Drawer fillRectNotRelative(Vector2D pos, Dimension size) {
        if(isUselessToDrawNotRelative(pos, size)) return null;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.fillRect((int) pos.x, (int) pos.y, (int) size.getWidth(), (int) size.getHeight());

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
        return this;
    }

    public Drawer fillRectNotRelative(Vector2D pos, Dimension size, Color color) {
        if(isUselessToDrawNotRelative(pos, size)) return null;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.setColor(color);
        g2d.fillRect((int) pos.x, (int) pos.y, (int) size.getWidth(), (int) size.getHeight());

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
        return this;
    }

    /*
     * Draw Image/BufferedImage at x, y
     * x, y relative to camera
     */
    public Drawer image(Image img, Vector2D pos) {
        if(img == null) return null;

        int newX = (int) (pos.x - camera.getPosition().x * camera.getZoom());
        int newY = (int) (pos.y - camera.getPosition().y * camera.getZoom());

        if(isUselessToDraw(pos, new Dimension(img.getWidth(), img.getHeight()))) return null;

        g2d.drawImage(img.getBufferedImage(),
                newX,
                newY, null);
        return this;
    }

    public Drawer image(BufferedImage img, Vector2D pos) {
        if(img == null) return null;

        int newX = (int) (pos.x - camera.getPosition().x);
        int newY = (int) (pos.y - camera.getPosition().y);

        if(isUselessToDraw(pos, new Dimension(img.getWidth(), img.getHeight()))) return null;

        g2d.drawImage(img, newX, newY, null);
        return this;
    }

    /*
     * Draw Image/BufferedImage at x, y
     */
    public Drawer imageNotRelative(Image img, Vector2D pos) {
        if(img == null) return null;
        if(img.getBufferedImage() == null) return null;

        if(isUselessToDrawNotRelative(pos, new Dimension(img.getWidth(), img.getHeight()))) return null;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawImage(img.getBufferedImage(), (int) pos.x , (int) pos.y, null);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
        return this;
    }

    public Drawer imageNotRelative(BufferedImage img, Vector2D pos) {
        if(img == null) return null;
        if(isUselessToDrawNotRelative(pos, new Dimension(img.getWidth(), img.getHeight()))) return null;

        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawImage(img, (int) pos.x , (int) pos.y, null);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
        return this;
    }

    /*
     * Draw String at x, y and color.
     * x, y relative to camera.
     * Todo: Change to Text and not drawString()
     */
    @Deprecated
    public Drawer text(String string, Vector2D pos) {
        g2d.drawString(string, (int) (pos.x - camera.getPosition().x), (int) (pos.y - camera.getPosition().y));
        return this;
    }


    /*
     * Draw String at x, y and color.
     * Todo: Change to Text and not drawString()
     */
    @Deprecated
    public Drawer textNotRelative(String string, Vector2D pos) {
        AffineTransform af = new AffineTransform();
        af.scale(1, 1);
        g2d.setTransform(af);

        g2d.drawString(string, (int) pos.x, (int) pos.y);

        af.scale(camera.getZoom(), camera.getZoom());
        g2d.setTransform(af);
        return this;
    }

    /*
     * TODO:
     *      - Useless for the moment, can be useful for rotating screen isUselessToDraw() method
     *      - https://www.gamedevelopment.blog/collision-detection-circles-rectangles-and-polygons/
     */
    public Point2D[] getPointsAfterTransform(Point2D[] src) {
        Point2D[] dest = new Point2D[src.length];
        baseTransform.transform(src, 0, dest, 0, src.length);
        return dest;
    }

    /*
     * If the object to draw is outside the screen, return false
     */
    public boolean isUselessToDraw(Vector2D pos, Dimension size) {
        Dimension screenSize = new Dimension(
                (int) (engine.getWindow().getFrame().getWidth() / camera.getZoom()),
                (int) (engine.getWindow().getFrame().getHeight() / camera.getZoom()));

        return isOutsideView(pos, size, screenSize);
    }

    public boolean isUselessToDrawNotRelative(Vector2D pos, Dimension size) {
        Dimension screenSize = new Dimension(
                (engine.getWindow().getFrame().getWidth()),
                (engine.getWindow().getFrame().getHeight()));

        return isOutsideView(pos, size, screenSize);
    }

    private boolean isOutsideView(Vector2D pos, Dimension size, Dimension screenSize) {
        double diag = Math.sqrt(Math.pow(screenSize.getWidth(), 2) + Math.pow(screenSize.getHeight(), 2));
        return !((pos.x < diag/2.0) && (pos.x + size.getWidth() > -diag/2.0) && (pos.y < diag/2.0) && (pos.y + size.getHeight() > -diag/2.0));
    }

    /*
     * Getters
     */
    public Graphics2D getGraphics2D() { return g2d; }
    public Engine getEngine() { return engine; }
}
