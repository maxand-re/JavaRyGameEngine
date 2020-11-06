package fr.ryfax.rge.engine.object.modules.particules;

import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Particule {

    private int size;
    private double life = 100;
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D force;
    private Color color;

    public Particule(int size, Vector2D position, Vector2D velocity, Vector2D force, Color color) {
        this(size, position, velocity, color);
        this.force = force;
    }

    public Particule(int size, Vector2D position, Vector2D velocity, Color color) {
        this.size = size;
        this.position = position;
        this.velocity = velocity;
        this.color = color;
        this.force = new Vector2D(0, 0);
    }

    public void update(double delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;

        velocity.x += force.x * delta;
        velocity.y += force.y * delta;

        life -= 0.1 * delta;
    }

    public Color getColor() { return color; }
    public double getLife() { return life; }
    public Vector2D getPosition() { return position; }
    public int getSize() { return size; }

    public Particule setColor(Color color) { this.color = color; return this; }
}
