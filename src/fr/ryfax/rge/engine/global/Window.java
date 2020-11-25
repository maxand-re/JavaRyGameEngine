package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.global.listeners.KeyEvents;
import fr.ryfax.rge.engine.global.listeners.MouseEvents;

import javax.swing.*;
import java.awt.*;

/*
 * Window of game, "main" of the engine
 */
public class Window {

    private final Engine engine;
    private final GameCanvas canvas;
    private final JFrame frame;
    private final MouseEvents mouseEvents;

    public Window(String title, Dimension size, Engine engine) {
        this.engine = engine;
        this.mouseEvents = new MouseEvents(engine);

        canvas = new GameCanvas(size, engine);

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(size);
        frame.setResizable(false);

        frame.addKeyListener(new KeyEvents(engine));
        canvas.addKeyListener(new KeyEvents(engine));
        canvas.addMouseListener(mouseEvents);

        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public Window setFullScreen() {
        engine.setPause(true);
        frame.dispose();

        frame.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());

        canvas.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        engine.setPause(false);
        return this;
    }

    public Window resize(Dimension size) {
        engine.setPause(true);
        frame.dispose();

        frame.setMaximumSize(size);
        frame.setSize(size);
        frame.setMinimumSize(size);

        canvas.setSize(size);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);
        engine.setPause(false);
        return this;
    }

    /*
     * Getters
     */
    public MouseEvents getMouseEvents() { return mouseEvents; }
    public GameCanvas getCanvas() { return canvas; }
    public JFrame getFrame() { return frame; }
}
