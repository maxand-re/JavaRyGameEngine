package fr.ryfax.rge.engine.utils.movements;

public class Rotation2D {

    public double degree;
    public Vector2D offset;

    /*
     * Rotation class for 2D
     */
    public Rotation2D(double degree, double x_offset, double y_offset) {
        this.setDegree(degree);
        this.offset = new Vector2D(x_offset, y_offset);
    }

    public Rotation2D setDegree(double degree) { this.degree = degree; return this; }
    public Rotation2D addAngle(double degree) { this.degree += degree; return this; }

    public double getRadians() {
        return Math.toRadians(degree);
    }

    public Rotation2D setRotation(Rotation2D rotation2D) {
        this.degree = rotation2D.degree;
        this.offset = rotation2D.offset;
        return this;
    }

    @Override
    public String toString() {
        return "Rotation2D{" +
                "degree=" + degree +
                ", offset=" + offset +
                '}';
    }
}
