package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

import java.awt.*;

public class Main {

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        Engine engine = new Engine("RGE", 1280, 720);
        Logger log = engine.getLogger();

        log.info("Starting...");

        engine.getParameters().setLimitFps(false);
        engine.getParameters().setLimitOverload(true);
        engine.getParameters().setClearBufferColor(Color.BLACK);

        engine.addGameObject(new InformationsPanel(engine));

        engine.addGameObject(new VisualGameObject() {
            double xDir = 0.7;

            public void draw(Drawer d) {
                d.text("x: 400 y: 400", 400, 390, Color.RED);
                d.fillRect(400, 400, 200, 200, Color.RED);
            }

            public void update(int tick) {
                if(tick == 60) {
                    xDir = -xDir;
                    log.info("Direction revert...");
                }
                engine.getCamera().getPosition().translate(xDir, 0);
            }
        });

        engine.init();

        log.info("Started!");
    }
}
