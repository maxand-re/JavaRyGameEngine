package fr.ryfax.rge.engine.object.modules.particules;

import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Particule {

    public int size;
    public double life = 100;
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D force;
    public Color color;

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

}
