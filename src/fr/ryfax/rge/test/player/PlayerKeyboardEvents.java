package fr.ryfax.rge.test.player;

import fr.ryfax.rge.engine.global.listeners.KeyboardListener;

import java.awt.event.KeyEvent;

public class PlayerKeyboardEvents implements KeyboardListener {

    private Player player;

    public PlayerKeyboardEvents(Player player) {
        this.player = player;
    }

    public void onKeyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case 90: //Z
                player.playAnimation(PlayerAnimation.UP);
                break;
            case 81: //Q
                player.playAnimation(PlayerAnimation.LEFT);
                break;
            case 83: //S
                player.playAnimation(PlayerAnimation.DOWN);
                break;
            case 68: //D
                player.playAnimation(PlayerAnimation.RIGHT);
                break;
        }
    }

    public void onKeyReleased(KeyEvent key) {}
}
