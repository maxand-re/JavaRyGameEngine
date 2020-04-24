package fr.ryfax.rge.engine.global;

import fr.ryfax.rge.engine.global.listeners.KeyEvents;
import fr.ryfax.rge.engine.global.listeners.MouseEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Window of game, "main" of the engine
 */
public class Window {

    private boolean onDrag = false;
    private int oldX, oldY;
    private Timer timer;

    private final GameCanvas canvas;
    private final JFrame frame;

    public Window(String title, int width, int height, Engine engine) {
        Dimension size = new Dimension(width, height);
        canvas = new GameCanvas(size, engine);

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(size);
        frame.setResizable(false);

        frame.addKeyListener(new KeyEvents(engine));
        canvas.addKeyListener(new KeyEvents(engine));
        canvas.addMouseListener(new MouseEvents(engine));

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                if(!onDrag) {
                    engine.setPause(true);
                    onDrag = true;
                    timer.start();
                }
            }
        });

        ActionListener al = actionEvent -> {
            if(frame.getX() == oldX && frame.getY() == oldY) {
                engine.setPause(false);
                timer.stop();
                onDrag = false;
            } else {
                oldX = frame.getX();
                oldY = frame.getY();
            }
        };

        timer = new Timer(150, al);

        oldX = frame.getX();
        oldY = frame.getY();

        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void setFullScreen(boolean fullScreen) {
        if(fullScreen) {
            frame.dispose();

            frame.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
            frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
            frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());

            canvas.setSize(Toolkit.getDefaultToolkit().getScreenSize());

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            frame.setVisible(true);
        }
    }

    /*
     * Getters
     */
    public GameCanvas getCanvas() { return canvas; }
    public JFrame getFrame() { return frame; }
}
