package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.elements.camera.Camera;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static fr.ryfax.rge.engine.utils.Sleep.*;


/*
 * Window of game, "main" of the engine
 */
public class Engine {

    /*
     * Elements
     */
    private final Runtime runtime = Runtime.getRuntime();
    private final FontLoader fontLoader = new FontLoader();
    private final Parameters parameters = new Parameters();
    private final Statistics statistics = new Statistics(this);
    private final SceneBuilder sceneBuilder = new SceneBuilder(this);
    private final SceneManager sceneManager = new SceneManager(this);
    private final Logger logger = new Logger(this);
    private final Window window;

    private boolean isRunning = true, pause = false;
    private final double UPDATE_OBJECTIVE = 1/60D;

    public Engine(String title, int width, int height) {
        loadText();
        window = new Window(title, width, height, this);
    }

    public synchronized void init() {
        window.getCanvas().init();
        statistics.setTotalRam((int) (runtime.totalMemory()/1024/1024));

        new Thread(this::loop).start();
    }

    private synchronized void loop() {
        int overload = parameters.getLimitOverload();
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

            if(!pause) {

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
            }

            if(overload != 0) sleepMicro(overload);

            firstTime = lastTime;
        }
    }

    private synchronized void update(int tick) {
        sceneManager.getCurrentScene().update(tick);
    }

    private synchronized void draw() {
        GameCanvas canvas = window.getCanvas();
        canvas.ready(); // Prepare the draw

        Drawer drawer = new Drawer(this);
        sceneManager.getCurrentScene().draw(drawer);

        canvas.finish(); // show
    }

    //TODO: Accesible via les scenes uniquement


    private void loadText() {
        fontLoader.load(FontLoader.RGE_DEFAULT_FONT, "fr/ryfax/rge/assets/fonts/ascii.png");
        HashMap<Character, Integer> chars = new HashMap<>();
        chars.put('i', 8);
        chars.put('I', 4);
        chars.put('l', 6);
        chars.put('t', 4);
        chars.put('f', 2);
        chars.put('.', 8);
        chars.put(':', 8);
        chars.put('!', 8);
        fontLoader.setSpecialCharsSize(FontLoader.RGE_DEFAULT_FONT, chars);
    }

    /*
     * Getters
     */
    public SceneBuilder getSceneBuilder() { return sceneBuilder; }
    public SceneManager getSceneManager() { return sceneManager; }
    public Parameters getParameters() { return parameters; }
    public Statistics getStatistics() { return statistics; }
    public FontLoader getFontLoader() { return fontLoader; }
    public Logger getLogger() { return logger; }
    public Window getWindow() { return window; }

    /*
     * Setters
     */
    public void setPause(boolean pause)     { this.pause = pause; }
    public void setRunning(boolean running) { isRunning = running; }
}
