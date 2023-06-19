package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ventana extends JFrame implements Runnable {

    // private Image buffer;
    private Thread hilo;
    private Animador animador;

    public Ventana() {
        setTitle("el ganador");
        setSize(500, 450);
        setLayout(null);

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


    public void update(Graphics g) {
        // doble buffer
        Image buffer = createImage(getWidth(), getHeight());

        Dibujar d= new Dibujar(this);

        d.putPixel(100,100);
        d.putPixel(101,100);
        d.putPixel(102,100);
        d.putPixel(103,100);
        d.putPixel(104,100);
        d.putPixel(105,100);

        d.drawToBuffer(buffer,this);

        g.drawImage(buffer, 0, 0, this); // doble buffer
    }


    public void paint(Graphics g) {

        update(g);

    }

}
