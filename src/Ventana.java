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
        lineaEcuacion(0,0,500,500);
    }

    public void lineaEcuacion(int x1, int y1, int x2, int y2){
        float pendiente = (float)(y2 - y1) / (x2 - x1);
        float b=y1-pendiente*x1;
        for (int i = x1; i <= x2; i++) {
            int y = (int)(pendiente * i + b);
            putPixel(i, y, Color.red);
        }
    }

}
