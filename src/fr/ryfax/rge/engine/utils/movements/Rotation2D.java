package fr.ryfax.rge.engine.utils.movements;

public class Rotation2D {

    public double degrees, x ,y;

    /*
     * Rotation class for 2D
     */
    public Rotation2D(double degrees, double x, double y) {
        this.degrees = degrees;
        this.x = x;
        this.y = y;
    }

    /*
     * Add degrees to the actual angle
     */
    public void addAngle(double degrees) {
        this.degrees += degrees;
    }

    /*
     * Add angle to the actual angle
     */
    public void addAngle(Rotation2D rotation2D) {
        this.degrees += rotation2D.degrees;
    }

}
