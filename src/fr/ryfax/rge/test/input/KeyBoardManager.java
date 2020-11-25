package fr.ryfax.rge.test.input;

import fr.ryfax.rge.engine.global.listeners.KeyboardListener;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyBoardManager implements KeyboardListener {

    public static HashMap<Character, KeyEvent> pressedKey = new HashMap<>();

    @Override
    public void onKeyPressed(KeyEvent keyEvent) {
        pressedKey.put(keyEvent.getKeyChar(), keyEvent);
    }

    @Override
    public void onKeyReleased(KeyEvent keyEvent) {
        pressedKey.remove(keyEvent.getKeyChar());
    }
}
