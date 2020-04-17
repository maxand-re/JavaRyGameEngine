package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.utils.drawing.FontLoader;

import java.awt.*;

public class Main {

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        Engine engine = new Engine("RGE", 1280, 720);
        FontLoader fontLoader = new FontLoader();
        Logger log = engine.getLogger();

        log.info("Starting...");

        fontLoader.addFile("main", "fr/ryfax/rge/assets/fonts/ascii.png");

        engine.getParameters().setLimitFps(false);
        engine.getParameters().setLimitOverload(true);
        engine.getParameters().setCursor(Parameters.RGE_HIDE_CURSOR);
        engine.getParameters().setClearBufferColor(new Color(69, 184, 198));

        engine.addGameObject(new InformationsPanel(engine), 1000);

        engine.init();

        log.info("Started!");
    }
}
