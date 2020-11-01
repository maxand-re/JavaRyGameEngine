package fr.ryfax.rge.game;

import fr.ryfax.rge.engine.camera.Camera;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.modules.VisualBox;
import fr.ryfax.rge.engine.object.modules.DebugTitle;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.object.modules.tilemap.TileMap;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.drawing.scaler.ScalerLayout;
import fr.ryfax.rge.engine.utils.movements.Rotation2D;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine("RyGame", 1280, 720);
        Parameters parameters = engine.getParameters();
        parameters.setClearBufferColor(Color.BLACK);
        parameters.setLimitFps(false);
        parameters.setAntiAliasing(true);
        parameters.setQualityRendering(true);
        parameters.setLimitOverload(0);

        SceneBuilder sb = engine.getSceneBuilder();

        Scene scene = sb.setName("TestingScene").build();
        SceneManager.setScene(scene);

        scene.addGameObject(new DebugTitle(), 0);
        scene.addGameObject(new InformationsPanel(), 9999);

        TileMap tm = new TileMap(new Resource("resource/Tiles.png"), 0, 0, 16, 16);

        tm.setLocation(new Vector2D(250, 250));

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 10; y++) {
                tm.setCell(x, y, new Random().nextInt(6));
            }
        }

        tm.build();

        Entity test = new Entity();
        test.setHeight(32);
        test.setWidth(32);
        test.setPosition(new Vector2D(250, 200));
        test.addModule(new VisualBox(), 1);

        scene.addGameObject(tm, 1);
        scene.addGameObject(test, 1);

        scene.addGameObject(new GameObject() {
            public void init(Engine engine) {

            }

            public void update(int tick) {
                Camera camera = scene.getCamera();
                Rotation2D rota = camera.getRotation();

                Scaler scaler = new Scaler(engine, ScalerLayout.CENTER, new Vector2D(0, 0), new Dimension(50, 250));

                scaler.getPosition();

                rota.setDegree(new Random().nextInt(7) - 3);
                camera.getPosition().x = (new Random().nextInt(51) - 25);
                camera.getPosition().y = (new Random().nextInt(51) - 25);
            }
        }, 0);
        engine.init();
    }

}
