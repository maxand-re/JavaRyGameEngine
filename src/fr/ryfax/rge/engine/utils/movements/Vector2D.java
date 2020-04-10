package fr.ryfax.rge.engine.utils.movements;

public class Vector2D {

    public double x, y;

    /*
     * Vector class for 2D
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y= y;
    }

    /*
     * Add x, y to the current x, y
     */
    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /*
     * Replace x, y by the value
     */
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
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
}
