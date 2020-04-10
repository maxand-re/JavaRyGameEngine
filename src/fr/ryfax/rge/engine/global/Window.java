package fr.ryfax.rge.engine.global;

import javax.swing.*;
import java.awt.*;

/*
 * Window of game, "main" of the engine
 */
public class Window {

    private final GameCanvas canvas;
    private final JFrame frame;

    public Window(String title, int width, int height, Engine engine) {
        Dimension size = new Dimension(width, height);
        canvas = new GameCanvas(size, engine);

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setPreferredSize(size);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();

        canvas.init();
    }

    /*
     * Getters
     */
    public GameCanvas getCanvas() { return canvas; }
    public JFrame getFrame() { return frame; }
}
