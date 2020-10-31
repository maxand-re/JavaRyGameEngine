package fr.ryfax.rge.game;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.modules.DebugTitle;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.object.modules.tilemap.TileMap;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine("RyGame", 1280, 720);
        Parameters parameters = engine.getParameters();
        parameters.setClearBufferColor(Color.BLACK);
        parameters.setLimitFps(false);
        parameters.setAntiAliasing(false);
        parameters.setQualityRendering(false);
        parameters.setLimitOverload(0);

        SceneBuilder sb = engine.getSceneBuilder();

        Scene scene = sb.setName("TestingScene").build();
        SceneManager.setScene(scene);

        scene.addGameObject(new DebugTitle(), 0);
        scene.addGameObject(new InformationsPanel(), 9999);

        TileMap tm = new TileMap(new Resource("resource/Tiles.png"), 0, 0, 16, 16);

        // G N O
        // 0 1 2
        tm.setLocation(new Vector2D(250, 250));

        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                tm.setCell(x, y, new Random().nextInt(6));
            }
        }

        tm.build();

        scene.addGameObject(tm, 1);

        engine.init();
    }

}
