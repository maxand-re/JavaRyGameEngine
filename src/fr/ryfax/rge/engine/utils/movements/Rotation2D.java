package fr.ryfax.rge.engine.utils.movements;

public class Rotation2D {

    public double degree;
    public Vector2D offset;

    /*
     * Rotation class for 2D
     */
    public Rotation2D(double degree, double x_offset, double y_offset) {
        this.setAngle(degree);
        this.offset = new Vector2D(x_offset, y_offset);
    }

    /*
     * Add degrees to the actual angle
     */
    public void setAngle(double degree) {
        this.degree = Math.max(Math.min(degree, 360), 0);
    }

    /*
     * Add angle to the actual angle
     */
    public void setAngle(Rotation2D rotation2D) {
        this.degree = Math.max(Math.min(rotation2D.degree, 360), 0);
    }

}
