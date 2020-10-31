package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.object.GameObject;

import javax.swing.*;

/*
 * Add FPS to the title of the Window
 */
public class DebugTitle implements GameObject {

    private JFrame frame;
    private Statistics statistics;
    private String originalName;

    public void init(Engine engine) {
        this.frame = engine.getWindow().getFrame();
        this.statistics = engine.getStatistics();

        originalName = frame.getTitle();
    }

    public void update(int tick) {
        if(tick == 1) frame.setTitle(originalName + " - " + statistics.getCurrentFps() + "FPS [" + statistics.getElapsedTime() + "]");
    }

    /*
     * Getters
     */
    public String getOriginalName() {
        return originalName;
    }

    /*
     * Setters
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
