package fr.ryfax.rge.engine.camera;

import fr.ryfax.rge.engine.utils.movements.Vector2D;

public class Camera {

    private Vector2D position = new Vector2D(0, 0);
    private double zoom = 1;

    /*
     * Setters
     */
    public void setPosition(Vector2D position) { this.position = position; }
    public void setZoom(double zoom) { this.zoom = zoom; }

    /*
     * Getters
     */
    public Vector2D getPosition() { return position; }
    public double getZoom() { return zoom; }
}
