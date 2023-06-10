package aplicacion;

public class Coordenada3DDouble {
    double x,y,z;

    public Coordenada3DDouble(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    public double dotProduct(Coordenada3DDouble vector) {
        double dotProduct = this.x * vector.x + this.y * vector.y + this.z * vector.z;
        return dotProduct;
    }
}
