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

    private final Scaler scaler;
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
    public void update(double delta, int accumulator) {}

    public void draw(Drawer drawer) {
        Vector2D position = scaler.getPosition();
        drawer.imageNotRelative(textImg, position);
    }

    private Label rebuildFont() {
        textImg = new FontRenderer(font).setSize(fontSize).build(text);
        scaler.setSize(new Dimension(textImg.getBufferedImage().getWidth(), textImg.getBufferedImage().getHeight()));
        return this;
    }

    /*
     * Setters
     */
    public Label setText(String text) {
        this.text = text;
        return rebuildFont();
    }

    public Label setFont(Font font) {
        this.font = font;
        return rebuildFont();
    }

    public Label setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return rebuildFont();
    }
}
