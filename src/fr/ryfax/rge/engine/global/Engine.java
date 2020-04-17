package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.elements.camera.Camera;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeMap;

import static fr.ryfax.rge.engine.utils.Sleep.*;


/*
 * Window of game, "main" of the engine
 */
public class Engine {

    /*
     * Elements
     */
    private Camera camera = new Camera();

    private final Runtime runtime = Runtime.getRuntime();
    private final Parameters parameters = new Parameters();
    private final Statistics statistics = new Statistics(this);
    private final Logger logger         = new Logger(this);
    private final Window window;

    private boolean isRunning = true;
    private final double UPDATE_OBJECTIVE = 1/60D;

    private TreeMap<Integer,  ArrayList<GameObject>> gameObjs = new TreeMap<>();
    private TreeMap<Integer,  ArrayList<VisualGameObject>> visualGameObjs = new TreeMap<>();

    public Engine(String title, int width, int height) { window = new Window(title, width, height, this); }

    public synchronized void init() {

        this.window.getCanvas().setCursor(parameters.getCursor());

        statistics.setTotalRam((int) (runtime.totalMemory()/1024/1024));
        new Thread(this::loop).start();
    }

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
                statistics.setCurrentTps(tick);
                statistics.setUsedRam((int) ((runtime.totalMemory() - runtime.freeMemory())/1024/1024));

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
        visualGameObjs.forEach((z, visualGameObjects) ->
                visualGameObjects.forEach(visualGameObject -> visualGameObject.draw(drawer)));

        canvas.finish(); // Dispose and show
    }

    private synchronized void update(int tick) {
        gameObjs.forEach((z, gameObjects) ->
                gameObjects.forEach(gameObject -> gameObject.update(tick)));
    }

    public void addGameObject(GameObject gameObject, int zindex) {
        boolean isVisualGO = gameObject instanceof VisualGameObject;

        if(gameObjs.containsKey(zindex)) {
            gameObjs.get(zindex).add(gameObject);
            if(isVisualGO) visualGameObjs.get(zindex).add((VisualGameObject) gameObject);
        }else {
            ArrayList<GameObject> list = new ArrayList<>();
            ArrayList<VisualGameObject> list2 = new ArrayList<>();
            list.add(gameObject);
            list2.add((VisualGameObject) gameObject);

            gameObjs.put(zindex, list);
            if(isVisualGO) visualGameObjs.put(zindex, list2);
        }
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
