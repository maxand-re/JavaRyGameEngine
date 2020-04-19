package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.modules.VisualBox;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.utils.Sleep;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Main {

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        Engine engine = new Engine("RGE", 1280, 720);
        Logger log = engine.getLogger();
        SceneBuilder sb = engine.getSceneBuilder();
        SceneManager sm = engine.getSceneManager();

        log.info("Loading...");

        engine.getParameters().setLimitFps(false);
        engine.getParameters().setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM);
        engine.getParameters().setAntiAliasing(false);
        engine.getParameters().setCursor(Parameters.RGE_CURSOR_DEFAULT);
        engine.getParameters().setClearBufferColor(new Color(69, 184, 198));

        Scene loadingScreen = sb.setName("LoadingScreen").build();
        Scene scene2 = sb.setName("Scene2").build();

        loadingScreen.addGameObject(new InformationsPanel(engine), 1000);
        loadingScreen.addGameObject(createEntity(), 0);

        sm.setScene(loadingScreen);

        log.info("Loaded!");
        log.info("Starting...");

        engine.init();

        log.info("Started!");

        new Thread(() -> {
            Sleep.sleep(5000);
            sm.setScene(scene2);
        }).start();
    }

    private static Entity createEntity() {
        Entity entity = new Entity();
        entity.setHeight(150);
        entity.setWidth(50);
        entity.setPosition(new Vector2D(200, 200));
        entity.addModule(new VisualBox(entity), 0);

        return entity;
    }
}
