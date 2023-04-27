package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Ventana extends JFrame implements Runnable {

    private BufferedImage image;
    private BufferedImage fondo;
    // private Image buffer;
    private Thread hilo;
    private int frameActual=0;
    private Animador animador;

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

        animador=new Animador();
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

        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Dibujar d = new Dibujar(image);

        animador.setDibujar(d);


        for(int i=0;i<400;i++){
            long t=i*10;
            animador.nuevaAnimacion();
            animador.traslacion(20,250,0,0);
            animador.traslacion(400,0,1000+t,1500+t);
            animador.traslacion(-200,200,2500+t,3500+t);
            animador.traslacion(-200,-200,4000+t,6500+t);

            for(int j=0;j<100;j++){
                long t2=j*350;
                animador.escalacion(1,0.5,t2+t,t2+150+t);
                animador.escalacion(-1,-0.5,t2+150+t,t2+300+t);
            }

//            animador.rotacion(10,0+t,6000+t);

            d.elipse(0,0,20,30);
//            d.floodFill(1,-99,Color.blue);
        }

        buffer.getGraphics().drawImage(image, 0, 0, null);
        cont++;
        frameActual++;
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }



    public void paint(Graphics g) {

        update(g);

    }

}
