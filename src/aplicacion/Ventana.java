package aplicacion;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Ventana extends JFrame implements Runnable {

    // private Image buffer;
    private Thread hilo;
    private Animador animador;

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


        // CUBOS PUNTOS FUGA
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        

        // // un punto de fuga
        // d.traslacion(125,297);
        // d.setVectorProyeccion(new Coordenada3D(75,-70,150));
        // d.setVectorRotacion(0,0,0);
        // d.color=new Color(200,0,200);
        // d.linea3D(0,0,0,70,0,0);
        // d.linea3D(0,0,0,0,-70,0);
        // d.linea3D(0,0,0,0,0,70);

        // d.color=new Color(200,0,0);;
        // d.cuboUnPuntoFuga(25,-25,25,50);


        // // dos puntos de fuga
        // d.traslacion(250,148);
        // d.setVectorProyeccion(new Coordenada3D(0,-60,131));
        // d.setVectorRotacion(0,0.57,0);
        // d.color=new Color(200,0,200);
        // d.linea3D(0,0,0,70,0,0);
        // d.linea3D(0,0,0,0,-70,0);
        // d.linea3D(0,0,0,0,0,70);

        // d.color=new Color(200,0,0);;
        // d.cuboUnPuntoFuga(25,-25,25,50);



        // // tres puntos de fuga
        // d.traslacion(375,297);
        // d.setVectorProyeccion(new Coordenada3D(0,0,111));
        // d.setVectorRotacion(.69,.78,0);
        // d.color=new Color(200,0,200);
        // d.linea3D(0,0,0,70,0,0);
        // d.linea3D(0,0,0,0,-70,0);
        // d.linea3D(0,0,0,0,0,70);

        // d.color=new Color(200,0,0);;
        // d.cuboUnPuntoFuga(25,-25,25,50);


//
//        // un punto de fuga
//        d.traslacion(getWidth()/2,getHeight()/2);
//        d.setVectorProyeccion(new Coordenada3D(0,0,150));
//        d.setVectorEscalacion(20,20,20);
//        d.setVectorRotacion((double) cont/100,(double) cont/85,(double) cont/74);
//        d.curvaReloj(-2,-2,-2,1);

        // oclusion
        d.traslacion(250,225);
        d.setVectorProyeccion(new Coordenada3D(0,0,10000));
        d.setVectorRotacion(0.2,(double)cont/30,0);

        d.cuboUnPuntoFill(0,0,0,100);


        cont++;
        buffer.getGraphics().drawImage(image,0,0,this);
        g.drawImage(buffer, 0, 0, this); // doble buffer
    }


    public void paint(Graphics g) {

        update(g);

    }

}
