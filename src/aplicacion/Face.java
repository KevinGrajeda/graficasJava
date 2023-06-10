package aplicacion;

public class Face {
    public Coordenada3DDouble[] vertices;
    private Coordenada3DDouble normal;

    public Face(Coordenada3DDouble[] vertices) {
        this.vertices = vertices;
        this.normal = calculateFaceNormal(this.vertices);
    }

    public Coordenada3DDouble getNormal() {
        return normal;
    }

    private Coordenada3DDouble calculateFaceNormal(Coordenada3DDouble[] faceVertices) {

        Coordenada3DDouble vector1 = new Coordenada3DDouble(
                faceVertices[1].getX() - faceVertices[0].getX(),
                faceVertices[1].getY() - faceVertices[0].getY(),
                faceVertices[1].getZ() - faceVertices[0].getZ()
        );
        Coordenada3DDouble vector2 = new Coordenada3DDouble(
                faceVertices[2].getX() - faceVertices[0].getX(),
                faceVertices[2].getY() - faceVertices[0].getY(),
                faceVertices[2].getZ() - faceVertices[0].getZ()
        );


        double nx = vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY();
        double ny = vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ();
        double nz = vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();


        double length = Math.sqrt(nx * nx + ny * ny + nz * nz);
        nx /= length;
        ny /= length;
        nz /= length;


        return new Coordenada3DDouble(nx, ny, nz);
    }
    public Coordenada3DDouble calculateCenterPoint() {
        double sumX = 0;
        double sumY = 0;
        double sumZ = 0;

        for (Coordenada3DDouble vertex : vertices) {
            sumX += vertex.getX();
            sumY += vertex.getY();
            sumZ += vertex.getZ();
        }

        int vertexCount = vertices.length;
        double centerX = sumX / vertexCount;
        double centerY = sumY / vertexCount;
        double centerZ = sumZ / vertexCount;

        return new Coordenada3DDouble(centerX, centerY, centerZ);
    }
}
