package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.sounds.SoundManager;
import fr.ryfax.rge.engine.object.modules.ui.button.Button;
import fr.ryfax.rge.engine.object.modules.ui.button.ButtonListener;

import java.util.ArrayList;

public class ButtonEvents implements ButtonListener {

    private Button button;
    private ArrayList<Image> sprites;

    public ButtonEvents(ArrayList<Image> sprites) {
        this.sprites = sprites;
    }

    public void init(Button button) {
        this.button = button;
    }

    public void onMouseEntered() {
        button.setSprite(sprites.get(1));
    }

    public void onMouseExited() {
        button.setSprite(sprites.get(0));
    }

    public void onClick() {
        button.setSprite(sprites.get(2)); }

    public void onClickExit() {
        SoundManager.play("click");
        button.setSprite(sprites.get(1));

        if(button.getText().equals("Quit")) {
            System.exit(0);
        }
    }

}
