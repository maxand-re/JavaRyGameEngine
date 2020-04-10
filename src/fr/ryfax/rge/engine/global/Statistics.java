package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

public class Statistics {

    private Engine engine;

    private int currentFps = 0;
    public static final long timestamp = System.currentTimeMillis();

    public Statistics(Engine engine) { this.engine = engine; }


    /*
     * Setters
     */
    public void setCurrentFps(int currentFps) { this.currentFps = currentFps; }

    /*
     * Getters
     */
    public int getCurrentFps() { return currentFps; }
    public Vector2D getCameraPosition() { return engine.getCamera().getPosition(); }

    public String getElapsedTime() {
        long current = System.currentTimeMillis() - timestamp;

        long s = current/1000, m = 0, h = 0;

        for(long i = s; i >= 60; i -= 60) { m++; s -= 60; }
        for(long i = m; i >= 60; m -= 60) { h++; m -= 60; }

        return Tools.intToDigit(h) + ":" + Tools.intToDigit(m) + ":" + Tools.intToDigit(s);
    }
}
