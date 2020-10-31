package fr.ryfax.rge.engine.global.listeners;

import java.awt.event.KeyEvent;

public interface KeyboardListener {

    void onKeyPressed(KeyEvent key);
    void onKeyReleased(KeyEvent key);

}
