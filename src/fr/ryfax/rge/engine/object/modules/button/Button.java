package fr.ryfax.rge.engine.object.modules.button;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Button implements VisualGameObject {

    private final Engine engine;
    private final ButtonListener listener;

    private Image sprite, text;
    private Vector2D position = new Vector2D(0, 0);
    private Dimension size    = new Dimension(200, 50);
    private boolean hover = false, click = false;

    public Button(Engine engine, ButtonListener listener) {
        this.engine = engine;
        this.listener = listener;
        listener.init(this);
    }

    public void update(int tick) {
        Point mouse = engine.getMousePosition();

        if(mouse != null) {
            if(mouse.x <= position.x + size.width
                    && mouse.x >= position.x
                    && mouse.y <= position.y + size.height
                    && mouse.y >= position.y){
                if(!hover) {
                    listener.onMouseEntered();
                    hover = true;
                }

                if(!click) {
                    if(engine.getButtonsPressed().contains(1)) {
                        listener.onClick();
                        click = true;
                    }
                }else if(!engine.getButtonsPressed().contains(1)) {
                        click = false;
                }

            }else if(hover) {
                listener.onMouseExited();
                hover = false;
                click = false;
            }
        }
    }

    public void draw(Drawer drawer) {
        drawer.imageNotRelative(sprite, (int) position.x, (int) position.y);
        drawer.imageNotRelative(text,
                (int) position.x + size.width/2 - text.getBufferedImage().getWidth()/2,
                (int) position.y + size.height/2 - text.getBufferedImage().getHeight()/2);
    }


    /*
     * Setters
     */
    public void setPosition(Vector2D position) { this.position = position; }
    public void setSize(Dimension size) { this.size = size; }
    public void setSprite(Image sprite) { this.sprite = sprite; }
    public void setText(Image text) { this.text = text; }

}
