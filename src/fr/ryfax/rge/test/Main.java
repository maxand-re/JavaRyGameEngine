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

        Parameters param = engine.getParameters();

        SoundManager.load("click", "fr/ryfax/rge/assets/sounds/click.wav");
        SoundManager.load("music1", "fr/ryfax/rge/assets/sounds/vlad-gluschenko-everything-you-need-is-by-your-side.wav");

        SoundManager.setVolume("music1", 0.07f);
        SoundManager.setVolume("click", 0.25f);

        SoundManager.play("music1", true);

        param.setLimitFps(false);
        param.setAntiAliasing(false);
        param.setQualityRendering(false);
        param.setCursor(Parameters.RGE_CURSOR_DEFAULT);
        param.setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM);
        param.setClearBufferColor(new Color(26, 150, 238));

        Menu menu = new Menu();
        Introduction introduction = new Introduction();

        SceneManager.setScene(introduction.getScene());

        engine.init();
    }

}
