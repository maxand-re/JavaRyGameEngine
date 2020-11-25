package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.object.modules.particules.Particules;
import fr.ryfax.rge.engine.object.modules.particules.emitters.FireEmitter;
import fr.ryfax.rge.engine.object.modules.timer.Timer;
import fr.ryfax.rge.engine.object.modules.timer.TimerRunnable;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.test.input.KeyBoardManager;
import fr.ryfax.rge.test.input.MouseManager;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine("RyGame", new Dimension(1280, 720));
        engine.getParameters().setClearBufferColor(Color.BLACK).setFPSLimit(60).setAntiAliasing(true).setQualityRendering(true);

        engine.addListener(new MouseManager()).addListener(new KeyBoardManager());

        SceneBuilder sb = engine.getSceneBuilder();
        Scene labScene = sb.setName("RGELab").build();
        SceneManager.setScene(labScene);

        labScene/*.addGameObject(new Particules(new FireEmitter(new Vector2D(0, 0))), 1)
                .addGameObject(new Particules(new FireEmitter(new Vector2D(50, 0))), 1)
                .addGameObject(new Particules(new FireEmitter(new Vector2D(100, 0))), 1)
                .addGameObject(new Particules(new FireEmitter(new Vector2D(-50, 0))), 1)
                .addGameObject(new Particules(new FireEmitter(new Vector2D(-100, 0))), 1)
                .addGameObject(new GameObject() {
                    double zDir = -0.001, z = 0;

                    public void init(Engine engine) { }

                    public void update(double delta, int accumulator) {
                        z += zDir * (delta * 2);
                        labScene.getCamera().setZoom(z);
                        labScene.getCamera().getRotation().addAngle(delta / 20);

                        if(z > 10) zDir = -0.001;
                        if(z < 0.1) zDir = 0.001;
                    }
                }, 2)*/
                .addGameObject(new VisualGameObject() {
                    @Override
                    public void draw(Drawer drawer) {
                        drawer.setColor(Color.WHITE);
                        for (int i = 0; i < 80; i++) {
                            drawer.fillRect(new Vector2D(i * 40 + i, 0), new Dimension(40, 40));
                        }
                    }

                    @Override
                    public void init(Engine engine) {

                    }

                    @Override
                    public void update(double delta, int accumulator) {

                    }
                }, 10)
                .addGameObject(new InformationsPanel(), 10000)
                .addGameObject(new CameraManager(), 0);

        Timer timer = new Timer(new TimerRunnable() {
            @Override
            public void run() {
                System.out.println("after 3 second");
            }
        });

        labScene.addGameObject(timer, 0);

        timer.start(3);

        engine.init();
    }

}
