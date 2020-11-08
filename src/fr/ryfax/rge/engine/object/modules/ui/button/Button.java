package fr.ryfax.rge.engine.object.modules.ui.button;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.collision.CollisionUtils;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Button implements VisualGameObject {

    private final ButtonListener listener;

    private Engine engine;
    private Font font;
    private final String textStr;
    private Image sprite, text;
    private final Scaler scaler;
    private Vector2D position;
    private Dimension size;
    private boolean hover = false, click = false;

    //TODO: Faire les fonts
    public Button(Scaler scaler, Image sprite, String text, ButtonListener listener, Font font) {
        this.listener = listener;
        this.scaler = scaler;
        this.position = scaler.getPosition();
        this.size = scaler.getSize();
        this.sprite = sprite;
        this.textStr = text;

        if(listener != null) listener.init(this);
    }

    public void init(Engine engine) {
        this.engine = engine;

        Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW);
        FontRenderer fr = new FontRenderer(font);

        this.text = fr.build(textStr);
    }

    public void update(double delta, int accumulator) {
        position = scaler.getPosition();

        Point mouse = engine.getMousePosition();

        if(mouse != null) {
            // "If the mouse is on the button"
            if(CollisionUtils.vectorIsWithin(Vector2D.from(mouse), position, size)){
                if(!hover) {
                    listener.onMouseEntered();
                    hover = true;
                }

                if(!click && engine.getButtonsPressed().contains(1)) {
                    listener.onClick();
                    click = true;
                }else if(!engine.getButtonsPressed().contains(1)) {
                    listener.onClickExit();
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
        drawer.imageNotRelative(sprite, position)
                .imageNotRelative(text,
                new Vector2D((int) position.x + size.width/2.f - text.getBufferedImage().getWidth()/2.f,
                (int) position.y + size.height/2.f - text.getBufferedImage().getHeight()/2.f));
    }


    /*
     * Getters
     */
    public String getText() { return textStr; }

    /*
     * Setters
     */
    public Button setPosition(Vector2D position) { this.position = position; return this; }
    public Button setSize(Dimension size) { this.size = size; return this; }
    public Button setSprite(Image sprite) { this.sprite = sprite; return this; }
    public Button setText(Image text) { this.text = text; return this; }

}
