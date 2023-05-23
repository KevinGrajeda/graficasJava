package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Ventana extends JFrame implements Runnable {

    private BufferedImage fondo;
    private BufferedImage edificios;
    private BufferedImage arboles;
    private BufferedImage cantina;
    private BufferedImage cementerio;

    // private Image buffer;
    private Thread hilo;
    private Animador animador;
    private boolean ojos=false;

    public Ventana() {
        setTitle("Si manejas no tomes");
        setSize(500, 450);
        setLayout(null);

        // buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        // graPixel = (Graphics2D) buffer.createGraphics();

//        Graphics g = fondo.getGraphics();
//        g.setColor(new Color(250, 250, 250));
//        g.fillRect(0, 0, getWidth(), getHeight());

        animador = new Animador();


        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        animador.setInicioAplicacion(System.currentTimeMillis());

        while (true) {
            try {
                repaint();
                hilo.sleep(20);
            } catch (InterruptedException ex) {

            }
        }
    }


    int cont = 0;

    public void update(Graphics g) {
        Image buffer = createImage(getWidth(), getHeight());

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d = new Dibujar(image);
        animador.setDibujar(d);


        animador.nuevaAnimacion();
        d.traslacion(250,100);
        d.tetris(0,0,0,100,(double)cont/100);



        cont++;
        buffer.getGraphics().drawImage(image,0,0,this);
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }


    public void paint(Graphics g) {

        update(g);

    }

}
