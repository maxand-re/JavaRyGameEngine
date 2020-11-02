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

    public void setDegree(double degree) { this.degree = degree; }
    public void addAngle(double degree) { this.degree += degree; }

    public double getRadians() {
        return Math.toRadians(degree);
    }

    public void setRotation(Rotation2D rotation2D) {
        this.degree = rotation2D.degree;
        this.offset = rotation2D.offset;
    }

    @Override
    public String toString() {
        return "Rotation2D{" +
                "degree=" + degree +
                ", offset=" + offset +
                '}';
    }
}
