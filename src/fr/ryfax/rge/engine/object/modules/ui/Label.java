package fr.ryfax.rge.engine.object.modules.ui;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Label implements VisualGameObject {

    private Scaler scaler;
    private Image textImg;
    private String text;
    private int fontSize;
    private Font font;

    public Label(Scaler scaler, String text, Font font, int fontSize) {
        this.scaler = scaler;
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        rebuildFont();
    }

    public void init(Engine engine) {}
    public void update(int tick) {}

    public void draw(Drawer drawer) {
        Vector2D position = scaler.getPosition();
        drawer.imageNotRelative(textImg, position.x, position.y);
    }

    private void rebuildFont() {
        FontRenderer fr = new FontRenderer(font);
        fr.setSize(fontSize);
        textImg = fr.build(text);
        scaler.setSize(new Dimension(textImg.getBufferedImage().getWidth(), textImg.getBufferedImage().getHeight()));
    }

    /*
     * Setters
     */
    public void setText(String text) {
        this.text = text;
        rebuildFont();
    }

    public void setFont(Font font) {
        this.font = font;
        rebuildFont();
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        rebuildFont();
    }
}
