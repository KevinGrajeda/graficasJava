package aplicacion;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame implements Runnable {
    private final Thread hilo;
    private final Animador animador;

    public Ventana() {
        setTitle("titulo");
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
                System.out.println("error");
            }
        }
    }


    public void update(Graphics g) {
        Image buffer = createImage(getWidth(), getHeight());

        Dibujar d= new Dibujar(this);
        animador.setDibujar(d);

        // escena 1 (animacion 2D)
        if(animador.tiempoActual<=16000) {
            animador.nuevaAnimacion();
            animador.traslacion(300, 300, 0, 10000);
            animador.traslacion(-300, -200, 10000, 15000);
            d.elipse(0, 0, 40, 40);
            d.floodFill(0, 0);

            animador.nuevaAnimacion();
            animador.rotacion(30, 0, 10000);
            animador.escalacion(1, 1, 0, 10000);
            animador.escalacion(-1, -1, 10000, 15000);
            d.color=Color.green;
            d.traslacion(300, 300);
            d.linea(0, 0, 60, 60);

            d.drawToBuffer(buffer, this);
        }
        // escena 2 (animacion 3D)
        if(animador.tiempoActual>16000 && animador.tiempoActual<30000) {
            animador.nuevaAnimacion();
            d.traslacion(getWidth()/2,getHeight()/2);
            d.setVectorProyeccion(new Coordenada3D(40,40,-10000));
            animador.rotacionY3D(0,10,16000,30000);

            d.resetImage(this);
            d.color=Color.blue;
            d.plano(0,15,0,200,200);
            d.drawToBuffer(buffer, this);

            d.resetImage(this);
            d.color=Color.red;
            d.cubo(0,0,0,30,30,30);
            d.drawToBuffer(buffer, this);


            d.resetImage(this);
            d.color=Color.black;
            d.linea3D(100,15,100,0,-50,0);
            d.linea3D(-100,15,100,0,-50,0);
            d.linea3D(-100,15,-100,0,-50,0);
            d.linea3D(100,15,-100,0,-50,0);
            d.drawToBuffer(buffer, this);
        }

        g.drawImage(buffer, 0, 0, this);
    }


    public void paint(Graphics g) {
        update(g);
    }

}
