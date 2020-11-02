package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.GameObject;
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
        parameters.setFPSLimit(0);
        parameters.setAntiAliasing(false);
        parameters.setQualityRendering(false);

        SceneBuilder sb = engine.getSceneBuilder();

        Scene scene = sb.setName("TestingScene").build();
        SceneManager.setScene(scene);

        scene.addGameObject(new DebugTitle(), 0);
        scene.addGameObject(new InformationsPanel(), 9999);

        TileMap tm1 = new TileMap(new Resource("resource/Tiles.png"), 0, 0, 16, 16);
        TileMap tm2 = new TileMap(new Resource("resource/Tiles.png"), 0, 0, 16, 16);
        tm1.setLocation(new Vector2D(200, 200));
        tm2.setLocation(new Vector2D(200, 200));

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                tm1.setCell(x, y, new Random().nextInt(5));
            }
        }

        tm2.setCell(0, 0, 5);

        scene.addGameObject(tm1, 1);
        scene.addGameObject(tm2, 2);
        engine.init();
    }

}
