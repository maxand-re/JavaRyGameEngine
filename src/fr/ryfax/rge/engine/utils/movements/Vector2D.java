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

    public int[] toTableInt() {
        return new int[] {(int) x, (int) y};
    }

    /*
     * Add x, y to the current x, y
     */
    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /*
     * Return distance between this vector and the vec2
     */
    public double distance(Vector2D vec2) {
        return Math.sqrt(Math.pow(vec2.x - this.x, 2) + Math.pow(vec2.y - this.y, 2));
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
