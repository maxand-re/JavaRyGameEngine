package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.image.ImageBuilder;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class SplashScreen implements VisualGameObject {

    private final Color background;
    private final Scene sceneAfter;
    private final Image image;
    private Engine engine;

    private double width, height;
    private int screenW;
    private int screenH;
    private int opacity;
    private int sec = 0;

    public void init(Engine engine) {
        this.engine = engine;

        screenW = engine.getWindow().getCanvas().getWidth();
        screenH = engine.getWindow().getCanvas().getHeight();

        width = image.getBufferedImage().getWidth();
        height = image.getBufferedImage().getHeight();
    }

    public SplashScreen(Image image, Color background, Scene sceneAfter) {
        this.background = background;
        this.sceneAfter = sceneAfter;
        this.image = image;
    }

    public void update(int tick) {
        if(tick == 60) sec ++;

        if(sec < 2) {
            opacity++;
            if(opacity > 100) opacity = 100;
        }else if(sec >= 3) {
            opacity--;
            if(opacity < 0) opacity = 0;
            if(sec == 5) SceneManager.setScene(sceneAfter);
        }

        image.opacity(opacity/100f);
    }

    public void draw(Drawer d) {
        d.setColor(background);
        d.fillRectNotRelative(0, 0, screenW, screenH);

        d.getGraphics2D().drawImage(image.getBufferedImage(),
                (int) (screenW / 2 - width / 2),
                (int) (screenH / 2 - height / 2), null);
    }

    /*
     * Setters
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;

        image.resize(width, height);
    }

}
