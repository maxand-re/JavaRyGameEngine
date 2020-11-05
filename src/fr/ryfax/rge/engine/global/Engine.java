package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.global.listeners.KeyboardListener;
import fr.ryfax.rge.engine.global.listeners.MouseListener;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.utils.Logger;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static fr.ryfax.rge.engine.utils.Sleep.sleepMicro;


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
    public double accumulator = 0;

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

        logger.info("RyGameEngine\n\tVersion: " + Statistics.VERSION +
                "\n\tAllocated RAM: " + statistics.getTotalRam() + "Mo");

        if(SceneManager.getCurrentScene() == null)
            logger.warn("No scenes, please set a scene to start correctly");

        new Thread(this::loop).start();
        update(1);
    }

    private synchronized void loop() {
        int frameCount = 0;

        double firstTime, frameTime = 0;
        double lastTime = System.nanoTime() / 1e9;
        double currentTime = 0.0;

        while(isRunning) {
            firstTime = lastTime;
            lastTime = System.nanoTime() / 1e9;
            frameTime += lastTime - firstTime;
            currentTime += lastTime - firstTime;

            if(!pause) {
                update((lastTime - firstTime) * 1000);

                if(parameters.getFPSLimit() == 0) {
                    draw();
                    frameCount++;
                }else if(currentTime >= 1.0/parameters.getFPSLimit()) {
                    currentTime = 0;
                    draw();
                    frameCount++;
                }

                if (frameTime >= 1) {
                    statistics.setCurrentFps(frameCount);

                    frameTime = 0;
                    frameCount = 0;
                }
            }
        }
    }

    private synchronized void update(double delta) {
        accumulator += delta;

        if(accumulator > 1000) accumulator -= 1000;

        int accumulatorInt = (int) Math.floor(accumulator);

        if(getAccumulator() % (1000 / 2) == 0)
            statistics.setUsedRam((int) ((runtime.totalMemory() - runtime.freeMemory())/1024/1024));
        if(getAccumulator() % (1000 / 75) == 0)
            mousePosition = window.getCanvas().getMousePosition();

        window.getMouseEvents().update(delta, accumulatorInt);
        SceneManager.getCurrentScene().update(delta, accumulatorInt);
    }

    private synchronized void draw() {
        GameCanvas canvas = window.getCanvas();

        Drawer drawer = new Drawer(this);
        SceneManager.getCurrentScene().draw(drawer);

        canvas.finish();
    }

    private void loadText() {
        fontLoader.setASCII(new Resource("resource/fonts/ascii.png"));
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
    public int getAccumulator() { return (int) Math.floor(accumulator); }
    public Logger getLogger() { return logger; }
    public Window getWindow() { return window; }

    /*
     * Setters
     */
    public void setPause(boolean pause)     { this.pause = pause; }
    public void setRunning(boolean running) { isRunning = running; }
}
