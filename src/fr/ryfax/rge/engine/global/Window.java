package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.global.listeners.KeyEvents;
import fr.ryfax.rge.engine.global.listeners.MouseEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/*
 * Window of game, "main" of the engine
 */
public class Window {

    private boolean onDrag = false;
    private int oldX, oldY;
    private Timer timer;

    private final Engine engine;
    private final GameCanvas canvas;
    private final JFrame frame;
    private final MouseEvents me;

    public Window(String title, int width, int height, Engine engine) {
        Dimension size = new Dimension(width, height);
        this.engine = engine;
        this.me = new MouseEvents(engine);

        canvas = new GameCanvas(size, engine);

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(size);
        frame.setResizable(false);

        frame.addKeyListener(new KeyEvents(engine));
        canvas.addKeyListener(new KeyEvents(engine));
        canvas.addMouseListener(me);

        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void setFullScreen() {
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
    }

    public void resize(Dimension size) {
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
    }

    /*
     * Getters
     */
    public MouseEvents getMouseEvents() { return me; }
    public GameCanvas getCanvas() { return canvas; }
    public JFrame getFrame() { return frame; }
}
