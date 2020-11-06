package fr.ryfax.rge.engine.utils.drawing.scaler;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.GameCanvas;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Scaler {

    private final GameCanvas canvas;
    private Vector2D position = new Vector2D(0, 0);
    private Dimension size;
    private ScalerLayout layout = null;

    public Scaler(Engine engine) {
        canvas = engine.getWindow().getCanvas();
    }

    public Scaler(Engine engine, ScalerLayout layout, Vector2D position, Dimension size) {
        canvas = engine.getWindow().getCanvas();
        this.position = position;
        this.layout = layout;
        this.size = size;
    }

    /*
     * Setters
     */
    public Scaler setPosition(Vector2D position) { this.position = position; return this; }
    public Scaler setLayout(ScalerLayout layout) { this.layout = layout; return this; }
    public Scaler setSize(Dimension size) { this.size = size; return this; }

    /*
     * Getters
     */
    public Dimension getSize() { return size; }

    public Vector2D getRealPosition() { return position; }
    public Vector2D getPosition() {
        return switch (layout) {
            case CENTER -> new Vector2D(canvas.getWidth() / 2d + position.x - size.width / 2d, canvas.getHeight() / 2d + position.y - size.height / 2d);
            case TOP_RIGHT -> new Vector2D(canvas.getWidth() + position.x - size.width, position.y);
            case TOP_LEFT -> position;
            case TOP_MIDDLE -> new Vector2D(canvas.getWidth() / 2d + position.x - size.width / 2d, position.y);
            case MIDDLE_RIGHT -> new Vector2D(canvas.getWidth() + position.x - size.width, canvas.getHeight() / 2d + position.y - size.height / 2d);
            case MIDDLE_LEFT -> new Vector2D(position.x, canvas.getHeight() / 2d + position.y - size.height / 2d);
            case BOTTOM_RIGHT -> new Vector2D(canvas.getWidth() + position.x - size.width, canvas.getHeight() - size.height + position.y);
            case BOTTOM_LEFT -> new Vector2D(position.x, canvas.getHeight() - size.height + position.y);
            case BOTTOM_MIDDLE -> new Vector2D(canvas.getWidth() / 2d + position.x - size.width / 2d, canvas.getHeight() - size.height + position.y);
        };
    }
}
