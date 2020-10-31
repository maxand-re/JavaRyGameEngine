package fr.ryfax.rge.game;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.modules.DebugTitle;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine("RyGame", 1280, 720);
        Parameters parameters = engine.getParameters();
        parameters.setLimitFps(false);
        parameters.setAntiAliasing(false);
        parameters.setQualityRendering(false);
        parameters.setLimitOverload(Parameters.RGE_OVERLOAD_LOW);

        SceneBuilder sb = engine.getSceneBuilder();

        Scene scene = sb.setName("TestingScene").build();
        SceneManager.setScene(scene);

        scene.addGameObject(new DebugTitle(), 0);
        scene.addGameObject(new InformationsPanel(), 1);

        engine.init();
    }

}
