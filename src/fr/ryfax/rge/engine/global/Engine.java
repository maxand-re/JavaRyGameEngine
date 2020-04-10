package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.elements.camera.Camera;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

import java.awt.*;
import java.util.ArrayList;

import static fr.ryfax.rge.engine.utils.Sleep.*;


/*
 * Window of game, "main" of the engine
 */
public class Engine {

    /*
     * Elements
     */
    private Camera camera = new Camera();

    private final Parameters parameters = new Parameters();
    private final Statistics statistics = new Statistics(this);
    private final Logger logger         = new Logger(this);
    private final Window window;

    private boolean isRunning = true;
    private final double UPDATE_OBJECTIVE = 1/60D;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<VisualGameObject> visualGameObjects = new ArrayList<>();


    public Engine(String title, int width, int height) { window = new Window(title, width, height, this); }

    public synchronized void init() { new Thread(this::loop).start(); }

    private synchronized void loop() {
        double firstTime = System.nanoTime() / 1e9;
        double lastTime, currentTime = 0.0;

        boolean render;

        int frameCount = 0, tick = 0;
        double frameTime = 0;

        while(isRunning) {
            render = false;

            lastTime = System.nanoTime() / 1e9;
            currentTime += lastTime - firstTime;
            frameTime += lastTime - firstTime;

            while (currentTime >= UPDATE_OBJECTIVE) {
                currentTime -= UPDATE_OBJECTIVE;
                tick++;
                render = true;
                update(tick);
            }

            if(render || !parameters.isLimitFps()) {
                frameCount++;
                draw();
            }

            if (frameTime >= 1) {
                statistics.setCurrentFps(frameCount);

                frameTime = 0;
                frameCount = 0;
                tick = 0;
            }

            if(parameters.isLimitOverload()) sleep(1);

            firstTime = lastTime;
        }
    }

    private synchronized void draw() {
        GameCanvas canvas = window.getCanvas();
        canvas.ready(); // Prepare the draw

        Drawer drawer = new Drawer(this, canvas.getGraphics());
        visualGameObjects.forEach(visualGameObject -> visualGameObject.draw(drawer));

        canvas.finish(); // Dispose and show
    }

    private synchronized void update(int tick) {
        gameObjects.forEach(gameObject -> gameObject.update(tick));
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);

        if(gameObject instanceof VisualGameObject)
            visualGameObjects.add((VisualGameObject) gameObject);
    }

    /*
     * Getters
     */
    public Parameters getParameters() { return parameters; }
    public Statistics getStatistics() { return statistics; }
    public Logger getLogger()         { return logger; }
    public Camera getCamera() { return camera; }
    public Window getWindow() { return window; }
}
