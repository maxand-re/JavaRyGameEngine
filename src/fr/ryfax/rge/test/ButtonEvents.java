package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.object.modules.button.Button;
import fr.ryfax.rge.engine.object.modules.button.ButtonListener;

import java.awt.*;
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
        System.out.println("Click !");
    }

}
