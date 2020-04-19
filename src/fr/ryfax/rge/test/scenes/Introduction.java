package fr.ryfax.rge.test.scenes;


import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.image.ImageBuilder;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.object.modules.SplashScreen;

import java.awt.*;

public class Introduction {

    private Engine engine;
    private Scene scene;

    public Introduction(Engine engine, Scene afterIntro) {
        this.engine = engine;
        this.scene = engine.getSceneBuilder().setName("intro").build();

        SplashScreen splashScreen = new SplashScreen(
                engine, new ImageBuilder("fr/ryfax/rge/assets/logos/RyGameEngine.png").build(),
                Color.BLACK, afterIntro);

        splashScreen.setSize(512, 512);
        scene.addGameObject(splashScreen, 0);
    }

    /*
     * Getters
     */
    public Scene getScene() { return scene; }
}
