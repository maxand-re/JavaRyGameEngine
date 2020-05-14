package fr.ryfax.rge.engine.global;


import fr.ryfax.rge.engine.camera.Camera;
import fr.ryfax.rge.engine.global.listeners.KeyboardListener;
import fr.ryfax.rge.engine.global.listeners.MouseListener;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.path.Resource;
import fr.ryfax.rge.engine.utils.path.PathType;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

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
    private final Logger logger = new Logger(this);
    private final Window window;

    private final ArrayList<KeyboardListener> keyboardListeners = new ArrayList<>();
    private final ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private final ArrayList<Integer> buttonsPressed = new ArrayList<>();

    private Point mousePosition = null;
    private boolean isRunning = true, pause = false;
    private final double UPDATE_OBJECTIVE = 1/60D;

    public Engine(String title, int width, int height) {
        loadText();
        window = new Window(title, width, height, this);
    }

    public Engine(String title, int width, int height, boolean fullscreen) {
        this(title, width, height);
        if(fullscreen) window.setFullScreen();
    }

    public synchronized void init() {
        window.getCanvas().init();
        statistics.setTotalRam((int) (runtime.totalMemory()/1024/1024));

        new Thread(this::loop).start();
        update(0);
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
        mousePosition = window.getCanvas().getMousePosition();

        window.getMouseEvents().update(tick);
        SceneManager.getCurrentScene().update(tick);
    }

    private synchronized void draw() {
        GameCanvas canvas = window.getCanvas();
        Camera camera = SceneManager.getCurrentScene().getCamera();
        canvas.ready(); // Prepare the draw

        Drawer drawer = new Drawer(this);
        SceneManager.getCurrentScene().draw(drawer);

        canvas.finish(); // show
    }

    private void loadText() {
        fontLoader.setASCII(new Resource("resource/fonts/ascii.png", PathType.OUTSIDE));
        fontLoader.setFontColor(new Color(255, 255, 255));

        HashMap<Character, Integer> chars = new HashMap<>();
        chars.put('i', 8);
        chars.put('I', 4);
        chars.put('l', 6);
        chars.put('t', 4);
        chars.put('f', 2);
        chars.put('.', 8);
        chars.put(':', 8);
        chars.put('!', 8);

        fontLoader.setSpacingChar(4);
        fontLoader.setSpecialCharsSize(chars);
        fontLoader.load(FontLoader.RGE_DEFAULT);

        fontLoader.setShadow(2, 2);
        fontLoader.load(FontLoader.RGE_SHADOW);

        fontLoader.setBackgroundColor(new Color(0, 0, 0, 100));
        fontLoader.load(FontLoader.RGE_SHADOW_BACKGROUND);

        fontLoader.setShadow(0, 0);
        fontLoader.load(FontLoader.RGE_DEFAULT_BACKGROUND);
    }

    public void addListener(KeyboardListener keyboardListener) { keyboardListeners.add(keyboardListener); }
    public void addListener(MouseListener mouseListener) { mouseListeners.add(mouseListener); }

    /*
     * Getters
     */
    public ArrayList<KeyboardListener> getKeyboardListeners() { return keyboardListeners; }
    public ArrayList<MouseListener> getMouseListeners() { return mouseListeners; }
    public ArrayList<Integer> getButtonsPressed() { return buttonsPressed; }
    public SceneBuilder getSceneBuilder() { return sceneBuilder; }
    public Point getMousePosition() { return mousePosition; }
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
