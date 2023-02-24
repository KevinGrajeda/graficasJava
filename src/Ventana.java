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
        lineaEcuacion(250, 250, 500, 0);
        lineaEcuacion(250, 250, 500, 125);
        lineaEcuacion(250, 250, 500, 250);
        lineaEcuacion(250, 250, 500, 375);
        lineaEcuacion(250, 250, 500, 500);
        lineaEcuacion(250, 250, 375, 500);
        lineaDDA(250, 250, 250, 500);
        lineaDDA(250, 250, 125, 500);
        lineaDDA(250, 250, 0, 500);
        lineaDDA(250, 250, 0, 375);
        lineaDDA(250, 250, 0, 250);
        lineaBresenham(250, 250, 0, 125);
        lineaBresenham(250, 250, 0, 0);
        lineaBresenham(250, 250, 375, 0);
        lineaBresenham(250, 250, 250, 0);
        lineaBresenham(250, 250, 125, 0);
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
                putPixel(y, x, Color.green);
            } else {
                putPixel(x, y, Color.green);
            }
        }

    }
}
