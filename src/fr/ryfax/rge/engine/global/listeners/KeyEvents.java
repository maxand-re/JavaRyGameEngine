package fr.ryfax.rge.engine.global.listeners;

import fr.ryfax.rge.engine.global.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyEvents implements KeyListener {

    private final Engine engine;

    private final ArrayList<Integer> keysPressed = new ArrayList<>();

    public KeyEvents(Engine engine) {
        this.engine = engine;
    }

    public void keyTyped(KeyEvent keyEvent) {}

    public void keyPressed(KeyEvent keyEvent) {
        if(!keysPressed.contains(keyEvent.getKeyCode())) {
            engine.getKeyboardListeners().forEach(list -> list.onKeyPressed(keyEvent));
            keysPressed.add(keyEvent.getKeyCode());
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        keysPressed.remove((Object) keyEvent.getKeyCode());
        engine.getKeyboardListeners().forEach(list -> list.onKeyReleased(keyEvent));
    }

}
