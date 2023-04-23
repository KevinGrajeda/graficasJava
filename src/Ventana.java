import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.Calendar;
import java.util.Stack;

public class Ventana extends JFrame implements Runnable {

    private BufferedImage image;
    private BufferedImage fondo;
    //private Image buffer;
    private Thread hilo;

    public Ventana() {
        setTitle("ventana");
        setSize(500, 500);
        setLayout(null);

        // buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        // graPixel = (Graphics2D) buffer.createGraphics();


        fondo = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = fondo.getGraphics();
        g.setColor(new Color(250, 250, 250));
        g.fillRect(0, 0, 500, 500);

        hilo = new Thread(this);
        hilo.start();
    }
    @Override
    public void run() {
        while (true) {
            try {
                repaint();
                hilo.sleep(40);
            } catch (InterruptedException ex) {

            }
        }
    }
    int cont=0;
    public void update(Graphics g) {
        //g.drawImage(fondo,0,0,null);
        Image buffer=createImage(getWidth(), getHeight());

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d=new Dibujar(image);
        d.traslacion(cont, 10);
        d.elipse(40, 250, 10, 30);
//        d.floodFill(40,250,Color.red);
        buffer.getGraphics().drawImage(image,0,0,null);
        cont++;
        g.drawImage(buffer, 0, 0, this); //doble buffer
    }
    public void paint(Graphics g) {
        // tarea figuras
        // lineaPuntoMedio(20,120,80,180);
        // lineaBresenham(100,150,160,150);
        // lineaBresenham(180,180,240,120);
        // lineaBresenham(340,150,260,150);

        // for(int i=0;i<4;i++){
        // circuloPuntoMedio(60,275,i*10+2);
        // putPixel(60+(3+i*8),275+(3+i*8),Color.black);
        // putPixel(60-(3+i*8),275-(3+i*8),Color.black);
        // }
        // rectangulo(115,250,215,300);
        // rectangulo(200,265,130,285);
        // for(int i=0;i<4;i++){
        // elipse(290,275,i*10+25,i*10+2);

        // putPixel(290+(27+i*9),275+(3+i*6),Color.black);
        // putPixel(290-(27+i*9),275-(3+i*6),Color.black);
        // }

        // curva1(50, 300, 60, 250, 8);
        // curva1(250, 300, 60, 250, 100);
        // curvaSol(100, 350, 4, 4);
        // curvaFlor(350, 350, 60, 60);

        // curvaParametrica1(250, 250, 4, 4);
        // curvaInfinito(250, 250, 200);
        // rectangulo(100, 100, 300, 300);
        // malla(100, 100, 300, 300);
        update(g);

    }

}
