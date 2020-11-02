package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.drawing.scaler.ScalerLayout;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class SplashScreen implements VisualGameObject {

    private final Color background;
    private final Scene sceneAfter;
    private final Image image;
    private Engine engine;

    private Scaler scaler;
    private int screenW;
    private int screenH;
    private int opacity;
    private int sec = 0;

    public void init(Engine engine) {
        this.engine = engine;

        int width = image.getBufferedImage().getWidth();
        int height = image.getBufferedImage().getHeight();

        scaler = new Scaler(engine);
        scaler.setSize(new Dimension(width, height));
        scaler.setLayout(ScalerLayout.CENTER);

        screenW = engine.getWindow().getCanvas().getWidth();
        screenH = engine.getWindow().getCanvas().getHeight();

    }

    public SplashScreen(Image image, Color background, Scene sceneAfter) {
        this.background = background;
        this.sceneAfter = sceneAfter;
        this.image = image;
    }

    public void update(double delta, int accumulator) {
        if(delta == 60) sec ++;

        if(sec < 2) {
            opacity++;
            if(opacity > 100) opacity = 100;
        }else if(sec >= 3) {
            opacity--;
            if(opacity < 0) opacity = 0;
            if(sec == 5) SceneManager.setScene(sceneAfter);
        }

        screenW = engine.getWindow().getCanvas().getWidth();
        screenH = engine.getWindow().getCanvas().getHeight();

        image.opacity(opacity/100f);
    }

    public void draw(Drawer d) {
        Vector2D position = scaler.getPosition();

        d.setColor(background);
        d.fillRectNotRelative(0, 0, screenW, screenH);

        d.image(image, position.x,position.y);
    }

    /*
     * Setters
     */
    public void setSize(int width, int height) {
        if(engine != null) {
            scaler.setSize(new Dimension(width, height));
        }
        image.resize(width, height);
    }

}
