package fr.ryfax.rge.engine.utils.movements;

import java.awt.*;
import java.util.Objects;

public class Vector2D {

    public double x, y;

    /*
     * Vector class for 2D
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y= y;
    }

    public static Vector2D from(Point point){ return new Vector2D(point.x, point.y); }
    public static Vector2D from(Dimension dimension){ return new Vector2D(dimension.width, dimension.height); }
    public static Vector2D add(Vector2D x, Vector2D y) { return new Vector2D(x.x + y.x, x.y + y.y);}

    /*
     * Add x, y to the current x, y
     */
    public Vector2D translate(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D translate(Vector2D dest) {
        this.x += dest.x;
        this.y += dest.y;
        return this;
    }

    /*
     * Replace x, y by the value
     */
    public Vector2D setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2D setPosition(Vector2D newPos) {
        this.x = newPos.x;
        this.y = newPos.y;
        return this;
    }

    /*
     * Return distance between this vector and the vec2
     */
    public double distance(Vector2D vec2) {
        return Math.sqrt(Math.pow(vec2.x - this.x, 2) + Math.pow(vec2.y - this.y, 2));
    }

    /*
     * Return string of the Vector2D
     */
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.x, x) == 0 &&
                Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
