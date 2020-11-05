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
import fr.ryfax.rge.engine.utils.movements.Rotation2D;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine("RyGame", 1280, 720);
        Parameters parameters = engine.getParameters();
        parameters.setClearBufferColor(Color.BLACK);
        parameters.setFPSLimit(0);
        parameters.setAntiAliasing(true);
        parameters.setQualityRendering(true);

        SceneBuilder sb = engine.getSceneBuilder();

        Scene scene = sb.setName("TestingScene").build();
        SceneManager.setScene(scene);

        //scene.getCamera().setZoom(100);
        scene.getCamera().setPosition(new Vector2D(0, 0));
        //scene.getCamera().getRotation().setDegree(45);
        //scene.getCamera().setRotation(new Rotation2D(45, 0, 0));


        scene.addGameObject(new DebugTitle(), 0);
        scene.addGameObject(new InformationsPanel(), 9999);

        TileMap tm1 = new TileMap(new Resource("resource/Tiles.png"), 0, 0, 16, 16);
        tm1.setLocation(new Vector2D(-500, -500));

        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                tm1.setCell(x, y, new Random().nextInt(5));
            }
        }

        scene.addGameObject(tm1, 1);
        scene.addGameObject(new GameObject() {
            double dir = -0.002;

            Engine engine;

            @Override
            public void init(Engine engine) { this.engine = engine; }

            @Override
            public void update(double delta, int accumulator) {
                if(scene.getCamera().getZoom() < 0.5 || scene.getCamera().getZoom() > 25) dir = -dir;
                scene.getCamera().setZoom(scene.getCamera().getZoom() + dir * delta);
                scene.getCamera().getRotation().addAngle(delta * 0.01);

                //System.out.println(scene.getCamera().getRotation());

                //scene.getCamera().getPosition().translate(-delta/10, 0);
            }
        }, 1);
        engine.init();
    }

}
