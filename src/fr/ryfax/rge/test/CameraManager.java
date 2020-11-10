package fr.ryfax.rge.test;

import static fr.ryfax.rge.test.input.KeyBoardManager.*;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

public class CameraManager implements GameObject {

    private Scene gameScene;
    public static final Vector2D cameraPos = new Vector2D(0, 0);
    public static double cameraZoom = 1;

    public void init(Engine engine) {
        gameScene = SceneManager.getCurrentScene();
    }

    public void update(double v, int i) {

        boolean up = pressedKey.containsKey('z') || pressedKey.containsKey('Z');
        boolean down = pressedKey.containsKey('s') || pressedKey.containsKey('S');
        boolean right = pressedKey.containsKey('d') || pressedKey.containsKey('D');
        boolean left = pressedKey.containsKey('q') || pressedKey.containsKey('Q');

        int dir_x = (right ? 1 : 0) - (left ? 1 : 0);
        int dir_y = (down ? 1 : 0) - (up ? 1 : 0);

        cameraPos.x += dir_x * (v * 0.2);
        cameraPos.y += dir_y * (v * 0.2);

        gameScene.getCamera().setPosition(cameraPos);

        boolean zoomIn = pressedKey.containsKey('r') || pressedKey.containsKey('R');
        boolean zoomOut = pressedKey.containsKey('f') || pressedKey.containsKey('F');

        double zoomDir = (zoomIn ? 1 : 0) - (zoomOut ? 1 : 0);

        cameraZoom += zoomDir*(v*0.005);

        gameScene.getCamera().setZoom(cameraZoom);

    }
}
