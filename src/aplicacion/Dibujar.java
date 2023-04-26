package aplicacion;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Dibujar {
    private BufferedImage image;
    private int dx = 0, dy = 0;
    private float sx = 1, sy = 1;
    private float theta = 0;
    public Color color = Color.red;

    public int frameActual=0;

    public Dibujar(BufferedImage image) {
        this.image = image;
    }

    public void putPixel(int x, int y, Color color) {
        if ((x < 0 || x >= image.getWidth()) || (y < 0 || y >= image.getHeight()))
            return;
        image.setRGB(x, y, color.getRGB());
    }

    public void putPixel(int x, int y) {
        if ((x < 0 || x >= image.getWidth()) || (y < 0 || y >= image.getHeight()))
            return;
        image.setRGB(x, y, color.getRGB());
    }

    public Color getPixel(int x, int y) {
        if ((x < 0 || x >= image.getWidth()) || (y < 0 || y >= image.getHeight()))
            return null;
        return new Color(image.getRGB(x, y));
    }

    private Point2D.Float calcularEscalacion(Point2D.Float origen) {
        float[][] matrizOrigen = {{origen.x, origen.y, 1}};
        float[][] matrizTransformacion = {
                {
                        sx,
                        0,
                        0
                },
                {
                        0,
                        sy,
                        0
                },
                {
                        0,
                        0,
                        1
                }
        };

        float[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new Point2D.Float((int) resultado[0][0], (int) resultado[0][1]);
    }

    private Point2D.Float calcularTraslacion(Point2D.Float origen) {
        float[][] matrizOrigen = {{origen.x, origen.y, 1}};
        float[][] matrizTransformacion = {
                {
                        1,
                        0,
                        0
                },
                {
                        0,
                        1,
                        0
                },
                {
                        dx,
                        dy,
                        1
                }
        };

        float[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new Point2D.Float(resultado[0][0], resultado[0][1]);
    }

    private Point2D.Float calularRotacion(Point2D.Float origen) {
        float[][] matrizOrigen = {{origen.x, origen.y, 1}};
        float[][] matrizTransformacion = {
                {
                        (float) Math.cos(theta),
                        (float) Math.sin(theta),
                        0
                },
                {
                        (float) -Math.sin(theta),
                        (float) Math.cos(theta),
                        0
                },
                {
                        0,
                        0,
                        1
                }
        };

        float[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new Point2D.Float(resultado[0][0], resultado[0][1]);
    }

    private Point calcularTransformaciones(Point puntoOrigen) {
        Point2D.Float punto = new Point2D.Float(puntoOrigen.x, puntoOrigen.y);
        Point2D.Float puntoDestino = calcularTraslacion(calcularEscalacion(calularRotacion(punto)));
        return new Point((int) puntoDestino.x, (int) puntoDestino.y);
    }


    private float[][] multiplicarMatriz(float[][] matrizOrigen, float[][] matrizTransformacion) {
        float[][] resultado = new float[1][3];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    resultado[i][j] += matrizOrigen[i][k] * matrizTransformacion[k][j];
                }
            }
        }
        return resultado;
    }

    public void traslacion(int dxNuevo, int dyNuevo) {
        dx = dxNuevo;
        dy = dyNuevo;
    }

    public void escalacion(float sxNuevo, float syNuevo) {
        sx = sxNuevo;
        sy = syNuevo;
    }

    public void rotacion(float anguloNuevo) {
        theta = anguloNuevo;
    }

    public void malla(int x1, int y1, int x2, int y2, int pasos) {
        Point pos1 = calcularTransformaciones(new Point(x1, y1));
        Point pos2 = calcularTransformaciones(new Point(x2, y2));

        x1 = pos1.x;
        y1 = pos1.y;

        x2 = pos2.x;
        y2 = pos2.y;

        float tamPaso = (float) (x2 - x1) / pasos;
        for (float x = x1; x <= x2; x += 10) {
            for (float y = y1; y <= y2; y += 10) {
                circuloPuntoMedio((int) x, (int) y, 1);
            }
        }
        for (float x = x1; x <= x2; x += 10) {
            lineaBresenham((int) x, y1, (int) x, y2);
        }
        for (float y = y1; y <= y2; y += 10) {
            lineaBresenham(x1, (int) y, x2, (int) y);
        }
    }

    public void curva1(int x, int y, int ancho, int alto, int pasos) {
        Point puntoAnterior = new Point(x, y - (int) (Math.sin(0) * 200));
        for (float i = 0; i < Math.PI; i += Math.PI / pasos) {

            Point puntoNuevo = new Point(x + (int) (i * ancho), y - (int) (Math.sin(i) * alto));
            circuloPuntoMedio(puntoNuevo.x, puntoNuevo.y, 2);
            lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaHumo(int x, int y, int ancho, int alto) {
        Point puntoAnterior = new Point(x + (int) (0 * Math.cos(4 * 0) * ancho), y - (int) (0 * alto));
        for (float i = 0; i < 2 * Math.PI; i += Math.PI / 60) {

            Point puntoNuevo = new Point(x + (int) (i * Math.cos(4 * i) * ancho), y - (int) (i * alto));

            putPixel(puntoNuevo.x, puntoNuevo.y);
            lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaFlor(int xPos, int yPos, int ancho, int alto) {
        Double xI = Math.cos(0) + (1.0 / 2) * Math.cos(7 * 0) + (1.0 / 3) * Math.sin(17 * 0);
        Double yI = Math.sin(0) + (1.0 / 2) * Math.sin(7 * 0) + (1.0 / 3) * Math.cos(17 * 0);
        Point puntoAnterior = new Point((int) (xPos + xI * ancho), (int) (yPos + yI * alto));
        for (float i = 0; i < 2 * Math.PI; i += Math.PI / 400) {

            Double x = Math.cos(i) + (1.0 / 2) * Math.cos(7 * i) + (1.0 / 3) * Math.sin(17 * i);
            Double y = Math.sin(i) + (1.0 / 3) * Math.sin(7 * i) + (1.0 / 3) * Math.cos(17 * i);
            Point puntoNuevo = new Point((int) (xPos + x * ancho), (int) (yPos + y * alto));

            // System.out.println(puntoNuevo.x + "," + puntoNuevo.y);
            // putPixel(puntoNuevo.x, puntoNuevo.y);
            lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaSol(int xPos, int yPos, int ancho, int alto) {
        Point puntoAnterior = null;
        for (float i = 0; i <= 14 * Math.PI; i += Math.PI / 100) {

            Double x = 17 * Math.cos(i) + (7) * Math.cos((17.0 / 7) * i);
            Double y = 17 * Math.sin(i) - (7) * Math.sin((17.0 / 7) * i);
            Point puntoNuevo = new Point((int) (xPos + x * ancho), (int) (yPos + y * alto));

            // putPixel(puntoNuevo.x, puntoNuevo.y);
            if (puntoAnterior != null)
                lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaParametrica1(int xPos, int yPos, int ancho, int alto) {
        Point puntoAnterior = null;
        for (float i = 0; i <= 14 * Math.PI; i += Math.PI / 100) {

            Double x = i - 3 * Math.sin(i);
            Double y = 4 - 3 * Math.cos(i);
            Point puntoNuevo = new Point((int) (xPos + x * ancho), (int) (yPos + y * alto));

            // putPixel(puntoNuevo.x, puntoNuevo.y);
            if (puntoAnterior != null)
                lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaInfinito(int xPos, int yPos, int ancho) {
        Point puntoAnterior = null;
        for (float i = 0; i <= 2 * Math.PI; i += Math.PI / 100) {

            Double x = ancho * Math.sin(i) / (1 + Math.pow(Math.cos(i), 2));
            Double y = ancho * Math.sin(i) * Math.cos(i) / (1 + Math.pow(Math.cos(i), 2));
            Point puntoNuevo = new Point((int) (xPos + x), (int) (yPos + y));
            // putPixel(puntoNuevo.x, puntoNuevo.y);
            if (puntoAnterior != null)
                lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void lineaEcuacion(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        boolean avanzaY = dy > dx;
        if (avanzaY) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
        }
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        float pendiente = (float) (y2 - y1) / (x2 - x1);
        float b = y1 - pendiente * x1;
        for (int x = x1; x <= x2; x++) {
            int y = (int) (pendiente * x + b);
            if (avanzaY) {
                putPixel(y, x);
            } else {
                putPixel(x, y);
            }
        }
    }

    public void lineaDDA(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int pasos = Math.max(Math.abs(dx), Math.abs(dy));
        float xInc = (float) dx / pasos;
        float yInc = (float) dy / pasos;
        float x = x1, y = y1;
        putPixel(Math.round(x), Math.round(y));
        for (int i = 0; i <= pasos; i++) {
            x += xInc;
            y += yInc;
            putPixel(Math.round(x), Math.round(y));
        }
    }

    public void linea(int x1, int y1, int x2, int y2) {

        Point pos1 = calcularTransformaciones(new Point(x1, y1));
        Point pos2 = calcularTransformaciones(new Point(x2, y2));
        x1 = pos1.x;
        y1 = pos1.y;

        x2 = pos2.x;
        y2 = pos2.y;
        lineaPuntoMedio(x1, y1, x2, y2);
    }


    public void lineaBresenham(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        boolean avanzaY = dy > dx;
        if (avanzaY) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
        }
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        dx = x2 - x1;
        dy = Math.abs(y2 - y1);
        int A = 2 * dy, B = 2 * dy - 2 * dx;
        int p = 2 * dy - dx;
        int y = y1;
        int sumaY = y1 < y2 ? 1 : -1;
        for (int x = x1; x <= x2; x++) {
            if (p < 0) {
                p += A;
            } else {
                y += sumaY;
                p += B;
            }
            if (avanzaY) {
                putPixel(y, x);
            } else {
                putPixel(x, y);
            }
        }

    }

    public void lineaPuntoMedio(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        boolean avanzaY = dy > dx;

        if (avanzaY) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
        }

        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        dx = x2 - x1;
        dy = Math.abs(y2 - y1);


        int dosDy = 2 * dy;
        int dosDymenosDx = 2 * (dy - dx);
        int p = 2 * dy - dx;

        int y = y1;
        for (int x = x1; x <= x2; x++) {
            if (avanzaY) {
                putPixel(y, x);
            } else {
                putPixel(x, y);
            }
            if (p > 0) {
                p += dosDymenosDx;
                y += (y2 > y1 ? 1 : -1);
            } else {
                p += dosDy;
            }
        }
    }

    public void circulo1(int xc, int yc, int r) {
        for (int xPos = xc - r; xPos <= xc + r; xPos++) {
            float yPos = (float) (yc + Math.sqrt(Math.pow(r, 2) - Math.pow(xPos - xc, 2)));
            putPixel(xPos, Math.round(yPos));
        }
        for (int xPos = xc - r; xPos <= xc + r; xPos++) {
            float yPos = (float) (yc - Math.sqrt(Math.pow(r, 2) - Math.pow(xPos - xc, 2)));
            putPixel(xPos, Math.round(yPos));
        }
    }

    public void circuloPolar(int xc, int yc, int r) {
        for (float angulo = 0; angulo <= Math.PI / 2; angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }
        for (float angulo = (float) (Math.PI / 2); angulo <= Math.PI; angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }
        for (float angulo = (float) Math.PI; angulo <= Math.PI * (3 / 2); angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }
        for (float angulo = (float) (Math.PI * (3 / 2)); angulo <= 2 * Math.PI; angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }

    }

    public void rectangulo(int x1, int y1, int x2, int y2) {


        linea(x1 , y1, x2 , y1);
        linea(x2, y1 , x2, y2 );
        linea(x2 , y2, x1 , y2);
        linea(x1, y2 , x1, y1 );
    }

    // int ancho = Math.abs(x1 - x2);
    // int altura = Math.abs(y1 - y2);
    // int xInicio = Math.min(x1, x2);
    // int yInicio = Math.min(y1, y2);
    // lineaBresenham(xInicio, yInicio, xInicio + ancho, yInicio);
    // lineaBresenham(xInicio, yInicio, xInicio, yInicio + altura);
    // lineaBresenham(xInicio, yInicio + altura, xInicio + ancho, yInicio + altura);
    // lineaBresenham(xInicio + ancho, yInicio, xInicio + ancho, yInicio + altura);

    public void elipse(int xc, int yc, int rx, int ry) {

        Point2D.Float centro = calularRotacion(new Point2D.Float(xc, yc));
        xc = (int) centro.x;
        yc = (int) centro.y;

        int x1 = xc - rx;
        int y1 = yc - ry;
        int x2 = xc + rx;
        int y2 = yc + ry;
        Point2D.Float pos1 = calcularTraslacion(calcularEscalacion(new Point2D.Float(x1, y1)));
        Point2D.Float pos2 = calcularTraslacion(calcularEscalacion(new Point2D.Float(x2, y2)));
        x1 = (int) pos1.x;
        y1 = (int) pos1.y;

        x2 = (int) pos2.x;
        y2 = (int) pos2.y;

        xc = (x1 + x2) / 2;
        yc = (y1 + y2) / 2;
        rx = (x2 - x1) / 2;
        ry = (y2 - y1) / 2;


        for (float angulo = 0; angulo <= 2 * Math.PI; angulo += 0.005) {
            float xPos = (float) (xc + rx * Math.cos(theta) * Math.cos(angulo) - ry * Math.sin(theta) * Math.sin(angulo));
            float yPos = (float) (yc + rx * Math.sin(theta) * Math.cos(angulo) + ry * Math.cos(theta) * Math.sin(angulo));

            putPixel((int) xPos, (int) yPos);
        }
    }

    public void circuloPuntoMedio(int xc, int yc, float R) {
        putPixel(0, (int) R);
        int xk = -1;
        int yk = (int) R;
        float pk = (float) (5 / 4) - R;
        while (xk <= yk) {
            xk += 1;
            if (pk < 0) {
                putPixel(xk + xc, yk + yc);
                pk = pk + 2 * xk + 3;
            } else {
                yk -= 1;
                putPixel(xk + xc, yk + yc);
                pk = pk + 2 * xk - 2 * yk + 5;
            }
            // simetria
            putPixel(xk + xc, -yk + yc);
            putPixel(-xk + xc, yk + yc);
            putPixel(-xk + xc, -yk + yc);
            putPixel(yk + xc, xk + yc);
            putPixel(-yk + xc, xk + yc);
            putPixel(yk + xc, -xk + yc);
            putPixel(-yk + xc, -xk + yc);
        }
    }

    public void floodFill(int x, int y, Color colorRelleno) {
        Point pos1 = calcularTransformaciones(new Point(x, y));
        x = pos1.x;
        y = pos1.y;
        Color colorObjetivo = getPixel(x, y);

        if ((x < 0 || x >= image.getWidth()) || (y < 0 || y >= image.getHeight()))
            return;
        if (colorObjetivo.equals(colorRelleno)) {
            return;
        }

        Stack<Point> pila = new Stack<>();
        pila.push(new Point(x, y));

        while (!pila.isEmpty()) {
            Point p = pila.pop();
            int xPos = p.x;
            int yPos = p.y;
            putPixel(xPos, yPos, colorRelleno);

            if (xPos > 0 && getPixel(xPos - 1, yPos).equals(colorObjetivo)) {
                pila.push(new Point(xPos - 1, yPos));
            }
            if (xPos < image.getWidth() - 1 && getPixel(xPos + 1, yPos).equals(colorObjetivo)) {
                pila.push(new Point(xPos + 1, yPos));
            }
            if (yPos > 0 && getPixel(xPos, yPos - 1).equals(colorObjetivo)) {
                pila.push(new Point(xPos, yPos - 1));
            }
            if (yPos < image.getHeight() - 1 && getPixel(xPos, yPos + 1).equals(colorObjetivo)) {
                pila.push(new Point(xPos, yPos + 1));
            }
        }
    }


    public void scanLineFill(int x, int y, Color colorRelleno) {
        Point pos1 = calcularTransformaciones(new Point(x, y));
        x = pos1.x;
        y = pos1.y;
        Color colorObjetivo = getPixel(x, y);

        if (!colorObjetivo.equals(colorRelleno)) {
            Stack<Point> pila = new Stack<Point>();
            pila.push(new Point(x, y));

            while (!pila.empty()) {
                Point p = pila.pop();

                if (getPixel(p.x, p.y).equals(colorObjetivo)) {
                    int xIzq = p.x;
                    int xDer = p.x;

                    while (xIzq >= 0 && getPixel(xIzq, p.y).equals(colorObjetivo)) {
                        xIzq--;
                    }

                    while (xDer < image.getWidth() && getPixel(xDer, p.y).equals(colorObjetivo)) {
                        xDer++;
                    }

                    for (int i = xIzq + 1; i < xDer; i++) {
                        putPixel(i, p.y, colorRelleno);
                    }

                    for (int i = xIzq + 1; i < xDer; i++) {
                        if (p.y > 0 && getPixel(i, p.y - 1).equals(colorObjetivo)) {
                            pila.push(new Point(i, p.y - 1));
                        }
                    }

                    for (int i = xIzq + 1; i < xDer; i++) {
                        if (p.y < image.getHeight() - 1 && getPixel(i, p.y + 1).equals(colorObjetivo)) {
                            pila.push(new Point(i, p.y + 1));
                        }
                    }
                }
            }
        }
    }

    public void triangulo(int x1, int y1, int x2, int y2, int x3, int y3) {
        linea(x1,y1,x2,y2);
        linea(x2,y2,x3,y3);
        linea(x3,y3,x1,y1);
    }

    public void cuadrado(int x,int y, int k) {
        rectangulo(x,y,x+k,y+k);
    }

    public void animarTraslacion(int dx,int dy,int frameInicio,int fremeFinal) {

    }
}
