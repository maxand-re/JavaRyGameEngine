package fr.ryfax.rge.test.input;

import fr.ryfax.rge.engine.global.listeners.MouseListener;

import java.awt.*;

public class MouseManager implements MouseListener {

    public static Point point = new Point(0, 0);
    public static boolean leftClicking = false;
    public static boolean rightClicking = false;

    @Override
    public void onButtonPressed(int i) {
        if (i == 1) {
            leftClicking = true;
        } else if (i == 3) {
            rightClicking = true;
        }
    }

    @Override
    public void onButtonReleased(int i) {
        if (i == 1) {
            leftClicking = false;
        } else if (i == 3) {
            rightClicking = false;
        }
    }

    @Override
    public void onMove(Point point) {
        MouseManager.point = point;
    }

}
