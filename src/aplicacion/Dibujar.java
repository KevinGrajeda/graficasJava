package aplicacion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Dibujar {
    private BufferedImage image;
    private int dx = 0, dy = 0;
    private int dxAnimacion = 0, dyAnimacion = 0;
    private double sxAnimacion = 0, syAnimacion = 0;
    private double sx = 1, sy = 1;
    private double theta = 0;
    public Color color = Color.red;


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

    private CoordenadaDouble calcularEscalacion(CoordenadaDouble origen) {
        double[][] matrizOrigen = {{origen.x, origen.y, 1}};
        double[][] matrizTransformacion = {
                {
                        sx*(sxAnimacion+1),
                        0,
                        0
                },
                {
                        0,
                        sy*(sxAnimacion+1),
                        0
                },
                {
                        0,
                        0,
                        1
                }
        };

        double[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new CoordenadaDouble((int) resultado[0][0], (int) resultado[0][1]);
    }

    private CoordenadaDouble calcularTraslacion(CoordenadaDouble origen) {
        double[][] matrizOrigen = {{origen.x, origen.y, 1}};
        double[][] matrizTransformacion = {
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
                        dx + dxAnimacion,
                        dy + dyAnimacion,
                        1
                }
        };

        double[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new CoordenadaDouble(resultado[0][0], resultado[0][1]);
    }

    private CoordenadaInt calcularTraslacion(CoordenadaInt origen) {
        int[][] matrizOrigen = {{origen.x, origen.y, 1}};
        int[][] matrizTransformacion = {
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
                        dx + dxAnimacion,
                        dy + dyAnimacion,
                        1
                }
        };

        int[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new CoordenadaInt(resultado[0][0], resultado[0][1]);
    }

    private CoordenadaDouble calularRotacion(CoordenadaDouble origen) {
        double[][] matrizOrigen = {{origen.x, origen.y, 1}};
        double[][] matrizTransformacion = {
                {
                        (double) Math.cos(theta),
                        (double) Math.sin(theta),
                        0
                },
                {
                        (double) -Math.sin(theta),
                        (double) Math.cos(theta),
                        0
                },
                {
                        0,
                        0,
                        1
                }
        };

        double[][] resultado = multiplicarMatriz(matrizOrigen, matrizTransformacion);
        return new CoordenadaDouble(resultado[0][0], resultado[0][1]);
    }

    private CoordenadaInt calcularTransformaciones(CoordenadaInt puntoOrigen) {
        CoordenadaDouble punto = new CoordenadaDouble(puntoOrigen.x, puntoOrigen.y);
        CoordenadaDouble puntoDestino = calcularTraslacion(calcularEscalacion(calularRotacion(punto)));
        return new CoordenadaInt((int) puntoDestino.x, (int) puntoDestino.y);
    }


    private double[][] multiplicarMatriz(double[][] matrizOrigen, double[][] matrizTransformacion) {
        double[][] resultado = new double[1][3];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    resultado[i][j] += matrizOrigen[i][k] * matrizTransformacion[k][j];
                }
            }
        }
        return resultado;
    }

    private int[][] multiplicarMatriz(int[][] matrizOrigen, int[][] matrizTransformacion) {
        int[][] resultado = new int[1][3];
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

    public void traslacionAnimacion(int dxNuevo, int dyNuevo) {
        dxAnimacion = dxNuevo;
        dyAnimacion = dyNuevo;
    }

    public void escalacion(double sxNuevo, double syNuevo) {
        sx = sxNuevo;
        sy = syNuevo;
    }
    public void escalacionAnimacion(double sxNuevo, double syNuevo) {
        sxAnimacion = sxNuevo;
        syAnimacion = syNuevo;
    }

    public void rotacion(double anguloNuevo) {
        theta = anguloNuevo;
    }

    public void malla(int x1, int y1, int x2, int y2) {
//        CoordenadaInt pos1 = calcularTransformaciones(new CoordenadaInt(x1, y1));
//        CoordenadaInt pos2 = calcularTransformaciones(new CoordenadaInt(x2, y2));

//        x1 = pos1.x;
//        y1 = pos1.y;
//
//        x2 = pos2.x;
//        y2 = pos2.y;

//        double tamPaso = (double) (x2 - x1) / pasos;
//        for (double x = x1; x <= x2; x += 10) {
//            for (double y = y1; y <= y2; y += 10) {
//                circuloPuntoMedio((int) x, (int) y, 1);
//            }
//        }
        for (double x = x1; x <= x2; x += 4) {
            lineaBresenham((int) x, y1, (int) x, y2);
        }
        for (double y = y1; y <= y2; y += 10) {
            lineaBresenham(x1, (int) y, x2, (int) y);
        }
    }

    public void curva1(int x, int y, int ancho, int alto, int pasos) {
        CoordenadaInt puntoAnterior = new CoordenadaInt(x, y - (int) (Math.sin(0) * 200));
        for (double i = 0; i < Math.PI; i += Math.PI / pasos) {

            CoordenadaInt puntoNuevo = new CoordenadaInt(x + (int) (i * ancho), y - (int) (Math.sin(i) * alto));
            circuloPuntoMedio(puntoNuevo.x, puntoNuevo.y, 2);
            lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaHumo(int x, int y, int ancho, int alto,int paso) {
        CoordenadaInt puntoAnterior = new CoordenadaInt(x + (int) (0 * Math.cos(4 * 0) * ancho), y - (int) (0 * alto));
        for (double i = 0; i < 2 * Math.PI; i += Math.PI / 60) {

            CoordenadaInt puntoNuevo = new CoordenadaInt(x + (int) (i * Math.cos(4 * i+(paso/10.0)) * ancho), y - (int) (i * alto));

            putPixel(puntoNuevo.x, puntoNuevo.y);
            lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaFlor(int xPos, int yPos, int ancho, int alto) {
        Double xI = Math.cos(0) + (1.0 / 2) * Math.cos(7 * 0) + (1.0 / 3) * Math.sin(17 * 0);
        Double yI = Math.sin(0) + (1.0 / 2) * Math.sin(7 * 0) + (1.0 / 3) * Math.cos(17 * 0);
        CoordenadaInt puntoAnterior = new CoordenadaInt((int) (xPos + xI * ancho), (int) (yPos + yI * alto));
        for (double i = 0; i < 2 * Math.PI; i += Math.PI / 400) {

            Double x = Math.cos(i) + (1.0 / 2) * Math.cos(7 * i) + (1.0 / 3) * Math.sin(17 * i);
            Double y = Math.sin(i) + (1.0 / 3) * Math.sin(7 * i) + (1.0 / 3) * Math.cos(17 * i);
            CoordenadaInt puntoNuevo = new CoordenadaInt((int) (xPos + x * ancho), (int) (yPos + y * alto));

            // System.out.println(puntoNuevo.x + "," + puntoNuevo.y);
            // putPixel(puntoNuevo.x, puntoNuevo.y);
            lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaSol(int xPos, int yPos, int ancho, int alto) {
        CoordenadaInt puntoAnterior = null;
        for (double i = 0; i <= 14 * Math.PI; i += Math.PI / 100) {

            Double x = 17 * Math.cos(i) + (7) * Math.cos((17.0 / 7) * i);
            Double y = 17 * Math.sin(i) - (7) * Math.sin((17.0 / 7) * i);
            CoordenadaInt puntoNuevo = new CoordenadaInt((int) (xPos + x * ancho), (int) (yPos + y * alto));

            // putPixel(puntoNuevo.x, puntoNuevo.y);
            if (puntoAnterior != null)
                lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaParametrica1(int xPos, int yPos, int ancho, int alto) {
        CoordenadaInt puntoAnterior = null;
        for (double i = 0; i <= 14 * Math.PI; i += Math.PI / 100) {

            Double x = i - 3 * Math.sin(i);
            Double y = 4 - 3 * Math.cos(i);
            CoordenadaInt puntoNuevo = new CoordenadaInt((int) (xPos + x * ancho), (int) (yPos + y * alto));

            // putPixel(puntoNuevo.x, puntoNuevo.y);
            if (puntoAnterior != null)
                lineaBresenham(puntoAnterior.x, puntoAnterior.y, puntoNuevo.x, puntoNuevo.y);
            puntoAnterior = puntoNuevo;
        }
    }

    public void curvaInfinito(int xPos, int yPos, int ancho) {
        CoordenadaInt puntoAnterior = null;
        for (double i = 0; i <= 2 * Math.PI; i += Math.PI / 100) {

            Double x = ancho * Math.sin(i) / (1 + Math.pow(Math.cos(i), 2));
            Double y = ancho * Math.sin(i) * Math.cos(i) / (1 + Math.pow(Math.cos(i), 2));
            CoordenadaInt puntoNuevo = new CoordenadaInt((int) (xPos + x), (int) (yPos + y));
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
        double pendiente = (double) (y2 - y1) / (x2 - x1);
        double b = y1 - pendiente * x1;
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
        double xInc = (double) dx / pasos;
        double yInc = (double) dy / pasos;
        double x = x1, y = y1;
        putPixel((int) Math.round(x), (int) Math.round(y));
        for (int i = 0; i <= pasos; i++) {
            x += xInc;
            y += yInc;
            putPixel((int) Math.round(x), (int) Math.round(y));
        }
    }

    public void linea(int x1, int y1, int x2, int y2) {

        CoordenadaInt pos1 = calcularTransformaciones(new CoordenadaInt(x1, y1));
        CoordenadaInt pos2 = calcularTransformaciones(new CoordenadaInt(x2, y2));
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
            double yPos = (double) (yc + Math.sqrt(Math.pow(r, 2) - Math.pow(xPos - xc, 2)));
            putPixel(xPos, (int) Math.round(yPos));
        }
        for (int xPos = xc - r; xPos <= xc + r; xPos++) {
            double yPos = (double) (yc - Math.sqrt(Math.pow(r, 2) - Math.pow(xPos - xc, 2)));
            putPixel(xPos, (int) Math.round(yPos));
        }
    }

    public void circuloPolar(int xc, int yc, int r) {
        for (double angulo = 0; angulo <= Math.PI / 2; angulo += 0.01) {
            double xPos = (double) (xc + r * Math.sin(angulo));
            double yPos = (double) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }
        for (double angulo = (double) (Math.PI / 2); angulo <= Math.PI; angulo += 0.01) {
            double xPos = (double) (xc + r * Math.sin(angulo));
            double yPos = (double) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }
        for (double angulo = (double) Math.PI; angulo <= Math.PI * (3 / 2); angulo += 0.01) {
            double xPos = (double) (xc + r * Math.sin(angulo));
            double yPos = (double) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }
        for (double angulo = (double) (Math.PI * (3 / 2)); angulo <= 2 * Math.PI; angulo += 0.01) {
            double xPos = (double) (xc + r * Math.sin(angulo));
            double yPos = (double) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos);
        }

    }

    public void rectangulo(int x1, int y1, int x2, int y2) {


        linea(x1, y1, x2, y1);
        linea(x2, y1, x2, y2);
        linea(x2, y2, x1, y2);
        linea(x1, y2, x1, y1);
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
        CoordenadaDouble centro = calularRotacion(new CoordenadaDouble(xc, yc));
        xc = (int) centro.x;
        yc = (int) centro.y;

        int x1 = xc - rx;
        int y1 = yc - ry;
        int x2 = xc + rx;
        int y2 = yc + ry;
        CoordenadaDouble pos1 = calcularTraslacion(calcularEscalacion(new CoordenadaDouble(x1, y1)));
        CoordenadaDouble pos2 = calcularTraslacion(calcularEscalacion(new CoordenadaDouble(x2, y2)));
        x1 = (int) pos1.x;
        y1 = (int) pos1.y;

        x2 = (int) pos2.x;
        y2 = (int) pos2.y;

        xc = (x1 + x2) / 2;
        yc = (y1 + y2) / 2;
        rx = (x2 - x1) / 2;
        ry = (y2 - y1) / 2;


        for (double angulo = 0; angulo <= 2 * Math.PI; angulo += 0.005) {
            double xPos = (double) (xc + rx * Math.cos(theta) * Math.cos(angulo) - ry * Math.sin(theta) * Math.sin(angulo));
            double yPos = (double) (yc + rx * Math.sin(theta) * Math.cos(angulo) + ry * Math.cos(theta) * Math.sin(angulo));

            putPixel((int) xPos, (int) yPos);
        }
    }

    public void circuloPuntoMedio(int xc, int yc, double R) {
        CoordenadaInt pos = calcularTraslacion(new CoordenadaInt(xc, yc));
        xc = pos.x;
        yc = pos.y;
//        putPixel(0, (int) R);
        int xk = -1;
        int yk = (int) R;
        double pk = (double) (5 / 4) - R;
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
        CoordenadaInt pos1 = calcularTransformaciones(new CoordenadaInt(x, y));
        x = pos1.x;
        y = pos1.y;
        Color colorObjetivo = getPixel(x, y);

        if ((x < 0 || x >= image.getWidth()) || (y < 0 || y >= image.getHeight()))
            return;
        if (colorObjetivo.equals(colorRelleno)) {
            return;
        }

        Stack<CoordenadaInt> pila = new Stack<>();
        pila.push(new CoordenadaInt(x, y));

        while (!pila.isEmpty()) {
            CoordenadaInt p = pila.pop();
            int xPos = p.x;
            int yPos = p.y;
            putPixel(xPos, yPos, colorRelleno);

            if (xPos > 0 && getPixel(xPos - 1, yPos).equals(colorObjetivo)) {
                pila.push(new CoordenadaInt(xPos - 1, yPos));
            }
            if (xPos < image.getWidth() - 1 && getPixel(xPos + 1, yPos).equals(colorObjetivo)) {
                pila.push(new CoordenadaInt(xPos + 1, yPos));
            }
            if (yPos > 0 && getPixel(xPos, yPos - 1).equals(colorObjetivo)) {
                pila.push(new CoordenadaInt(xPos, yPos - 1));
            }
            if (yPos < image.getHeight() - 1 && getPixel(xPos, yPos + 1).equals(colorObjetivo)) {
                pila.push(new CoordenadaInt(xPos, yPos + 1));
            }
        }
    }


    public void scanLineFill(int x, int y) {
        Color colorRelleno = color;
        CoordenadaInt pos1 = calcularTransformaciones(new CoordenadaInt(x, y));
        x = pos1.x;
        y = pos1.y;
        Color colorObjetivo = getPixel(x, y);
        if (colorObjetivo == null) {
            return;
        }
        if (!colorObjetivo.equals(colorRelleno)) {
            Stack<CoordenadaInt> pila = new Stack<CoordenadaInt>();
            pila.push(new CoordenadaInt(x, y));

            while (!pila.empty()) {
                CoordenadaInt p = pila.pop();

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
                            pila.push(new CoordenadaInt(i, p.y - 1));
                        }
                    }

                    for (int i = xIzq + 1; i < xDer; i++) {
                        if (p.y < image.getHeight() - 1 && getPixel(i, p.y + 1).equals(colorObjetivo)) {
                            pila.push(new CoordenadaInt(i, p.y + 1));
                        }
                    }
                }
            }
        }
    }

    public void triangulo(int x1, int y1, int x2, int y2, int x3, int y3) {
        linea(x1, y1, x2, y2);
        linea(x2, y2, x3, y3);
        linea(x3, y3, x1, y1);
    }

    public void cuadrado(int x, int y, int k) {
        rectangulo(x, y, x + k, y + k);
    }

    public void poligono(int[] puntosX, int[] puntosY) {
        int longitud = puntosX.length;
        for (int i = 0; i < longitud - 1; i++) {
            linea(puntosX[i], puntosY[i], puntosX[i + 1], puntosY[i + 1]);
        }
        linea(puntosX[longitud - 1], puntosY[longitud - 1], puntosX[0], puntosY[0]);
    }

    public void rectanguloRelleno(int x1, int y1, int x2, int y2) {
        CoordenadaDouble pos1 = calcularTraslacion(calcularEscalacion(new CoordenadaDouble(x1, y1)));
        CoordenadaDouble pos2 = calcularTraslacion(calcularEscalacion(new CoordenadaDouble(x2, y2)));
        for (int y = (int) pos1.y; y <= pos2.y; y++) {
            for (int x = (int) pos1.x; x <= pos2.x; x++) {
                putPixel(x, y);
            }
        }
    }

    public void reiniciarTransformaciones() {
        sx = 1;
        sy = 1;
        dx = 0;
        dy = 0;
        theta = 0;
        dxAnimacion=0;
        dyAnimacion=0;

    }
    public void reiniciarRotacion() {
        theta = 0;
    }
}
