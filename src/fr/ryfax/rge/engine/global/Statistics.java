package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {

    private Engine engine;

    private int currentFps = 0, currentTps = 0, usedRam = 0, totalRam = 0;
    private ArrayList<Integer> averageFps = new ArrayList<>();
    public static final long timestamp = System.currentTimeMillis();

    public Statistics(Engine engine) { this.engine = engine; }


    /*
     * Setters
     */
    public void setCurrentFps(int currentFps) {
        this.currentFps = currentFps;
        this.averageFps.add(currentFps);

        if(this.averageFps.size() > 20)
            this.averageFps.remove(0);
    }
    public void setCurrentTps(int currentTps) { this.currentTps = currentTps; }

    public void setTotalRam(int totalRam) { this.totalRam = totalRam; }
    public void setUsedRam(int usedRam) { this.usedRam = usedRam; }

    /*
     * Getters
     */
    public int getCurrentFps() { return currentFps; }
    public int getCurrentTps() { return currentTps; }

    public int getAverageFps() {
        if(averageFps.size() > 0) {
            int calc = 0;
            for(int n : averageFps) calc += n;
            return calc / averageFps.size();
        }else return 0;
    }

    public int getTotalRam() { return totalRam; }
    public int getUsedRam() { return usedRam; }

    public Vector2D getCameraPosition() { return engine.getSceneManager().getCurrentScene().getCamera().getPosition(); }

    public String getElapsedTime() {
        long current = System.currentTimeMillis() - timestamp;

        long s = current/1000, m = 0, h = 0;

        while(s >= 60) { m++; s -= 60; }
        while(m >= 60) { h++; m -= 60; }

        return Tools.intToDigit(h) + ":" + Tools.intToDigit(m) + ":" + Tools.intToDigit(s);
    }
}
