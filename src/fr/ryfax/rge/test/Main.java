package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.test.scenes.Introduction;

import java.awt.*;
import java.io.IOException;

public class Main {

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        Engine engine = new Engine("RGE - Example", 1280, 720);
        SceneManager sm = engine.getSceneManager();
        Parameters param = engine.getParameters();

        Scene menu = engine.getSceneBuilder().setName("menu").build();
        Scene intro = new Introduction(engine, menu).getScene();

        param.setLimitFps(false);
        param.setLimitOverload(Parameters.RGE_OVERLOAD_LOW);
        param.setAntiAliasing(false);
        param.setQualityRendering(false);
        param.setCursor(Parameters.RGE_CURSOR_DEFAULT);
        param.setClearBufferColor(new Color(69, 184, 198));

        menu.addGameObject(new InformationsPanel(engine), 1000);

        sm.setScene(intro);
        engine.init();
    }

}
