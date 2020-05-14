package fr.ryfax.rge.engine.global.listeners;

import java.awt.*;

public interface MouseListener {

    void onButtonPressed(int button);
    void onButtonReleased(int button);
    void onMove(Point mousePos);

}
