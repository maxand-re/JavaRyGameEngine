package fr.ryfax.rge.test.scenes;


import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.image.ImageBuilder;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.modules.SplashScreen;
import fr.ryfax.rge.test.Main;

import java.awt.*;

public class Introduction {

    private final Scene scene;

    public Introduction() {
        Engine engine = Main.getInstance();
        this.scene = engine.getSceneBuilder().setName("intro").build();

        SplashScreen splashScreen = new SplashScreen(new ImageBuilder("fr/ryfax/rge/assets/logos/RyGameEngine.png").build(),
                Color.BLACK, SceneManager.getSceneByName("menu"));

        splashScreen.setSize(512, 512);
        scene.addGameObject(splashScreen, 0);
    }

    /*
     * Getters
     */
    public Scene getScene() { return scene; }
}
