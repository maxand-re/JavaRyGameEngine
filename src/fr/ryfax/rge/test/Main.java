package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.sounds.SoundManager;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.test.scenes.Introduction;
import fr.ryfax.rge.test.scenes.Menu;

import java.awt.*;

public class Main {

    private static Engine engine;

    public static Engine getInstance() {
        return engine;
    }

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        engine = new Engine("RGE - Example", 1280, 720);

        SceneManager sm = engine.getSceneManager();
        Parameters param = engine.getParameters();

        SoundManager.load("click", "fr/ryfax/rge/assets/sounds/click.wav");
        SoundManager.load("music1", "fr/ryfax/rge/assets/sounds/vlad-gluschenko-everything-you-need-is-by-your-side.wav");

        SoundManager.play("music1", true);

        param.setLimitFps(false);
        param.setAntiAliasing(false);
        param.setQualityRendering(false);
        param.setCursor(Parameters.RGE_CURSOR_DEFAULT);
        param.setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM);
        param.setClearBufferColor(new Color(26, 150, 238));

        // Player player = new Player();

        Menu menu =  new Menu();
        Introduction introduction = new Introduction();

        sm.setScene(introduction.getScene());

        engine.init();
    }

}
