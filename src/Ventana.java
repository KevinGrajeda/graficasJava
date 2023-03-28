import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Ventana extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    public Ventana() {
        setTitle("ventana");
        setSize(500, 500);
        setLayout(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //circulo(100, 100, 50);
        //elipse(200, 200, 100, 170);
        //rectangulo(100, 200, 20, 10);
        circuloPuntoMedio(200,200,150);
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
                putPixel(y, x, Color.red);
            } else {
                putPixel(x, y, Color.red);
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
        putPixel(Math.round(x), Math.round(y), Color.green);
        for (int i = 0; i <= pasos; i++) {
            x += xInc;
            y += yInc;
            putPixel(Math.round(x), Math.round(y), Color.green);
        }
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
                putPixel(y, x, Color.red);
            } else {
                putPixel(x, y, Color.red);
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

        int p = dy - dx / 2;
        int x = x1;
        int y = y1;
        int xFinal = x2;

        int sumaY = y1 < y2 ? 1 : -1;
        while (x < xFinal) {
            x++;
            if (p < 0) {
                p += dy;
            } else {
                y+=sumaY;
                p += dy - dx;
            }
            if (avanzaY) {
                putPixel(y, x, Color.blue);
            } else {
                putPixel(x, y, Color.blue);
            }
        }
    }

    public void circulo(int xc, int yc, int r) {
        for (int xPos = xc - r; xPos <= xc + r; xPos++) {
            float yPos = (float) (yc + Math.sqrt(Math.pow(r, 2) - Math.pow(xPos - xc, 2)));
            putPixel(xPos, Math.round(yPos), Color.blue);
        }
        for (int xPos = xc - r; xPos <= xc + r; xPos++) {
            float yPos = (float) (yc - Math.sqrt(Math.pow(r, 2) - Math.pow(xPos - xc, 2)));
            putPixel(xPos, Math.round(yPos), Color.blue);
        }
    }

    public void circuloPolar(int xc, int yc, int r) {
        for (float angulo = 0; angulo <= Math.PI / 2; angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos, Color.red);
        }
        for (float angulo = (float) (Math.PI / 2); angulo <= Math.PI; angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos, Color.red);
        }
        for (float angulo = (float) Math.PI; angulo <= Math.PI * (3 / 2); angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos, Color.red);
        }
        for (float angulo = (float) (Math.PI * (3 / 2)); angulo <= 2 * Math.PI; angulo += 0.01) {
            float xPos = (float) (xc + r * Math.sin(angulo));
            float yPos = (float) (xc + r * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos, Color.red);
        }

    }

    public void rectangulo(int x1, int y1, int x2, int y2) {
        int ancho = Math.abs(x1 - x2);
        int altura = Math.abs(y1 - y2);
        int xInicio = Math.min(x1, x2);
        int yInicio = Math.min(y1, y2);
        lineaBresenham(xInicio, yInicio, xInicio + ancho, yInicio);
        lineaBresenham(xInicio, yInicio, xInicio, yInicio + altura);
        lineaBresenham(xInicio, yInicio + altura, xInicio + ancho, yInicio + altura);
        lineaBresenham(xInicio + ancho, yInicio, xInicio + ancho, yInicio + altura);
    }

    public void elipse(int xc, int yc, int rx, int ry) {
        for (float angulo = 0; angulo <= 2 * Math.PI; angulo += 0.005) {
            float xPos = (float) (xc + rx * Math.sin(angulo));
            float yPos = (float) (xc + ry * Math.cos(angulo));
            putPixel((int) xPos, (int) yPos, Color.red);
        }
    }

    public void circuloPuntoMedio(int xc, int yc, float R) {
        putPixel(0, (int) R, Color.blue);
        int xk = 0;
        int yk = (int) R;
        float pk = (float)(5/4) -R;
        while (xk <= yk) {
            xk += 1;
            if (pk < 0) {
                putPixel(xk+xc, yk+yc, Color.blue);
                pk = pk + 2*xk + 3;
            } else {
                yk -= 1;
                putPixel(xk+xc, yk+yc, Color.blue);
                pk = pk + 2*xk - 2*yk + 5;
            }
            // simetria
            putPixel(xk+xc, -yk+yc, Color.blue);
            putPixel(-xk+xc, yk+yc, Color.blue);
            putPixel(-xk+xc, -yk+yc, Color.blue);
            putPixel(yk+xc, xk+yc, Color.blue);
            putPixel(-yk+xc, xk+yc, Color.blue);
            putPixel(yk+xc, -xk+yc, Color.blue);
            putPixel(-yk+xc, -xk+yc, Color.blue);
        }
    }
}
