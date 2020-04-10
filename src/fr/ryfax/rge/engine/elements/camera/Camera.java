package fr.ryfax.rge.engine.elements.camera;

import fr.ryfax.rge.engine.utils.movements.Vector2D;

public class Camera {

    private Vector2D position = new Vector2D(0, 0);

    /*
     * Setters
     */
    public void setPosition(Vector2D position) { this.position = position; }

    /*
     * Getters
     */
    public Vector2D getPosition() { return position; }
}
