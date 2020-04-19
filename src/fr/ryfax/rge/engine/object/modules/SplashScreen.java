package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.image.ImageBuilder;
import fr.ryfax.rge.engine.global.scenes.Scene;
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

    Color background;
    Engine engine;
    Scene sceneAfter;
    Image image;

    double width, height;

    int screenW, screenH;

    int tick,opacity;
    int sec = 0;

    RescaleOp op;

    /*
     * Methods
     */
    public SplashScreen(Engine engine, Image image, Color background, Scene sceneAfter) {
        this.engine = engine;
        this.background = background;
        this.sceneAfter = sceneAfter;
        this.image = image;

        screenW = engine.getWindow().getCanvas().getWidth();
        screenH = engine.getWindow().getCanvas().getHeight();

        width = image.getBufferedImage().getWidth();
        height = image.getBufferedImage().getHeight();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;

        image.resize(width, height);
    }

    public void update(int tick) {
        if(tick == 60) sec ++;
        this.tick = tick;

        if(sec < 2) {
            opacity++;
            if(opacity > 100) opacity = 100;
        }else if(sec >= 3) {
            opacity--;
            if(opacity < 0) opacity = 0;
            if(sec == 5) engine.getSceneManager().setScene(sceneAfter);
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

}
